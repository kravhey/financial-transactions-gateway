# financial-service-b

Requirements: https://www.notion.so/kadmos/Case-Study-Backend-Engineering-e20c314a99f1451bbdea8100c7942679

## Application overview
The application is powered by Spring Boot. It exposes 2 endpoints for changing and fetching the balance.


### Changing balance

`/POST /balance` Changes the balance of the account `b` and returns a modified entity.
Sample request: `{"amount" : 100}`

Sample response: `{"amount" : 50}`

### Getting balance
`/GET /balance` Returns balance of account `b`.
Sample response: `{"amount" : 50}`


## Launch
In order to launch the application in Docker you need to execute

`/docker.sh` - it will build Docker image and push it to a local registry

`./run.sh` - will launch the image and will put an id you can use to check for logs


## Logs

Once you run a docker image, you can check the logs using the following command:

` docker ps | grep 'io-kadmos/financial-service-b' | awk '{print $1}' | xargs -I {} docker logs -f {} `
It will tail the logs so you can check it out.

## Downstreams

Postgres database