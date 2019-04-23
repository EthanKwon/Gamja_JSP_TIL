<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bbs.*"%>
<%@ page import="bbs.*"%>   
<%
	int id = Integer.parseInt(request.getParameter("id"));
	BbsDAO bDao = new BbsDAO();
	BbsDTO bDto = bDao.selectOne(id);
	bDao.close();
%>
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
	}
	
</style>
</head>
<body>
<center>
<%
	BbsDTO bbs = (BbsDTO) request.getAttribute("bbs");
	System.out.println(bbs.getId());
%>
	<h2>게시물 변경</h2>
	<hr>
	<form name="registerForm" action="BbsServlet?action=execute" method=post>
		<input type="hidden" id="id" name="id" value ="<%=bbs.getId()%>"> 
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
					<td style="text-align:center"><%=bDto.getId()%></td>
					<td><input type ="text" name ="title" value="<%=bbs.getTitle() %>" size="10"></td>
					<td style="text-align:center"><%=bDto.getName()%></td>
					<td><%=bDto.getDate()%></td>
				</tr>
				<tr><th class="bbsContent" colspan ="4" >게시글</th></tr>
				<tr>
					<td colspan ="4"><input type ="text" name ="content" value="<%=bbs.getContent()%>" style="width:400px; height:200px; word-break:break-all"></td>
				</tr>
			</tbody>
		</table>
		
		<label><span></span><input type ="submit" value ="회원수정" name="B1"> &nbsp;&nbsp;</label>
		<label>	<input type="reset" value ="재작성" name = "B2"> </label>
	</form>

</center>
</body>
</html>