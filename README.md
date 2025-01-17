# Biblioteca Persitente
Reto de AluraLatam para poner en practica lo aprendido sobre Spring boot y la persistencia de datos usando BD postgres haciendo uso de una API de libros llamada Gutendex

## Descripción
Este proyecto es una solución para el reto de Alura Latam que consiste en crear una Biblioteca usando java. El objetivo es consumir una API para poder realizar la consulta sobre los libros que solicite el usuario y estos sean almacenados en una BD de postgres para poder ser consultados cuando se desee haciendo uso de los componente JPA de Spring boot.

## Contenido del repositorio
Este repositorio contiene el código fuente del proyecto, organizado en los siguientes paquetes y archivos:

* **modelos**: Contiene la clase necesarias para poder realizar los cambios de los atributos recibidos en el JSON de la API convirtiendolos a los atributos utilizados para nuestras clases Autor y Libro, son las entidades de la BD para realizar las consultas.
* **principal**: Contiene la funcion principal donde se encuentra el menu de la interfaz de usuario y el llamado de las funciones para realizar la consulta.
* **repository**: Contiene el las interfaces las cuales extienden de JpaRepository que permiten la conexion con la BD para poder realizar las diferentes consultas del CRUD.
* **README.md**: Este archivo de lectura.
* **LiteraturapersistenteApplication.java**: Es el archivo raiz de la aplicacion Spring desde la cual se va a ejecutar el programa.

## Requisitos previos
Para ejecutar este proyecto, se requiere:

* Instalar java 17 minimo
* Instalar la ultima version de postgres.
* Crear una BD que se llame libreria_alejandria
* Registrar las variables de entorno de la bd las cuales serian:
    * DB_HOST : Para determinar el host donde se encuentra la BD
    * DB_USER : Es el usuario con el cual se puede acceder a la BD
    * DB_PASSWORD : Es la contraseña del usuario de la BD


## Uso
Para utilizar el encriptador y desencriptador, sigue los siguientes pasos:

1. Abre el proyecto en un IDE que pueda ejecutar java.
2. Ingrese a la siguiente ruta `src/main/java/com/aluralatam/retoliteratura/literaturapersistente/LiteraturapersistenteApplication.java` y Ejecute el programa
3. Selecciona una opcion del menu que se muestra en consola.
4. Primero si no se tienen ningun registro en la BD s recomienda ingresar la primer opcion para ir almacenando informacion en la BD para que se puedan reflejar datos en las demas opciones
5. Si se desea salir del programa ingrese el numero 0.

## Autor
Este proyecto fue creado por Cesar Moises Arteaga German.

## Agradecimientos
Agradezco a Alura Latam por proporcionar este reto y la oportunidad de desarrollar mis habilidades en programación.
## API Reference

#### Consultar libros en la API https://gutendex.com/

```http
  GET https://gutendex.com/books/?search={DATO_BUSCAR}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `DATO_BUSCAR` | `string` | **requerido**. El nombre del libro que se desea buscar, puede ser cualquier palabra y la API busca nombres que se asemejen |