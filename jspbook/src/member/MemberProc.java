package member;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberProc
 */
@WebServlet("/login/MemberProcServlet")
public class MemberProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberProc() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String action = request.getParameter("action");
		int id = 0;
		System.out.println(action + "," + id);
		MemberDAO mDao = new MemberDAO();
		MemberDTO member = new MemberDTO();
		
		String password = new String();
		String name = new String();
		String birthday = new String();
		String address = new String();
		String message = new String();
		RequestDispatcher rd;
		
		//페이지 관련 변수
		int pageNum = 1;
		List<String> pageLists = new ArrayList<String>();
		
		switch(action) {
		case "intoMain" :
			request.setAttribute("page", 1);
			session.setAttribute("MemberPage", 1);
			
			pageNum = 1;
			
			while(pageNum <= mDao.totalPage()) {
				 pageLists.add("<a href='MemberProcServlet?action=pageButton&page="+pageNum+"'>"+pageNum+"</a>");
				pageNum++;
			}
			request.setAttribute("pageList", pageLists);
			rd = request.getRequestDispatcher("loginMain.jsp");
			rd.forward(request, response);
			break;
			
		case "pageButton" :
			
			int currPage = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("MemberPage", currPage);
			
			if(currPage>mDao.totalPage()) {
				message = "마지막 페이지 입니다.";
				request.setAttribute("message", message);
				request.setAttribute("url", "MemberProcServlet?action=pageButton&page="+mDao.totalPage());
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				mDao.close();
				break;
			} else if(currPage<1) {
				message = "첫 페이지 입니다.";
				request.setAttribute("message", message);
				request.setAttribute("url", "MemberProcServlet?action=pageButton&page=1");
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				mDao.close();
				break;
			}
			
			pageNum = 1;
			while(pageNum <= mDao.totalPage()) {
				pageLists.add("<a href='MemberProcServlet?action=pageButton&page="+pageNum+"'>"+pageNum+"</a>");
				pageNum++;
			}
			request.setAttribute("pageList", pageLists);
			request.setAttribute("page", currPage);
			rd = request.getRequestDispatcher("loginMain.jsp");
			rd.forward(request, response);
			break;
		
		case "update":
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));			
			}
			if (id != (Integer)session.getAttribute("memberId")) {
				/*System.out.println("권한 없음");
				response.sendRedirect("loginMain.jsp");
				*/
				message = "수정권한이 없습니다. \\n";
				request.setAttribute("message", message);
				request.setAttribute("url", "MemberProcServlet?action=pageButton&page="+session.getAttribute("MemberPage"));
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			mDao = new MemberDAO();
			member = mDao.searchById(id);
			request.setAttribute("member", member);
			rd = request.getRequestDispatcher("update.jsp");
			rd.forward(request, response);
			break;
			
		case "delete":
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));			
			}
			if (id != (Integer)session.getAttribute("memberId")) {
				/*System.out.println("권한 없음");
				response.sendRedirect("loginMain.jsp");
				*/
				message = "삭제권한이 없습니다. \\n";
				request.setAttribute("message", message);
				request.setAttribute("url", "loginMain.jsp");
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			
			mDao = new MemberDAO();
			mDao.deleteMember(id);
			message = "id = " + id + "(이)가 삭제 되었습니다.";
			String url = "loginMain.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			break;
			
		case "login":
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));			
			}
			password = request.getParameter("password");
			
			mDao = new MemberDAO();
			
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
			
			if(result == MemberDAO.ID_PASSWORD_MATCH){
				member = mDao.searchById(id);
				session.setAttribute("memberId", id);
				session.setAttribute("memberName", member.getName());
				System.out.println(member.toString());
				response.sendRedirect("loginMain.jsp");
			} else{
				/*request.setAttribute("error", errorMessage);
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);*/
				String uri = "Login.jsp?error=" + URLEncoder.encode(errorMessage, "UTF-8");
				response.sendRedirect(uri);
			}
			mDao.close();
			break;
			
		case "logout":
			session.removeAttribute("memberId");
			session.removeAttribute("memberName");
			session.removeAttribute("currPage");
			
			response.sendRedirect("Login.jsp");
			break;
			
		case "register":

			password = request.getParameter("password");
			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			mDao = new MemberDAO();
			
			member = new MemberDTO(password, name, birthday, address);
			System.out.println(member.toString());
			mDao.insertMember(member);
			
			member = mDao.searchByNewMember();
			session.setAttribute("memberId", member.getId());
			session.setAttribute("memberName", name);
			System.out.println(member.toString());
			
			/*message = "귀하의 아이디는 " + member.getId() + " 입니다.";
			url = "loginMain.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			mDao.close();*/
//			response.sendRedirect("loginMain.jsp");
			
			break;
			
		case "execute":
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));			
			}
			
			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			
			member = new MemberDTO(id,"*", name, birthday, address);
			System.out.println(member.toString());
			
			mDao = new MemberDAO();
			mDao.updateMember(member);
			
			message = "다음과 같이 수정하였습니다. \\n" + member.toString();
			request.setAttribute("message", message);
			request.setAttribute("url", "loginMain.jsp");
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			mDao.close();
			break;
			
		default:
		}
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
