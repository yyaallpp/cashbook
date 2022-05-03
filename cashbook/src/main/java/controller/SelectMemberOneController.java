package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;


@WebServlet("/SelectMemberOneController")
public class SelectMemberOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 상태 확인
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인이 되어있지 않으면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}	
		MemberDao memberDao = new MemberDao();	
		Member member = memberDao.selectMemberOne(sessionMemberId);
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/WEB-INF/view/member/memberOne.jsp").forward(request, response);
		
	}

}
