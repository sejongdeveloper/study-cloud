FROM openjdk:17-ea-slim
VOLUME /tmp
COPY build/libs/account-1.0.jar account.jar
ENTRYPOINT ["java", "-jar", "account.jar"]