FROM openjdk:17-jdk AS installer

ENV SPRING_PROFILES_ACTIVE=dev

WORKDIR /springboot-webflux-postgresql-convert

EXPOSE 8080

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--environment=dev"]