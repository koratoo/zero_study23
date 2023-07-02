<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dto.BookmarkGroupDTO" %>
<%@ page import="java.util.List" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detail Page</title>
    <link rel="stylesheet" type="text/css" href="./css/common.css">
    
    <style>

	th{
		background-color: #00ff00;
		color:black;
		width : 30%;
	    font-size: 15px;
	}
	td{
		width: 70%;
		text-align:left;
		font-size: 15px;
	}
	tr:hover{
		background-color: lightblue;
		cursor : pointer;
	}
	
</style>
</head>
<body>
    <h1>와이파이 상세페이지</h1>
    <form action="mybookmark_list.do" method="post">
	<select name="bmk_name">
		<option value="북마크 그룹 보기">북마크 그룹 보기</option>
		<c:forEach var="bmklist" items="${bmklist}">
			<option value="${bmklist.getBmk_name() }">${bmklist.getBmk_name() }</option>
		</c:forEach>
	</select>
	<input type="hidden" name="X_SWIFI_MAIN_NM" value="${wifiDetails.getX_SWIFI_MAIN_NM()}">
	<input type="submit" value="즐겨찾기 등록하기" />
    </form>
<p/>

<%
	String distance = request.getParameter("X_SWIFI_DIST");
%>
    <table>
        <tr>
            <th>거리(km)</th>
            <td><%=distance %></td>
        </tr>
        <tr>
            <th>관리번호</th>
            <td>${wifiDetails.getX_SWIFI_MGR_NO()}</td>
        </tr>
        <tr>
            <th>자치구</th>
            <td>${wifiDetails.getX_SWIFI_WRDOFC()}</td>
        </tr>
        <tr>
            <th>와이파이명</th>
    		<td>${wifiDetails.getX_SWIFI_MAIN_NM()}</td>
        </tr>
        <tr>
            <th>도로명주소</th>
	        <td>${wifiDetails.getX_SWIFI_ADRES1()}</td>
        </tr>
        <tr>
            <th>상세주소</th>
	        <td>${wifiDetails.getX_SWIFI_ADRES2()}</td>
        </tr>
        <tr>
            <th>설치위치(층)</th>
	        <td>${wifiDetails.getX_SWIFI_INSTL_FLOOR()}</td>
        </tr>
        <tr>
            <th>설치유형</th>
	        <td>${wifiDetails.getX_SWIFI_INSTL_TY()}</td>
        </tr>
        <tr>
            <th>설치기관</th>
	        <td>${wifiDetails.getX_SWIFI_INSTL_MBY()}</td>
        </tr>
        <tr>
            <th>서비스구분</th>
	        <td>${wifiDetails.getX_SWIFI_SVC_SE()}</td>
        </tr>
        <tr>
            <th>망종류</th>
	        <td>${wifiDetails.getX_SWIFI_CMCWR()}</td>
        </tr>
        <tr>
            <th>설치년도</th>
	        <td>${wifiDetails.getX_SWIFI_CNSTC_YEAR()}</td>
        </tr>
        <tr>
            <th>실내외구분</th>
	        <td>${wifiDetails.getX_SWIFI_INOUT_DOOR()}</td>
        </tr>
        <tr>
            <th>WIF접속환경</th>
	        <td>${wifiDetails.getX_SWIFI_REMARS3()}</td>
        </tr>
        <tr>
            <th>X좌표</th>
	        <td>${wifiDetails.getLAT()}</td>
        </tr>
        <tr>
            <th>Y좌표</th>
	        <td>${wifiDetails.getLNT()}</td>
        </tr>
        <tr>
            <th>작업일자</th>
	        <td>${wifiDetails.getWORK_DTTM()}</td>
        </tr>
    </table>
</body>
</html>
