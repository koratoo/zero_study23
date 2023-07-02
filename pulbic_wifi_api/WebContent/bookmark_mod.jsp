<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.BookmarkGroupDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 수정하기</title>
<link rel="stylesheet" type="text/css" href="./css/common.css">

<style>
	
	input{
		align-items: left;
		width:100%;
	}
	.back {
	  margin: 10px auto;
	  width: 10%;
	  display: flex;
	}
	#enroll_btn{
	  width: 50%;
	}

</style>
</head>
<body>

<h2>북마크 수정하기</h2>
<%@ include file="./common/menu.jsp" %>

<p/>
<form action="bookmark_mod.do" method="post">
<input type="hidden" name="bmk_mno" value="${dto.getBmk_mno()}">

<table>
	<tr>
		<th>북마크 이름</th>
		<td><input type="text" name="bmk_name" value="${dto.getBmk_name() }" /></td>
	</tr>
	<tr>
		<th>북마크 순서</th>
		<td><input type="text" name="bmk_sequence" value="${dto.getBmk_sequence() }" /></td>
	</tr>
	
</table>
	<div class = "back">
		<a href="bookmark_list.do">돌아가기</a>&nbsp;|<input type="submit" value="수정하기" id="enroll_btn" />
	</div>
</form>
</body>
</html>