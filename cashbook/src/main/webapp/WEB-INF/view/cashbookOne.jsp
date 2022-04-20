<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="vo.Cashbook" %>
<%
	Cashbook cashbook = new Cashbook();
	cashbook = (Cashbook)request.getAttribute("cashbookOne");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbookOne</title>
</head>
<body>
	<div>
		<h1>cashbookOne(상세정보)</h1>	
		<table border ="1">
			<thead>
				<th>kind</th>
				<th>cash</th>
				<th>memo</th>
				<th>createDate</th>
			</thead>
			<tbody>
				<tr>
					<td><%=cashbook.getKind()%></td>
					<td><%=cashbook.getCash()%>원</td>
					<td><%=cashbook.getMemo()%></td>
					<td><%=cashbook.getCreateDate()%></td>
				</tr>
			</tbody>
		</table>
		<div>
			<a href="<%=request.getContextPath()%>/DeleteCashbookController?cashbookNo=<%=cashbook.getCashbookNo()%>">삭제</a>
			<a href="<%=request.getContextPath()%>/UpdateCashbookController">수정</a>
		</div>
	</div>
</body>
</html>