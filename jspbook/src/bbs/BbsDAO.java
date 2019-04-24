package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BbsDAO {
	private Connection conn;
	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";
	
	public BbsDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//삽입
	public void insertBbs(BbsDTO write) {
		String query = "insert into bbs (memberId,title, content) values(?,?,?);";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, write.getMemberId());
			pStmt.setString(2, write.getTitle());
			pStmt.setString(3, write.getContent());
			
			pStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//변경 
	public void updateBbs(BbsDTO write) {
		String query = "update bbs set title=?, content=?, date=now() where id =?;";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, write.getTitle());
			pStmt.setString(2, write.getContent());
			pStmt.setInt(3, write.getId());
			
			pStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public void updatePageBbs(int num) {
		String query = "update bbs set page=? where is not null order by id desc limit 10;";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, num);
			
			pStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//삭제
	public void deleteBbs(int id) {
		String query = "delete from bbs where id=?;";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			
			pStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//총 페이지 수 얻기
	public int totalPage() {
		int pageList = 10;
		String query = "select count(*) from bbs;";
		PreparedStatement pStmt = null;
		int count = 0;
		int totalCount=0;
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){ //데이터베이스 속성값 오타 주의 !!
				count = rs.getInt("count(*)");
			}
			
			totalCount = count/pageList;
			
			if(count%pageList > 0) {
				totalCount++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return totalCount;
	}
	
		
	//memberId로 출력
	public BbsDTO selectOne(int id) {
		String query = "select bbs.id, bbs.memberId, bbs.title, mb.name, bbs.date, bbs.content from bbs "
				+ "inner join info_member as mb "
				+ "on mb.id = bbs.memberId where bbs.id =" + id + ";";
		PreparedStatement pStmt = null;
		BbsDTO write = new BbsDTO();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){ //데이터베이스 속성값 오타 주의 !!
				write.setId(rs.getInt("id"));
				write.setMemberId(rs.getInt("memberId"));
				write.setTitle(rs.getString("title"));
				write.setName(rs.getString("name"));
				write.setDate(rs.getString("date"));
				write.setContent(rs.getString("content"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return write;
	}

	//전체 출력 함수 기본틀
	public List<BbsDTO> selectCondition(String query) {
		PreparedStatement pStmt = null;
		List<BbsDTO> writeList = new ArrayList<BbsDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				BbsDTO write = new BbsDTO();
				write.setId(rs.getInt("id"));
				write.setMemberId(rs.getInt("memberId"));
				write.setTitle(rs.getString("title"));
				write.setDate(rs.getString("date"));
				write.setContent(rs.getString("content"));
				writeList.add(write);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return writeList;
	}

	//이름을 포함한 전체 출력 기본틀(최신순,내림차순)
	public List<BbsDTO> selectNameCondition(String sql){
		PreparedStatement pStmt = null;
		List<BbsDTO> writeList = new ArrayList<BbsDTO>();
		try {
			pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				BbsDTO write = new BbsDTO();
				write.setId(rs.getInt("id"));
				write.setMemberId(rs.getInt("memberId"));
				write.setTitle(rs.getString("title"));
				write.setName(rs.getString("name"));
				write.setDate(rs.getString("date"));
				writeList.add(write);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return writeList;
	}
	
	//이름에 맞는 게시물만 출력
	public List<BbsDTO> selectNameAllWriter(String name){
		String sql = "select bbs.id, bbs.title, mb.name, bbs.date from bbs "
				+ "inner join info_member as mb "
				+ "on mb.id = bbs.memberId where mb.name like '"+name+"';";
		List<BbsDTO> writeList = selectNameCondition(sql);
		return writeList;
		
	}
	
	//이름을 포함한 전체 출력(최신순,내림차순)
	public List<BbsDTO> selectNameAlldesc(){
		String sql = "select bbs.id,bbs.memberId, bbs.title, mb.name, bbs.date from bbs "
				+ "inner join info_member as mb "
				+ "on mb.id = bbs.memberId order by id desc;";
		System.out.println(sql);
		List<BbsDTO> writeList = selectNameCondition(sql);
		return writeList;
	}
	
	//페이지 구현
	public List<BbsDTO> selectNameAllPage(int currPage){
		int startBbs = (currPage-1)*10;
		String sql = "select bbs.id,bbs.memberId, bbs.title, mb.name, bbs.date from bbs "
				+ "inner join info_member as mb "
				+ "on mb.id = bbs.memberId order by id desc limit "+ startBbs+",10;";
		System.out.println(sql);
		List<BbsDTO> writeList = selectNameCondition(sql);
		return writeList;
	}
	
	//컨텐츠를 포함한 전체 출력 기본틀
	public List<BbsDTO> selectContentCondition(String sql){
		PreparedStatement pStmt = null;
		List<BbsDTO> writeList = new ArrayList<BbsDTO>();
		try {
			pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				BbsDTO write = new BbsDTO();
				write.setTitle(rs.getString("title"));
				write.setName(rs.getString("name"));
				write.setDate(rs.getString("date"));
				write.setContent(rs.getString("content"));
				writeList.add(write);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return writeList;
	}
	
	//아이디에 맞는 게시물만 출력
	public List<BbsDTO> selectContentId(int id){
		String sql = "select bbs.title, mb.name, bbs.date, bbs.content from bbs "
				+ "inner join info_member as mb "
				+ "on mb.id = bbs.memberId where bbs.id =" + id + ";";
		List<BbsDTO> writeList = selectContentCondition(sql);
		return writeList;
	}
	
	//이름에 맞는 게시물만 출력
	public List<BbsDTO> selectContentAllWriter(String name){
		String sql = "select bbs.title, mb.name, bbs.date, bbs.content from bbs "
				+ "inner join info_member as mb "
				+ "on mb.id = bbs.memberId where mb.name like '" + name +"';";
		List<BbsDTO> writeList = selectContentCondition(sql);
		return writeList;
	}
	
	
	public void close() {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
