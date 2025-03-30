# 베이스 이미지 설정
FROM openjdk:21-jdk-slim

# JAR 복사 및 실행 경로 설정
COPY build/libs/Network_Device_Monitoring_System-0.0.1-SNAPSHOT.jar app.jar

# 실행 명령
ENTRYPOINT ["java", "-jar", "/app.jar"]
