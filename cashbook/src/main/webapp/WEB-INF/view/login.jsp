<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>login</title>
</head>
<body>
	<h1>login</h1>
	<form action="<%=request.getContextPath()%>/LoginController" method="post">
		<table border="1">
			<tr>
				<td>memerId</td>	
				<td><input type="text" name="memberId"></td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit">로그인</button>
	</form>
	<div>
		<a href="<%=request.getContextPath()%>/InsertMemberController">회원가입</a>
	</div>
</body>
</html>