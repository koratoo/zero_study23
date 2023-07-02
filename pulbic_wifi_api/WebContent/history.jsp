<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="dto.History" %>
<%@ page import="java.util.List" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위치 조회 목록</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<link rel="stylesheet" type="text/css" href="./css/common.css">


</head>
<body>

<h2>위치 조회 목록</h2>
<%@ include file="./common/menu.jsp" %>

<p/>
<div class="bookmarks">
	<table>
		<tr>
			<th>ID</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>조회일자</th>
			<th>비고</th>
		</tr>
		<c:forEach var="hlist" items="${hlist}">
		<tr>
			<td>${hlist.getHno() }</td>
			<td>${hlist.getLat() }</td>
			<td>${hlist.getLnt() }</td>
			<td>${hlist.getInq_date() }</td>
			<td><a href="history_del.do?hno=${hlist.getHno() }">삭제</a></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>