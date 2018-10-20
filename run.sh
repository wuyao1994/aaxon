#!/usr/bin/env bash

set -e

# Build the project and docker images
mvn clean install -Dmaven.test.skip=true

# Export the active docker machine IP
export DOCKER_IP=$(docker-machine ip $(docker-machine active))
# docker-machine doesn't exist in Linux, assign default ip if it's not set
DOCKER_IP=${DOCKER_IP:-0.0.0.0}

docker-compose down

# Start the config service first and wait for it to become available
docker-compose up -d zookeeper --no-recreate
docker-compose up -d kafka --no-recreate
docker-compose up -d config-service

while [ -z ${CONFIG_SERVICE_READY} ]; do
  echo "Waiting for config service..."
  if [ "$(curl --silent $DOCKER_IP:8888/actuator/health 2>&1 | grep -q '\"status\":\"UP\"'; echo $?)" = 0 ]; then
      CONFIG_SERVICE_READY=true;
  fi
  sleep 2
done

# Start the discovery service next and wait
docker-compose up -d discovery-service

while [ -z ${DISCOVERY_SERVICE_READY} ]; do
  echo "Waiting for discovery service..."
  if [ "$(curl --silent $DOCKER_IP:8761/actuator/health 2>&1 | grep -q '\"status\":\"UP\"'; echo $?)" = 0 ]; then
      DISCOVERY_SERVICE_READY=true;
  fi
  sleep 2
done

# Start the other containers
docker-compose up redis -d --no-recreate
docker-compose up mysql -d --no-recreate
docker-compose up hystrix-dashboard -d
docker-compose up inventory-service -d
docker-compose up price-service -d
docker-compose up web-storefront -d

# Attach to the log output of the cluster
docker-compose logs -f
