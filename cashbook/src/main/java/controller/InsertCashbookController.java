package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;
import vo.Cashbook;

@WebServlet("/InsertCashbookController")
public class InsertCashbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 상태 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}			
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		// 디버깅
		System.out.println(year + " <-- InsertCashbookController doGet()");
		System.out.println(month + " <-- InsertCashbookController doGet()");
		System.out.println(day + " <-- InsertCashbookController doGet()");
		String cashDate = year + "-" + month + "-" + day ;
		request.setAttribute("cashDate", cashDate);
		request.getRequestDispatcher("/WEB-INF/view/cashbook/insertCashbookForm.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 상태 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		
		String cashDate = request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");	
		// 디버깅
		System.out.println(cashDate + " <-- cashDate InsertCashbookController doPost()");
		System.out.println(kind + " <--  kind InsertCashbookController doPost()");
		System.out.println(cash + " <-- cash InsertCashbookController doPost()");
		System.out.println(memo + " <-- memo InsertCashbookController doPost()");
		
		Cashbook cashbook = new Cashbook();
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);
		
		List<String> hashtag = new ArrayList<String>();
		String memo2 = memo.replace("#"," #");
		String[] arr = memo2.split(" ");
		for(String s : arr) {
			if(s.startsWith("#")) {
				String temp = s.replace("#", "");
				if(temp.length() > 0) {
					hashtag.add(temp);
				}
			}
		}
		// 디버깅
		System.out.println(hashtag.size() + " <-- hashtag.size() InsertCashbookController");
		for(String h : hashtag) {
			System.out.println(h + " <-- hashtag InsertCashbookController");
		}
		
		CashbookDao cashbookDao = new CashbookDao();
		cashbookDao.insertCashbook(cashbook, hashtag,sessionMemberId);
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
