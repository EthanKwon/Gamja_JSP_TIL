<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import = "bbs.*" %>
<%
	BbsDAO bDao = new BbsDAO();
	int count = (Integer)session.getAttribute("count");
	List<BbsDTO> list = bDao.selectNameAllPage(count);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<style>
	thead { background-color : #00FF00; color : #494D4A;}
	th {
		border: 2px solid #333333;
		padding : 10px;
	}
	td {
		text-align :center;
		border: 1px solid #333333;
		padding : 5px;	
	}
	.button {
		float : right;
	}
	.foot {
		border: 0;
	}
	
</style>
</head>
<body>
<center>
	<h2>게시판</h2>
	<p><%=(String) session.getAttribute("memberName") %> 회원님이 로그인 하셨습니다. </p>
	<hr>
	<a href="/jspbook/login/loginMain.jsp">메인화면</a> &nbsp;&nbsp;
	<a href="/jspbook/twitter/twitterServlet?action=login">트윗</a>&nbsp; &nbsp;
	<a href="/jspbook/login/MemberProcServlet?action=logout">로그아웃</a> <br><br>
	<div>
	<table border="1" style="border-collapse:collapse;">
	<thead>
	<tr><th>게시번호</th><th style="width:200px">제목</th><th>글쓴이</th><th>작성날짜</th><th>액션</th></tr>
	</thead>
	<tbody>
	<%
	for(BbsDTO bbs : list){
	String uriLook = "BbsServlet?action=look&id=" + bbs.getId();
		%>
	<tr><td> <%=bbs.getId() %> </td> 
	<td><a href="<%=uriLook%>"><%=bbs.getTitle() %></a></td>
	<td> <%=bbs.getName() %> </td>
	<td> <%=bbs.getDate() %> </td>
	<%
		String uriUpdate = "BbsServlet?action=update&id=" + bbs.getId()+ "&memberId=" + bbs.getMemberId();
		String uriDelete = "BbsServlet?action=delete&id=" + bbs.getId()+ "&memberId=" + bbs.getMemberId();
	%>
	<td>
	<button onclick="location.href='<%=uriUpdate%>'">수정</button>&nbsp;&nbsp;
	<button onclick="location.href='<%=uriDelete%>'">삭제</button></td></tr>
	<%
	}
	%>
	</tbody>
	<tfoot>
	<tr ><th class="foot"  colspan="5"><button class="button" onclick="location.href='bbsInsert.jsp'">작성</button></th></tr>
	<%
		String upPageURL = "BbsServlet?action=downPage";
		String downPageURL = "BbsServlet?action=upPage";
	%>
	<tr>
	<th colspan="5">
	<button onclick="location.href='BbsServlet?action=downPage'">&lt;</button>
	<%=count%>/<%=bDao.countPage() %>
	<button onclick="location.href='BbsServlet?action=upPage'">&gt;</button>
	</th>
	</tr>
	</tfoot>
	</table>
	</div>
	<br>
</center>
</body>
</html>