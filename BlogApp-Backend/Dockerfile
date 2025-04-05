FROM eclipse-temurin:17.0.10_7-jre-alpine
RUN mkdir -p /app/blogapp
WORKDIR /app/blogapp
COPY target/blogapp-backend.jar .
EXPOSE 8080
CMD ["sh", "-c", "java $JAVA_OPTS -jar blogapp-backend.jar"]
