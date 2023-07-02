<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="dto.MyBookmark" %>
<%@ page import="java.util.List" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 북마크 리스트</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/common.css">


</head>
<body>

<h2>나의 북마크 리스트</h2>
<%@ include file="./common/menu.jsp" %>

<p/>

<div class="mybookmarks">
	<table>
		<tr>
			<th>ID</th>
			<th>북마크 이름</th>
			<th>와이파이명</th>
			<th>등록일자</th>
			<th>비고</th>
		</tr>
		<c:forEach var="bmklist" items="${mybmk}">
		<tr>
			<td>${bmklist.getMybmk_no() }</td>
			<td>${bmklist.getBmk_name() }</td>
			<td>${bmklist.getX_SWIFI_MAIN_NM() }</td>
			<td>${bmklist.getReg_date() }</td>
			<td><a href="mybookmark_del.do?mybmk_no=${bmklist.getMybmk_no()}">삭제</a></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>