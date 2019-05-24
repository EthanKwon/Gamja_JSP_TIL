package memberTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import member.MemberDAO;

public class MemberDAOTest {
	private MemberDAO mDao = new MemberDAO();
	
	@Before
	public void before() {
		mDao = new MemberDAO();
	}
	
	@Test
	public void testVerifydPassword() {
		assertEquals(MemberDAO.ID_PASSWORD_MATCH, mDao.verifyIdPassword(3000, "java"));
	}
	
	
	

}
