FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/devops-project-1.0.jar devops-project-1.0.jar
ENTRYPOINT ["java","-jar","/devops-project-1.0.jar"]