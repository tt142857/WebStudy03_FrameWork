package kr.or.ddit.servlet03;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Model2 + MVC
 * 
 * Controller 역할?
 *  1) request 수령(command 수령).
 *  2) 처리 -> 결과 데이터(Model)
 *  3) model 을 view로 전달(공유, controller 와 view 가 같은 model 을 공유함)
 *  4) view 로 이동(forward).
 *
 */
@WebServlet("/03/model2ImageForm.do")
public class Model2ImageFormServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application = getServletContext();
		String folderPath = "D:\\contents";
		File folder = new File(folderPath);
		File[] children = folder.listFiles((dir, name)->{
			String mime = application.getMimeType(name);
			return mime!=null && mime.startsWith("image/");
		});
		String pattern = "<option>%s</option>";
		StringBuffer options = new StringBuffer();
		for(File tmp : children) {
			options.append(String.format(pattern, tmp.getName()));
		}
		req.setAttribute("options", options);
		String view = "/WEB-INF/views/imageStreamingView.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}











