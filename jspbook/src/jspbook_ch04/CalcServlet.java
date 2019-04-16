package jspbook_ch04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int num1,num2;
		int result;
		String op;
		
		res.setCharacterEncoding("text/html; charset=UTF-8");
		
		res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		
		num1 = Integer.parseInt(req.getParameter("num1"));
		num2 = Integer.parseInt(req.getParameter("num2"));
		op = req.getParameter("operator");
		Calc calc = new Calc();
		result = calc.calc(num1, num2, op);
		
		out.println("<HTML>");
		out.println("<HEAD><TITLE>계산기</TITLE></HEAD>");
		out.println("<BODY><center>");
		out.println("<H2>계산결과</H2>");
		out.println("<HR>");
		out.println(num1+" " +op + " " + num2 + " = " +result);
		out.println("</BODY></HTML>");
	}

}
