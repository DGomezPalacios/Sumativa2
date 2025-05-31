# Sumativa2
# Proyecto Microservicios - DSY1103 Desarrollo Fullstack I

## Descripción General

Este proyecto consiste en el desarrollo de 10 microservicios independientes utilizando Spring Boot y Maven, como parte de la Evaluación Parcial 2.

## Estructura del Proyecto

- `CargaMasiva`
- `Categoria`
- `Compras`
- `Envios`
- `Inventario`
- `Notificaciones`
- `Pago`
- `Producto`
- `Reporte`
- `Usuario`

Cada microservicio incluye:
- `pom.xml` propio
- Capas de controller, service, repository, model
- Configuración de base de datos
- Endpoints RESTful (CRUD)

## Tecnologías

- Java 17+
- Spring Boot
- Maven
- JPA/Hibernate
- MySQL / H2 / PostgreSQL
- Git

## Instalación y Ejecución

1. Clona el repositorio:
2. Configura la base de datos en el archivo `application.properties` de cada microservicio.
3. Ejecuta en cada carpeta de microservicio:
   mvn clean install
   mvn spring-boot:run
4. Accede a los endpoints vía Postman según los puertos y rutas de cada servicio.

## Pruebas

- Prueba cada endpoint usando Postman.  

## Contribución

Revisa el archivo `CONTRIBUTING.md` para conocer las normas de colaboración.

## Equipo

- Daniela Gómez Palacios
- Berta Soto Jerez




