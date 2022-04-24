FROM openjdk:17-alpine
ADD /target/vit-1.0-RELEASE.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]