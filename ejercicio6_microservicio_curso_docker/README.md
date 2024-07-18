# Ejercicio 4: Documentación microservicio Curso

## Descripción del Proyecto

Este proyecto implementa un microservicio RESTful para la gestión de cursos educativos. Proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para entidades de tipo Curso, permitiendo una gestión eficiente de la información de cursos en una plataforma educativa.

### Características Principales:

- Creación de nuevos cursos con detalles como código, nombre, duración y precio.
- Consulta de cursos individuales o listado completo de cursos disponibles.
- Actualización de la información de cursos existentes.
- Eliminación de cursos del sistema.
- Búsqueda de cursos por rango de precios.
- Documentación automática de la API mediante Swagger.

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación principal.
- **Spring Boot 3.3.1**: Framework para el desarrollo de aplicaciones Java.
- **Spring Data JPA**: Para la persistencia de datos y operaciones CRUD.
- **MySQL**: Sistema de gestión de base de datos relacional.
- **Maven**: Herramienta de gestión y construcción del proyecto.
- **Lombok**: Biblioteca para reducir el código boilerplate.
- **Spring Boot DevTools**: Para el reinicio automático durante el desarrollo.
- **Springdoc OpenAPI**: Para la documentación del proyecto

## Configuración y Ejecución del Proyecto

### Prerrequisitos

- JDK 17 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior

### Pasos para Configurar

1. **Clonar el Repositorio**
   - `git clone https://github.com/ext-gonbonan/ejercicio3_securizacion_microservicio_curso.git`
   
2. **Configurar la Base de Datos**:
   - Crea una base de datos MySQL llamada `cursosdb`.
   - Actualiza el archivo `src/main/resources/application.properties` con tus credenciales de MySQL:
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/cursosdb
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     ```

3. **Inicialización de Datos**: 
   - La aplicación está configurada para crear automáticamente 50 registros de cursos al iniciar.
   - Esta inicialización se realiza a través de un CommandLineRunner en la clase principal de la aplicación.
   - Los cursos incluyen una variedad de temas, con énfasis en programación Java y otras tecnologías relevantes.
   - La inicialización solo ocurre si la base de datos está vacía para evitar duplicados.
  
4. **Compilar el Proyecto**:
   - `mvn clean install`
 
### Ejecución del Proyecto

1. **Iniciar la Aplicación**:
   - `mvn spring-boot:run`

## Endpoints Disponibles

- GET /cursos: Obtiene todos los cursos.
- GET /cursos/{codCurso}: Obtiene un curso específico por su código.
- POST /cursos: Crea un nuevo curso.
- PUT /cursos/{codCurso}/{duracion}: Actualiza la duración de un curso.
- DELETE /cursos/{codCurso}: Elimina un curso.
- GET /cursos/precios/{precioMin}/{precioMax}: Obtiene cursos dentro de un rango de precios usando una función Spring Data JPA.
- GET /cursos/precios2/{precioMin}/{precioMax}: Obtiene cursos dentro de un rango de precios usando una función QUERY


## Documentación de la API
La documentación de la API está disponible mediante Swagger. Para acceder a ella, sigue estos pasos:

Asegúrate de que la aplicación esté en ejecución.
Ve a http://localhost:8090/swagger-ui.html en tu navegador.

![imagen](https://github.com/ext-gonbonan/ejercicio4_documentacion_microservicios/assets/173496006/6fe22786-4dcd-4007-b031-26f799f98768)


### Configuración de Swagger
La configuración de Swagger se realiza en el archivo application.properties. A continuación, se muestra un ejemplo de configuración:

#### Configuración Spring Doc
    springdoc.packages-to-scan=com.curso.controller
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


