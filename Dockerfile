
FROM openjdk/openjdk11-jre-slim
EXPOSE 8099

# Refer to Maven build -> finalName
ARG JAR_FILE=target/spring-boot-web.jar

# cd /opt/app
WORKDIR /AMS

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY build/libs/ams.jar-0.0.1-SNAPSHOT-plain.jar app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]