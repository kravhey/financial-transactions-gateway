# financial-database-service

Requirements: https://www.notion.so/kadmos/Case-Study-Backend-Engineering-e20c314a99f1451bbdea8100c7942679

## Application overview

The application contains necessary configurations to enable database connectivity. It uses Spring boot.
The central class is `HibernateConfiguration` which establishes database connectivity and auto-configures Hibernate.

## Launch
In order to launch the application in Docker you need to execute

`/build.sh` - it will build Docker image with Postgres datbase and push it to a local registry

`./run.sh` - will launch the image and will put an id you can use to check for logs

## Logs

Once you run a docker image, you can check the logs using the following command:

` docker ps | grep 'kadmos-postgres' | awk '{print $1}' | xargs -I {} docker logs -f {} `
It will tail the logs so you can check it out.


## Downstreams

The application does not have any downstreams.