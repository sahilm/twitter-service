FROM java:openjdk-8-alpine


RUN mkdir -p /opt/twitter-service

COPY maven/lib /opt/twitter-service/lib/

COPY maven/twitter-service.jar /opt/twitter-service/

EXPOSE 8080

WORKDIR /opt/twitter-service/

ENTRYPOINT java -jar twitter-service.jar

