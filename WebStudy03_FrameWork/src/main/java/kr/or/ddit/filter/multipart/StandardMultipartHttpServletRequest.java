package kr.or.ddit.filter.multipart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

public class StandardMultipartHttpServletRequest extends HttpServletRequestWrapper {

	private Map<String, List<MultipartFile>> multiPartFiles;
	
	public StandardMultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		multiPartFiles = new LinkedHashMap<>();
		
		parseRequest(request);
	}

	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		for(Part single : parts) {
			String contentType = single.getContentType();
			if(StringUtils.isBlank(contentType)) {
				continue;
			}
			String partName = single.getName();
			StandardServletMultipartFile multipartFile = new StandardServletMultipartFile(single);
			List<MultipartFile> alreadyFiles = multiPartFiles.get(partName);
			if (alreadyFiles == null) {
				alreadyFiles = new ArrayList<>();
				multiPartFiles.put(partName, alreadyFiles);
			}
			alreadyFiles.add(multipartFile);
		}
	}
	
	public Map<String, List<MultipartFile>> getMultiPartFiles() {
		return multiPartFiles;
	}
	
	public MultipartFile getFile(String partName) {
		List<MultipartFile> files = multiPartFiles.get(partName);
		if (files != null) {
			return files.get(0);
		} else {
			return null;
		}
	}
	
	public List<MultipartFile> getFiles(String partName) {
		return multiPartFiles.get(partName);
	}
}
