<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="dto.WifiDetail" %>
<%@ page import="java.util.List" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근처 와이파이 불러오기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/common.css">

</head>
<body>
<script>
$(document).ready(function() {

  $("#get-location-button").click(function() {
    if (navigator.permissions) {
      navigator.permissions.query({name:'geolocation'}).then(function(result) {
        if (result.state === 'granted') {
          navigator.geolocation.getCurrentPosition(function(position) {
            getLocation(position);
          });
        } else if (result.state === 'prompt') {
          navigator.geolocation.getCurrentPosition(function(position) {
            getLocation(position);
          });
        } else {
          alert("이 브라우저는 위치 정보를 지원하지 않습니다.");
        }
      });
    } else if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function(position) {
        getLocation(position);
      });
    } else {
      alert("이 브라우저는 위치 정보를 지원하지 않습니다.");
    }
  });

  function getLocation(position) {
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;

    $("#lat").val(latitude);
    $("#lnt").val(longitude);
   
  }
  
  
});

</script>

<h2>와이파이 정보 구하기</h2>
<%@ include file="./common/menu.jsp" %>

<form id="location-form" action="GetMyLocation.do" method="post">
	
    LAT: <input type="text" id="lat" name="lat" />, LNT: <input type="text" id="lnt" name="lnt" />
    <input type="button" value="내 위치 가져오기" id="get-location-button" />
	

    <input type="submit" value="주변 와이파이 정보 불러오기" id="submit-location" />
</form>
<p/>
<div class="location">
	<table>
		<tr>
			<th>거리(km)</th>
			<th>관리번호</th>
			<th>자치구</th>
			<th>와이파이명</th>
			<th>도로명주소</th>
			<th>상세주소</th>
			<th>설치위치(층)</th>
			<th>설치유형</th>
			<th>설치기관</th>
			<th>서비스구분</th>
			<th>망종류</th>
			<th>설치년도</th>
			<th>실내외구분</th>
			<th>WIF접속환경</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>작업일자</th>
		</tr>
		<c:choose>
  		<c:when test="${not empty wifiDetails}">
		<c:forEach var="wifiDetail" items="${wifiDetails}">
		<tr>
			<td>${ wifiDetail.getX_SWIFI_DIST() }</td>
			<td>${ wifiDetail.getX_SWIFI_MGR_NO()}</td>
			<td>${ wifiDetail.getX_SWIFI_WRDOFC() }</td>
    		<td><a href="detail_page.do?X_SWIFI_MGR_NO=${wifiDetail.getX_SWIFI_MGR_NO() }&X_SWIFI_DIST=${ wifiDetail.getX_SWIFI_DIST() }">${ wifiDetail.getX_SWIFI_MAIN_NM() }</a></td> <!-- 와이파이명 -->
	        <td>${ wifiDetail.getX_SWIFI_ADRES1() }</td> <!-- 도로명주소 -->
	        <td>${ wifiDetail.getX_SWIFI_ADRES2() }</td> <!-- 상세주소 -->
	        <td>${ wifiDetail.getX_SWIFI_INSTL_FLOOR() }</td> <!-- 설치위치(층) -->
	        <td>${ wifiDetail.getX_SWIFI_INSTL_TY() }</td> <!-- 설치유형 -->
	        <td>${ wifiDetail.getX_SWIFI_INSTL_MBY() }</td> <!-- 설치기관 -->
	        <td>${ wifiDetail.getX_SWIFI_SVC_SE() }</td> <!-- 서비스구분 -->
	        <td>${ wifiDetail.getX_SWIFI_CMCWR() }</td> <!-- 망종류 -->
	        <td>${ wifiDetail.getX_SWIFI_CNSTC_YEAR() }</td> <!-- 설치년도 -->
	        <td>${ wifiDetail.getX_SWIFI_INOUT_DOOR() }</td> <!-- 실내외구분 -->
	        <td>${ wifiDetail.getX_SWIFI_REMARS3() }</td> <!-- WIF접속환경 -->
	        <td>${ wifiDetail.getLAT() }</td> <!-- X좌표 -->
	        <td>${ wifiDetail.getLNT() }</td> <!-- Y좌표 -->
	        <td>${ wifiDetail.getWORK_DTTM() }</td> <!-- 작업일자 -->
			</tr>
		<tr>
		</c:forEach>
		</c:when>
		  <c:otherwise>
		    <tr>
		      <td colspan="17">위치 정보를 입력한 후에 사용해주세요</td>
		    </tr>
		  </c:otherwise>
		</c:choose>
	</table>
</div>

</body>
</html>