FROM amazonlinux:latest

USER root
RUN amazon-linux-extras install java-openjdk11

RUN mkdir -p /home/app

COPY build/libs/financial-service-a-0.0.1.jar /home/app/app.jar

ENTRYPOINT ["sh", "-c", "java -jar /home/app/app.jar"]
