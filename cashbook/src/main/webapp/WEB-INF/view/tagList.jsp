<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TagList</title>
</head>
<body>
<%
	List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
%>
<div class="container">
	<h1>hashtag</h1>
	<div>
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController">가계부 돌아가기</a>
		<a href="<%=request.getContextPath()%>/HashtagKindRankController">수입/지출별 검색</a>
		<a href="<%=request.getContextPath()%>/HashtagDateRankController">날짜별 검색</a>
	</div> <br>
	<table border="1">
		<thead>
			<tr>
				<th>rank</th>
				<th>hashtag</th>
				<th>count</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(Map<String,Object> map : list) {
			%>
					<tr>
						<td><%=map.get("rank")%></td>
						<td><a href="<%=request.getContextPath()%>/TagOneController?tag=<%=map.get("tag")%>&tagCount=<%=map.get("cnt")%>"><%=map.get("tag")%></a></td>
						<td><%=map.get("cnt")%></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
</div>
</body>
</html>