# # API-Operacion de Quasar
API REST con el objetivo de obtener la ubicacion y poder decodificar el mensaje de auxilio de un nave imperial a la deriva, utilizando 3 satelites a distintas distancias.

Tecnología utilizada: Springboot

Lenguaje: Java 11

Data base: H2

Deploy en Heroku: https://empleadas-luciana-maldonado.herokuapp.com

Documentacion en Postman: https://documenter.getpostman.com/view/16267232/UVC2J9TV


# # Como ejecutar el proyecto:
1- Descarga el proyecto utlizando la opcion de clonar proyecto disponible en GitHub y copiando el URL en tu IDE de preferencia (recomiendo IntelliJ).

2- En caso de no tener previamente descargado el Maven Wrapper, descargarlo (pueden seguir el siguiente tutorial: https://www.baeldung.com/maven-wrapper)

3- Ir a la main class del proyecto y ejecutarlo, o mismo ejecutarlo utilizando la opcion run de tu IDE de preferencia.
El proyecto se deplegara en el puerto 8080 (en caso de querer modificar el puerto, se puee hacer desde el application.properties,
en la opcion que dice: server.port=8080)

4- Los diferentes endpoint pueden ser ejecutados a traves de Postman siguiendo la documentacion provista anteriormente.
Tener en cuenta de que en caso de ejecutarlo de forma local utilizar: http://localhost:8080 (o el puerto elegido) y sino se puede modificar por el url base de Heroku.

# # Datos a tener en cuenta:
-H2 en una base de datos relacional en memoria que en el caso de este proyecto no es persistida. Con esto se quiere decir que una vez que el proyecto deje de correr los datos guardados en ella van a ser borrados y por ello no se recomienda su uso en ambientes productivos.
Tambien implica que se recomienda verificar la existencia de los diferentes satelites en la base de datos haciendo uso del endpoint (/topsecret_split/{satellite_name}), ya que alguno de ellos podria estar fallando por esa misma razon.

-En caso de querer acceder a la consola de H2 primero asegurarse de que en el application.properties el siguiente elemento este de esta manera: spring.h2.console.enabled=true (en caso de estar false no permitira acceder a la consola)
Una vez verificado eso, se puede acceder a la misma con el siguiente endpoint: http://localhost:8080/h2-console y usando como password = password.

-Si se quiere mas informacion sobre como incorporar H2 a tus proyectos y su uso recomiendo el siguiente link: https://refactorizando.com/base-de-datos-memoria-h2-spring-boot/

# # Tipo de arquitectura utilizada:

El proyecto se creo siguiendo las bases y estructura de la arquitectura hexagonal, un tipo de clean architecture.
Con el siguiente flujo 

# #Testing

Para los test unitarios se utilizo Junit. 

