package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;

@WebServlet("/DeleteCashbookController")
public class DeleteCashbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// cashbookOne에서 cashbookNo받아오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		// 디버깅
		System.out.println(cashbookNo);	
		CashbookDao cashbookDao = new CashbookDao();
		cashbookDao.deleteCashbook(cashbookNo);
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}
	
}
