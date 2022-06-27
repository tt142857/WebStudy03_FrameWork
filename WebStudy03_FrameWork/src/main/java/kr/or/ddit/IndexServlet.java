package kr.or.ddit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.DelegatingViewResolver;

@WebServlet("/index.do")
public class IndexServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setAttribute("contents", "/WEB-INF/views/index.jsp");
//		String view = "/WEB-INF/views/template.jsp";
//		req.getRequestDispatcher(view).forward(req, resp);
		DelegatingViewResolver resolver = new DelegatingViewResolver();
		String viewName = "/index.tiles";
		resolver.viewResolve(viewName, req, resp);
	}
}
