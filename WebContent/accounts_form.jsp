<form action="AccountServlet" method="post">
	<div>
		<label>Name:</label>
		<br>
		<input name="name">
		
		<br>
		
		<label>CPF:</label>
		<br>
		<input name="register">
		
		<br> 
		
		<label>Age:</label>
		<br> 
		<input name="age">
	</div>

	<br>

	<table border="0" cellpadding="2" cellspacing="4">
		<tbody>
			<tr>
				<td width="10">Number</td>
				<td>Balance</td>
				<td>Limit</td>
				<td></td>
			</tr>
			<tr class="linhas">
				<td><input type="text" name="number"></td>
				<td><input type="text" name="balance"></td>
				<td><input type="text" name="limit"></td>
				<td><a href="#" class="removerCampo" title="Delete">Delete</a></td>
			</tr>
			<tr>
				<td colspan="4"><a href="#" class="adicionarCampo" title="Adicionar item">New</a></td>
			</tr>
			<tr>
				<td align="right" colspan="4">
					<button type="submit">Submit</button>
				</td>
			</tr>
		</tbody>
	</table>
</form>