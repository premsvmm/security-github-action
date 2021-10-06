FROM openjdk:8-jre-alpine3.7
COPY . /app
WORKDIR /app
RUN chmod +x entrypoint.sh
ENTRYPOINT ["sh", "entrypoint.sh"]