FROM openjdk:17-ea-slim
VOLUME /tmp
COPY build/libs/todo-1.0.jar todo.jar
ENTRYPOINT ["java", "-jar", "todo.jar"]