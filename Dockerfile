# Use an official Java runtime as a parent image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy project files and build the app
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Run the app (replace 'your-app.jar' with your JAR's real name)
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
