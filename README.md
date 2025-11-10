# Sumativa2
# Proyecto Microservicios - DSY1103 Desarrollo Fullstack I

## Descripción General

Este proyecto consiste en el desarrollo de un sistema compuesto por 10 microservicios independientes, cada uno implementado con Spring Boot y Maven, como parte de la Evaluación Parcial 2 del curso DSY1103 Desarrollo Fullstack I.

Cada microservicio aborda una funcionalidad clave del sistema y opera de manera desacoplada, siguiendo las mejores prácticas de arquitectura de microservicios.

---

## Estructura del Proyecto

- **CargaMasiva:** Procesamiento y registro de eventos de carga masiva de productos.Actualmente opera de forma independiente (sin integración directa con Producto); se implementó un CRUD completo y carga manual para pruebas. Puede extenderse fácilmente para comunicarse con otros microservicios vía API REST en futuras versiones. Puerto : 8081
- **Categoria:** Gestión y organización de categorías de productos. Puerto : 8082
- **Compras:** Administración de órdenes de compra y sus detalles. Puerto : 8083
- **Envios:** Gestión de despachos y seguimiento de envíos. Puerto : 8084
- **Inventario:** Control y actualización de stock de productos. Puerto : 8085
- **Notificaciones:** Envío y registro de notificaciones a los usuarios. Puerto : 8086
- **Pago:** Registro y validación de transacciones de pago. Puerto : 8087
- **Producto:** Administración y consulta de productos del catálogo. Puerto : 8088
- **Reporte:** Generación de reportes sobre distintas áreas del sistema. Puerto : 8089
- **Usuario:** Gestión de perfiles, autenticación y roles de usuario. Puerto : 8080

Cada microservicio incluye:
- Archivo `pom.xml` propio.
- Capas: **controller**, **service**, **repository**, **model**.
- Configuración de base de datos independiente.
- Endpoints RESTful (CRUD y operaciones específicas).

---

## Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot**
- **Maven**
- **JPA/Hibernate**
- **MySQL** / **H2** / **PostgreSQL** (según configuración)
- **Git**

---

## Instalación y Ejecución

1. Clona el repositorio
2. Configura la base de datos en el archivo `application.properties` de cada microservicio.
3. Ejecuta en cada carpeta de microservicio:
   mvn clean install
   mvn spring-boot:run
4. Accede a los endpoints vía Postman según los puertos y rutas de cada servicio.

## Pruebas y Colección Postman

Puedes probar todos los endpoints usando la colección oficial de Postman:

- **[Colección Postman - CharmeetChic](https://api.postman.com/collections/44443461-f9789895-405c-4ae2-b8a6-924e7c1f36cd?access_key=PMAT-01JWM94M9JA3DG05Z8G88AFSAW)**

**¿Cómo usarla?**
1. Copia el enlace.
2. Abre Postman y haz clic en **"Import"**.
3. Selecciona **"Link"** y pega la URL.
4. Haz clic en **"Continue"** y luego **"Import"**.
5. La colección quedará lista y organizada por microservicio para tus pruebas.

> La colección contiene ejemplos y descripciones para facilitar el testing de la API.

Todos los endpoints quedarán organizados por microservicio.

## Contribución

Revisa el archivo `CONTRIBUTING.md` para conocer las normas de colaboración.

## Autoras

- Daniela Gómez Palacios
- Berta Soto Jerez




