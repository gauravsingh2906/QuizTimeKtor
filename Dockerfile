# Use official Java image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Build the project
RUN ./gradlew installDist

# Expose the Ktor port
EXPOSE 8080

# Start the server
CMD ["./build/install/QuizTimeKtor/bin/QuizTimeKtor"]
