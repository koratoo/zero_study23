package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WifiListDAO;
import dto.WifiDetail;
import service.CalculateDistance;
import service.HistoryService;
import service.PublicWifiAPI;


@WebServlet("/GetMyLocation.do")
public class GetMyLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HistoryService h_service = new HistoryService();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 위치 정보 가져오기
        String latitude = request.getParameter("lat");
        String longitude = request.getParameter("lnt");

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("위치가 입력되지 않았습니다.");
		
	    //히스토리 기록
	    h_service.insert(latitude, longitude);
	    
	    request.setCharacterEncoding("UTF-8");
	    CalculateDistance cal = new CalculateDistance();
	    //PublicWifiAPI publicWifiAPI = new PublicWifiAPI(); //API를 통한 호출
	   // List<WifiDetail> wifiDetails = publicWifiAPI.getWifiDetails(); 
	    
		
		  WifiListDAO dao = new WifiListDAO(); 
		 List<WifiDetail> wifiDetails = dao.getWifiDetails();
		 
	    cal.calculate_dist(latitude, longitude, wifiDetails);
	    
	    List<WifiDetail> near = dao.getNearWifiDetails(wifiDetails);
	    // X_SWIFI_DIST를 기준으로 정렬
	    Collections.sort(near, new Comparator<WifiDetail>() {
	        @Override
	        public int compare(WifiDetail wifi1, WifiDetail wifi2) {
	            double dist1 = wifi1.getX_SWIFI_DIST();
	            double dist2 = wifi2.getX_SWIFI_DIST();
	            return Double.compare(dist1, dist2);
	        }
	    });
	    
	    if (near.size() > 20) {
	        near = near.subList(0, 20); // 앞에서 20개만 가져옵니다.
	    }
	    
	    
	    
	    // 데이터를 request 객체에 저장
	    request.setAttribute("wifiDetails", near);

	    // JSP 파일로 포워딩
	    request.getRequestDispatcher("list.jsp").forward(request, response);
	    
	    
	    
	}

}


