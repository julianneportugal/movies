FROM openjdk:8-jdk-alpine
MAINTAINER "Julianne Portugal"
RUN addgroup -S projmovies && adduser -S projmovies -G projmovies
USER projmovies:projmovies
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} movies.jar
ENTRYPOINT ["java","-jar","/movies.jar"]