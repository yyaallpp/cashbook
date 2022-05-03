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
@WebServlet("/HashtagOneController")
public class HashtagOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tag = request.getParameter("tag");
		int tagCount = Integer.parseInt(request.getParameter("tagCount"));
		
		HashtagDao hashtagDao = new HashtagDao();
		List<Map<String, Object>> list = hashtagDao.selectHashtagOne(tag);
		
		request.setAttribute("tagCount", tagCount);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/hashtag/hashtagOne.jsp").forward(request, response);
	}
}
