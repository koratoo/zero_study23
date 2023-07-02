package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MyBookmarkService;

@WebServlet("/mybookmark_del.do")
public class MyBookmarkDel extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	MyBookmarkService service = new MyBookmarkService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mybmk_no = request.getParameter("mybmk_no");
		
		service.delete(mybmk_no);
		
		request.getRequestDispatcher("mybookmark_list.do").forward(request, response);
	}

}
