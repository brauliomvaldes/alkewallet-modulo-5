<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css" />
<title>Operaciones Wallet</title>
</head>
<body>
	<div class="container">

		<div class="col-1">
			<a class="btn btn-secondary" href="LogoutServlet">logout</a>
		</div>
		<h1>Operaciones Wallet</h1>
		<h2>Bienvenido ${sessionScope.nombre}</h2>
		<h3>Su Saldo Disponible es $ ${sessionScope.saldo}</h3>

		<form method="post" action="OperacionesController">
			<div class="container-login">
				<label>Ingrese el monto de la operacion $</label> <input type="number"
					name="amount" required>
			</div>
			<div class="container-login">
				<label>Defina el tipo de operación</label> <select name="operacion">
					<option value="add">Depositar Dinero</option>
					<option value="subtract">Retirar Dinero</option>
				</select>
				<button type="submit" class="btn btn-success">Procesar</button>
			</div>
		</form>
		<br> <br> <br> <br>
		<hr>
		<div>
			<p>
			<h4>${sessionScope.mensaje}</h4>
			</p>
		</div>
	</div>
</body>
</html>