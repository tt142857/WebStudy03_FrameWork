package kr.or.ddit.vo.fancytree;

import java.io.File;

public class FancyTreeNodeFileAdapter implements FancyTreeNode<File> {
	private File adaptee;
	private String serverFilePath;
	public FancyTreeNodeFileAdapter(File adaptee, String serverFilePath) {
		super();
		this.adaptee = adaptee;
		this.serverFilePath = serverFilePath;
	}

	@Override
	public String getKey() {
		return this.serverFilePath;
	}

	@Override
	public String getTitle() {
		return adaptee.getName();
	}

	@Override
	public boolean isFolder() {
		return adaptee.isDirectory();
	}
	
	@Override
	public boolean isLazy() {
		return isFolder();
	}
	
	@Override
	public boolean isExpanded() {
		return false;
	}
	
	@Override
	public File getAdaptee() {
		return this.adaptee;
	}
	
//	@Override
//	public File getData() {
//		return adaptee;
//	}

	@Override
	public int compareTo(FancyTreeNode<File> o) {
		File otherFile = o.getAdaptee();
		if(!(adaptee.isDirectory()^otherFile.isDirectory())) {
			return FancyTreeNode.super.compareTo(o);
		}else {
			if(adaptee.isDirectory()) {
				return -1;
			}else {
				return 1;
			}
		}
	}
}



















