package kr.or.ddit.prod.web;

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
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertServlet extends HttpServlet {
	
	ProdService service = new ProdServiceImpl();
	OthersDAO othersDao = new OthersDAOImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("lprodList", othersDao.selectLprodList());
		req.setAttribute("buyerList", othersDao.selectBuyerList());
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/prod/prodForm.tiles";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		try {
			// 넘어온 parameter와 값이 같으면 reflection(값이 대응)이 됨
			BeanUtils.populate(prod, req.getParameterMap());
		} catch(IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors); // 성공해서 redirection이 되기 전까지 유지되는 값
		boolean valid = ValidateUtils.validate(prod, errors, InsertGroup.class);
		
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) { // Null Point에 안전한 코드가 되기 위해서는 확실한 녀석을 앞에다 두어야 함
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
			} else {
				req.getSession().setAttribute("message", "서버 오류");
				viewName = "/prod?prodForm.tiles";
			}
		} else {
			viewName = "/prod/prodForm.tiles";
		}
		
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}
}
