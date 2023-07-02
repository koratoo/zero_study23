package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import common.DBConn;
import dto.WifiDetail;

//API 만들었는데 쓰진 않음
public class PublicWifiAPI {

	 public static void main(String[] args) throws IOException {
	    	
	    	PublicWifiAPI p = new PublicWifiAPI();
	    	
	  } 
	 
	 PublicWifiAPI p = new PublicWifiAPI();
	 public List<WifiDetail> getWifiDetails() {
		    List<WifiDetail> wifiDetails = new ArrayList<>();
		    int startIndex = 1;
		    int endIndex = 100;
		    int totalCount = 0;

		    try {
		        do {
		            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
		            urlBuilder.append("/" + URLEncoder.encode("786968535973737936337441516650", "UTF-8"));
		            urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
		            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
		            urlBuilder.append("/" + startIndex);
		            urlBuilder.append("/" + endIndex + "/");

		            URL url = new URL(urlBuilder.toString());
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		            conn.setRequestMethod("GET");
		            conn.setRequestProperty("Content-type", "application/json");

		            BufferedReader rd;
		            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            } else {
		                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		            }

		            StringBuilder sb = new StringBuilder();
		            String line;
		            while ((line = rd.readLine()) != null) {
		                sb.append(line);
		            }

		            rd.close();
		            conn.disconnect();

		            String json = sb.toString();

		            // Update indexes for the next API request
		            startIndex += 1000;
		            endIndex += 1000;

		            totalCount = wifiDetails.size();

		        } while (totalCount == 1000); // Continue making requests until fewer than 1000 records are returned

		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    return wifiDetails;
		}
    
    public void parseWifiDetailList(String json) {
    	List<WifiDetail> wifi = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(json);
        JSONObject tbPublicWifiInfoObj = jsonObj.getJSONObject("TbPublicWifiInfo");
        JSONArray rowArray = tbPublicWifiInfoObj.getJSONArray("row");
        
        for (int i = 0; i < rowArray.length(); i++) {
            JSONObject rowObj = rowArray.getJSONObject(i);
            String X_SWIFI_MGR_NO = rowObj.getString("X_SWIFI_MGR_NO");
            String X_SWIFI_WRDOFC = rowObj.getString("X_SWIFI_WRDOFC");
            String X_SWIFI_MAIN_NM = rowObj.getString("X_SWIFI_MAIN_NM");
            String X_SWIFI_ADRES1 = rowObj.getString("X_SWIFI_ADRES1");
            String X_SWIFI_ADRES2 = rowObj.getString("X_SWIFI_ADRES2");
            String X_SWIFI_INSTL_FLOOR = rowObj.getString("X_SWIFI_INSTL_FLOOR");
            String X_SWIFI_INSTL_TY = rowObj.getString("X_SWIFI_INSTL_TY");
            String X_SWIFI_INSTL_MBY = rowObj.getString("X_SWIFI_INSTL_MBY");
            String X_SWIFI_SVC_SE = rowObj.getString("X_SWIFI_SVC_SE");
            String X_SWIFI_CMCWR = rowObj.getString("X_SWIFI_CMCWR");
            String X_SWIFI_CNSTC_YEAR = rowObj.getString("X_SWIFI_CNSTC_YEAR");
            String X_SWIFI_INOUT_DOOR = rowObj.getString("X_SWIFI_INOUT_DOOR");
            String X_SWIFI_REMARS3 = rowObj.getString("X_SWIFI_REMARS3");
            double LAT = rowObj.getDouble("LAT");
            double LNT = rowObj.getDouble("LNT");
            String WORK_DTTM = rowObj.getString("WORK_DTTM");

            
            
            
            WifiDetail wifiDetail = new WifiDetail();
            wifiDetail.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
            wifiDetail.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
            wifiDetail.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
            wifiDetail.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
            wifiDetail.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
            wifiDetail.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
            wifiDetail.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
            wifiDetail.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
            wifiDetail.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
            wifiDetail.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
            wifiDetail.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
            wifiDetail.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
            wifiDetail.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
            wifiDetail.setLAT(LAT);
            wifiDetail.setLNT(LNT);
            wifiDetail.setWORK_DTTM(WORK_DTTM);

           
            p.insertWifiDetail(wifiDetail);
        }
    }
    
    private DBConn dbConn;

    public PublicWifiAPI() {
        dbConn = new DBConn();
    }

    public void insertWifiDetail(WifiDetail wifiDetail) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbConn.getConnection();

            String query = "INSERT INTO wifiDetail (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, wifiDetail.getX_SWIFI_MGR_NO());
            pstmt.setString(2, wifiDetail.getX_SWIFI_WRDOFC());
            pstmt.setString(3, wifiDetail.getX_SWIFI_MAIN_NM());
            pstmt.setString(4, wifiDetail.getX_SWIFI_ADRES1());
            pstmt.setString(5, wifiDetail.getX_SWIFI_ADRES2());
            pstmt.setString(6, wifiDetail.getX_SWIFI_INSTL_FLOOR());
            pstmt.setString(7, wifiDetail.getX_SWIFI_INSTL_TY());
            pstmt.setString(8, wifiDetail.getX_SWIFI_INSTL_MBY());
            pstmt.setString(9, wifiDetail.getX_SWIFI_SVC_SE());
            pstmt.setString(10, wifiDetail.getX_SWIFI_CMCWR());
            pstmt.setString(11, wifiDetail.getX_SWIFI_CNSTC_YEAR());
            pstmt.setString(12, wifiDetail.getX_SWIFI_INOUT_DOOR());
            pstmt.setString(13, wifiDetail.getX_SWIFI_REMARS3());
            pstmt.setDouble(14, wifiDetail.getLAT());
            pstmt.setDouble(15, wifiDetail.getLNT());
            pstmt.setString(16, wifiDetail.getWORK_DTTM());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pstmt, null);
        }
    }
}
