FROM maven:3.9.4-amazoncorretto-17 AS build
WORKDIR /

COPY ["/src", "/src"]
COPY ["pom.xml", "pom.xml"]

RUN mvn clean package spring-boot:repackage
RUN mvn clean package

FROM eclipse-temurin:17-jdk-ubi9-minimal as base
EXPOSE 8081
WORKDIR /
COPY --from=build /target/*.jar /app/weather.jar
ENTRYPOINT ["java", "-jar", "/app/weather.jar"]
v