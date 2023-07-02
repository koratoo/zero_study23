<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="dto.BookmarkGroupDTO" %>
<%@ page import="java.util.List" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 리스트</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<link rel="stylesheet" type="text/css" href="./css/common.css">


</head>
<body>

<h2>북마크 리스트 관리 화면</h2>
<%@ include file="./common/menu.jsp" %>

<p/>
<a href="bookmark_add.jsp"><input type="button" value="북마크 등록하기" /></a>
<div class="bookmarks">
	<table>
		<tr>
			<th>ID</th>
			<th>북마크 이름</th>
			<th>순서</th>
			<th>등록일자</th>
			<th>수정일자</th>
			<th>비고</th>
		</tr>
		<c:forEach var="bmklist" items="${bmklist}">
		<tr>
			<td>${bmklist.getBmk_mno() }</td>
			<td>${bmklist.getBmk_name() }</td>
			<td>${bmklist.getBmk_sequence() }</td>
			<td>${bmklist.getReg_date() }</td>
			<td>${bmklist.getMod_date() }</td>
			<td><a href="bookmark_mod.do?bmk_mno=${bmklist.getBmk_mno() }">수정</a>&nbsp;<a href="bookmark_del.do?bmk_mno=${bmklist.getBmk_mno() }">삭제</a></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>