package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/InsertMemberController")
public class InsertMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId != null) { // 로그인이 되어있다면 
			response.sendRedirect(request.getContextPath() + "/CashbookListByMonthController");
			return;
		}
		request.getRequestDispatcher("WEB-INF/view/member/insertMember.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId != null) { // 로그인이 되어있다면 
			response.sendRedirect(request.getContextPath() + "/CashbookListByMonthController");
			return;
		}
		
		// inserMember.jsp에서 값 받아오기
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));	
		member.setPhone(request.getParameter("phone"));	
		member.setEmail(request.getParameter("email"));	
		//디버깅
		System.out.println(member.getMemberId() + " <-- memeberId InsertMemberController");
		System.out.println(member.getMemberPw() + " <-- memeberPw InsertMemberController");
		System.out.println(member.getPhone() + " <-- phone InsertMemberController");
		System.out.println(member.getEmail() + " <-- eamil InsertMemberController");
		MemberDao memberDao = new MemberDao();
		
		memberDao.insertMemeber(member);
		response.sendRedirect(request.getContextPath() + "/LoginController");
		
	}

}
