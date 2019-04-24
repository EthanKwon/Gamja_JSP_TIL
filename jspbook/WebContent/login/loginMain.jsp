<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import = "member.*" %>
<%
	MemberDAO mDao = new MemberDAO();
	int currPage = (Integer)request.getAttribute("page");
	List<MemberDTO> list = mDao.selectNameAllPage(currPage);
	List<String> listPages = (List<String>)request.getAttribute("pageList");
	mDao.close();
%>
 
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
	<p><%=(String) session.getAttribute("memberName") %> 회원님이 로그인 하셨습니다. </p>
	<hr>
	<a href="/jspbook/bbs/BbsServlet?action=intoBoard">게시판</a> &nbsp; &nbsp;
	<a href="/jspbook/twitter/twitterServlet?action=login">트윗</a>&nbsp; &nbsp;
	<a href="/jspbook/login/MemberProcServlet?action=logout">로그아웃</a> <br><br>
	<table border="1" style="border-collapse:collapse;">
	<thead>
	<tr><th>아이디</th><th style="width:100px">이름</th><th style="width:130px">생일</th><th style="width:250px">주소</th><th colspan=2>액션</th></tr>
	</thead>
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
	<tfoot>
	<tr>
	<th colspan="6">
	<%
		String upPageURL = "MemberProcServlet?action=pageButton&page="+ (currPage+1);
		String downPageURL = "MemberProcServlet?action=pageButton&page="+ (currPage-1);
	%>
	현재 페이지는 <%=currPage %>쪽 입니다.<br><br>
	<button onclick="location.href='<%=downPageURL%>'">&lt;</button>
	<% for(String listPage : listPages) out.print(listPage + " ");%>
	<button onclick="location.href='<%=upPageURL%>'">&gt;</button>
	</th>
	</tr>
	</tfoot>
	</table>
</center>
</body>
</html>