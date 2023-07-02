package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.History;
import service.HistoryService;

@WebServlet("/history.do")
public class HistoryController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	HistoryService service = new HistoryService();	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<History> hlist = service.getAll();
		request.setAttribute("hlist", hlist);
        request.getRequestDispatcher("history.jsp").forward(request, response);
	}
}


