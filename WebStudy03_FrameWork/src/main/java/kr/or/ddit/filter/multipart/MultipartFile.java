package kr.or.ddit.filter.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

public interface MultipartFile {
	public byte[] getBytes() throws IOException;
	public String getContentType();
	public InputStream getInputStream() throws IOException;
	public String getName();
	public String getOriginalFilename();
	public long getSize();
	public boolean isEmpty();
	
	public default void transferTo(File dest) throws IOException {
		FileUtils.copyInputStreamToFile(getInputStream(), dest);
	}
}
