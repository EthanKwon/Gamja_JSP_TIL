<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import = "member.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>메인 페이지</title>
	<style>
		thead { background-color : #00FF00; color : #494D4A;}
		th {
			border: 2px solid #333333;
			padding : 5px;
		}
		td, th{ text-align: center; padding :3px; }
		
		
	</style>
</head>
<body><center>
	<h2>회원 명단</h2>
	<p>${memberName} 회원님이 로그인 하셨습니다. </p>
	<hr>
	<a href="/jspbook/bbs/BbsServlet?action=list&page=1">게시판</a> &nbsp; &nbsp;
	<a href="/jspbook/twitter/twitterServlet?action=login">트윗</a>&nbsp; &nbsp;
	<a href="/jspbook/login/MemberProcServlet?action=logout">로그아웃</a> <br><br>
	<table border="1" style="border-collapse:collapse;">
	<thead>
	<tr><th>아이디</th><th style="width:100px">이름</th><th style="width:130px">생일</th><th style="width:250px">주소</th><th colspan=2>액션</th></tr>
	</thead>
	
	<c:set var="memberList" value="${requestScope.memberList}"/>
	<c:forEach var="member" items="${memberList}">
	<tr><td> ${member.id} </td> 
	<td> ${member.name} </td>
	<td> ${member.birthday} </td>
	<td> ${member.address} </td>
	<td><button onclick="location.href='MemberProcServlet?action=update&id=${member.id}'">수정</button></td>
	<td><button onclick="location.href='MemberProcServlet?action=delete&id=${member.id}'">삭제</button></td></tr>
	
	</c:forEach>
	<tfoot>
	<tr>
	<th colspan="6">
	현재 페이지는 ${requestScope.page} 쪽 입니다.<br><br>
	<button onclick="location.href='MemberProcServlet?action=intoMain&page=${requestScope.page-1}'">&lt;</button>
	<c:set var="listPages" value="${requestScope.pageList}"/>
	<c:forEach var="listPage" items="${listPages}">
	${listPage }
	</c:forEach>
	<button onclick="location.href='MemberProcServlet?action=intoMain&page=${requestScope.page+1}'">&gt;</button>
	</th>
	</tr>
	</tfoot>
	</table>
</center>
</body>
</html>