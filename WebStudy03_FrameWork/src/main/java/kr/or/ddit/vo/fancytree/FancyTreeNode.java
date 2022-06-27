package kr.or.ddit.vo.fancytree;

public interface FancyTreeNode<T> extends  Comparable<FancyTreeNode<T>>{
	public String getKey();
	public String getTitle();
	public boolean isFolder();
	public boolean isLazy();
	public boolean isExpanded();
	
	public T getAdaptee();
	
	public default T getData() {
		return getAdaptee();
	}
	
	@Override
	default int compareTo(FancyTreeNode<T> o) {
		return getTitle().toLowerCase().compareTo(o.getTitle().toLowerCase());
	}
}
