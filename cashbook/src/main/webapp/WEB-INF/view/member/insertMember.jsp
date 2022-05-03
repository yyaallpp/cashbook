<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertMember</title>
</head>
<body>
	<div>
		<h1>insertMember</h1>
		<form action="<%=request.getContextPath()%>/InsertMemberController" method = "post" >
			<table>
				<tr>
					<td>memberId</td>
					<td>
						<input type="text" name="memberId" >
					</td>
				</tr>
				<tr>
					<td>password</td>
					<td>
						<input type="password" name="memberPw" >
					</td>
				</tr>
				<tr>
					<td>phone</td>
					<td>
						<input type="text" name="phone" >
					</td>
				</tr>
				<tr>
					<td>email</td>
					<td>
						<input type="email" name="email" >
					</td>
				</tr>
			</table>
			<button type="submit">회원가입</button>
		</form>
	</div>
</body>
</html> 