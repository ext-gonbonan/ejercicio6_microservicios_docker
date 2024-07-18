# Implementación y Despliegue de Microservicios con Docker

## Introducción

Este proyecto contiene dos microservicios, `curso` y `formacion`, implementados y desplegados utilizando Docker y Docker Compose. Cada microservicio tiene su propia configuración y un Dockerfile para la construcción de la imagen Docker. Se han configurado para asegurar su correcto funcionamiento y comunicación entre ellos.

## Estructura del Proyecto

El proyecto consta de dos microservicios con la siguiente estructura de directorios:


### ejercicio6_microservicios_docker
```docker-compose.yml

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

- docker-compose build


Despliegue de Servicios:
Se utilizó Docker Compose para desplegar los servicios en modo desatendido.

bash
Copiar código
docker-compose up -d
Verificación de Servicios:
Se verificó que los servicios estuvieran en ejecución utilizando el comando docker ps.

bash
Copiar código
docker ps
Logs de los Servicios:
Se revisaron los logs de los servicios para asegurarse de que todo estuviera funcionando correctamente.

bash
Copiar código
docker logs ejercicio6-curso-service-1
docker logs ejercicio6-formacion-service-1
