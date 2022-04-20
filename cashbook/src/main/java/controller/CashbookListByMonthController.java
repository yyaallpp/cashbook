package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;

@WebServlet("/CashbookListByMonthController")
public class CashbookListByMonthController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 월별 cashbook리스트를 요쳥 분석
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // -1된 값으로 나옴 -> 0이 1월이다.
		
		if(request.getParameter("year") != null) {
			year = Integer.parseInt(request.getParameter("year"));
		}
		if(request.getParameter("month") != null) {
			month = Integer.parseInt(request.getParameter("month"));
		}
		if(month == 0) { // 이전 다음을 넣을 때 전 연도 또는 다음 년도로 가기 위해서
			month = 12;
			year -= 1;
		}
		if(month == 13) {
			month = 1;
			year += 1;
		}
		
		// firstDay는 오늘날짜를 구해서 그것을 1롤 바꾼다.
		Calendar firstDay = Calendar.getInstance(); // 오늘날짜에서 1일로 변경
		// 시작시 필요한 공백<TD>의 개수를 구하는 알고리즘 startBlank
		firstDay.set(Calendar.YEAR, year);
		firstDay.set(Calendar.MONTH, month-1);
		firstDay.set(Calendar.DATE, 1);
		int dayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);
		// dayOfWeek 일1,월2, ... 토7
		// startBlank 일0, 월1, .... 토6
		int startBlank = dayOfWeek - 1; // 시작시 필요한 공백
		// 마지막 날짜는 Calnedar api이용
		int endDay = firstDay.getActualMaximum(Calendar.DATE); // firstDay변수의 가장 큰 숫자(날짜)를 구함
		// startBlank와 endDay를 합의 결과에 endBlank를 구해서 7의 배수가 되도록
		int endBlank = 0;
		if((startBlank + endDay) % 7 != 0) {
			// 7에서 startBlank + endDay의 7로 나눈 나머지 값을 빼면된다.
			endBlank = 7 - ((startBlank + endDay) % 7);
		}
		// 4) total TD
		int totalTd = startBlank + endDay + endBlank;
		
		// 디버깅
		System.out.println(year + " <-- year CashbookListByMonthController");
		System.out.println(month + " <-- month CashbookListByMonthController");
		
		/*
		 달력출력에 필요한 모델값(1,2,3,4) + 데이터베이스에서 반환된 모델값(list,출력년도(year, 출력월(year)) + 오늘 날짜(today)
		 */
		
		// 2) 모델 값(월별 리스트)을 반환하는 비지니스 로직(모델)
		CashbookDao cashbookDao = new CashbookDao();
		List<Map<String,Object>> list = cashbookDao.selectCashbookListByMonth(year, month);
		request.setAttribute("startBlank", startBlank);
		request.setAttribute("endDay", endDay);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("totalTd", totalTd);
		
		request.setAttribute("list", list);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		
		// 3) 뷰 포워딩 
		request.getRequestDispatcher("/WEB-INF/view/cashbookListByMonth.jsp").forward(request, response);
	}

}
