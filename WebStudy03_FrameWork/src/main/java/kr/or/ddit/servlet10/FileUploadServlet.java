package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/file/upload.do")
public class FileUploadServlet extends HttpServlet {
	
	private static final String CHARSET = "UTF-8";
	private static final String ATTACHES_DIR = "D:/contents";
	private static final int LIMIT_SIZE_BYTES = 1024 * 1024;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/15/fileUploadForm.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		// 1번
//		Part uploader = req.getPart("uploader");
//		log.info("업로더 : {}", uploader.getSubmittedFileName());
//		
//		String savePath = "D:/contents";
//		
//		List<Part> parts = (List<Part>) req.getParts();
//		for(Part part : parts) {
//			log.info("{}, {}", part.getSubmittedFileName(), part.getName());
//		}
//		
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding(CHARSET);
		
		PrintWriter out = resp.getWriter();
		
		File attachesDir = new File(ATTACHES_DIR);
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setRepository(attachesDir);
		fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		try {
			List<FileItem> items = fileUpload.parseRequest(req);
			for(FileItem item : items) {
				if (item.isFormField()) {
					log.info("{}. {}", item.getFieldName(), item.getString(CHARSET));
				} else {
					log.info("{}, {}, {}", item.getFieldName(), item.getName(), item.getSize(), item.isFormField());
					if(item.getSize() > 0) {
						int index = item.
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
//		PrintWriter out = resp.getWriter();
//		 
//		File attachesDir = new File(ATTACHES_DIR);
//		 
//		 
//		        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
//		        fileItemFactory.setRepository(attachesDir);
//		        fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
//		        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
//		 
//		 
//		        try {
//		            List<FileItem> items = fileUpload.parsereq(req);
//		            for (FileItem item : items) {
//		                if (item.isFormField()) {
//		                    System.out.printf("파라미터 명 : %s, 파라미터 값 :  %s \n", item.getFieldName(), item.getString(CHARSET));
//		                } else {
//		                    System.out.printf("파라미터 명 : %s, 파일 명 : %s,  파일 크기 : %s bytes \n", item.getFieldName(),
//		                            item.getName(), item.getSize());
//		                    if (item.getSize() > 0) {
//		                        String separator = File.separator;
//		                        int index =  item.getName().lastIndexOf(separator);
//		                        String fileName = item.getName().substring(index  + 1);
//		                        File uploadFile = new File(ATTACHES_DIR +  separator + fileName);
//		                        item.write(uploadFile);
//		                    }
//		                }
//		            }
//		 
//		 
//		            out.println("<h1>파일 업로드 완료</h1>");
//		 
//		 
//		        } catch (Exception e) {
//		            // 파일 업로드 처리 중 오류가 발생하는 경우
//		            e.printStackTrace();
//		            out.println("<h1>파일 업로드 중 오류가  발생하였습니다.</h1>");
//		        }
}}
