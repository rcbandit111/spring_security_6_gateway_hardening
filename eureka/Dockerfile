# Use a Java runtime as the base image
FROM openjdk:17-slim-buster

# Set the working directory to /app
WORKDIR /app

# Copy the built JAR file into the container
COPY build/libs/*.jar /app/

# Expose the default port for the app
EXPOSE 8761

# Start the app when the container launches
CMD ["java", "-jar", "eureka-server-0.0.1.jar"]
