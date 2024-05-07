package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coneccionDB.Coneccion;
import jakarta.servlet.http.HttpSession;
import models.Usuario;

public class UserDAO {

	public static Usuario validaUsuario(String username, String password, HttpSession session) {
		try {
			Coneccion conn = Coneccion.getInstance();// establece una coneción a la DB
			String consulta = "SELECT id_usuario FROM authusuarios WHERE username = ? AND password = ?";
			PreparedStatement preparedStatement = conn.getConeccion().prepareStatement(consulta);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultado = preparedStatement.executeQuery();// consulta la DB
			while (resultado.next()) {// si existe
				int idUsuario = resultado.getInt("id_usuario");
				Usuario usuario = recuperaUsuario(idUsuario, conn);
				if(usuario != null) {					
					session.setAttribute("idusuario", usuario.getId());
					session.setAttribute("nombre", usuario.getNombre());
					session.setAttribute("saldo", usuario.getBalance());
					session.setAttribute("mensaje", "");
					return usuario;
				}
			}
		} catch (SQLException exceptionSql) {
			exceptionSql.printStackTrace();
		} 
		return null;
	}

	private static Usuario recuperaUsuario(int idUsuario, Coneccion conn) {
		try {
			String consulta = "SELECT nombre, balance FROM usuarios WHERE id = ?";
			PreparedStatement preparedStatement = conn.getConeccion().prepareStatement(consulta);
			preparedStatement.setInt(1, idUsuario);
			ResultSet resultado = preparedStatement.executeQuery();// consulta la DB
			while (resultado.next()) {// si existe
				Usuario usuario = new Usuario();
				usuario.setId(idUsuario);
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setBalance(BigDecimal.valueOf(resultado.getDouble("balance")));
				return usuario; // retorna el usuario correspondiente
			}
		} catch (SQLException exceptionSql) {
			exceptionSql.printStackTrace();
		} 
		return null;
	}

	public static boolean depositar(int idUsuario, double amount, HttpSession session) {
		try {
			Coneccion conn = Coneccion.getInstance();// establece una coneción a la DB
			// recupera el saldo actual del usuario para validar disponible
			Usuario usuario = recuperaUsuario(idUsuario, conn);
			if (usuario != null) {
				BigDecimal balance = usuario.getBalance();

				// existe saldo disponible
				balance = balance.add(BigDecimal.valueOf(amount));
				String consulta = "UPDATE usuarios SET balance = balance + ? WHERE id = ?";
				PreparedStatement preparedStatement = conn.getConeccion().prepareStatement(consulta);
				preparedStatement.setDouble(1, amount);
				preparedStatement.setInt(2, idUsuario);
				int filasAfectadas = preparedStatement.executeUpdate();// ejecuta cambio en la DB
				if (filasAfectadas > 0) {
					session.removeAttribute("saldo");
					session.setAttribute("saldo", balance);
					session.setAttribute("mensaje", "Depósito de dinero efectuado existosamente");
					return true;
				}
			} else {
				return false;
			}
		} catch (SQLException exceptionSql) {
			exceptionSql.printStackTrace();
		} 
		return false;
	}

	public static boolean retirar(int idUsuario, double amount, HttpSession session) {
		try {
			Coneccion conn = Coneccion.getInstance();// establece una coneción a la DB
			// recupera el saldo actual del usuario para validar disponible
			Usuario usuario = recuperaUsuario(idUsuario, conn);
			if (usuario != null) {
				BigDecimal balance = usuario.getBalance();
				if (balance.compareTo(BigDecimal.valueOf(amount)) >= 0) {
					balance = balance.subtract(BigDecimal.valueOf(amount));
					// existe saldo disponible
					String consulta = "UPDATE usuarios SET balance = balance - ? WHERE id = ?";
					PreparedStatement preparedStatement = conn.getConeccion().prepareStatement(consulta);
					preparedStatement.setDouble(1, amount);
					preparedStatement.setInt(2, idUsuario);
					int filasAfectadas = preparedStatement.executeUpdate();// consulta la DB
					if (filasAfectadas > 0) {
						//session.removeAttribute("saldo");
						session.setAttribute("saldo", balance);
						session.setAttribute("mensaje", "Retiro de dinero efectuado existosamente");
						return true;
					}
				} else {
					session.setAttribute("mensaje", "El monto ingresado es superior al saldo disponible");
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException exceptionSql) {
			exceptionSql.printStackTrace();
		} 
		return false;
	}

	public static void usuarioLogout() {
		Coneccion.cerrar();
	}
}
