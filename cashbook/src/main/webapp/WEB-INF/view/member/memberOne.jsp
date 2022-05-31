<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<div class="container">
		<h1>SelectMemberOne</h1>
		<table class="table table-bordered">
			<thead>
				<th>memberId</th>
				<th>memberPw</th>
				<th>phone</th>
				<th>email</th>
				<th>createDate</th>
			</thead>
			<tbody>		
				<td><%=member.getMemberId()%></td>
				<td><%=member.getMemberPw()%></td>
				<td><%=member.getPhone()%></td>
				<td><%=member.getEmail()%>
				<td><%=member.getCreateDate()%></td>
			</tbody>
		</table>
		<div>
		<a href="<%=request.getContextPath()%>/updateMemberController">회원정보 수정</a>
		<a href="<%=request.getContextPath()%>/UpdateMemberPwController">비밀번호수정</a>
		<a href="<%=request.getContextPath()%>/DeleteMemberController">회원탈퇴</a>		
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController">HOME</a>	
		</div>
	</div>
</body>
</html> 