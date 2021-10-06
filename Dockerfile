FROM openjdk:8-jre-alpine3.7
WORKDIR /app
COPY . .
COPY entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]