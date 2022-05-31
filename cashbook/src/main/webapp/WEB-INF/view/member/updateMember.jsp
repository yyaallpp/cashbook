<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMember</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<div class="container">
		<div class="p-3 my-3 ">
			<h1>updateMember</h1>
		</div>
		<div class="row">
			<form class="col-sm-6" action="<%=request.getContextPath()%>/updateMemberController" method="post" >
				<table class="table">
					<tr>
						<td>memberID</td>
						<td><input class="form-control" type="text" name="memberId" value="<%=member.getMemberId()%>" readonly="readonly"></td>
					</tr>
					<tr>
						<td>phone</td>
						<td><input class="form-control" type="text" name="memberPhone"></td>
					</tr>
					<tr>
						<td>email</td>
						<td><input class="form-control" type="text" name="memberEmail"></td>
					</tr>
				</table>
				<button class="btn btn-primary" type="submit">회원정보 수정</button>
			</form>
		</div>
	</div>
</body>
</html>