# Implementación y Despliegue de Microservicios con Docker

## Introducción

Este proyecto contiene dos microservicios, `curso` y `formacion`, implementados y desplegados utilizando Docker y Docker Compose. Cada microservicio tiene su propia configuración y un Dockerfile para la construcción de la imagen Docker. Se han configurado para asegurar su correcto funcionamiento y comunicación entre ellos.
Además en el directorio raiz tenemos un `docker-compose.yml` facilitando la creación y configuración de los microservicios en docker.

## Estructura del Proyecto

El proyecto consta de dos microservicios con la siguiente estructura de directorios:


### ejercicio6_microservicios_docker
```docker-compose.yml

docker-compose.yml

ejercicio6_microservicio_curso_docker
        curso.jar
        Dockerfile

ejercicio6_microservicio_formacion_docker
        formacion.jar
        Dockerfile
```

        
### Dockerfile para `cursos`

```Dockerfile
FROM openjdk:17-jdk-slim
ADD curso.jar curso.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/curso.jar"]
```

### Dockerfile para `formacion`

```Dockerfile
FROM openjdk:17-jdk-slim
ADD formacion.jar formacion.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "/formacion.jar"]
```

### Archivo Docker Compose
El archivo docker-compose.yml se utiliza para orquestar el despliegue de los microservicios.

```docker-compose.yml
version: '3.8'  # Versión del formato de Docker Compose que se está utilizando

services:       # Define los servicios que serán ejecutados en los contenedores
  curso-service: # Primer servicio, llamado 'curso-service'
    build: 
      context: ./ejercicio6_microservicio_curso_docker  # Directorio de contexto para la construcción de la imagen Docker
      dockerfile: Dockerfile                            # Archivo Dockerfile que se usará para construir la imagen de este servicio
    ports:
      - "9001:8090"         # Mapea el puerto 9001 del host al puerto 8090 del contenedor
    networks:
      - mynetwork
    
  formacion-service: # Segundo servicio, llamado 'formacion-service'
    build: 
      context: ./ejercicio6_microservicio_formacion_docker  # Directorio de contexto para la construcción de la imagen Docker
      dockerfile: Dockerfile                                # Archivo Dockerfile que se usará para construir la imagen de este servicio
    ports:
      - "9000:8091"         # Mapea el puerto 9000 del host al puerto 8091 del contenedor
    depends_on:
      - curso-service       # Establece una dependencia con el servicio 'curso-service'. Docker Compose se asegurará de que 'curso-service' esté iniciado antes de 'formacion-service'.
    networks:
      - mynetwork
    environment:
      CURSO_SERVICE_URL: http://curso-service:8090/cursos

networks:
  mynetwork:
    driver: bridge          # Define una red llamada 'mynetwork' utilizando el driver 'bridge'
```



### Pasos Realizados
 - Construcción de Imágenes Docker:
        Se construyeron las imágenes Docker para ambos microservicios utilizando los Dockerfiles proporcionados. Con solo un comando se crean las imágenes de los microservicios docker.

        C:\Users\antonio.gonzalez\Desktop\ALTIA\MICROSERVICIOS\imagenes_docker\ejercicio6> docker-compose build

![imagen](https://github.com/user-attachments/assets/4a2e94e7-a8fd-4f2d-af2a-650792ffb221)


- Despliegue de Servicios:
        Se utilizó Docker Compose para desplegar los servicios en modo desatendido `-d`.

        C:\Users\antonio.gonzalez\Desktop\ALTIA\MICROSERVICIOS\imagenes_docker\ejercicio6> docker-compose up -d

![imagen](https://github.com/user-attachments/assets/03577005-4d3e-40aa-9257-38d11deb79ad)



- Verificación de Servicios:
        Se verificó que los servicios estuvieran en ejecución utilizando el comando `docker ps`.

        C:\Users\antonio.gonzalez\Desktop\ALTIA\MICROSERVICIOS\imagenes_docker\ejercicio6> docker ps

  ![imagen](https://github.com/user-attachments/assets/0a500ea4-c708-4465-b9bd-094cde24c43b)


- Logs de los Servicios:
        Se revisaron los logs de los servicios para asegurarse de que todo estuviera funcionando correctamente.

        C:\Users\antonio.gonzalez\Desktop\ALTIA\MICROSERVICIOS\imagenes_docker\ejercicio6> docker logs ejercicio6-curso-service-1
  ![imagen](https://github.com/user-attachments/assets/1b21b8bd-7799-4435-bfc2-8d1503a2dfaf)

        C:\Users\antonio.gonzalez\Desktop\ALTIA\MICROSERVICIOS\imagenes_docker\ejercicio6> docker logs ejercicio6-formacion-service-1
  ![imagen](https://github.com/user-attachments/assets/ff1b03f6-02ea-466a-b471-e989c8f1d869)



### Acceso a Swagger
- Para acceder a la interfaz de Swagger y probar los endpoints, se utilizó la siguiente URL:

        Microservicio Curso: http://localhost:9001/swagger-ui/index.html
        Microservicio Formación: http://localhost:9000/swagger-ui/index.html

![imagen](https://github.com/user-attachments/assets/51bf655e-372c-478f-a793-00c09daa2c81)

![imagen](https://github.com/user-attachments/assets/ef9fc0b8-5ec3-4178-8ba3-dd96e06ed6a1)




### Conclusión
- Se ha implementado y desplegado con éxito dos microservicios utilizando Docker y Docker Compose. Los microservicios se comunican entre ellos y están configurados para trabajar con una base de datos MySQL. Además, se ha configurado Swagger para probar los endpoints de los microservicios.

### Observaciones
- Ejecución en Modo Desatendido: El uso de docker-compose up -d permite ejecutar los servicios en segundo plano.
- Manejo de Redes: Se utiliza una red bridge para permitir la comunicación entre los contenedores.
