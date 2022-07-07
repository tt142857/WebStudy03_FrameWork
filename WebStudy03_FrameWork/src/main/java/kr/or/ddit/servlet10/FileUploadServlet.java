package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.mvc.DelegatingViewResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/file/upload.do")
public class FileUploadServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/15/fileUploadForm.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 저장위치
		File fileSystemFolder = new File("d:/contents");
		String webPath = "/resources/images"; // 절대경로: 시작부터 끝까지 다 있어야 됨. 얘는 상대경로
		// **서버를 기준, 서버의 위치에 따라 달라지는 경로가 필요함
		// → D:\B_Util\eGovFrameDev-3.9.0-64bit\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WebStudy03_Framework\resources\images
		String filePath = getServletContext().getRealPath(webPath);
		File webResourceFolder = new File(filePath); 
		
		DiskFileItemFactory itemFactory = new DiskFileItemFactory();
		// 파라미터가 없으면 기본 10KB로 둠
		// sizeThreshold : 이 사이즈보다 크면 repository(임시저장소)를 사용, 작으면 메모리에 저장
		ServletFileUpload uploadHandler = new ServletFileUpload(itemFactory);
		try {
			List<FileItem> itemList = uploadHandler.parseRequest(req); // body 영역을 파싱한 후 파트 하나하나를 캡슐화해서 FileItem 객체로 가져와 List화함
			if(itemList != null && !itemList.isEmpty()) {
				List<String> savePathList = new ArrayList<>();
				for(FileItem item : itemList) {
					String partName = item.getFieldName();
					if(item.isFormField()) {
						String encoding = req.getCharacterEncoding();
						String partValue = item.getString(encoding);
						log.info("파트명 : {}, 파트값 : {}", partName, partValue);
					} else {
						// 파일일 경우
						String savePath = null;
						if(partName.startsWith("fileSystem")) {
							String saveName = uploadFile(item, fileSystemFolder);
							savePath = new File(fileSystemFolder, saveName).getAbsolutePath(); // 흠
						} else if(partName.startsWith("web")) {
							if (item.getContentType().startsWith("image/")) {
								String saveName = uploadFile(item, webResourceFolder);
								savePath = webPath + "/" + saveName;
							}
						}
						savePathList.add(savePath);
					} // if(item.isFormField()) end
				} // for(FileItem item : itemList) end
				
				// 이동하기 전에 데이터를 공유해야 함(scope 쓰기)
				// 이동 방식이 redirection이니까 session을 사용
				req.getSession().setAttribute("savePathList", savePathList);
				
			} // if(itemList != null && !itemList.isEmpty()) end
			
			new DelegatingViewResolver().viewResolve("redirect:/15/fileUploadForm.jsp", req, resp);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} 
	}
	
	private String uploadFile(FileItem item, File saveFolder) throws IOException {
		String originalFileName = item.getName();
		String saveName = UUID.randomUUID().toString(); // ? 왜한건지 못들었네
		try (
			InputStream is = item.getInputStream();
		){
			// 경로와 파일명을 지정
			File dest = new File(saveFolder, saveName);
			FileUtils.copyInputStreamToFile(is, dest);
		}
		return saveName;
	}
}