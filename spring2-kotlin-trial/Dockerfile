FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/spring2-kotlin-trial-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar