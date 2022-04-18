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
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1; // -1된 값으로 나옴 -> 0이 1월이다.
		
		if(request.getParameter("year") != null) {
			year = Integer.parseInt(request.getParameter("year"));
		}
		if(request.getParameter("month") != null) {
			month = Integer.parseInt(request.getParameter("month"));
		}
		if(month == 0) {
			month = 12;
			year -= 1;
		}
		if(month == 13) {
			month = 1;
			year += 1;
		}
		// 디버깅
		System.out.println(year + " <-- year CashbookListByMonthController");
		System.out.println(month + " <-- month CashbookListByMonthController");
		
		// 2) 모델 값(월별 리스트)을 반환하는 비지니스 로직(모델)
		CashbookDao cashbookDao = new CashbookDao();
		List<Map<String,Object>> list = cashbookDao.selectCashbookListByMonth(year, month);
		request.setAttribute("list", list);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		
		// 3) 뷰 포워딩 
		request.getRequestDispatcher("/WEB-INF/view/cashbookListByMonth.jsp").forward(request, response);
	}

}
