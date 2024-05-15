FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY . .
RUN apk add maven
RUN mvn clean package
EXPOSE 8765
CMD [ "java", "-jar", "./target/api-gateway-0.0.1-SNAPSHOT.jar" ]