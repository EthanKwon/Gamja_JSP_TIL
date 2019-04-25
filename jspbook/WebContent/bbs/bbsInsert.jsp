<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
</head>
<body>
<center>
	<h2>게시글 작성</h2>
	<hr>
	<div style="text-align:right" > 작성자 : <%=session.getAttribute("memberName") %></div>
	<form name="insertForm" action="BbsServlet?action=insert" method=post>
		<label><span>제목 : </span>
			<input type ="text" name ="title" size="40"></label> <br><br>
		<label><span>내용 : </span>
			<textarea name="content" rows="10" cols="42"></textarea></label><br><br>
		<label><span></span><input type ="submit" value ="저장" name="B1"> &nbsp;&nbsp;
			<input type="reset" value ="재작성" name = "B2"> </label>
	</form><br>
	<% String returnBoard = "BbsServlet?action=pageButton&page="+session.getAttribute("BoardPage"); %>
		<a href="<%=returnBoard%>">뒤로가기</a><br>
</center>
</body>
</html>