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

	@Test
	public void testSelectMember() {
		String memId = "a001";
		MemberVO member = dao.selectMember(memId);
		assertNotNull(member);
		System.out.println(member);
		System.out.println(member.getBuyList());
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
	public void testUpdateMember() {
		MemberVO member = new MemberVO();
		member.setMemName("엄준식");
		member.setMemAdd1("서울");
		member.setMemAdd2("서울");
		member.setMemHometel("000-111-2222");
		member.setMemComtel("000-333-4444");
		member.setMemHp("000-5555-6666");
		member.setMemMail("aa@gmail.net");
		member.setMemJob("학생");
		member.setMemLike("롤");
		member.setMemMemorial("롤20주년");
		member.setMemMemorialday("2022-06-28");
		member.setMemId("a004");
		assertNotNull(member);
		int rowcnt = dao.updateMember(member);
		assertEquals(1, rowcnt);
	}

	@Test
	public void testDeleteMember() {
		String memId = "b001";
		int rowcnt = dao.deleteMember(memId);
		assertEquals(1, rowcnt);
	}
}


















