FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY target/dostavka-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT java -jar /app/dostavka-0.0.1-SNAPSHOT.jar