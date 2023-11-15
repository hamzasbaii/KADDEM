FROM openjdk:12-alpine
EXPOSE 8082
WORKDIR /KADDEM
COPY target/DevOps_Project-2.1.jar /KADDEM/
CMD ["java", "-jar", "DevOps_Project-2.1.jar"]
