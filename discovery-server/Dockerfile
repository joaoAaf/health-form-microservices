FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY . .
RUN apk add maven
RUN mvn clean package
EXPOSE 8761
CMD [ "java", "-jar", "./target/discovery-server-0.0.1-SNAPSHOT.jar" ]