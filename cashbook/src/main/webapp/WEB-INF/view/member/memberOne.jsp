<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne</title>
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<div>

		<h1>SelectMemberOne</h1>
		<table border="1">
			<thead>
				<th>memberId</th>
				<th>memberPw</th>
				<th>createDate</th>
			</thead>
			<tbody>		
				<td><%=member.getMemberId()%></td>
				<td><%=member.getMemberPw()%></td>
				<td><%=member.getCreateDate()%></td>
			</tbody>
		</table>
		<div>
		<a href="<%=request.getContextPath()%>/UpdateMemberPwController">비밀번호수정</a>
		<a href="<%=request.getContextPath()%>/DeleteMemberController">회원탈퇴</a>		
		</div>
	</div>
</body>
</html> 