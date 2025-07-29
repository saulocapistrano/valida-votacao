FROM openjdk:17-jdk-slim as builder
WORKDIR application
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw clean package

FROM openjdk:17-jdk-slim
WORKDIR application
COPY --from=builder application/target/coopere-voto-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]