# financial-transactions-gateway

Requirements: https://www.notion.so/kadmos/Case-Study-Backend-Engineering-e20c314a99f1451bbdea8100c7942679

## Application overview


## Security

Application is secured with plan username/password (for the sake of exercise, )
In order to access the service, make sure to include the following header:

`Authorization: Basic a2FkbW9zOj81c2hGQFlLc01qZlErcCQ4dD10WmtZKms3VG03d0NkeWNyeXc1PzdzJDI2KzM2K0pD`

## Modules
The project contains 4 modules.

`financial-database-service` - containing database related functionality and confiurations in order to be able to communicate with Postgres database

`financial-service-a` - Spring Boot Web application serving traffic for `account-a`

`financial-service-b` - Spring Boot Web application serving traffic for `account-b`

`financial-transactions-gateway` - Spring Boot application that acts as a API gateway and forwarding the requests to
`financial-service-a` and `financial-service-b`

## Launch
In order to launc the application in Docker you need to execute

`/docker.sh` - it will build Docker image and push it to a local registry

`./run.sh` - will launch the image and will put an id you can use to check for logs


## Logs

Once you run a docker image, you can check the logs using the following command:

` docker ps | grep 'io-kadmos/financial-transactions-gateway' | awk '{print $1}' | xargs -I {} docker logs -f {} `
It will tail the logs so you can check it out.

## Request logging

Once the app starts, you can use `request-log.log` to analyze all incoming requests being served by the application
Please note, that the file is autorotated according to the parameters set in `src/main/resources/logback.xml`

## Downstreams

`financial-service-a` - the app needs to be running on port 8081 

`financial-service-b` the app needs to be running on port 8082

## Timeouts

The gateway imposes 5 seconds timeout on all requests and returns `504`. 

Sample response:
`{
"timestamp": "2022-04-05T11:32:26.718+00:00",
"path": "/savings/a/balance",
"status": 504,
"error": "Gateway Timeout",
"requestId": "2edde258"
}`

## Considerations

**Q: How to scale your API gateway?**

A: Since the application is dockerized, 
we can use AWS EKS to automatically increase number of running pods.


**Q: How to monitor uptime so you can sleep at night?**

A: Set up Datadog (or any other tooling) dashboards. It is always a good idea to come up with SLO for the service, establish tiles and define monitors 
that will be triggered and notify you via Slack or PagerDuty when threshold is crossed.

**Q: How to test the timeouts?**

A: Setup https://wiremock.org/docs/getting-started/ and mock the endpoints with timeout of > 5 seconds. And write an integration test.
