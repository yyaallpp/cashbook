<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>cashbookListByMonth</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid">
		<h1>cashbookListByMonth</h1>
		<div>
			<a href="<%=request.getContextPath()%>/TagController?">tags</a>
		</div>
		<%
			List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
			int year = (Integer)request.getAttribute("year");
			int month = (Integer)request.getAttribute("month");
			int startBlank = (Integer)request.getAttribute("startBlank");
			int endDay = (Integer)request.getAttribute("endDay");
			int endBlank = (Integer)request.getAttribute("endBlank");
			int totalTd = (Integer)request.getAttribute("totalTd");
			
			// 디버깅
			System.out.println(list.size()+" <-- list.size() cashbookListByMonth.jsp");
			System.out.println(year +" <--  year cashbookListByMonth.jsp");
			System.out.println(month +" <--  month cashbookListByMonth.jsp");
			System.out.println(startBlank +" <--  startBlank cashbookListByMonth.jsp");
			System.out.println(endDay +" <--  endDay cashbookListByMonth.jsp");
			System.out.println(endBlank +" <--  endBlank cashbookListByMonth.jsp");
			System.out.println(totalTd +" <--  totalTd cashbookListByMonth.jsp");
			System.out.println();
		%>
		<h2><%=year%>년 <%=month%>월</h2>
		
		<!-- 
			1) 이번달 1일의 요일 firstDayYoil
			   요일 -> startBlank가 필요하다. -> 일요일 0 월요일 1 화요일 2
			3) 이번달 마지막 날짜 endDay
			4) endlBlank -> totalBlank
			5) td의 개수 1 ~ totalBlank
			6) cashbook list
			7) 오늘 날짜
		 -->
		
		<div>
			<a href="<%=request.getContextPath()%>/CashbookListByMonthController?year=<%=year%>&month=<%=month-1%>">이전달</a>
			<a href="<%=request.getContextPath()%>/CashbookListByMonthController?year=<%=year%>&month=<%=month+1%>">다음달</a>
		</div>
		
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>일</th>
					<th>월</th>
					<th>화</th>
					<th>수</th>
					<th>목</th>
					<th>금</th>
					<th>토</th>
				</tr>
			</thead>
			<tbody>			
				<tr>
					<%
						for(int i = 1; i <= totalTd ; i++) {
							if((i-startBlank) > 0 && (i-startBlank) <= endDay){
								String c = "";
								if(i % 7 == 0){
									c = "text-primary";
								} else if(i % 7 == 1){
									c = "text-danger";
								}
					%>
								<td class ="<%=c%>"> 
									<%=i-startBlank%> 
									<a href="<%=request.getContextPath()%>/InsertCashbookController?year=<%=year%>&month=<%=month%>&day=<%=i-startBlank%>" class="btn btn-light">입력</a> 
									<div>
										<%
											// 해당 날짜의 cashbookList 출력 
											 for(Map m : list){
												 if((Integer)m.get("day") == (i-startBlank)){
										%>
													<div>
														<a href ="<%=request.getContextPath()%>/CashbookOneController?cashbookNo=<%=m.get("cashbookNo")%>">
															[<%=m.get("kind")%>] 
															<%=m.get("cash")%>원
															<%=m.get("memo")%>...												
														</a> 
													</div>
										<%			 
												 }
											 }
										%>
									</div>
								</td>
					<% 							
						} else{
					%>
							<td>&nbsp;</td>
					<%
						}
						if(i < totalTd && i % 7 == 0){
							
					%>
						<tr></tr> <!-- 새로운 행을 추가 -->
					<%
							}
						}
					%>
				</tr>
			</tbody>
		</table>		
	</div>
</body>
</html>