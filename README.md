# # API-Operacion de Quasar
API REST con el objetivo de obtener la ubicacion y poder decodificar el mensaje de auxilio de un nave imperial a la deriva, utilizando 3 satelites a distintas distancias.

Tecnología utilizada: Springboot

Lenguaje: Java 11

Data base: H2

Deploy en Heroku: https://empleadas-luciana-maldonado.herokuapp.com

Documentacion en Postman: https://documenter.getpostman.com/view/16267232/UVC2J9TV


# # Como ejecutar el proyecto:
1- Descarga el proyecto utlizando la opcion de clonar proyecto disponible en GitHub y copiando el URL en tu IDE de preferencia (recomiendo IntelliJ.

2- En caso de no tener previamente descargado el Maven Wrapper, descargarlo (pueden seguir el siguiente tutorial: https://www.baeldung.com/maven-wrapper)

3- Ir a la main class del proyecto y ejecutarlo, o mismo de la opcion run de tu IDE de preferencia.
El proyecto se deplegara en el puerto 8080 (en caso de querer modificar el puerto, modificarlo desde el application.properties,
en la opcion que dice: server.port=8080)

4- Los diferentes endpoint pueden ser ejecutados a traves de Postman siguiendo la documentacion provista anteriormente.
Tener en cuenta de que en caso de ejecutarlo de forma local se puede utilizar: http://localhost:8080 (o el puerto elegido) en vez del URL de deploy en Heroku

# # Tipo de arquitectura utilizada:

El proyecto se creo siguiendo las bases y estructura de la arquitectura hexagonal, un tipo de clean architecture.
Con el siguiente flujo 

# #Testing

Para los test unitarios se utilizo Junit. 

