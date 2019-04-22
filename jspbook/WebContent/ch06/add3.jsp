<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" %>
<%@ page import="jspbook_ch06.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");

	String productName = request.getParameter("product");
	int productCount = Integer.parseInt(request.getParameter("count"));
	ArrayList<CartDTO> list = (ArrayList<CartDTO>)session.getAttribute("ProductList3");
	if(list == null){
		list= new ArrayList<CartDTO>();
		session.setAttribute("productList3",list);
	}
	CartDTO cart = new CartDTO();
	cart.setName(productName);
	cart.setCount(productCount);
	list.add(cart);
%>
<script>
	alert("<%=productName %> <%=productCount%>개 가  추가되었습니다!!");
	history.go(-1);
</script>

</body>
</html>