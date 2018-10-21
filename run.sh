#!/usr/bin/env bash

set -e
#remove images
docker-compose stop
docker-compose rm -f config-service
docker-compose rm -f discovery-service
docker-compose rm -f hystrix-dashboard
docker-compose rm -f price-service
docker-compose rm -f inventory-service
docker-compose rm -f web-storefront

# Build the project and docker images
mvn clean install -Dmaven.test.skip=true

# Export the active docker machine IP
export DOCKER_IP=$(docker-machine ip $(docker-machine active))
# docker-machine doesn't exist in Linux, assign default ip if it's not set
DOCKER_IP=${DOCKER_IP:-0.0.0.0}


# Start the config service first and wait for it to become available
docker-compose up --no-recreate -d zookeeper
docker-compose up --no-recreate -d kafka
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
docker-compose up --no-recreate -d redis
docker-compose up --no-recreate -d mysql
docker-compose up -d hystrix-dashboard
docker-compose up -d price-service
docker-compose up -d inventory-service
docker-compose up -d web-storefront

# Attach to the log output of the cluster
docker-compose logs -f
