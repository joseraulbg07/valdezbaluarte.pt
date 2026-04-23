# Etapa 1: Construcción (Build)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build

# Copiamos primero únicamente el archivo pom.xml.
# Esto permite a Docker cachear las dependencias descargadas si el pom.xml no sufre modificaciones, acelerando futuros builds.
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el resto del código fuente y compilamos el proyecto, omitiendo los tests para agilizar este proceso de empaquetado.
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Runtime)
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# Creamos un usuario y grupo sin privilegios de root (no-root) para cumplir con las mejores prácticas de seguridad en contenedores.
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Extraemos el archivo .jar generado en la etapa de construcción al directorio de trabajo del entorno de ejecución.
COPY --from=build /build/target/*.jar app.jar

# Exponemos el puerto 8080, que es el puerto estándar en el que escucha la aplicación Spring Boot.
EXPOSE 8080

# Definimos el comando principal de inicio para ejecutar nuestra aplicación Java.
ENTRYPOINT ["java", "-jar", "app.jar"]
