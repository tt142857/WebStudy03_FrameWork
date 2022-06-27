package kr.or.ddit.explorer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Design pattern(Adapter pattern) 과 jquery 플러그인(FancyTree)을 이용한 
 * 웹 리소스 파일 브라우징
 *
 */
@WebServlet("/serverExplorer.do")
public class ServerExplorerServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String src = req.getParameter("srcFile");
		String dest = req.getParameter("destFolder");
		String command = req.getParameter("command");
		Map<String, Object> resultMap = new HashMap<>();
		boolean valid = validate(src, dest, command, resultMap);
		if(valid) {
			File srcFile = (File) resultMap.get("srcFile");
			File destFolder = (File) resultMap.get("destFolder");
			CommandType commandType = (CommandType) resultMap.get("commandType");
			boolean result = commandType.process(srcFile, destFolder);
			resultMap.clear();
			resultMap.put("success", result);
			resp.setContentType("application/json;charset=UTF-8");
			try(
				PrintWriter out = resp.getWriter();
			){
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(resultMap);
				out.print(json);
			}
		}else {
			resp.sendError((Integer)resultMap.get("statusCode"), (String)resultMap.get("message"));
		}
	}
	
	private boolean validate(String src, String dest, String command, Map<String, Object> resultMap) {
		int sc = 200;
		String message = null;
		File srcFile = null;
		File destFolder = null;
		CommandType commandType = null;
		
		if(StringUtils.isBlank(command) || StringUtils.isBlank(src) || StringUtils.isBlank(dest)) {
			sc = 400;
			message = " 필수 파라미터 누락 ";
		}else {
			srcFile = new File(application.getRealPath(src));
			destFolder = new File(application.getRealPath(dest));
			
			if(!srcFile.exists() || srcFile.isDirectory()) {
				sc = 400;
				message = " 대상 파일이 존재하지 않습니다. ";
			}else {
				resultMap.put("srcFile", srcFile);
			}
			
			try {
				commandType = CommandType.valueOf(command);
				resultMap.put("commandType", commandType);
				
				if(commandType!=CommandType.DELETE && 
						(!destFolder.exists() || destFolder.isFile())) {
					sc = 400;
					message = " 대상 폴더가 존재하지 않습니다. ";
				}else {
					resultMap.put("destFolder", destFolder);
				}
			}catch (IllegalArgumentException e) {
				sc = 400;
				message = " 처리할수 있는 커맨드가 아닙니다. ";
			}
		}
		resultMap.put("statusCode", sc);
		resultMap.put("message", message);
		return sc==200;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getParameter("url");
		String currentUrl = StringUtils.isBlank(url)?"/":url;
		
		String currentPath = application.getRealPath(currentUrl);
		File currentFolder = new File(currentPath);
		
		int sc = 200;
		if(!currentFolder.exists()) {
			sc=404;
		}else if(currentFolder.isFile()){
			sc=400;
		}
		
		if(sc!=200) {
			resp.sendError(sc);
			return;
		}
		
		WebResource[] resources = WebResource.getWebResourcesFromFolder(currentFolder, application);
		
		String accept = req.getHeader("Accept");
		
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			try(
				PrintWriter out = resp.getWriter();
			){
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(resources);
				out.print(json);
			}
		}else {
			req.setAttribute("resources", resources);
			req.getRequestDispatcher("/WEB-INF/views/explorerView.jsp").forward(req, resp);
		}
		
	}
}
