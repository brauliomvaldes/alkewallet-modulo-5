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
 * Servlet implementation class OperacionesController
 */

@WebServlet(name="OperacionesController", urlPatterns={"/OperacionesController"})
public class OperacionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OperacionesController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Get-Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("idusuario") == null || request.getParameter("idUsuarioWeb") == null) {
				response.sendRedirect("index.jsp");
			}
			String operacion = request.getParameter("operacion");
			double amount = Integer.parseInt(request.getParameter("amount"));
			int idUsuarioWeb = Integer.parseInt(request.getParameter("idUsuarioWeb"));
			
			if (operacion != null && amount > 0) {
				int idUsuario = (int)session.getAttribute("idusuario");
				if(idUsuario == idUsuarioWeb) {					
					if (operacion.equals("add")) {
						UserDAO.depositar(idUsuario, amount, session);
					} else if (operacion.equals("subtract")) {
						UserDAO.retirar(idUsuario, amount, session);
					}
					response.sendRedirect("DepositosRetirosServlet");
				}else {
					response.sendRedirect("index.jsp");  // formulario no corresponde con el login
				}
				//RequestDispatcher dispatcher = request.getRequestDispatcher("UsuarioController");
				//dispatcher.forward(request, response);
			} else {
				session.setAttribute("mensaje", "");
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().println("<html><body>");
				response.getWriter().println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
				response.getWriter().println("<link rel='stylesheet' href='css/style.css'>");
				response.getWriter().println("<body>");
				response.getWriter().println("</body></html>");
				response.getWriter().println("<div class='container-login'>");
				response.getWriter().println("<h1>Operación no válida !!</h1>");
				response.getWriter().println("<br>");
				response.getWriter().println("<a class='btn btn-secondary' href='DepositosRetirosServlet'>reintentar</a>"); 
				response.getWriter().println("</div>"); 
				response.getWriter().println("</body></html>");
				response.getWriter().append("Served at: ").append(request.getContextPath());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			doGet(request, response);
		}
	}
}
