# Etapa 1: Construcción (Build)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Runtime) - CAMBIO CLAVE: Quitamos 'alpine' para usar el Ubuntu/Debian estándar
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app

# Creamos un usuario sin privilegios de root (sintaxis de Debian)
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

COPY --from=build /build/target/app-backend.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
