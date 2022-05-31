<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMemberPw</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<%
			Member member = (Member)request.getAttribute("member");
		%>
		<div class="p-3 my-3 ">
			<h1>updateMemberPw</h1>
		</div>
		
		<div class="row">
			<form class="col-sm-6" action="<%=request.getContextPath()%>/UpdateMemberPwController" method="post" >
				<table class="table">
					<tr>
						<td>현재 비밀번호</td>
						<td><input class="form-control" type="password" name="currentMemberPw" value="<%=member.getMemberPw()%>" readonly="readonly"></td>
					</tr>
					<tr>
						<td>현재 비밀번호 확인</td>
						<td><input class="form-control" type="password" name="checkCurrentMemberPw"></td>
					</tr>
					<tr>
						<td>변경할 비밀번호 </td>
						<td><input class="form-control" type="password" name="changeMemberPw"></td>
					</tr>
				</table>
				<button class="btn btn-primary" type="submit">비밀번호 수정</button>
			</form>
		</div>
	</div>
</body>
</html> 