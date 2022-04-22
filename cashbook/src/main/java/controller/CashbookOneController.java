package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;
import vo.Cashbook;

@WebServlet("/CashbookOneController")
public class CashbookOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}	
		
		CashbookDao cashbookDao = new CashbookDao();
		// cashbookListByMonth에서 값 받아오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println(cashbookNo + " <-- cashbookNo CashbookOneController");
		Cashbook cashbookOne = cashbookDao.selectCashbook(cashbookNo,sessionMemberId);
		request.setAttribute("cashbookOne", cashbookOne);
		
		request.getRequestDispatcher("WEB-INF/view/cashbookOne.jsp").forward(request, response);
	}

}
