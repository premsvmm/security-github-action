FROM openjdk:8-jre-alpine3.7
COPY . .
COPY entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]