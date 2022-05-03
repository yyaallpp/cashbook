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

@WebServlet("/HashtagDateRankController")
public class HashtagDateRankController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("cashDate") != null) {
			String cashDate = request.getParameter("cashDate");
			HashtagDao hashbookDao = new HashtagDao();
			List<Map<String, Object>> list = hashbookDao.hashtagDateRankList(cashDate);
			request.setAttribute("cashDate", cashDate);
			request.setAttribute("list", list);
		}
		request.getRequestDispatcher("/WEB-INF/view/hashtag/hashtagDateRank.jsp").forward(request, response);
	}
}
