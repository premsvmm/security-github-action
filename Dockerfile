FROM openjdk:8-jdk-alpine3.7 AS builder
RUN java -version
COPY . /usr/src/myapp/
WORKDIR /usr/src/myapp/
RUN apk --no-cache add maven && mvn --version
RUN mvn package
FROM openjdk:8-jre-alpine3.7
COPY entrypoint.sh /entrypoint.sh
COPY . /
COPY --from=builder /usr/src/myapp/target/security-github-action-1.0-SNAPSHOT.jar /security-github-action-1.0-SNAPSHOT.jar
#COPY target/security-github-action-1.0-SNAPSHOT.jar /security-github-action-1.0-SNAPSHOT.jar
ENTRYPOINT ["/entrypoint.sh"]