# Ejercicio 4: Documentación microservicio de Formación

## Descripción

Este microservicio forma parte de un sistema de gestión educativa y se encarga de manejar las formaciones. Interactúa con el microservicio de cursos para proporcionar una capa adicional de abstracción y funcionalidad.

## Características

- Obtención de formaciones basadas en los cursos existentes
- Creación de nuevas formaciones
- Cálculo automático de asignaturas basado en la duración del curso
- Generación de códigos de curso a partir del nombre de la formación
- Documentación automática de la API mediante Swagger.

## Tecnologías

- Java 17
- Spring Boot 3.x
- Spring RestClient
- Maven
- Lombok
- Springdoc OpenAPI

## Configuración

1. Asegúrate de tener instalado Java 17 y Maven.
2. Clona este repositorio:
  - https://github.com/ext-gonbonan/ejercicio2_microservicio_formacion.git

## Ejecución

1. Asegúrate de que el microservicio de cursos esté en ejecución.
2. Ejecuta la aplicación:
   - mvn spring-boot:run

## Uso

### Endpoints

- `GET /formacion`: Obtiene todas las formaciones
- `POST /formacion`: Crea una nueva formación

## Dependencias

Este microservicio depende del microservicio de cursos. Asegúrate de que esté en ejecución y accesible antes de usar este servicio.
- https://github.com/ext-gonbonan/ejercicio1_desarrollo_microservicio_crud_cursos.git


## Documentación de la API
La documentación de la API está disponible mediante Swagger. Para acceder a ella, sigue estos pasos:

Asegúrate de que la aplicación esté en ejecución.
Ve a http://localhost:9000/swagger-ui.html en tu navegador.

![imagen](https://github.com/ext-gonbonan/ejercicio4_documentacion_microservicios/assets/173496006/5a223792-e068-4287-84be-475e1da0e3f9)


### Configuración de Swagger
La configuración de Swagger se realiza en el archivo application.properties. A continuación, se muestra un ejemplo de configuración:

#### Configuración Spring Doc
    springdoc.packages-to-scan=com.formacion.controller
    springdoc.paths-to-match=/**

#### Anotaciones en Controladores
    Los controladores están anotados con @Operation y @Parameter para documentar automáticamente los endpoints. 

### Dependencias para Documentación
Para habilitar la documentación automática de la API mediante Swagger, hemos añadido las siguientes dependencias en el archivo pom.xml:

- springdoc-openapi-starter-webmvc-ui:
Esta dependencia proporciona la integración con Swagger UI para generar la documentación interactiva de la API a partir de las anotaciones en los controladores.
- spring-boot-starter-validation:
Esta dependencia se utiliza para la validación de datos dentro de las aplicaciones Spring Boot, asegurando que los datos que llegan a los endpoints cumplan con los requisitos definidos.

```xml
<dependencies>
    <!-- Dependencias para documentación Spring Doc -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.2.0</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
</dependencies>
