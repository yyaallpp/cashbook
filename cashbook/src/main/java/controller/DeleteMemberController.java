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

@WebServlet("/DeleteMemberController")
public class DeleteMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 상태 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		MemberDao memberDao = new MemberDao();
		Member member = new Member();
		member = (Member) memberDao.selectMemberOne(sessionMemberId);
		request.setAttribute("member", member);		
		request.getRequestDispatcher("WEB-INF/view/deleteMember.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 상태 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		MemberDao memberDao = new MemberDao();
		String memberPw = request.getParameter("memberPw");
		String checkMemberPw = request.getParameter("checkMemberPw");
		// 디버깅
		System.out.println(memberPw + " <-- memberPw DeleteMemberController");
		System.out.println(checkMemberPw + " <-- checkMemberPw DeleteMemberController");
//		
//		if(!memberPw.equals(checkMemberPw)) {
//			response.sendRedirect(request.getContextPath() + "/SelectMemberOneController");
//			return;
//		}		
		memberDao.deleteMember(sessionMemberId);
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
