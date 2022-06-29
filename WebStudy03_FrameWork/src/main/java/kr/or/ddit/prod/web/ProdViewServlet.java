package kr.or.ddit.prod.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodView.do")
public class ProdViewServlet extends HttpServlet {
	ProdService service = new ProdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodId = req.getParameter("what");
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "어떤 상품 조회?");
			return;
		}
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		
		String viewName = "/prod/prodView.tiles";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
}
