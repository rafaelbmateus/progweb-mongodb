<%@ page import="java.util.List"%>
<%@ page import="br.com.model.User"%>

<% List<User> users = (List<User>) request.getAttribute("users"); %>

<table border="1">
	<% if (!users.isEmpty()) { %>
		<thead>
			<tr>
				<td>Name</td>
				<td>CPF</td>
				<td>Age</td>
			</tr>
		</thead>
		<tbody>
			<% for (User user : users) {
				out.println("<tr><td>" + user.getName() + "</td>");
				out.println("<td>" + user.getRegister() + "</td>");
				out.println("<td>" + user.getAge() + "</td></tr>");
			} %>
	<% }else{ %>	<!-- if list.isEmpty -->
		<tr><td>Registers not found</td></tr>
	<% } %>	<!-- end if list.isEmpty -->
	</tbody>
</table>