FROM java:openjdk-8-alpine

RUN addgroup twitter-service

RUN adduser -S -h '/home/twitter-service' -G twitter-service twitter-service

USER twitter-service

COPY maven/lib /home/twitter-service/lib

COPY maven/twitter-service.jar /home/twitter-service/

EXPOSE 8080

WORKDIR /home/twitter-service/

ENTRYPOINT java -DAPP_HOME=$APP_HOME -jar twitter-service.jar
