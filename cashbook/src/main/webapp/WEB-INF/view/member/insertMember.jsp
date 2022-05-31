<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertMember</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="p-3 my-3 ">
			<h1>insertMember</h1>
		</div>
		<div class="row">
			<form class="col-sm-6" action="<%=request.getContextPath()%>/InsertMemberController" method="post" >
				<table class="table">
					<tr>
						<td>memberId</td>
						<td>
							<input class="form-control" type="text" name="memberId" >
						</td>
					</tr>
					<tr>
						<td>password</td>
						<td>
							<input class="form-control" type="password" name="memberPw" >
						</td>
					</tr>
					<tr>
						<td>phone</td>
						<td>
							<input class="form-control" type="text" name="phone" >
						</td>
					</tr>
					<tr>
						<td>email</td>
						<td>
							<input class="form-control" type="email" name="email" >
						</td>
					</tr>
				</table>
				<button class="btn btn-primary" type="submit">회원가입</button>
			</form>
		</div>
	</div>
</body>
</html> 