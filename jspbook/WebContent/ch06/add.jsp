<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add.jsp</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");

	String productName = request.getParameter("product");
	ArrayList list = (ArrayList)session.getAttribute("ProductList");
	if(list == null){
		list= new ArrayList();
		session.setAttribute("productList",list);
	}
	list.add(productName);
%>
<script>
	alert("<%=productName %>이(가) 추가되었습니다!!");
	history.go(-1);
</script>

</body>
</html>