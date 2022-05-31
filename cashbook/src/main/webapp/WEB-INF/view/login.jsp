<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>login</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="p-3 my-3 ">
			<h1>login</h1>
		</div>
		<div class="row">
			<form class="col-sm-6" action="<%=request.getContextPath()%>/LoginController" method="post">
				<table class="table">
					<tr>
						<td>memerId</td>	
						<td><input class="form-control" type="text" name="memberId"></td>
					</tr>
					<tr>
						<td>memberPw</td>
						<td><input class="form-control" type="password" name="memberPw" ></td>
					</tr>
				</table>
				<button class="btn btn-primary" type= "submit" >로그인</button>
				<a href="<%=request.getContextPath()%>/InsertMemberController">회원가입</a>
			</form>
		</div>
	</div>
</body>
</html>