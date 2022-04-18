<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>cashbookListByMonth</title>
</head>
<body>
	<h1>cashbookListByMonth</h1>
	<%
		int year = (Integer)request.getAttribute("year");
		int month = (Integer)request.getAttribute("month");
	%>
	<h2><%=year%>년 <%=month%>월</h2>
	<table border ="1">
		<tr>
			<th>day</th>
			<th>kind</th>
			<th>cash</th>
		</tr>
		<%
			List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
			for(Map m : list) {
		%>
			<tr>
				<td><%=m.get("day") %></td>
				<td><%=m.get("kind") %></td>
				<td><%=m.get("cash") %></td>
			</tr>
		<%
			}
		%>
	</table>
	<div>
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?year=<%=year%>&month=<%=month-1%>">이전</a>
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?year=<%=year%>&month=<%=month+1%>">다음</a>
	</div>
</body>
</html>