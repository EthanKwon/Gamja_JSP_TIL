<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="bbs.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 보기</title>
<style>
	thead,.bbsContent { background-color : #00FF00; color : #494D4A; width : 400px;}
	
	th {
		text-align:center;
		border: 2px solid #333333;
		padding : 5px;
	}
	td {
		border: 1px solid #333333;
		padding : 5px;	
	}
	
</style>
</head>
<body>
	<center>
		<h2>게시글 보기</h2>
		<p>${memberName} 회원님이 로그인 하셨습니다. </p>
		<hr>
		<table border="1" style="border-collapse: collapse;">
			<thead>
				<tr>
					<th>게시번호</th>
					<th style="width:40% ">제목</th>
					<th>글쓴이</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<c:set var="bbs" value="${requestScope.bDto}"/>
					<td style="text-align:center">${bbs.id}</td>
					<td>${bbs.title}</td>
					<td style="text-align:center">${bbs.name}</td>
					<td style="text-align:center">${bbs.date}</td>
				</tr>
				<tr><th class="bbsContent" colspan ="4" >게시글</th></tr>
				<tr>
					<td colspan ="4">${bbs.content}</td>
				</tr>
			</tbody>
		</table>
		<br>
		<a href="BbsServlet?action=list&page=${BoardPage}">뒤로가기</a><br>
	</center>
</body>
</html>