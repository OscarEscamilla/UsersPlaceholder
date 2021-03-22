
# EVALUACIÓN TÉCNICA NUXIBA #

Prueba: **MOBILE DEVELOPER**

Deadline: **1 día**

Nombre: **Oscar Escamilla Luqueño**

------
## Clona y crea tu repositorio para la evaluación ##
* Clona este repositorio en tu máquina local
* Crear un repositorio público en tu cuenta personal de GitHub, BitBucket o Gitlab
* Cambia el origen remoto para que apunte al repositorio público que acabas crear en tu cuenta
* Coloca tu nombre en este archivo README.md y realiza un push al repositorio remoto

------
## Prueba ##
* Lee la documentación del API de [JSONPlaceholder](http://jsonplaceholder.typicode.com/guide/) y crea una aplicación móvil nativa en **iOS** o **Android** (dentro del directorio **Prueba**) que consuma los end-poins necesarios para realizar lo siguiente:
	* Listar a los 10 usuarios **_(15 puntos)_**
	* Al seleccionar un usuario muestra algunos campos con su información(name, username, email, etc.) y coloca 2 botones para poder seleccionar los "posts" y "todos" que estén relacionados con el usuario. **_(15 puntos)_**
	* Al dar click en el botón de "posts" muestra todas las publicaciones que ha realizado el usuario, cada publicación deberá tener anidados sus comentarios. **_(20 puntos)_**
	* Al dar click en el botón de "todos" muestra las tareas del usuario ordenadas por la propiedad "id" de mayor a menor. **_(20 puntos)_**
	* En la sección de "todos", crea un formulario para poder agregar una nueva tarea al usuario, este debe de contener una caja de texto (title), un checkbox (completed) y un botón de guardar. Al dar click en el botón, manda la información necesaria al API con el método HTTP correcto para que la tarea quede guardada. **_(30 puntos)_**

> *Nota: al hacer la petición de la nueva tarea, el API no la guardará en alguna base de datos y solo regresará un objeto JSON con la propiedad **id** de la nueva tarea agregada (id: 201), esto indica que todo se realizó de forma correcta*

**PLUS: Si agregas el objeto de la tarea creada a la sección de "todos" listada anteriormente, obtendrás** **_(+ 5 puntos)_**

Algunos endpoints que puedes utilizar:

* https://jsonplaceholder.typicode.com/users 
* https://jsonplaceholder.typicode.com/users/(userId)
* https://jsonplaceholder.typicode.com/users/(userId)/posts
* https://jsonplaceholder.typicode.com/post/(postId)/comments
* https://jsonplaceholder.typicode.com/users/(userId)/todos

Objeto que espera el servidor para guardar la nueva tarea:


```javascript
{
  "userId": <int>,
  "title": <string>
  "completed": <bool>
}
```

**PLUS: Si conoces algún patrón de diseño de software no dudes en usarlo** **_(+ 10 puntos)_**

------
### Realiza el push del código de tus pruebas y compártenos el link a tu repositorio remoto 😊 

------
Si tienes alguna duda sobre la evaluación puedes mandar un correo electrónico a [Rodrigo Acevedo](mailto:racevedo@nuxiba.com?subject=Dudas%20sobre%20evaluación%20técnica)

Manda la liga de tu repositorio público a [Verónica Llerenas](mailto:vllerenas@nuxiba.com?subject=[EvaluaciónDesarrollo]%20Este%20es%20mi%20repositorio)