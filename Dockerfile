FROM openjdk:8-jre-alpine3.7
WORKDIR /app
COPY . .
ENTRYPOINT ["entrypoint.sh"]