package br.com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.UserDao;
import br.com.model.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// List User
		UserDao userDao = new UserDao();
		List<User> users = userDao.getList();
		request.setAttribute("users", users);
		
		// Dispatcher to users.jsp
		RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Get parameters
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setRegister(Integer.parseInt(request.getParameter("register")));
		user.setAge(request.getParameter("age"));
		
		// Create User
		UserDao userDao = new UserDao(user);
		userDao.create();
		
		// Redirect
		response.sendRedirect("UserServlet");
	}
}
