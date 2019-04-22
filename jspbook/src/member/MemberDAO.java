package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	public static final int ID_PASSWORD_MATCH = 1;
	public static final int ID_DOES_NOT_EXIST = 2;
	public static final int PASSWORD_IS_WRONG = 3;
	public static final int DATABASE_ERROR = -1;
	private Connection conn;
	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";
	
	public MemberDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int verifyIdPassword(int id, String password) {
		System.out.println("verifyidPassword(): " + id + ", " + password);
		String query = "select hashed from info_member where id=?;";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String hashedPassword = "";
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1,id);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				hashedPassword = rs.getString(1);
				if(BCrypt.checkpw(password,hashedPassword))
					return ID_PASSWORD_MATCH;
				else
					return PASSWORD_IS_WRONG;
			}
			return ID_DOES_NOT_EXIST;
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
		return DATABASE_ERROR;
	}
	
	
	public void insertMember(MemberDTO member) {
		String query = "insert into info_member (password,name,birthday,address,hashed) values(?,?,?,?,?);";
		PreparedStatement pStmt = null;
		try {
			String hp = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, "*");
			pStmt.setString(2, member.getName());
			pStmt.setString(3, member.getBirthday());
			pStmt.setString(4, member.getAddress());
			pStmt.setString(5, hp);
			
			
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

	
	public void updateMember(MemberDTO member) {
		String query = "update info_member set password=?, name=?, birthday=?, address=? where id=?;";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, member.getPassword());
			pStmt.setString(2, member.getName());
			pStmt.setString(3, member.getBirthday());
			pStmt.setString(4, member.getAddress());
			pStmt.setInt(5, member.getId());
			
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
	
	public List<MemberDTO> selectAll(){
		String sql = "select * from info_member ;";
		List<MemberDTO> memberList = selectCondition(sql);
		return memberList;
	}
	
	public List<MemberDTO> selectAlldesc(){
		String sql = "select * from info_member order by id desc;";
		List<MemberDTO> memberList = selectCondition(sql);
		return memberList;
	}
	
	public List<MemberDTO> selectIdLookUp(String name){
		String sql = "select * from info_member where name like '"+ name +"' ;";
		List<MemberDTO> memberList = selectCondition(sql);
		return memberList;
	}
	
	public List<MemberDTO> selectCondition(String query) {
		PreparedStatement pStmt = null;
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				MemberDTO member = new MemberDTO();
				member.setId(rs.getInt("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setBirthday(rs.getString("birthday"));
				member.setAddress(rs.getString("address"));
				memberList.add(member);
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
		return memberList;
	}
	
	 public MemberDTO searchByNewMember() {
	    	String sql = "select * from info_member order by id desc limit 1;";
	    	MemberDTO mDto = selectOne(sql);
	    	return mDto;
	    }
	 public MemberDTO searchById(int memberId) {
	    	String sql = "select * from info_member where id=" + memberId + ";";
	    	MemberDTO mDto = selectOne(sql);
	    	return mDto;
	    }
	    
    public MemberDTO searchByName(String memberName) {
	    	String sql = "select * from info_member where name like '" + memberName + "';";
	    	MemberDTO mDto = selectOne(sql);
	    	return mDto;
	    }

    public MemberDTO selectOne(String query) {
	    	PreparedStatement pStmt = null;
	    	MemberDTO member = new MemberDTO();
	    	try {
				pStmt = conn.prepareStatement(query);
				ResultSet rs = pStmt.executeQuery();
				
				while (rs.next()) {
					member.setId(rs.getInt(1));
					member.setPassword(rs.getString(2));
					member.setName(rs.getString(3));
					member.setBirthday(rs.getString(4));
					member.setAddress(rs.getString(5));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pStmt != null && !pStmt.isClosed()) 
						pStmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
	    	return member;
	    }

	public void deleteMember(int id) {
		String query = "delete from info_member where id=?;";
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
	
	public boolean searchMember(String name) {
		PreparedStatement pStmt = null;
		boolean findMember = false;
		String query = "select name from info_member";
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				if(rs.getString("name").equals(name)) {
					findMember = true;
					break;
				}
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
		return findMember;	
	}
	
	public boolean IsMember(int id, String password) {
		PreparedStatement pStmt = null;
		boolean findMember = false;
		String query = "select id,password from info_member";
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				if(rs.getInt("id")==id) {
					if(rs.getString("password").equals(password)) {
						findMember = true;
						break;
					}
				}
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
		return findMember;	
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
