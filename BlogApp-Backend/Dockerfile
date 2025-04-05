# --------------------
# 🏗️  STAGE 1: Build
# --------------------
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app/blogapp

# Copy all project files into the container
COPY . .

# Fix: Make mvnw executable
RUN chmod +x mvnw

# Build the project and skip tests (faster)
RUN ./mvnw clean install -DskipTests

# ----------------------
# 🚀 STAGE 2: Run
# ----------------------
FROM eclipse-temurin:17-jdk

WORKDIR /app/blogapp

# Copy the JAR from the builder stage
COPY --from=builder /app/target/*.jar blogapp-backend.jar

# Run the Spring Boot app
CMD ["sh", "-c", "java $JAVA_OPTS -jar blogapp-backend.jar"]
