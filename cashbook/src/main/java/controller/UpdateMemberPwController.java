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

import dao.MemberDao;
import vo.Member;


@WebServlet("/UpdateMemberPwController")
public class UpdateMemberPwController extends HttpServlet {
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
		response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		System.out.println(sessionMemberId);
		MemberDao memberDao = new MemberDao();
		Member member = new Member();
		member = (Member) memberDao.selectMemberOne(sessionMemberId);
		request.setAttribute("member", member);		
		request.getRequestDispatcher("WEB-INF/view/member/updateMemberPw.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// 로그인 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;

		}
		String currentMemberPw = request.getParameter("currentMemberPw");
		String checkCurrentMemberPw = request.getParameter("checkCurrentMemberPw");
		String changeMemberPw = request.getParameter("changeMemberPw");
		// 디버깅
		System.out.println(sessionMemberId + " <-- sessionMemberId UpdateMemberPwController");
		System.out.println(currentMemberPw + " <-- currentMemberPw UpdateMemberPwController");
		System.out.println(checkCurrentMemberPw + " <-- checkCurrentMemberPw UpdateMemberPwController");
		System.out.println(changeMemberPw + " <-- changeMemberPw UpdateMemberPwController");
		
		MemberDao memberDao = new MemberDao();
		String comparePw = memberDao.compareMemberPw(sessionMemberId, checkCurrentMemberPw);
		System.out.println(comparePw + " <-- comparePw doPost() UpdateMemberPwController");
		if(!comparePw.equals(currentMemberPw)) {
			response.sendRedirect(request.getContextPath()+"/SelectMemberOneController");
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		List<Member> list = memberDao.updateMemberPw(sessionMemberId,changeMemberPw);
		response.sendRedirect(request.getContextPath() + "/LogoutController");
	}

}
