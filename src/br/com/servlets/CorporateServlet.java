package br.com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.CorporateDao;
import br.com.model.Corporate;

@WebServlet("/CorporateServlet")
public class CorporateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CorporateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// List Corporates
		CorporateDao corporateDao = new CorporateDao();
		List<Corporate> cosporates = corporateDao.getList();
		request.setAttribute("users", cosporates);

		// Dispatcher to corporates.jsp
		RequestDispatcher rd = request.getRequestDispatcher("corporates.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get parameters corporate
		Corporate corporate = new Corporate();
		corporate.setName(request.getParameter("name"));
		corporate.setRegister(request.getParameter("register"));
		// TODO: get list users "partners"

		CorporateDao corporateDao = new CorporateDao(corporate);
		corporateDao.create();
		response.sendRedirect("CorporateServlet");
	}
}
