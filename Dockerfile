FROM openjdk:8-jre-alpine3.7
COPY . /app
WORKDIR /app
ENTRYPOINT ["sh", "entrypoint.sh"]