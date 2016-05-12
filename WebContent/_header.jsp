<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank - MongoDB</title>

<!-- Styles - start -->
<style type="text/css">
ul#menu li {
	display: inline;
}
</style>
<!-- Styles - end -->

<!-- Scripts - start -->
<script type="text/javascript" src="resources/js/jquery-2.2.2.min.js"></script>
<script type="text/javascript">
$(function () {
  function removeCampo() {
	$(".removerCampo").unbind("click");
	$(".removerCampo").bind("click", function () {
	   if($("tr.linhas").length > 1){
		$(this).parent().parent().remove();
	   }
	});
  }
 
  $(".adicionarCampo").click(function () {
	novoCampo = $("tr.linhas:first").clone();
	novoCampo.find("input").val("");
	novoCampo.insertAfter("tr.linhas:last");
	removeCampo();
  });
});
</script>

<script language="JavaScript">
<% if (session.getAttribute("email") == null){ %>
	window.location="login.jsp";
<% } %>
</script>
<!-- Scripts - end -->

</head>
<body>

	<ul id="menu">
		<li><a href="UserServlet">Users</a></li>
		<li><a href="CorporateServlet">Corporates</a></li>
		<li><a href="AccountServlet">Account</a></li>
		<li><a href="SessionServlet?action=logout">Logout</a></li>
	</ul>