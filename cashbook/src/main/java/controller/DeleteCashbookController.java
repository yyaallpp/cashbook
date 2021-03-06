package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;

@WebServlet("/DeleteCashbookController")
public class DeleteCashbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
		response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		// cashbookOne에서 cashbookNo받아오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		// 디버깅
		System.out.println(cashbookNo);	
		CashbookDao cashbookDao = new CashbookDao();
		cashbookDao.deleteCashbook(cashbookNo,sessionMemberId);
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}
	
}
