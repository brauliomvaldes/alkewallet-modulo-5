# alkewallet-modulo-5

La evaluación se solicita que el usuario pueda operar con su saldo, realizar operaciones de retiro y depósito de dinero. En base a esta información y en el entendido que se pretende evaluar poner en práctica los contenidos comprendido en el módulo 5, esto es la creación de una aplicación del tipo Web Dynamix Project y que opere con una base de datos mediante la implementación del patrón DAO/MVC, es que decidí crear sólo dos entidades, la clase AuthUsuario para almecenar usuario autenticados y otra clase para operar con el usuario, la clase Usuario, con 3 atributos: id, nombre y saldo, parámetros que entiendo son los mínimos necesarios para probar las funcionalidades requeridas.

JDK 17, tomcat 10.1, conector mysql 8.3.0

Se implementan dos clases para la gestión DAO, con métodos necesarios para realizar las funcionalidades necesarias para operar con la BBDD.

La BBDD se llama "walletmod5", se acompaña script para generar la base de datos y poblar las tablas generadas. 

La clase para la conexión a mysql tiene registrado, como usuario: root y contraseña: 150919

Las credenciales de los usuarios registrados en la base de datos son:

username: marcelo, password: 123

username: braulio, password: 123

Los usuarios inician con un saldo igual a 0 (cero).

Al ingresar a la página principal index.jsp, le solicita autenticarse.

Una vez autenticado, se dirige la vista "operaciones" donde se puede realizar retiro y deposito de dinero contra la cuenta del usuario logeado.

Se valida que el usuario exista en la base de datos

Se valida que no se pueda retirar una suma mayor al saldo

Se valida que no se pueda operar con valores negativos

Se puede hacer logout para operar con otro usuario

Si se abre la app en otro navegador es posible operar simultanemente con ambos o el mismo usuario pero el saldo no se verá reflejado hasta que se realice una operación que refresque la vista inactiva.

No permite utilizar el cache del navegador, de intentar hacerlo se redirige al index para realizar login.

