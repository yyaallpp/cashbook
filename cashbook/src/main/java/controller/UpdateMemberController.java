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

@WebServlet("/updateMemberController")
public class UpdateMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 확인
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
		
		request.getRequestDispatcher("WEB-INF/view/member/updateMember.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		// 값 받아오기
		Member member = new Member();
		member.setPhone(request.getParameter("memberPhone"));
		member.setEmail(request.getParameter("memberEmail"));
		
		// 디버깅
		System.out.println(member.getPhone() + " <-- member.getPhone() doPost() updateMemberController");
		System.out.println(member.getEmail() + " <-- member.getEmail() doPost() updateMemberController");
		
		MemberDao memberDao = new MemberDao();
		memberDao.updateMember(member,sessionMemberId);
		
		response.sendRedirect(request.getContextPath() + "/SelectMemberOneController");
	}

}
