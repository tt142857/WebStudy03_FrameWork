package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원 관리(CRUD)를 위한 Business Logic Layer
 *
 */
public interface MemberService {
	/**
	 * 회원 가입 로직
	 * @param member 가입할 회원의 정보를 가진 VO
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createMember(MemberVO member);
	
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO);
	
	/**
	 * 회원 상세 조회
	 * @param memId 조회할 회원 아이디
	 * @return 존재하지 않는 경우, {@link PKNotFoundException} 발생
	 */
	public MemberVO retrieveMember(String memId);
	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return {@link PKNotFoundException}, INVALIDPASSWORD, OK, FAIL}
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	/**
	 * 회원 탈퇴
	 * @param member 탈퇴할 회원의 아이디와 비밀번호
	 * @return {@link PKNotFoundException}, INVALIDPASSWORD, OK, FAIL}
	 */
	public ServiceResult removeMember(MemberVO member);
}
