

# First stage: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY . .
RUN mvn clean package -DskipTests

# Second stage: Run the built application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Bell-Curve-0.0.1-SNAPSHOT.jar Bell-Curve.jar
EXPOSE 7060

ENTRYPOINT ["java", "-jar", "Bell-Curve.jar"]
