package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.security.PasswordUtils;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
	MemberDAO memberDAO = new MemberDAOImpl();
	
	@Override
	public ServiceResult authenticate(MemberVO inputData) {
		ServiceResult result = null;
		MemberVO member = memberDAO.selectMemberForAuth(inputData);
		if(member != null) {
			String inputPass = inputData.getMemPass();
			String savedPass = member.getMemPass();
			if(PasswordUtils.matche(inputPass, savedPass)) {
				try {
					BeanUtils.copyProperties(inputData, member);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}
