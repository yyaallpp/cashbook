<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteMember</title>
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<div>
		<h1>deleteMember</h1>
		<div><%=session.getAttribute("sessionMemberId")%>를 삭제하시겠습니까?</div>
		<form action="<%=request.getContextPath()%>/DeleteMemberController" method="post">
			비밀번호를 입력해주세요 
			<input type="password" name="memberPw">
			<input type="hidden" name="checkMemberPw" value="<%=member.getMemberPw()%>">
			<button type="submit">탈퇴</button>
		</form>
	</div>
</body>
</html>