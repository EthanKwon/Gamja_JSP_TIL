package jspbook_ch04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int kor,eng,math;
		int sum =0;
		double avg =0;
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		kor = Integer.parseInt(request.getParameter("kor"));
		eng = Integer.parseInt(request.getParameter("eng"));
		math = Integer.parseInt(request.getParameter("math"));
		
		Score score = new Score(kor, eng, math);
		sum = score.scoreSum();
		avg = score.scoreAvg();
		
		out.println("<HTML>");
		out.println("<HEAD><TITLE>점수 결과</TITLE></HEAD>");
		out.println("<BODY><center>");
		out.println("<H2>과목별 점수</H2>");
		out.println("<HR>");
		out.println("<TABLE><TBODY>");
		out.println("<tr><td>국어</td><td>영어</td><td>수학</td><td>총합</td><td>평균</td></tr>");
		out.println("<tr><td>"+score.getKor()+"</td><td>"+score.getKor()+"</td><td>"+score.getKor()+"</td><td>"+sum+"</td><td>"+avg+"</td></tr>");		
		out.println("</BODY></HTML>");
		
		/*request.setAttribute("kor", score.getKor());
		request.setAttribute("eng", score.getEng());
		request.setAttribute("math", score.getMath());
		request.setAttribute("sum", sum);
		request.setAttribute("avg", avg);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/ch04/resultScore.jsp");

		dispatcher.forward(request, response);*/
	
	}

}
