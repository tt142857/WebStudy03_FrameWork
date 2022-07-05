package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

/**
 * TDD(Test Driven Development : 테스트 주도형 개발) 개발방법론 
 * EDD(Eevent Driven Development : 이벤트 주도형 개발) 
 *
 */
public class MemberServiceTest {
	MemberService service = new MemberServiceImpl();

	@Test
	public void testCreateMember() {
		MemberVO member = new MemberVO();
		member.setMemId("a001");
		member.setMemPass("java");
		member.setMemName("신규");
		member.setMemBir("1999-01-01");
		member.setMemZip("000-000");
		member.setMemAdd1("대전");
		member.setMemAdd2("오류");
		member.setMemHp("000-000-0000");
		member.setMemMail("aa@gmail.net");
		ServiceResult result = service.createMember(member);
		assertEquals(ServiceResult.PKDUPLICATED, result);
		member.setMemId("a003");
		result = service.createMember(member);
		assertEquals(ServiceResult.OK, result);
	}
}













