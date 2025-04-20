# Step 1: Build
FROM gradle:8.4.0-jdk21 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN chmod +x ./gradlew
RUN ./gradlew build -x test --no-daemon

# Step 2: Run
FROM eclipse-temurin:21-jdk
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]