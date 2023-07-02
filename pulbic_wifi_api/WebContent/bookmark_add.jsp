<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 등록하기</title>
<link rel="stylesheet" type="text/css" href="./css/common.css">

<style>
	input{
		align-items: left;
		width:100%;
	}
	#enroll_btn {
	  margin: 10px auto;
	  width: 10%;
	  display: block;
	}

</style>
</head>
<body>

<h2>북마크 추가하기</h2>
<%@ include file="./common/menu.jsp" %>

<p/>
<form action="bookmark_add.do" method="post">
<table>
	<tr>
		<th>북마크 이름</th>
		<td><input type="text" name="bmk_name" /></td>
	</tr>
	<tr>
		<th>북마크 순서</th>
		<td><input type="text" name="bmk_sequence" /></td>
	</tr>
	
</table>
		<input type="submit" value="등록하기" id="enroll_btn" />
</form>
</body>
</html>