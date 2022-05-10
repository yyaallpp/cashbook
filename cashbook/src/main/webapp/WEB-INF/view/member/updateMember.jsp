<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMember</title>
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<h1>updateMember</h1>
	<form action="<%=request.getContextPath()%>/updateMemberController" method="post" >
			<table>
				<tr>
					<td>memberID</td>
					<td><input type="text" name="memberId" value="<%=member.getMemberId()%>" readonly="readonly"></td>
				</tr>
				<tr>
					<td>phone</td>
					<td><input type="text" name="memberPhone"></td>
				</tr>
				<tr>
					<td>email</td>
					<td><input type="text" name="memberEmail"></td>
				</tr>
			</table>

			<button type="submit">회원정보 수정</button>
		</form>
</body>
</html>