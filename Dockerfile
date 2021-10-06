FROM openjdk:8-jre-alpine3.7
WORKDIR /app
COPY . .
RUN chmod +x entrypoint.sh
ENTRYPOINT ["sh","entrypoint.sh"]
RUN PWD
RUN ls