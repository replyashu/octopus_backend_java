#
# Build stage
#

FROM maven:3.8-openjdk-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/octopus-0.0.1-SNAPSHOT.jar octopus.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","funds.jar"]