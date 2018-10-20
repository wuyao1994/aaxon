FROM anapsix/alpine-java:8
VOLUME /tmp
ADD web-storefront-1.0.0-SNAPSHOT.war app.war
RUN bash -c 'touch /app.war'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]