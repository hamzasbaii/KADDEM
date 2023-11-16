FROM openjdk:11-slim
COPY target/DevOps_Project-2.4.jar DevOps_Project-2.4.jar
EXPOSE 8089
RUN apt-get update && apt-get install -y curl
ENTRYPOINT [ "java", "-jar", "DevOps_Project-2.4.jar" ]

