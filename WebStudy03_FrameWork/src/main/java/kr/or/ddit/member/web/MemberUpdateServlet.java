package kr.or.ddit.member.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 초기값을 가지고 있는 수정 양식 제공 - member/memberForm 재활용
		// 이 세션이 정상인지, 로그인 아이디가 정상인지 확인해야 함
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
	
		String viewName = null;
		if(ObjectUtils.isEmpty(authMember)) {
			viewName = "/index.tiles";
		}
		else {
			String memId = authMember.getMemId();
			MemberVO member = service.retrieveMember(memId);
			req.setAttribute("member", member);
			viewName = "/member/memberForm.tiles";
		}
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터에 포함된 특수문자에 대한 디코딩 방식 설정
		// 전달되는 여러개의 파라미터를 Domain layer를 이용해 바인딩
		// 해당 VO는 정렬 처리가 완료되기 전까지 공유해야 함
		// 요청 데이터 검증
		// 검증을 통과하면 로직을 사용하여 수정
		// 통과하지 못하면, 기존 입력 데이터의 검증 결과 데이터를 가지고 view layer로 이동
		
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = ValidateUtils.validate(member, errors, UpdateGroup.class);
		String viewName = null;
		
		if (valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
				case INVALIDPASSWORD:
					req.setAttribute("message", "비밀번호 오류");
					viewName = "/member/memberFrom.tiles";
					break;
				case FAIL:
					req.setAttribute("message", "서버의 문제로 수정을 못했음. 쫌따 다시하셈.");
					viewName = "/member/memberForm.tiles";
					break;
				default:
					req.getSession().setAttribute("message", "수정 성공");
					req.setAttribute("member", member);
					viewName = "redirect:/myPage.do";
					break;
			}
		} else {
			viewName = "/member/memberForm.tiles";
		}
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
}
