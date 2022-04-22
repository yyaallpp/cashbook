<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.Cashbook" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>udpateCashbookForm</title>
</head>
<body>
	<h1>updateCashbookForm</h1>
	<%
		Cashbook cashbook = (Cashbook)request.getAttribute("cashbook");
	%>
	<form method ="post" action ="<%=request.getContextPath()%>/UpdateCashbookController">
		<input type ="number" name="cashbookNo" value="<%=cashbook.getCashbookNo() %>" readonly="readonly">
		<table border = "1">
			<tr>
				<td>cashDate</td>
				<td>
					<input type = "date" value ="<%=cashbook.getCashDate()%>" name ="cashDate" >
				</td>
			</tr>
			<tr>
				<td>kind</td>
				<td>
					<input type = "radio" name="kind" value="수입">수입 
					<input type = "radio" name="kind" value="지출">지출
				</td>
			</tr>
			<tr>
				<td>cash</td>
				<td>
					<input type = "number" name ="cash" value="<%=cashbook.getCash() %>" >
				</td>
			</tr>
			<tr>
				<td>memo</td>
				<td>
					<textarea rows="5" cols="50" name ="memo"><%=cashbook.getMemo() %></textarea>
				</td>
			</tr>
			<tr>
				<td colspan ="2">
					<button type = "submit">수정</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>