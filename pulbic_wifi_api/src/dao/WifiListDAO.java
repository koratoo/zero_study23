package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBConn;
import dto.WifiDetail;
//csv삽입하여 사용시 필요함
public class WifiListDAO {

    private DBConn conn;

    public WifiListDAO() {
        conn = new DBConn();
    }

    public List<WifiDetail> getWifiDetails() {
        List<WifiDetail> wifiDetails = new ArrayList<>();

        try (Connection connection = conn.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM wifidetail");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                WifiDetail wifiDetail = new WifiDetail();
                wifiDetail.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifiDetail.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifiDetail.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifiDetail.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifiDetail.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifiDetail.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifiDetail.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifiDetail.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifiDetail.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifiDetail.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifiDetail.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                wifiDetail.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifiDetail.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifiDetail.setLAT(rs.getDouble("LAT"));
                wifiDetail.setLNT(rs.getDouble("LNT"));
                wifiDetail.setWORK_DTTM(rs.getString("WORK_DTTM"));
                wifiDetails.add(wifiDetail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리 방법에 대한 추가 로직 작성예정
        }

        return wifiDetails;
    }
    
    
    public List<WifiDetail> getNearWifiDetails(List<WifiDetail> list) {
        List<WifiDetail> wifiDetails = new ArrayList<>();
        
        for(int i=0;i<list.size();i++) {
        	if(list.get(i).getX_SWIFI_DIST() !=0 ) {
        		wifiDetails.add(list.get(i));
        	}
        }
        return wifiDetails;
    }
    
    public WifiDetail getOneWifiDetails(String X_SWIFI_MGR_NO) {
        WifiDetail wifiDetail = new WifiDetail();

        try (Connection connection = conn.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM wifidetail WHERE X_SWIFI_MGR_NO=?")) {
        	
        	stmt.setString(1, X_SWIFI_MGR_NO); // X_SWIFI_MGR_NO 값 설정

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                   
                    wifiDetail.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                    wifiDetail.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                    wifiDetail.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                    wifiDetail.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                    wifiDetail.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                    wifiDetail.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                    wifiDetail.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                    wifiDetail.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                    wifiDetail.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                    wifiDetail.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                    wifiDetail.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                    wifiDetail.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                    wifiDetail.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                    wifiDetail.setLAT(rs.getDouble("LAT"));
                    wifiDetail.setLNT(rs.getDouble("LNT"));
                    wifiDetail.setWORK_DTTM(rs.getString("WORK_DTTM"));
                   
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리 방법에 대한 추가 로직 작성예정
        }

        return wifiDetail;
    }

}
