package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BookmarkGroupDTO;
import service.BookmarkService;
@WebServlet("/bookmark_mod.do")
public class BookmarkMod extends HttpServlet{

	 private static final long serialVersionUID = 1L;
	 BookmarkService service = new BookmarkService();
	    //get요청시
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    		request.setCharacterEncoding("UTF-8");
		        String bmk_mno = request.getParameter("bmk_mno");
		        
		        BookmarkGroupDTO dto = service.getById(bmk_mno);
		        request.setAttribute("dto",dto);
		        request.getRequestDispatcher("bookmark_mod.jsp").forward(request, response);
	     
	    }
	    
	    //post 요청시
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    		request.setCharacterEncoding("UTF-8");
		        String bmk_name = request.getParameter("bmk_name");
		        String bmk_sequence = request.getParameter("bmk_sequence");
		        String bmk_mno = request.getParameter("bmk_mno");
		      
		        service.update(bmk_name, bmk_sequence, bmk_mno);
		        List<BookmarkGroupDTO> bmklist = service.getAll();
		        
		        // 데이터를 request 객체에 저장
			    request.setAttribute("bmklist", bmklist);
		        request.getRequestDispatcher("bookmark_list.jsp").forward(request, response);
	     
	    }

}
