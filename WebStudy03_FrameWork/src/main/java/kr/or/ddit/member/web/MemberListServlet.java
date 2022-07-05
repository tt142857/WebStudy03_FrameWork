package kr.or.ddit.member.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchCondition;

/**
 * 회원 목록 조회를 위한 Controller Layer
 *
 */
@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(MemberListServlet.class);
	
	MemberService service = new MemberServiceImpl();
	
	private void processHTML(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/member/memberList.tiles";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
	
	private void processJsonData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		SimpleSearchCondition searchVO = new SimpleSearchCondition(searchType, searchWord);
		
		log.info("searchType : {}, searchWord : {}", searchType, searchWord); // slf는 {}와 같이 구멍을 뚫어줄 수 있음.
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(searchVO);
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		
		req.setAttribute("pagingVO", pagingVO);
		
		if(ObjectUtils.isNotEmpty(memberList)) {
			req.setAttribute("memberList", memberList);
		}

		String viewName = "forward:/jsonView.do";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("accept");
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			processJsonData(req, resp);
		} else {
			processHTML(req, resp);
		}
	}
}
