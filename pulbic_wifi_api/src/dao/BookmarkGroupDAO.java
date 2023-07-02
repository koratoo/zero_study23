package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.DBConn;
import dto.BookmarkGroupDTO;

public class BookmarkGroupDAO {
    private DBConn dbConn;

    public BookmarkGroupDAO() {
        dbConn = new DBConn();
    }

    public void insertBookmark(String bmk_name, String sequence) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        int bmk_sequence = Integer.parseInt(sequence);

        try {
            conn = dbConn.getConnection();

            String query = "INSERT INTO bookmark_group (bmk_name, bmk_sequence, reg_date) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bmk_name);
            pstmt.setInt(2, bmk_sequence);
           
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
    
    public List<BookmarkGroupDTO> getBookmarkList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BookmarkGroupDTO> bmklist = new ArrayList<>();
        

        try {
            conn = dbConn.getConnection();

            String query = "SELECT * FROM bookmark_group";
            pstmt = conn.prepareStatement(query);
            rs= pstmt.executeQuery();
            while(rs.next()) {
            	BookmarkGroupDTO bk = new BookmarkGroupDTO();
            	bk.setBmk_mno(rs.getInt(1));
            	bk.setBmk_name(rs.getString(2));
            	bk.setBmk_sequence(rs.getInt(3));
            	bk.setReg_date(rs.getString(4));
            	bk.setMod_date(rs.getString(5));
            	bmklist.add(bk);
            }

            
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
        
        return bmklist;
    }
    
    
    public BookmarkGroupDTO getOneBmkInfo(String bmk_mno) {
    	int bno = Integer.parseInt(bmk_mno);
    	Connection conn = null;
        PreparedStatement pstmt = null;
    	ResultSet rs = null;
        BookmarkGroupDTO dto = new BookmarkGroupDTO();
    	
        try {
            conn = dbConn.getConnection();

            String query = "select bmk_name, bmk_sequence from bookmark_group where bmk_mno = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bno);
            
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	
            	dto.setBmk_mno(bno);
            	dto.setBmk_name(rs.getString(1));
            	dto.setBmk_sequence(rs.getInt(2));
            }

            
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, rs);
        }
    	
        return dto;
    }
    
    public void modifyBookmark(String bmk_name, String bmk_sequence, String bmk_mno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int sequence = Integer.parseInt(bmk_sequence);
        int bno = Integer.parseInt(bmk_mno);
        System.out.println(bno);

        try {
            conn = dbConn.getConnection();

            String query = "update bookmark_group set bmk_name = ?, bmk_sequence = ?, mod_date = ? where bmk_mno = ?";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, bmk_name);
            pstmt.setInt(2, sequence);
            // 현재 날짜 및 시간 가져오기
            Date currentDate = new Date();
            Timestamp timestamp = new Timestamp(currentDate.getTime());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(timestamp);

            pstmt.setString(3, formattedDate);
            pstmt.setInt(4, bno);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, null);
        }
    }

    public void deleteBookmark(String bmk_mno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int bno = Integer.parseInt(bmk_mno);
        System.out.println(bno);

        try {
            conn = dbConn.getConnection();

            String query = "delete from bookmark_group where bmk_mno = ?";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, bno);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, null);
        }
    }
    
}
