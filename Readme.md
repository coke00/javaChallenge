# Important Information 
El siguiente repositorio está destinado para pruebas técnicas realizadas para Capitole y/o personas destinadas, el uso es confidencial.


## Instalacion MAVEN

macOS
```
brew install maven
```

generar Wrapper de Maven
```
mvn -N wrapper:wrapper

```
Este comando crea los siguientes archivos y directorios:
mvnw y mvnw.cmd: Scripts ejecutables para Unix/Mac y Windows, respectivamente.
.mvn/wrapper/: Un directorio que contiene el archivo maven-wrapper.jar y maven-wrapper.properties

Para configurar maven-wrapper.properties
dentro de .mvn/wrapper/maven-wrapper.properties se puede especificar la version de Maven que el wrapper debe descargar y usar.

Ejemplo:
```
distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip

```

Para la utilización se puede utilizar el siguiente comando:
```
./mvnw clean install

```

Se incorporan los siguientes archivos para garantizar que al momento de clonar repositorio se construya el proyecto sin necesidad de tener instalado Maven localmente.

```
mvnw
mvnw.cmd
.mvn/wrapper/maven-wrapper.jar
.mvn/wrapper/maven-wrapper.properties
```


### Reference Documentation


