1[CE 7.c] ¿Que librería/clases has utilizado para realizar la práctica.? Comenta los métodos mas interesantes

Librería utilizada:

java.io.File → Para gestionar archivos

Funciones sobre ficheros:
  readLines() → Leer contenido del fichero.
  appendText() → Añadir información sin borrar lo existente.
  writeText() → Sobrescribir el contenido del fichero.
  exists() → Comprobar si el fichero existe.
  createNewFile() → Crear fichero si no existe.


2[CE 7.d] 2.a ¿Que formato le has dado a la información del fichero para guardar y recuperar la información? 2.b ¿Que estrategia has usado para trabajar con los ficheros? (Carpeta en donde se guardan los ficheros, cuando crear los archivos, ....) 2.c ¿Cómo se gestionan los errores? Pon ejemplos de código (enlace permanente al código en GitHub).

2.a
He utilizado el formato CSV. Es un formato de texto plano donde cada línea representa un objeto y los atributos se separan por comas
Vuelos: id,descripcion,origen,destino,hora
Hoteles: id,descripcion,ubicacion,numeroNoches
Este formato facilita la interoperabilidad y permite abrir los datos fácilmente en herramientas como Excel para auditoría.

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-jvazlop/blob/d007dc1e7b9243dbd49121c0211d546b1b2ae276/src/main/kotlin/es/iesra/DAO/ReservaVueloDAO.kt#L21

2.b

Ubicación: Los archivos se guardan en la raíz del proyecto (reservas_vuelo.csv y reservas_hotel.csv) para facilitar el acceso relativo sin configurar rutas absolutas
Creación: Se utiliza un bloque init en las clases DAO. Al instanciar el DAO, el programa comprueba si el archivo existe; si no, lo crea y escribe la línea de cabecera
Persistencia: La escritura es incremental (append) cada vez que se crea una reserva, asegurando que los datos no se pierdan si la aplicación se cierra inesperadamente

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-jvazlop/blob/d007dc1e7b9243dbd49121c0211d546b1b2ae276/src/main/kotlin/es/iesra/DAO/ReservaVueloDAO.kt#L6-L18

2.c

Se gestionan mediante validación previa (en los dos DAO uso el mismo)
https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-jvazlop/blob/d007dc1e7b9243dbd49121c0211d546b1b2ae276/src/main/kotlin/es/iesra/DAO/ReservaHotelDAO.kt#L27-L40

[CE 7.e] 3.a Describe la forma de acceso para leer información 3.b Describe la forma de acceso para escribir información 3.c Describe la forma de acceso para actualizar información. Pon ejemplos de código (enlace permanente al código en GitHub).

3.a

Se utiliza un acceso secuencial mediante la función readLines() de la librería java.io.File
Se carga el archivo completo en memori
Se eliminan las cabeceras
Se transforman las cadenas de texto en objetos de dominio mediante los métodos creaInstancia

3.b

Para la escritura, se utiliza la función appendText(). Esta forma de acceso es de solo-escritura al final del archivo
Ventaja: Simplemente añade la nueva línea al final del flujo.
https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-jvazlop/blob/d007dc1e7b9243dbd49121c0211d546b1b2ae276/src/main/kotlin/es/iesra/DAO/ReservaVueloDAO.kt#L20-L23


3.c 

Se lee toda la lista de objetos a memoria
Se modifica el objeto deseado en la lista
Se utiliza writeText() para volcar la lista completa de nuevo al fichero CSV, reemplazando el contenido antiguo

https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-jvazlop/blob/d007dc1e7b9243dbd49121c0211d546b1b2ae276/src/main/kotlin/es/iesra/DAO/ReservaVueloDAO.kt#L41-L51
