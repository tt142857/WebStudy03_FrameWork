package kr.or.ddit.servlet07;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.vo.fancytree.FancyTreeNode;
import kr.or.ddit.vo.fancytree.FancyTreeNodeFileAdapter;

@WebServlet("/server/browsing.do")
public class ServerFileBrowsingServlet extends HttpServlet{
	
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			String parent = req.getParameter("parent");
			String currentRoot = null;
			if(StringUtils.isBlank(parent)) {
				currentRoot = "/";
			}else {
				if(parent.charAt(0)=='/') {
					currentRoot = parent;
				}else {
					currentRoot = "/"+parent;
				}
			}
			Set<String> children = application.getResourcePaths(currentRoot); // web root(context root)
			List<FancyTreeNode> adapterList = new ArrayList<>();
			for(String childPath : children) {
				String realPath = application.getRealPath(childPath);
				File childFile = new File(realPath);
				FancyTreeNodeFileAdapter adapter = new FancyTreeNodeFileAdapter(childFile, childPath);
				adapterList.add(adapter);
			}
			adapterList = adapterList.stream().sorted().collect(Collectors.toList());
//			List<FancyTreeNode> adapterList = fileList.stream().map((file)->{
//				return new FancyTreeNodeFileAdapter(file);
//			}).sorted().collect(Collectors.toList());
			
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				// closable 객체 생성 코드 -> 자동 close 됨.
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, adapterList);
			}
			
		}else {
//			String view = "/WEB-INF/views/server/browsing.jsp";
//			req.getRequestDispatcher(view).forward(req, resp);
			
			DelegatingViewResolver resolver = new DelegatingViewResolver();
			String viewName =  "/server/browsing.tiles";
			resolver.viewResolve(viewName, req, resp);
		}
		
	}
}


















