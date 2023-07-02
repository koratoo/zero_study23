package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookmarkGroupDAO;
import dao.MyBookmarkDAO;
import dto.BookmarkGroupDTO;
import dto.MyBookmark;
import service.MyBookmarkService;

@WebServlet("/mybookmark_list.do")
public class MyBookmarkList extends HttpServlet{

	private static final long serialVersionUID = 1L;
	MyBookmarkService service = new MyBookmarkService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		//dao 에서 getMyBookmark()메소드 실행
		List<MyBookmark> mybmk = service.getAll();
		request.setAttribute("mybmk", mybmk);
		
		request.getRequestDispatcher("mybookmark_list.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bmk_name = request.getParameter("bmk_name");
		String X_SWIFI_MAIN_NM = request.getParameter("X_SWIFI_MAIN_NM");
		
		service.insert(bmk_name, X_SWIFI_MAIN_NM);
		
		List<MyBookmark> mybmk = service.getAll();
		
		request.setAttribute("mybmk", mybmk);
		request.getRequestDispatcher("mybookmark_list.jsp").forward(request, response);
	}

	
}
