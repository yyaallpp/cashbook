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
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<h1>cashbookOne(상세정보)</h1>	
		<div>
			<%=session.getAttribute("sessionMemberId")%>님 반갑습니다.
			<a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
		</div>
		<table class ="table table-bordered">
			<thead class="thead-dark">
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
			<a href="<%=request.getContextPath()%>/UpdateCashbookController?cashbookNo=<%=cashbook.getCashbookNo()%>">수정</a>
		</div>
	</div>
</body>
</html>