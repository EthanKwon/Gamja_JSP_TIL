<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/register.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 수정</title>
</head>
<body><center>
<%
	MemberDTO member = (MemberDTO) request.getAttribute("member");
%>
	<h3> 회원수정</h3>
	<hr>
	<c:set var="member" value="${requestScope.member}"/>
	<form name="registerForm" action="MemberProcServlet?action=execute" method=post>
		<input type="hidden" id="id" name="id" value ="${member.id}"> 
		<label><span>ID : </span>
			${member.id}</label> <br><br>
		<label><span>Name : </span>
			<input type ="text" name ="name" value="${member.name}" size="10"></label> <br><br>
		<label><span>Password : </span>
			<input type ="password" name ="password" value="${member.password}"  size="10"></label> <br><br>
		<label><span>Birthday : </span>
			<input type ="text" name ="birthday"  value="${member.birthday}" size="10"></label> <br><br>
		<label><span>Address : </span>
			<input type ="text" name ="address" value="${member.address}"  size="50"></label> <br><br>
		<label><span></span><input type ="submit" value ="회원수정" name="B1"> &nbsp;&nbsp;</label>
		<label>	<input type="reset" value ="재작성" name = "B2"> </label>
	</form>

</center>
</body>
</html>