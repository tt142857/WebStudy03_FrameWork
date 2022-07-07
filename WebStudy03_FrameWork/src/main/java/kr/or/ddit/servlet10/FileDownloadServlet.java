package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/file/download.do")
public class FileDownloadServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filePath = req.getParameter("file");
		if(StringUtils.isBlank(filePath)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		
		File file = new File(filePath);
		if(!file.exists()) { // 파일패스의 파일 존재여부 파악
			// 없는 자원의 요청 = 404
			resp.sendError(404, "해당 파일은 없음");
			return;
		}
		
		// 압축이 필요한 경우
		// commons-compress : 폴더를 압축
		
		String fileName = file.getName();
		fileName = URLEncoder.encode(fileName, "UTF-8").replace("+", " "); // ?인코딩이 안되네
		resp.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
		resp.setContentLengthLong(file.length()); // 긴 body에 대해 처리할 수 있음
		
		// FileInputStream fis = new FileInputStream(file);
		try (
			OutputStream os = resp.getOutputStream(); // 응답 스트림 설정
		){
			FileUtils.copyFile(file, os); // 파일을 응답 스트림에 보냄
										  // 브라우저가 읽을 수 있는 파일이면 다운로드를 하지 않고 브라우저에서 엶
		}
		
	}
}
