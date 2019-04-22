<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.*" %>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="info" scope="page" class="member.MemberDTO"/>
<jsp:setProperty name="info" property="*"/>
<%
	MemberDAO mDao = new MemberDAO();
	mDao.insertMember(info);
	mDao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입 완료</title>
</head>
<body>
	<h3>축하합니다. 회원가입이 완료 되었습니다.</h3>

</body>
</html>