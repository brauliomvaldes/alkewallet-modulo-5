package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dao.UserDAO;

/**
 * Servlet implementation class Logout
 */

@WebServlet(name="LogoutServlet", urlPatterns= {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();

    }
	/**
	 * Método GET para hacer logout
	 *
	 * @param  request, response
	 * @return redirige a la vista index donde se realiza el login 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// recupera la session del usuario
		// 
		HttpSession session = request.getSession(false);
		// si la session existe
		if(session != null) {
			// si la variable que almacena el id del usuario existe, la remueve
			if(session.getAttribute("idusuario") != null) {
				session.removeAttribute("idusuario");
			}
		}
		// desconecta 
		UserDAO.usuarioLogout();  // desconecta la BD
		// se redirige a index para el login
		response.sendRedirect("index.jsp");
	}

}
