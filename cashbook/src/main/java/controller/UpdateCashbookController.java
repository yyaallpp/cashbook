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
import vo.Member;

@WebServlet("/UpdateCashbookController")
public class UpdateCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// 로그인 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
		response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		// cashbookOne에서 값 받아오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println(cashbookNo + " <-- cashbookNo UdateCashbookController doGet()"); // 디버깅
		CashbookDao cashbookDao = new CashbookDao();
		Cashbook cashbook = new Cashbook();
		cashbook= cashbookDao.selectCashbook(cashbookNo,sessionMemberId); 
		request.setAttribute("cashbook", cashbook);
		request.getRequestDispatcher("WEB-INF/view/updateCashbookForm.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
		response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}			
		request.setCharacterEncoding("UTF-8"); // 한글 인코딩
		
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo")); 
		String cashDate = request.getParameter("cashDate"); 
		String kind = request.getParameter("kind");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");
		
		// 디버깅 
		System.out.println(cashbookNo + " <-- cashBookNo UpdateCashbookController doPost()");
		System.out.println(cashDate + " <-- cashDate UpdateCashbookController doPost()");
		System.out.println(kind + " <-- kind UpdateCashbookController doPost()");
		System.out.println(cash + " <-- cash UpdateCashbookController doPost()");
		System.out.println(memo + " <-- memo UpdateCashbookController doPost()");
				
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
		System.out.println(hashtag.size() + " <-- hashtag.size() UpdateCashbookController");
		for(String h : hashtag) {
			System.out.println(h + " <-- hashtag UpdateCashbookController");
		}
		Cashbook cashbook = new Cashbook();
		cashbook.setCashbookNo(cashbookNo);
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);
		
		CashbookDao cashBookDao = new CashbookDao();
		cashBookDao.updateCashbook(cashbook,hashtag,sessionMemberId);
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		
	}
	
}
