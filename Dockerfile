# ─── Stage 1: Build ───────────────────────────────────────────────────────────
FROM maven:3.8-openjdk-11 AS build

WORKDIR /app

# Copy pom.xml first to leverage Docker layer caching for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build the jar (skip tests during image build)
COPY src ./src
RUN mvn package -DskipTests -B

# ─── Stage 2: Run ─────────────────────────────────────────────────────────────
FROM openjdk:11-jre-slim

WORKDIR /app

# Copy only the executable jar from the build stage
COPY --from=build /app/target/scientific-calculator-1.0-SNAPSHOT.jar app.jar

# Run App.java (com.calculator.App) via the packaged jar
ENTRYPOINT ["java", "-jar", "app.jar"]
