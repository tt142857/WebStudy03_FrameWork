package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Design Pattern
 * 1. Template Method Pattern
 * 		: 작업의 순서가 고정되어있으되, 그 순서내에서 세부 작업이 다양한 형태를 갖는 경우.
 *  1) template class (abstact) 
 *  	- template method : 작업의 순서 정의
 *  	- hook method(abstract) : 순서내에서의 세부 작업이 작업.
 *  2) concrete class (instance 생성 가능)	
 *  	- hook method 에 대한 구체적인 작업 정의.
 *
 */
public abstract class AbstractUsingTmplServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.application = getServletContext();
	}
	
	protected abstract String getContentType();
	
	protected abstract Map<String, Object> getDataMap(HttpServletRequest req, HttpServletResponse resp);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType(getContentType());
		
		Map<String, Object> dataMap = getDataMap(req, resp);
		
		String tmplPath = req.getServletPath();
		// IO ~~Stream : byte stream , ~~Reader/~~Writer : character stream
		InputStream is = application.getResourceAsStream(tmplPath); // 1차 stream
		InputStreamReader isr = new InputStreamReader(is); // 2차 stream
		BufferedReader reader = new BufferedReader(isr); // 2차 stream
		
		StringBuffer template = new StringBuffer();
		String line = null;
		while((line = reader.readLine())!=null) {
			template.append(line+"\n");
		}
		
		Pattern regex = Pattern.compile("<%=(\\w+)\\s*%>");
		Matcher matcher = regex.matcher(template);
		StringBuffer html = new StringBuffer();
		while(matcher.find()) {
			String dataName = matcher.group(1);
			Object dataValue = dataMap.get(dataName);
			matcher.appendReplacement(html, Objects.toString(dataValue, ""));
		}
		matcher.appendTail(html);
		
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.print(html);
		}finally {
			if(out!=null)
				out.close();
		}
	}
}











