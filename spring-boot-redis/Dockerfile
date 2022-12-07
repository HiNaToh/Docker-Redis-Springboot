FROM maven:3.6.0-jdk-11-slim AS build
COPY . .
RUN mvn clean package

FROM openjdk:11-jre-slim
COPY --from=build target/spring-boot-redis.jar spring-boot-redis.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-redis.jar"]