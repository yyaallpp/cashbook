<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>hashtagDateRank</title>
</head>
<body>
<div class="container">
	<h1>날짜별 검색</h1>
	<a href="<%=request.getContextPath()%>/TagController">hashtag</a><br><br>
	<form method="get" action="<%=request.getContextPath()%>/HashtagDateRankController">
		<table border="1">
			<tr>
				<td>날짜</td>
				<td>
					<input type="date" name="cashDate">
				</td>
				<td>
					<button type="submit">검색</button>
				</td>
			</tr>
		</table>
	</form>
	<%
		if(request.getAttribute("cashDate")!=null) {
			String cashDate = (String)request.getAttribute("cashDate");
			List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
	%>
			<h2 ><%=cashDate%></h2>
			<table >
				<thead>
					<tr>
						<th>ranking</th>
						<th>hashtag</th>
						<th>count</th>
					</tr>
				</thead>
				<tbody>
					<%
						for(Map<String, Object> map : list) {
					%>
							<tr>
								<td><%=map.get("ranking")%></td>
								<td><%=map.get("tag")%></td>
								<td><%=map.get("cnt")%></td>
							</tr>
					<%
						}
					%>
				</tbody>
			</table>
	<%
		}
	%>
</div>
</body>
</html>