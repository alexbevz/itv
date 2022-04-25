FROM openjdk:17-alpine
COPY ./ /home/vit
RUN apk add maven
RUN mvn -f /home/vit/pom.xml clean package
RUN apk add font-vollkorn font-misc-cyrillic font-mutt-misc font-screen-cyrillic font-winitzki-cyrillic font-cronyx-cyrillic
CMD ["java", "-jar", "/home/vit/target/vit-1.0-RELEASE.jar"]

