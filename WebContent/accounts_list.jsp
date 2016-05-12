<%@ page import="java.util.List"%>
<%@ page import="br.com.model.User"%>
<%@ page import="br.com.model.Account"%>

<% List<User> users = (List<User>) request.getAttribute("users"); %>

<table border="1">
	<% if (!users.isEmpty()) { %>
		<thead>
			<tr>
				<td>Name</td>
				<td>CPF</td>
				<td>Age</td>
				<td>Account Number</td>
				<td>Balance</td>
				<td>Limit</td>
			</tr>
		</thead>
		<tbody>
			<% for (User user : users) {
				out.println("<tr>");
				out.println("<td width=\"200\">" + user.getName() + "</td>");
				out.println("<td width=\"150\">" + user.getRegister() + "</td>");
				out.println("<td width=\"50\">" + user.getAge() + "</td>");
				out.println("<td></td><td></td><td></td>");
				for(Account account : user.getAccounts()){
					out.println("<tr>");
					out.println("<td></td><td></td><td></td>");
					out.println("<td width=\"100\">" + account.getNumber() + "</td>");
					out.println("<td width=\"100\">" + account.getNumber() + "</td>");
					out.println("<td width=\"100\">" + account.getBalance() + "</td>");
					out.println("</tr>");
				}
				out.println("</tr>");
			} %>
	<% }else{ %>	<!-- if list.isEmpty -->
		<tr><td>Registers not found</td></tr>
	<% } %>	<!-- end if list.isEmpty -->
	</tbody>
</table>