<%@ page import="java.util.List"%>
<%@ page import="br.com.model.Corporate"%>

<% List<Corporate> corporates = (List<Corporate>) request.getAttribute("users"); %>

<table border="1">
	<% if (!corporates.isEmpty()) { %>
		<thead>
			<tr>
				<td>Name</td>
				<td>CNPJ</td>
			</tr>
		</thead>
		<tbody>
			<% for (Corporate corporate : corporates) {
				out.println("<tr><td>" + corporate.getName() + "</td>");
				out.println("<td>" + corporate.getRegister() + "</td>");
			} %>
	<% }else{ %>	<!-- if list.isEmpty -->
		<tr><td>Registers not found</td></tr>
	<% } %>	<!-- end if list.isEmpty -->
	</tbody>
</table>