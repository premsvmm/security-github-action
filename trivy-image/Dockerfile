# Container image that runs your code
FROM golang:1.16
WORKDIR /go/src/security-github-action
COPY . .

RUN go mod download
RUN set -eux && \
    CGO_ENABLED=0 GOOS=linux go build -ldflags "-w -s" -o /bin/security-github-action /go/src/security-github-action/app/

# Copies your code file from your action repository to the filesystem path `/` of the container
COPY entrypoint.sh /entrypoint.sh

# Code file to execute when the docker container starts up (`entrypoint.sh`)
ENTRYPOINT ["/entrypoint.sh"]