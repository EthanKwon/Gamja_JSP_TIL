package member;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/login/FileServlet")
public class FileProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FileProc.class);
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		LOG.trace("");
		FileInputStream fis =null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis =null;
		int length;
		
		MemberDAO mDao = new MemberDAO();
		String sb = mDao.prepareDownload();
		String client = request.getHeader("User-Agent");
		
		File file = new File("");
		
		switch(action) {
		
		case "member":
			
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");
			
			if(client.indexOf("MSIE") != 1) {
				response.setHeader("Content-Disposition","attachment; filename=ClientMemberList.csv");
			} else {
				response.setHeader("Content-Disposition","attachment; filename=\"ClientMemberList.csv\"");
				response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
			}
			file = new File("c:/Temp/memberList.csv");
			response.setHeader ("Content-Length", "" + file.length());
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] bytes = new byte[1024];
				while ((length = bis.read(bytes)) != -1) {
					LOG.debug("Length = " + length);
					bos.write(bytes, 0, length);
				}
				bos.flush();
				bos.close();
				bis.close();
				fis.close();
			} catch (IllegalStateException e1) {
				LOG.info("doGet(): IllegalStateException Error");
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			}
			LOG.trace("MemberList file After try");
			break;
		case "bbs" :
			LOG.trace("");
	
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");
			
			if(client.indexOf("MSIE") != 1) {
				response.setHeader("Content-Disposition","attachment; filename=bbs_list.csv");
			} else {
				response.setHeader("Content-Disposition","attachment; filename=\"bbs_list.csv\"");
				response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
			}
			file = new File("c:/Temp/bbsList.csv");
			response.setHeader ("Content-Length", "" + file.length());
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] bytes = new byte[1024];
				while ((length = bis.read(bytes)) != -1) {
					LOG.debug("Length = " + length);
					bos.write(bytes, 0, length);
				}
				bos.flush();
				bos.close();
				bis.close();
				fis.close();
			} catch (IllegalStateException e1) {
				LOG.info("doGet(): IllegalStateException Error");
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			}
			LOG.trace("BbsList file After try");
			break;	
		default :
			break;
			}
		
	}

}
