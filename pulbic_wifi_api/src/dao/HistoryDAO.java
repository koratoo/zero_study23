package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import common.DBConn;
import dto.History;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class HistoryDAO {

	 private DBConn dbConn;
	 public HistoryDAO() {
		 dbConn = new DBConn();
	 }
	 
	 public void insertHistory(String lat, String lnt) {
		 Connection conn = null;
	        PreparedStatement pstmt = null;
	        
	        try {
	            conn = dbConn.getConnection();

	            String query = "INSERT INTO History (lat, lnt, inq_date) VALUES (?, ?, ?)";
	            pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, lat);
	            pstmt.setString(2, lnt);
	           
	            LocalDateTime currentDateTime = LocalDateTime.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
	            String formattedDateTime = currentDateTime.format(formatter);
	            pstmt.setString(3, formattedDateTime); // 등록일자
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        } finally {
	            dbConn.close(conn, pstmt, null);
	        }
	 }
	 
	 
	 public List<History> getHistory() {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        List<History> hlist = new ArrayList<>();
	        

	        try {
	            conn = dbConn.getConnection();

	            String query = "SELECT * FROM history order by hno desc";
	            pstmt = conn.prepareStatement(query);
	            rs= pstmt.executeQuery();
	            while(rs.next()) {
	            	History hs = new History();
	            	hs.setHno(rs.getInt(1));
	            	hs.setLat(rs.getDouble(2));
	            	hs.setLnt(rs.getDouble(3));
	            	hs.setInq_date(rs.getString(4));
	            	hlist.add(hs);
	            }

	            
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        } finally {
	            dbConn.close(conn, pstmt, rs);
	        }
	        
	        return hlist;
	    }
	 
	 public void deleteBookmark(String hno) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        int num = Integer.parseInt(hno);
	        
	        try {
	            conn = dbConn.getConnection();

	            String query = "delete from history where hno = ?";
	            pstmt = conn.prepareStatement(query);
	            
	            pstmt.setInt(1, num);

	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            dbConn.close(conn, pstmt, null);
	        }
	    }
}
