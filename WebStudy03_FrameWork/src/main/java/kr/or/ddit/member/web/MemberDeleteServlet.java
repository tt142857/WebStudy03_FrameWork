package kr.or.ddit.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet {
	MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession();
		
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String memId = authMember.getMemId();
		
		ServiceResult result = service.removeMember(MemberVO.builder()
															.memId(memId)
															.memPass(password)
															.build());
		String viewName = "redirect:/myPage.do";
		switch(result) {
		case INVALIDPASSWORD:
			session.setAttribute("message", "비밀번호 오류");
			break;
		case FAIL:
			session.setAttribute("message", "서버 오류");
			break;
		default:
			viewName = "forward:/login/logout.do";
			break;
		}
		
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
}
