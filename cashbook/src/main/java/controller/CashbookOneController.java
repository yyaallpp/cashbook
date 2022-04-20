package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;

@WebServlet("/CashbookOneController")
public class CashbookOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CashbookDao cashbookDao = new CashbookDao();
		// cashbookListByMonth에서 값 받아오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println(cashbookNo + " <-- cashbookNo CashbookOneController");
		Cashbook cashbookOne = cashbookDao.selectCashbook(cashbookNo);
		request.setAttribute("cashbookOne", cashbookOne);
		
		request.getRequestDispatcher("WEB-INF/view/cashbookOne.jsp").forward(request, response);
	}

}
