# Caso Practico 2  Tercer Examen Complexivo ISTA
<img src="https://www.tecazuay.edu.ec/assets/img/logo%20desenfocado4.png">


## Tabla de Contenidos
1. [Información General](#general-info)
2. [Tecnologias Implementadas](#technologies)
3. [Instalación](#installation)
4. [Coolaboradores](#collaboration)

[//]: # (5. [FAQs]&#40;#faqs&#41;)

### Información General
Este proyecto fue desarrollado como la parte practica de el tercer examen complexoivo para la carrera de Desarrollo de Software del Instituto Superior Tecnologico del Azuay

El objetivo del proyecto es automatizar el proceso de Practicas PreProfesionales de los estudiantes del ISTA en todas las carreras desde el momento en que se genera el convenio con
las empresas hasta el momento en que se genera el informe final de aprobación para los estudiantes, ademas el proyecto incluye una pagina web informativa para dar una introducción a todos los actores acerca de
la forma de desarrollar el proceso de Practicas PreProfesionales en el ISTA

El flujo del programa consta de de 4 fases `1.-Firma de Convenios`, `2.-Postulación`, `3.-Desarrollo de Prácticas`, `4.-Acreditación` las mismas que fueron subdivididas en 7 modulos para su mejor desarrollo
`Página web informativa APP móvil informativa`
`Gestión de coordinación de Vinculación`
`Gestión de coordinación de carrera:`
`Gestión Empresa:`
`Gestión de Responsable de PPP`
`Gestión de Tutor académico`
`Gestión Estudiantes`


### Tecnologias Implementadas
El proyecto se realizó con el Framework de SpringBoot con el objetivo de crear microservicios que luego seran cosnumidos por la aplicacion FrontEnd, en el proceso de desarrollo
se implemeto Spring Security el cual es un marco de autenticacion y control de acceso potente y altamente personalizable para poder proteger las aplicaciones basadas en Spring y para complementar la seguridad del sistema tambien se incluye una autenticación basada en tokens.

Las conexiones realizadas para el correcto funcionamiento del sistema son a dos bases de datos, la primera a una base de datos local que contiene un BackUp de la base de datos Fenix con una copia restringuida de los datos del ISTA del 2021 y otra conexión a una base de datos 
en un servidor de Heroku la cual almacena toda la información generada en el flujo del programa.

Para la documentación y pruebas se utiliza Swagger con su interfaz grafica `http://localhost:8080/swagger-ui.html`.

Los emails con los cuales se puede ingresar al sistema son autenticados con servicios externos, de esta forma es posible ingresar haciendo uso de el loggin por google siempre que el correo pertenezca a la comunidad del `@tecazuay.edu.ec`.

### Instalación
El primer paso para correr el proyecto es generar una base de datos y realizar un restore desde el backUp de la base de datos Fenix que se encuentra en el siguiente enlace de Drive
```
https://drive.google.com/file/d/1PncbfHx8BpmOzzOKkX0-T759vn9A0ZwS/view?usp=sharing
```

La segunda base de datos al estar en el servidor de Heroku no requiere de ningun proceso extra luego de correr la aplicación.

En caso de querer correr la aplicación con ambas bases de datos en local debemos descomentar la coexion local, verificar las credenciales y el nombre de la base de datos que almacenara los datos del proyecto, en dicho caso se debe incluir los siguientes 
SCRIPTS que garantizan el funcionamiento del loggin y crean la cuenta del super-usuario que puede generar los roles superiores como Coordinador de Carrera y Coordinador de Vinculación, los demas roles seran asignados de manera automatica. 
```
INSERT INTO public.roles(
	id, codigo, descripcion)
	VALUES (1,'AUT', 'Autoridad'),
	(2,'DOC', 'Docente'),
	(3,'DA', 'Tutor Academico'),
	(4,'EST', 'Alumno'),
	(5,'CC', 'Coordinador de Carrera'),
	(6,'RPPP', 'Responsable Practicas Pre Profesionales'),
	(7,'CV', 'Coordinador de Vinculacion');
```

```
INSERT INTO public.autoridades(
	id, cedula, estado, fecha_creacion, usuario_id)
	VALUES (1,'0105035828','true','04/06/2022', null);
```

Con esa configuración el programa estara listo para realizar los procesos solicitados desde el FrontEnd o atravez de la interfaz de Swagger.
### Coolaboradores
En el desarrollo de este proyecto participaron 10 integrantes:
* Leonardo Guartambel
* Daniel Barros
* Anthony Cardenas
* Jose Contreras
* Ana Medina
* Oscar Cardenas
* Darwin Tacury
* Cisne Ludisaca
* Fernando Andrade
* Darwin González
