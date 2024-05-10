# alkewallet-modulo-5

Es una App de tipo Dynamc Web Project en Java Servlet y patrón DAO/MVC

La BBDD se llaman "walletmod5", se crean 2 usuario con las credenciales:

Se acompaña script para generar la base de datos y poblar las tablas generadas. La clase de conexión mysql tiene registrado como usuario: root y contraseña: 150919

Las credaneciales de los usuarios registrados en la base de datos son:

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
No permite utilizar el cache del navegador cuando se retrocede en el navegador, se intentar hacerlo se redirige al index para login.

