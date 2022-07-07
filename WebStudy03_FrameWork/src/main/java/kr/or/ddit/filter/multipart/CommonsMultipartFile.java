package kr.or.ddit.filter.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 서블릿 스펙 2.x인 경우 사용되는 Commons-Fileupload의 FileItem을 adaptee로 가진 adapter 객체
 *
 */
public class CommonsMultipartFile implements MultipartFile {

	private FileItem fileItem;

	public CommonsMultipartFile(FileItem adaptee) {
		this.fileItem = adaptee;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return IOUtils.toByteArray(getInputStream());
	}

	@Override
	public String getContentType() {
		return fileItem.getContentType();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return fileItem.getInputStream();
	}

	@Override
	public String getName() {
		return fileItem.getFieldName();
	}

	@Override
	public String getOriginalFilename() {
		return fileItem.getName();
	}

	@Override
	public long getSize() {
		return fileItem.getSize();
	}

	@Override
	public boolean isEmpty() {
		return StringUtils.isBlank(getOriginalFilename());
	}
}
