package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOTest {
	MemberDAO dao = new MemberDAOImpl();			
			

	@Test
	public void testSelectMemberForAuth() {
		MemberVO inputData = new MemberVO();
		inputData.setMemId("a001");
		MemberVO member = dao.selectMemberForAuth(inputData);
		System.out.println(member);
		assertNotNull(member);
	}

	@Test(expected=RuntimeException.class)
	public void testInsertMemberThrow() {
		MemberVO member = new MemberVO();
		dao.insertMember(member);
	}
	
	@Test
	public void testInsertMember() {
		MemberVO member = new MemberVO();
		member.setMemId("a004");
		member.setMemPass("java");
		member.setMemName("신규");
		member.setMemBir("1999-01-01");
		member.setMemZip("000-000");
		member.setMemAdd1("대전");
		member.setMemAdd2("오류");
		member.setMemHp("000-000-0000");
		member.setMemMail("aa@gmail.net");
		
		int rowcnt = dao.insertMember(member);
		System.out.println(rowcnt);
		assertEquals(1, rowcnt);
		
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		assertNotNull(memberList);
		assertNotEquals(0, memberList.size());
		System.out.println(memberList);
	}

}


















