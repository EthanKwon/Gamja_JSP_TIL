package bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;

/**
 * Servlet implementation class bbsServlet
 */
@WebServlet("/bbs/BbsServlet")
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		
		String action = request.getParameter("action");
		int id = 0;
		int memberId=0;
		String title = new String();
		String content = new String();
		String name = new String();
		
		String message = new String();
		BbsDAO bDao = new BbsDAO();
		BbsDTO bDto = new BbsDTO();
		
		switch(action) {
		
		case "intoBbs" :
			
			if(!session.getAttribute("count").equals("1")) {
				session.setAttribute("count", 1);
			}
			response.sendRedirect("bbsMain.jsp");
			
			break;
		
		case "upPage" :
			
			int plusCount = (Integer)session.getAttribute("count")+1;
			if(plusCount>bDao.countPage()) {
				message = "마지막 페이지 입니다.";
				request.setAttribute("message", message);
				request.setAttribute("url", "bbsMain.jsp");
				rd = request.getRequestDispatcher("/login/alertMsg.jsp");
				rd.forward(request, response);
				bDao.close();
				break;
			}
			session.setAttribute("count", plusCount);
			response.sendRedirect("bbsMain.jsp");
			break;
			
		case "downPage" :
			int downCount = (Integer)session.getAttribute("count")-1;
			if(downCount<1) {
				message = "첫 페이지 입니다.";
				request.setAttribute("message", message);
				request.setAttribute("url", "bbsMain.jsp");
				rd = request.getRequestDispatcher("/login/alertMsg.jsp");
				rd.forward(request, response);
				bDao.close();
				break;
			}
			session.setAttribute("count", downCount);
			response.sendRedirect("bbsMain.jsp");
			break;
		
		case "insert" :
			if(!session.getAttribute("memberId").equals("")) {
				memberId = (Integer)session.getAttribute("memberId");			
			}
			
			title = request.getParameter("title");
			content = request.getParameter("content");
			
			bDto = new BbsDTO(title,content,memberId);
			System.out.println(bDto.toString());
			
			bDao = new BbsDAO();
			bDao.insertBbs(bDto);
			
			message = "내용을 저장하였습니다. \\n" + bDto.toStringUpdate();
			request.setAttribute("message", message);
			request.setAttribute("url", "bbsMain.jsp");
			rd = request.getRequestDispatcher("/login/alertMsg.jsp");
			rd.forward(request, response);
			bDao.close();
			break;
			
		case "look" :
			response.sendRedirect("bbsLook.jsp?id="+request.getParameter("id"));
			break;
			
		case "update" :
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));			
			}
			if(!request.getParameter("memberId").equals("")) {
				memberId = Integer.parseInt(request.getParameter("memberId"));			
			}
			if (memberId != (Integer)session.getAttribute("memberId")) {
				/*System.out.println("권한 없음");
				response.sendRedirect("loginMain.jsp");
				*/
				message = "수정권한이 없습니다. \\n";
				request.setAttribute("message", message);
				request.setAttribute("url", "BbsServlet?action=intoBbs");
				rd = request.getRequestDispatcher("/login/alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			bDao = new BbsDAO();
			bDto = bDao.selectOne(id);
			request.setAttribute("bbs", bDto);
			rd = request.getRequestDispatcher("bbsUpdate.jsp");
			rd.forward(request, response);
			break;
			
		case "execute" :
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));			
			}
			
			title = request.getParameter("title");
			content = request.getParameter("content");
			
			bDto = new BbsDTO(id,title,content);
			System.out.println(bDto.toString());
			
			bDao = new BbsDAO();
			bDao.updateBbs(bDto);
			
			message = "다음과 같이 수정하였습니다. \\n" + bDto.toStringUpdate();
			request.setAttribute("message", message);
			request.setAttribute("url", "bbsMain.jsp");
			rd = request.getRequestDispatcher("/login/alertMsg.jsp");
			rd.forward(request, response);
			bDao.close();
			break;
			
		case "delete":
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));			
			}
			if(!request.getParameter("id").equals("")) {
				memberId = Integer.parseInt(request.getParameter("memberId"));			
			}
			if (memberId != (Integer)session.getAttribute("memberId")) {
				/*System.out.println("권한 없음");
				response.sendRedirect("loginMain.jsp");
				*/
				message = "삭제권한이 없습니다. \\n";
				request.setAttribute("message", message);
				request.setAttribute("url", "bbsMain.jsp");
				rd = request.getRequestDispatcher("/login/alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			
			bDao = new BbsDAO();
			bDao.deleteBbs(id);
			message = "게시물 " + id + " (이)가 삭제 되었습니다.";
			request.setAttribute("message", message);
			request.setAttribute("url", "bbsMain.jsp");
			rd = request.getRequestDispatcher("/login/alertMsg.jsp");
			rd.forward(request, response);
			break;
			
		default:
		}
		
	}

}
