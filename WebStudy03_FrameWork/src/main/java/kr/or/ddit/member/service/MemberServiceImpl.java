package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	MemberDAO memberDao = new MemberDAOImpl();

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(memberDao.selectMemberForAuth(member)==null) {
			int rowcnt = memberDao.insertMember(member);
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}
	
	@Override
	public List<MemberVO> retrieveMemberList() {
		List<MemberVO> memberList = memberDao.selectMemberList();
		return memberList;
	}
	
	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = memberDao.selectMember(memId);
		if(member == null) {
			throw new PKNotFoundException(String.format("%s 아이디를 가진 회원이 없음", member.getMemId()));
		}
		return member;
	}
}
