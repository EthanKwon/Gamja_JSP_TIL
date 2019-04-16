<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>결과 확인</title>
</head>
<body>
	<h1>결과 확인</h1>
	<hr>
	<% 
		int kor = (int)request.getAttribute("kor");
		int eng = (int)request.getAttribute("eng");
		int math = (int)request.getAttribute("math");
		int sum = (int)request.getAttribute("sum");
		double avg = (double)request.getAttribute("avg");
	%>
	<table>
		<tbody>
			<tr><td>국어 점수</td><td>영어 점수</td><td>수학 점수</td><td>합계</td><td>평균</td><td></td></tr>
			<tr>
				<td><%= kor %></td>
				<td><%= eng %></td>
				<td><%= math %></td>
				<td><%= sum %></td>
				<td><%= avg %></td>
			</tr>
		</tbody>
	</table>
</html>