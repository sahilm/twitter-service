FROM java:openjdk-8-alpine

RUN mkdir -p /opt/twitter-service

COPY maven/lib /opt/twitter-service/lib/

COPY maven/twitter-service.jar /opt/twitter-service/

RUN addgroup -S twitter-service && adduser -SG twitter-service twitter-service

RUN chown -R twitter-service:twitter-service /opt/twitter-service

USER twitter-service

EXPOSE 8080

WORKDIR /opt/twitter-service/

ENTRYPOINT java -jar twitter-service.jar

