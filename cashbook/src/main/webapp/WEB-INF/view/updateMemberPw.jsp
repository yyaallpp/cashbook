<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMemberPw</title>
</head>
<body>
	<div>
		<%
			Member member = (Member)request.getAttribute("member");
		%>
		<h1>updateMemberPw</h1>
		<form action="<%=request.getContextPath()%>/UpdateMemberPwController" method="post" >
			<table>
				<tr>
					<td>현재 비밀번호</td>
					<td><input type="password" name="currentMemberPw" value="<%=member.getMemberPw()%>" readonly="readonly"></td>
				</tr>
				<tr>
					<td>현재 비밀번호 확인</td>
					<td><input type="password" name="checkCurrentMemberPw"></td>
				</tr>
				<tr>
					<td>변경할 비밀번호 </td>
					<td><input type="password" name="changeMemberPw"></td>
				</tr>
			</table>
			
			<button type="submit">비밀번호 수정</button>
		</form>
	
	</div>
</body>
</html>