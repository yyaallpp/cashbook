<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteMember</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>

	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<div class="container">
		<div class="p-3 my-3 ">
			<h1>deleteMember</h1>
		</div>
		<div><h3><%=session.getAttribute("sessionMemberId")%>를 삭제하시겠습니까?</h3></div>
		<form action="<%=request.getContextPath()%>/DeleteMemberController" method="post">
			비밀번호를 입력해주세요 
			<input class="form-control" type="password" name="memberPw">
			<input type="hidden" name="checkMemberPw" value="<%=member.getMemberPw()%>">
			<button class="btn btn-primary" type="submit">탈퇴</button>
		</form>
	</div>
</body>
</html>