FROM openjdk:11.0.9.1-jre-slim
WORKDIR /deployment
COPY target/seed-desafio-cdc*.jar /deployment/app.jar
CMD ["java", "-jar" , "app.jar"]