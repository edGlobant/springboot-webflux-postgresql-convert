FROM openjdk:17-jdk AS packager

WORKDIR /springboot-webflux-postgresql-convert

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]