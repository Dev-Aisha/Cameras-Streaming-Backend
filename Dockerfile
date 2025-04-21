# Step 1: Build the project
FROM gradle:8.4.0-jdk21 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN chmod +x ./gradlew
RUN ./gradlew build -x test --no-daemon

# Step 2: Run the application
FROM eclipse-temurin:21-jdk

# Install FFmpeg
RUN apt-get update && apt-get install -y ffmpeg

# Set working directory
WORKDIR /app

# Create HLS folder
RUN mkdir -p /app/static/hls

# Copy the built jar file
COPY --from=build /app/build/libs/cameras-backend-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]