# FROM maven:3.8.2-jdk-8 # for Java 8
FROM maven:3.8.5-openjdk-17

WORKDIR /bkool-app
COPY . .
RUN mvn clean package

RUN mvn clean install

WORKDIR /bkool-app/boot

CMD mvn spring-boot:run -Dspring-boot.run.profiles=docker