# Usar imagen base de Java 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Construir la aplicación
RUN mvn clean package -DskipTests

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/monitor_precios-0.0.1-SNAPSHOT.jar"]
