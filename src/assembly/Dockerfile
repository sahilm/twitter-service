FROM java:openjdk-8-alpine

RUN addgroup -S twitter-service && adduser -SG twitter-service twitter-service

USER twitter-service

RUN mkdir -p /opt/twitter-service

COPY maven/lib /opt/twitter-service/lib/

COPY maven/twitter-service.jar /opt/twitter-service/

EXPOSE 8080

WORKDIR /opt/twitter-service/

ENTRYPOINT java -DAPP_HOME=$APP_HOME -jar twitter-service.jar

