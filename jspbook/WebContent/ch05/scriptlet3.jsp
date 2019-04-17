<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h2>스크립트릿 테스트2 : 1-10까지 제곱출력</h2>
<hr>
<table width ="200" align ="center" border = "1">
	<thead bgcolor = "skyblue" style="color:white " ><tr><th>구분</th>
	<%
		for(int i=3;i<10;i+=2){
	%>
				<th><%=i %></th>
	<% 		
		}
	%>
	</tr>
<tbody align ="center">
	<%
		for(int i=3;i<10;i+=2){
	%>
				<tr><th bgcolor = "skyblue" style="color:white "><%=i %></th>
	<%			for(int k=3;k<10;k+=2){
	%>
						<td> <%= i*k %> </td>
	<% 
				}
	%>
				</tr>
	<% 
		}
	%>
</tbody>
</table>
</div>


</body>
</html>