#
# Build stage
#

FROM maven:3.8-openjdk-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
ARG APP_NAME="octopus"
ARG APP_VERSION="0.0.1"
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/${APP_NAME}-${APP_VERSION}-SNAPSHOT.jar octopus.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","funds.jar"]