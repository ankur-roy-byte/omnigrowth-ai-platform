# Build stage
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

# Copy Maven wrapper and pom files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY platform-domain/pom.xml platform-domain/
COPY platform-connectors/pom.xml platform-connectors/
COPY platform-workflow/pom.xml platform-workflow/
COPY platform-reporting/pom.xml platform-reporting/
COPY platform-app/pom.xml platform-app/
COPY connector-youtube/pom.xml connector-youtube/
COPY connector-linkedin/pom.xml connector-linkedin/
COPY connector-tiktok/pom.xml connector-tiktok/
COPY connector-video-shotstack/pom.xml connector-video-shotstack/

# Download dependencies (cached layer)
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copy source code
COPY platform-domain/src platform-domain/src
COPY platform-connectors/src platform-connectors/src
COPY platform-workflow/src platform-workflow/src
COPY platform-reporting/src platform-reporting/src
COPY platform-app/src platform-app/src
COPY connector-youtube/src connector-youtube/src
COPY connector-linkedin/src connector-linkedin/src
COPY connector-tiktok/src connector-tiktok/src
COPY connector-video-shotstack/src connector-video-shotstack/src

# Build the application
RUN ./mvnw clean package -DskipTests -B

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Add non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy the built JAR
COPY --from=builder /app/platform-app/target/*.jar app.jar

# Create plugins directory
RUN mkdir -p /app/plugins && chown -R appuser:appgroup /app

USER appuser

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
    CMD wget -q --spider http://localhost:8080/actuator/health || exit 1

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
