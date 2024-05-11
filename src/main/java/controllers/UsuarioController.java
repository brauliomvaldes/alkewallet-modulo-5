package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Usuario;

import java.io.IOException;

import dao.UserDAO;

/**
 * Servlet implementation class UsuarioController
 */

@WebServlet(name="UsuarioController", urlPatterns= {"/UsuarioController"})
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsuarioController() {
		super();
		
	}

	 /**
	 * Método POST para autenticar al usuario
	 * emplea DAO para operar con la BBDD
	 * @param  request, response
	 * @return resultado de la autenticación
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 
		// servlet para autenticar el usuario
		// se recuperan las credenciales entregadas
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		// recupera usuario, si no existe el resultado es null
		Usuario usuario = UserDAO.validaUsuario(username, password, session);
		// consulta si el usuario es válido
		if (usuario != null) { // usuario validado
			// redirige a vista/servlet operaciones
			response.sendRedirect("DepositosRetirosServlet");
		}
		// si el usuario no existe se crea vista con el mensaje
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("<html><body>");
		response.getWriter().println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
		response.getWriter().println("<link rel='stylesheet' href='css/style.css'>");
		response.getWriter().println("<body>");
		response.getWriter().println("</body></html>");
		response.getWriter().println("<div class='container-login'>");
		response.getWriter().println("<h1>Usuario no registrado !!</h1>");
		response.getWriter().println("<br>");
		response.getWriter().println("<a class='btn btn-secondary' href='index.jsp'>reintentar</a>"); 
		response.getWriter().println("</div>"); 
		response.getWriter().println("</body></html>");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 
		doPost(request, response);
	}
}
