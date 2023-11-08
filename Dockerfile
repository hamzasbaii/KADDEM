FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/devops-project-2.1.jar devops-project-2.1.jar
ENTRYPOINT ["java","-jar","/devops-project-2.1.jar"]
