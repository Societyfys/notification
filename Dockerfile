# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS build

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY .mvn .mvn

COPY mvnw .

COPY pom.xml .

# Copy source code
COPY src src

# Install Maven and build the application
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Copy the Firebase Admin SDK JSON file to the container
COPY societyfy-firebase-adminsdk.json /app/societyfy-firebase-adminsdk.json

# Expose the port your application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
