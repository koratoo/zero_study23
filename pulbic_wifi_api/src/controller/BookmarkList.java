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

@WebServlet("/bookmark_list.do")
public class BookmarkList extends HttpServlet {

	private static final long serialVersionUID = 1L;
	BookmarkService service = new BookmarkService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        
        List<BookmarkGroupDTO> bmklist = service.getAll();
        
        // 데이터를 request 객체에 저장
	    request.setAttribute("bmklist", bmklist);
	    
    	 request.getRequestDispatcher("bookmark_list.jsp").forward(request, response);
    }

}
