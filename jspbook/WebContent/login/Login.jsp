<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
<%
	String error = (String)request.getParameter("error");
	System.out.println(error);
	if(error != null){
		out.println("<script>alert('" + error + "')</script>");
	}
%>
	<center> <br><br>
	<h3>Member Login</h3> <br>
	<hr>
	<form name="loginForm" action=/jspbook/login/LoginProcServlet method=post>
		<label><span>ID : </span>
			<input type ="text" name ="id" size="10"></label> <br><br>
		<label><span>Password : </span>
			<input type ="password" name ="password" size="10"></label> <br><br>
		<label><input type ="submit" value ="로그인" name="B1"> &nbsp;&nbsp;</label>
	</form>
		<button onclick="location.href='register.html'">회원가입</button>
	</center>

</body>
</html>