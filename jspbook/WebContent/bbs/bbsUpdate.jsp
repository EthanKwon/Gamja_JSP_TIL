<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bbs.*"%>
<%@ page import="bbs.*"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 변경</title>
<style>
	thead,.bbsContent { background-color : #00FF00; color : #494D4A;}
	th {
		text-align:center;
		border: 2px solid #333333;
		padding : 10px;
	}
	td {
		border: 1px solid #333333;
		padding : 5px;	
		text-align:center
	}
	
</style>
</head>
<body>
<center>
	<h2>게시물 변경</h2>
	<hr>
	<c:set var="bbs" value="${requestScope.bbsOne}"/>
	<form name="registerForm" action="BbsServlet?action=execute" method=post>
		<input type="hidden" id="id" name="id" value ="${bbs.id}"> 
		<table border="1" style="border-collapse: collapse;">
			<thead>
				<tr>
					<th>게시번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${bbs.id}</td>
					<td><input type ="text" name ="title" value="${bbs.title}" size="40"></td>
					<td>${bbs.name}</td>
					<td>${bbs.date}</td>
				</tr>
				<tr><th class="bbsContent" colspan ="4" >게시글</th></tr>
				<tr>
					<td colspan ="4"><textarea name="content" rows="20" cols="100">${bbs.content}</textarea></td>
				</tr>
			</tbody>
		</table><br>
		
		<label><span></span><input type ="submit" value ="수정" name="B1"> &nbsp;&nbsp;</label>
		<label>	<input type="reset" value ="재작성" name = "B2"> </label>
	</form><br>
		<a href="BbsServlet?action=list&page=${BoardPage}">뒤로가기</a><br>

</center>
</body>
</html>