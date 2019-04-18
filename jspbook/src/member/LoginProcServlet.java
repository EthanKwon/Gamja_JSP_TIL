package member;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class LoginProcServlet
 */
@WebServlet("/login/LoginProcServlet")
public class LoginProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id=0;
		String strId = request.getParameter("id");
		if(strId != null) {
			id = Integer.parseInt(strId);
		}
		String password = request.getParameter("password");
		
		MemberDAO mDao = new MemberDAO();
		int result = mDao.verifyIdPassword(id, password);
		String errorMessage = null;
		
		switch(result){
		case MemberDAO.ID_PASSWORD_MATCH:
			break;
		case MemberDAO.ID_DOES_NOT_EXIST:
			errorMessage = "아이디가 존재하지 않음"; break;
		case MemberDAO.PASSWORD_IS_WRONG:
			errorMessage = "패스워드가 틀렸음"; break;
		case MemberDAO.DATABASE_ERROR:
			errorMessage = "DB 오류"; break;
		}
		mDao.close();
		
		if(result == MemberDAO.ID_PASSWORD_MATCH){
			response.sendRedirect("loginMain.jsp");
		} else{
			/*request.setAttribute("error", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);*/
			String uri = "Login.jsp?error=" + URLEncoder.encode(errorMessage, "UTF-8");
			response.sendRedirect(uri);
		}
	}

}
