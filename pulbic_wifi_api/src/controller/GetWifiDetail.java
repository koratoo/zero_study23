package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookmarkGroupDAO;
import dao.WifiListDAO;
import dto.BookmarkGroupDTO;
import dto.WifiDetail;

@WebServlet("/detail_page.do")
public class GetWifiDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void reqPro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String X_SWIFI_MGR_NO = request.getParameter("X_SWIFI_MGR_NO");
     

        WifiListDAO dao = new WifiListDAO();
        WifiDetail wifi = dao.getOneWifiDetails(X_SWIFI_MGR_NO);

        // 데이터를 request 객체에 저장
        request.setAttribute("wifiDetails", wifi);
        
        //북마크 리스트
        BookmarkGroupDAO bmkdao = new BookmarkGroupDAO();
        List<BookmarkGroupDTO> bmklist = bmkdao.getBookmarkList();
        
        // 데이터를 request 객체에 저장
	    request.setAttribute("bmklist", bmklist);
	    
	    

        request.getRequestDispatcher("detail_page.jsp").forward(request, response);
    }
}
