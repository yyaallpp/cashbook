package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;

@WebServlet("/HashtagKindRankController")
public class HashtagKindRankController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(request.getParameter("kind") != null) {
			String kind = request.getParameter("kind");
			HashtagDao hashtagDao = new HashtagDao();
			List<Map<String, Object>> list = hashtagDao.hashtagKindRankList(kind);
			request.setAttribute("kind", kind);
			request.setAttribute("list", list);
			
			System.out.println(kind + " <-- kind HashtagKindRankController");
		}
		
		request.getRequestDispatcher("/WEB-INF/view/hashtag/hashtagKindRank.jsp").forward(request, response);
	}
}
