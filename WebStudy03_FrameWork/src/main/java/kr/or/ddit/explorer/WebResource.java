package kr.or.ddit.explorer;

import java.io.File;

import javax.servlet.ServletContext;

public class WebResource extends File{
	public WebResource(File file, ServletContext application) {
		super(file.getAbsolutePath());
		contextRealPath = application.getRealPath("");
		StringBuffer tmp = new StringBuffer(
				getAbsolutePath().substring(contextRealPath.length()).replace(File.separatorChar, '/')
		);
		if(tmp.charAt(0)!='/') {
			tmp.insert(0, '/');
		}
		resourceId = tmp.toString();
		classValue = isDirectory()?"lazy folder":"file";
	}
	private String contextRealPath;
	private String resourceId;
	private String classValue;
	public String getResourceId() {
		return resourceId;
	}
	public String getKey() {
//		return resourceId; 
		return getName(); // tree 의 path 구조를 파라미터로 사용하는 경우.
	}
	public boolean isFolder() {
		return isDirectory();
	}
	public String getClassValue() {
		return classValue;
	}
	public boolean isLazy() {
		return isDirectory()?true:false;
	}
	public String getTitle() {
		return getName();
	}
	public boolean isUnselectable() {
		return isDirectory();
	}
	
	public static WebResource[] getWebResourcesFromFolder(File currentFolder, ServletContext application) {
		File[] listFiles = currentFolder.listFiles();
		WebResource[] resources = new WebResource[listFiles.length];
		int idx = 0;
		for(File tmp : listFiles) {
			resources[idx++] = new WebResource(tmp, application);
		}
		return resources;
	}
}

