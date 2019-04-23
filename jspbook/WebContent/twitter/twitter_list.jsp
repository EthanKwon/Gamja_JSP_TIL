<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
	String username = request.getParameter("username");
	
	if(username != null){
		session.setAttribute("user",username);
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Twitter</title>
</head>
<body>
<div align=center>
	<h3>My Simple Twitter!!</h3>
	<a href="/jspbook/twitter/twitterServlet?action=logout">회원 목록으로</a>
	<hr>
	<form action="/jspbook/twitter/twitterServlet?action=msg" method="POST">
		@<%=session.getAttribute("memberName") %> <input type="text" name="msg">
		<input type="submit" value="Tweet">
	</form>
	<hr>
	<div align="left">
	<UL>
		<%
			ArrayList<String>msgs=(ArrayList<String>)application.getAttribute("msgs");
			
			if(msgs != null){
				for(String msg : msgs){
					out.println("<LI>" + msg +"</LI>");
				}
			}
		%>
	</UL>
	</div>
</div>
</body>
</html>