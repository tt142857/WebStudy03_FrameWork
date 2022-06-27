package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * D://contents/Tulip.jpg 파일을 중계하는 서블릿.
 * 이미지 파일을 읽고(input), 
 * stream copy
 * 스트리밍 컨텐츠로 출력(output)
 * Content-Type(MIME) 설정 필요.
 * 1. source 확보 (D://contents/Tulip.jpg )
 * 2. source대상 input 스트림 개방
 *    FileInputStream
 * 3. destination 확보 (repsonse)
 * 4. dest 대성 output 스트림 개방
 * 	  response.getOutputStream	
 * 5. in/out stream copy
 * 
 *
 */
@WebServlet("/image.do")// URI
public class ImageStreamingServlet extends HttpServlet{
	// ServletContext 는 한 어플리케이션내에서 싱글턴으로 관리됨.
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		File source = new File("D://contents/Tulips.jpg");
//		String srcPath = "/kr/or/ddit/images/cat1.jpg";
		String webPath = "/WEB-INF/inner/cat1.jpg"; // URL, URI, 상대, 절대
//		String filePath = this.getClass().getResource(srcPath).getFile();
//		File source = new File(filePath);
		String mime = application.getMimeType(webPath);
		resp.setContentType(mime);
//		InputStream is = ImageStreamingServlet.class.getResourceAsStream(srcPath);
		InputStream is = application.getResourceAsStream(webPath);
//		FileInputStream fis = new FileInputStream(source);
		OutputStream os = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		int length = -1;
		while((length = is.read(buffer))!=-1) {
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();
	}
}













