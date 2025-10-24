# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy all files into the container
COPY . .

# ✅ Give Gradle wrapper execute permission
RUN chmod +x ./gradlew

# Build the project
RUN ./gradlew installDist

# Expose the port Ktor runs on
EXPOSE 8080

# Start the server
CMD ["./build/install/QuizTime-KtorServer/bin/QuizTime-KtorServer"]
