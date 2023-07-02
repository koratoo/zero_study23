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
import dto.MyBookmark;

public class MyBookmarkDAO {

	 private DBConn dbConn;

	    public MyBookmarkDAO() {
	        dbConn = new DBConn();
	    }

	    public void insertMyBookmark(String bmk_name, String X_SWIFI_MAIN_NM) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        
	        try {
	            conn = dbConn.getConnection();

	            String query = "INSERT INTO myBookmark (bmk_name, X_SWIFI_MAIN_NM, reg_date) VALUES (?, ?, ?)";
	            pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, bmk_name);
	            pstmt.setString(2, X_SWIFI_MAIN_NM);
	           
	            LocalDateTime currentDateTime = LocalDateTime.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
	            String formattedDateTime = currentDateTime.format(formatter);
	            pstmt.setString(3, formattedDateTime); // 등록일자

	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	        	e.getMessage();
	        } finally {
	            dbConn.close(conn, pstmt, null);
	        }
	    }
	    
	    
	    public List<MyBookmark> getMyBookmark() {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        List<MyBookmark> bmklist = new ArrayList<>();
	        

	        try {
	            conn = dbConn.getConnection();

	            String query = "SELECT * FROM myBookmark";
	            pstmt = conn.prepareStatement(query);
	            rs= pstmt.executeQuery();
	            while(rs.next()) {
	            	MyBookmark bk = new MyBookmark();
	            	bk.setMybmk_no(rs.getInt(1));
	            	bk.setBmk_name(rs.getString(2));
	            	bk.setX_SWIFI_MAIN_NM(rs.getString(3));
	            	bk.setReg_date(rs.getString(4));
	            	bmklist.add(bk);
	            }

	            
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        } finally {
	            dbConn.close(conn, pstmt, rs);
	        }
	        
	        return bmklist;
	    }
	    
	    public void deleteMybmk(String mybmk_no) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        
	        try {
	            conn = dbConn.getConnection();

	            String query = "delete from myBookmark where mybmk_no =?";
	            pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, mybmk_no);

	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	        	e.getMessage();
	        } finally {
	            dbConn.close(conn, pstmt, null);
	        }
	    }
	    
	
}
