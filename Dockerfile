FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/order-consumer-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]
