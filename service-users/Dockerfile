FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY . .
RUN apk add maven
RUN mvn clean package
CMD [ "java", "-jar", "./target/service-users-0.0.1-SNAPSHOT.jar" ]