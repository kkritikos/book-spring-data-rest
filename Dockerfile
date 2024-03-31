# Stage 1: Build the Maven project
FROM maven:3.9.6-eclipse-temurin-21-alpine@sha256:53f16a14a83c0c3600ec8e18d21939996816a085b8a2699b835fedade1ed893a AS build
WORKDIR /app

# Copy only the POM file to leverage Docker caching
COPY pom.xml .

# Download dependencies. This layer will be cached if pom.xml is not changed
RUN mvn -B dependency:go-offline

# Copy the rest of the source code and build the application
COPY src src
RUN mvn -B clean package -DskipTests

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:21-alpine@sha256:b5d37df8ee5bb964bb340acca83957f9a09291d07768fba1881f6bfc8048e4f5 AS runtime
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=build /app/target/book-0.0.1-SNAPSHOT.jar .

# Set a non-root user for security reasons
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
USER appuser

# Expose the port your application listens on
EXPOSE 8080

#Environment variables
ENV MYSQL_NAME=localhost
ENV MYSQL_USER=root
ENV MYSQL_PWD=root

# Command to run the application
CMD ["java", "-jar", "book-0.0.1-SNAPSHOT.jar"]