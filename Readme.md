# Información Importante 
El siguiente repositorio está destinado para pruebas técnicas realizadas para Capitole y/o personas destinadas, el uso es confidencial.


## Instalación MAVEN

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

### Clean Maven

`mvn clean`
### Compilación Maven

`mvn compile`
### Test Maven

`mvn test`

### Build Maven

`mvn package`

## Run 

`java -jar target/pruebaTec-0.0.1-SNAPSHOT.jar`



## Servicios entregados
### Endpoints

Se entrega siguiente **endpoint**:

_Obtención de precio_ acorde a una cadena(**brandId**), producto(**productId**) y la fecha a consultar(**applicationDate**).
Como parametria de consulta se detalla a continuación:
* _brandId_: siendo un número __entero__ natural(Ejemplo: 1).
* _productId_: siendo un número __entero__ natural(Ejemplo: 35455).
* _applicationDate_: siendo una fecha seleccionada en formato __ISO 8601__(Ejemplo: 2020-06-14T19:00:59).
  - Formato detallado a continuación:
      * _2020-06-14_: La fecha está compuesta por año-mes-dia de la siguiente manera YYYY-MM-DD(2020-06-14)
      * _T_: El separador de tiempo, indicando el inicio del componente de hora.
      * _19:00:59_: La componente hora está compuesta por la hora(19), los minutos(00) y los segundos(59), todos separados por dos puntos(traducido a ascii como %3A).

Como respuesta tenemos internamente la identification que si existen 2 precios para una hora señalada se obtenga el que tiene una mayor prioridad, identificada en el campo __PRIORITY__, siendo el mas lejano a 0 el de mayor prioridad

#### CURL V1

Request
```
curl --location --request GET 'localhost:8080/prices?applicationDate=2020-06-14T19%3A00%3A59&productId=35455&brandId=1'
```
Respuesta
```
{
    "brandId": 1,
    "startDate": "2020-06-14 00:00:00",
    "endDate": "2020-12-31 23:59:59",
    "priceList": 1,
    "productId": 35455,
    "priority": 0,
    "price": "35,50",
    "curr": "EUR"
}
```


### Reference Documentation


