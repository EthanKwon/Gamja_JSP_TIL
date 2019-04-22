<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import = "member.*" %>
<%
	MemberDAO mDao = new MemberDAO();
	List<MemberDTO> list = mDao.selectAll();
	mDao.close();
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>메인 페이지</title>
	<style>
		td, th{ text-align: center; }
	</style>
</head>
<body><center>
	<h3>회원 명단</h3>
	<hr>
	<%=(String) session.getAttribute("memberName") %> 회원님이 로그인 하셨습니다. <br>
	<a href="/jspbook/login/MemberProcServlet?action=logout">로그아웃</a>
	<table border="1" style="border-collapse:collapse;">
	<tr><th>아이디</th><th>이름</th><th>생일</th><th>주소</th><th colspan=2>액션</th></tr>
	<%
	for(MemberDTO member : list){
		%>
	<tr><td> <%=member.getId() %> </td> 
	<td> <%=member.getName() %> </td>
	<td> <%=member.getBirthday() %> </td>
	<td> <%=member.getAddress() %> </td>
	<%
		String uriUpdate = "MemberProcServlet?action=update&id=" + member.getId();
		String uriDelete = "MemberProcServlet?action=delete&id=" + member.getId();
	%>
	<td><button onclick="location.href='<%=uriUpdate%>'">수정</button></td>
	<td><button onclick="location.href='<%=uriDelete%>'">삭제</button></td></tr>
	<%
	}
	%>
	</table>
</center>
</body>
</html>