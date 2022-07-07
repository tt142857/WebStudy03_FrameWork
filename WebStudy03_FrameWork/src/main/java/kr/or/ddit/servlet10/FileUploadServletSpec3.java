package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/file/upload_3.do") // Servlet 3.0부터는 Part가 지원되므로 common-fileupload가 필요 없는 것
@MultipartConfig(maxFileSize=-1, maxRequestSize=-1) // 임시 저장소와 파일 업로드할 때의 정책을 구성
// maxFileSize=-1 : 제한 x
public class FileUploadServletSpec3 extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File fileSystemFolder = new File("d:/contents");
		String webPath = "/resources/images";
		String filePath = getServletContext().getRealPath(webPath);
		File webResourceFolder = new File(filePath);
		
		List<Part> partList = (List<Part>) req.getParts();
		if(partList != null && !partList.isEmpty()) {
			List<String> savePathList = new ArrayList<>();
			for(Part part : partList) {
				String contentType = part.getContentType();
				String partName = part.getName();
				if (contentType == null) {
					log.info("파트명 : {}, 파트값 : {}", partName, req.getParameter(partName));
				}
				else {
					String savePath = null;
					if(partName.startsWith("fileSystem")) {
						String saveName = uploadFile(part, fileSystemFolder);
						savePath = new File(fileSystemFolder, saveName).getAbsolutePath();
					} else if (partName.startsWith("web")) {
						if(contentType.startsWith("image/")) {
							String saveName = uploadFile(part, webResourceFolder);
							savePath = webPath + "/" + saveName;
						} // if(contentType.startsWith("image/") end
					} // if(partName.startsWith("fileSystem")) end
					savePathList.add(savePath);
				}
				req.getSession().setAttribute("savePathList", savePathList);
			}
			
			resp.sendRedirect(req.getContextPath() + "/15/fileUploadForm.jsp");
			
		} // if(partList != null && !partList.isEmpty()) end
//		업로드하고 업로드폼 jsp로
//		fileuploadServlet에서 했던 작업을 part로 바꾸어서 하기
//		serlvet2에서 했던 인코딩 작업은 3에서는 필요없다
	}
	
	// FileItem과 Part의 구조는 거의 동일하기 때문에 교체해도 문제가 없음
	private String uploadFile(Part part, File saveFolder) throws IOException {
		String originalFileName = part.getSubmittedFileName();
		String saveName = UUID.randomUUID().toString();
		try (
			InputStream is = part.getInputStream();
		){
			// 경로와 파일명을 지정
			File dest = new File(saveFolder, saveName);
			FileUtils.copyInputStreamToFile(is, dest);
		}
		return saveName;
	}
}
