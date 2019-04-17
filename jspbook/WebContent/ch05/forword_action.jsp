<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>forward_action.jsp</title>
</head>
<body>
<h2>include_action에서 footer.jsp 호출</h2>
<hr>
include_action에서 호출한 메세지입니다.<Br>
<jsp:forward page="footer.jsp">
	<jsp:param name="email" value="test@test.net"/>
	<jsp:param value="000-0000-0000" name="tel"/>
</jsp:forward>
</body>
</html>
</body>
</html>