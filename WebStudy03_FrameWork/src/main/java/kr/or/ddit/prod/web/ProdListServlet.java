package kr.or.ddit.prod.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodList.do")
public class ProdListServlet extends HttpServlet {
	ProdService service = new ProdServiceImpl();
	OthersDAO othersDAO = new OthersDAOImpl();
	
	private void processHTML(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/prod/prodList.tiles";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
	
	private void processJsonData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProdVO detailCondition = new ProdVO();
		try {
			BeanUtils.populate(detailCondition, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) { // 파라미터가 숫자로 구성되어 있는지 확인함
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<ProdVO> pagingVO = new PagingVO<>(3, 2);
		pagingVO.setCurrentPage(currentPage);
		// pagingVO.setSimpleCondition(searchVO);
		pagingVO.setDetailCondition(detailCondition);
		
		service.retrieveProdList(pagingVO);
		
		req.setAttribute("pagingVO", pagingVO);
		
		String viewName = "forward:/jsonView.do";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
		
		String accept = req.getHeader("accept");
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			processJsonData(req, resp);
		} else {
			processHTML(req, resp);
		}
	}
}
