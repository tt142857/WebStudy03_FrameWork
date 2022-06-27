package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02/imageForm.tmpl")
public class ImageFormServlet extends AbstractUsingTmplServlet {

	@Override
	protected String getContentType() {
		return "text/html;charset=UTF-8";
	}

	@Override
	protected Map<String, Object> getDataMap(HttpServletRequest req, HttpServletResponse resp) {
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
		Map<String, Object> dataMap = new LinkedHashMap<>();
		dataMap.put("options", options);
		return dataMap;
	}

}














