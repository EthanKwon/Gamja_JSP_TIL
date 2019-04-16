package jspbook_ch04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		response.setCharacterEncoding("text/html; charset=UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Member member = new Member(name, email,phone);
		member.getNewId();
		
		out.println("<HTML>");
		out.println("<HEAD><TITLE>회원 가입 결과</TITLE></HEAD>");
		out.println("<BODY><center>");
		out.println("<H2>회원 정보</H2>");
		out.println("<HR>");
		out.println("<TABLE><TBODY>");
		out.println("<tr><td>아이디 : </td><td>"+ member.getId() +"</td></tr>");
		out.println("<tr><td>이름 : </td><td>"+ member.getName() +"</td></tr>");
		out.println("<tr><td>이메일 : </td><td>"+ member.getEmail() +"</td></tr>");
		out.println("<tr><td>전화번호 : </td><td>"+ member.getPhone() +"</td></tr>");
		out.println("</BODY></HTML>");
		
	}

}
