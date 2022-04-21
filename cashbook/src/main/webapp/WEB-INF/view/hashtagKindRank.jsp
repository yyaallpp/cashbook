<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TagKindSearch</title>
</head>
<body>
<div class="container">
	<h1 >수입/지출별 검색</h1>
	<a href="<%=request.getContextPath()%>/TagController">tags</a><br><br>
	<form method="get" action="<%=request.getContextPath()%>/HashtagKindRankController">
		<table border="1">
			<tr>
				<td>선택</td>
				<td>
					<input type="radio" name="kind" value="수입"> 수입
					<input type="radio" name="kind" value="지출"> 지출
				</td>
				<td>
					<button type="submit">검색</button>
				</td>
			</tr>
		</table>
	</form>
	<%
		if(request.getAttribute("kind")!=null) {
			String kind = (String)request.getAttribute("kind");
			List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
	%>
			<h2><%=kind%></h2>
			<table border="1">
				<thead >
					<tr>
						<th>rank</th>
						<th>hashtag</th>
						<th>count</th>
					</tr>
				</thead>
				<tbody>
					<%
						for(Map<String, Object> map : list) {
					%>
							<tr>
								<td><%=map.get("rank")%></td>
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