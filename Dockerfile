FROM openjdk:21-jdk

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный JAR-файл в контейнер
COPY target/*.jar app.jar

# Запускаем
ENTRYPOINT ["java", "-jar", "app.jar"]