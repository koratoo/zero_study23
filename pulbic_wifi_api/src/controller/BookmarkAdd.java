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
@WebServlet("/bookmark_add.do")
public class BookmarkAdd extends HttpServlet{

	 private static final long serialVersionUID = 1L;
	 BookmarkService service = new BookmarkService();
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	    request.setCharacterEncoding("UTF-8");
		        String bmk_name = request.getParameter("bmk_name");
		        String bmk_sequence = request.getParameter("bmk_sequence");
		        
		        service.insert(bmk_name, bmk_sequence);
		        List<BookmarkGroupDTO> bmklist = service.getAll();
		        
			    request.setAttribute("bmklist", bmklist);
		        request.getRequestDispatcher("bookmark_list.jsp").forward(request, response);
	     
	    }

}
