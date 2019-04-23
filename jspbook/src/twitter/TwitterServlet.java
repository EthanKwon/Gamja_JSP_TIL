package twitter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class twitterServlet
 */
@WebServlet("/twitter/twitterServlet")
public class TwitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String msg = request.getParameter("msg");
		HttpSession session = request.getSession();
		Object username = session.getAttribute("memberName");
		String action = request.getParameter("action");
		ServletContext application = request.getServletContext();
		ArrayList<String> msgs=(ArrayList<String>)application.getAttribute("msgs");
		
		
		
		switch(action) {
		case "msg" :
			
			if(msgs == null){
				msgs = new ArrayList<String>();
				application.setAttribute("msgs",msgs);
			}
			
			msgs.add(username+" :: "+ msg +" , " + new java.util.Date());
			
			application.log(msg+"추가됨");
			
			response.sendRedirect("twitter_list.jsp");
			break;
			
		case "login" :
			
			if(msgs == null){
				msgs = new ArrayList<String>();
				application.setAttribute("msgs",msgs);
			}
			
			msgs.add(username+ " 님이 로그인 하셨습니다. ," + new java.util.Date());
			
			application.log(msg+"추가됨");
			
			response.sendRedirect("twitter_list.jsp");
			break;
			
		case "logout" :
			
			if(msgs == null){
				msgs = new ArrayList<String>();
				application.setAttribute("msgs",msgs);
			}
			
			msgs.add(username+ " 님이 로그아웃 하셨습니다. ," + new java.util.Date());
			
			application.log(msg+"추가됨");
			
			response.sendRedirect("/jspbook/login/loginMain.jsp");
			break;
		default:
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
