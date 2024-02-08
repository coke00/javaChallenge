# Build stage
FROM openjdk:17-slim as build
WORKDIR /app
# Copiamos  Maven Wrapper y pom.xml
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Damos los permisos correspondiente a Maven Wrapper
RUN chmod +x ./mvnw

# Usamos Maven Wrapper para descargar dependencias
RUN ./mvnw dependency:go-offline -B

# Copiamos los demas recursos
COPY src src

# Usamos Maven Wrapper para hacer build win correr los test
RUN ./mvnw package -DskipTests

# Copiamos el jar generado y corremos nuestro jar
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/pruebaTec-0.0.1-SNAPSHOT.jar ./app.jar
CMD ["java", "-jar", "app.jar"]
