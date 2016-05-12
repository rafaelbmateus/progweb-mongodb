package br.com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.UserDao;
import br.com.model.Account;
import br.com.model.User;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccountServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// List Accounts
		UserDao userDao = new UserDao();
		List<User> users = userDao.getList();
		request.setAttribute("users", users);
		
		// Dispatcher to account.jsp
		RequestDispatcher rd = request.getRequestDispatcher("accounts.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Get parameters user
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setRegister(Integer.parseInt(request.getParameter("register")));
		user.setAge(request.getParameter("age"));
		
		
		// Get parameters Accounts
		Account account = new Account();
		String[] number = request.getParameterValues("number");
		String[] balance = request.getParameterValues("balance");
		String[] limit = request.getParameterValues("limit");
		List<Account> accounts = new ArrayList<Account>();
		for(int i=0; i < number.length; i++){
			account =  new Account();
			account.setNumber(Integer.parseInt(number[i]));
			account.setBalance(Integer.parseInt(balance[i]));
			account.setLimit(Integer.parseInt(limit[i]));
			accounts.add(account);
			user.setAccounts(accounts);
		}
		
		// Create User
		UserDao userDao = new UserDao(user);
		userDao.create();
		
		// Redirect
		response.sendRedirect("AccountServlet");
	}
}
