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

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do") // RESTful URI(X)
public class MemberInsertServlet extends HttpServlet{
	MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setAttribute("contents", "/WEB-INF/views/member/memberForm.jsp");
//		String view = "/WEB-INF/views/template.jsp";
//		req.getRequestDispatcher(view).forward(req, resp);
		String viewName = "/member/memberForm.tiles";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
//		member.setMemId(req.getParameter("memId"));
		
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member, errors, InsertGroup.class);
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createMember(member);	
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디 중복");
				viewName = "/member/memberForm.tiles";
				break;
			case FAIL:
				req.setAttribute("message", "서버의 문제로 등록을 못했음. 쫌따 다시하셈.");
				viewName = "/member/memberForm.tiles";
				break;	
			default:
				req.getSession().setAttribute("message", "등록 성공");
				viewName = "redirect:/login/loginForm.jsp";
				break;
			}
		}else {
			viewName = "/member/memberForm.tiles";
		}

		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
}

