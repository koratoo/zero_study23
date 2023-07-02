package service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dto.WifiDetail;

public class CalculateDistance {

	public void calculate_dist(String latitude, String longitude,List<WifiDetail> list){
		
		
		double myLat = Double.parseDouble(latitude);
		double myLnt = Double.parseDouble(longitude);
		
		
	    
	    List<Point> saveLoc = new ArrayList<>();
	    
	    for(int i=0;i<list.size();i++) {
	    	double lat = list.get(i).getLAT();
	    	double lnt = list.get(i).getLNT();
	    	saveLoc.add(new Point(lat,lnt));
	    }
	    
	    DecimalFormat decimalFormat = new DecimalFormat("#.####"); // 소숫점 네 자리까지 포맷
	    List<Double> dist = new ArrayList<>();
	    //System.out.println(saveLoc.size());
	    for(int i=0;i<list.size();i++) {
	    	double distance = distance(myLat,myLnt,saveLoc.get(i).lat,saveLoc.get(i).lnt);
	    	if(distance<2) {//4키로 이내
	    		distance = Double.parseDouble(decimalFormat.format(distance)); // 반올림하여 소숫점 네 자리까지 저장
	    		dist.add(distance);
		    	list.get(i).setX_SWIFI_DIST(distance);
	    	}
	    } 
	    
	}	
	private static double distance(double lat1, double lon1, double lat2, double lon2){
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60*1.1515*1609.344;
		
		return dist/1000; //단위 kilometer
	}
	
	//10진수를 radian(라디안)으로 변환
	private static double deg2rad(double deg){
		return (deg * Math.PI/180.0);
	}
	//radian(라디안)을 10진수로 변환
	private static double rad2deg(double rad){
		return (rad * 180 / Math.PI);
	}
}

class Point{
	double lat;
	double lnt;
	
	Point(double lat, double lnt){
		this.lat = lat;
		this.lnt = lnt;
	}
}
