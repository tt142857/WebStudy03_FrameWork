package kr.or.ddit.enumpkg;

public enum OsKind {
	WINDOWS("윈도우"), ANDROID("안드로이드"), IPHONE("아이폰"), UBUNTU("우분투"), UNKNOWN("식별불가 OS") ;
	private String osName;

	private OsKind(String osName) {
		this.osName = osName;
	}
	
	public String getOsName() {
		return osName;
	}
	
	public static OsKind findOs(String agent) {
		agent = agent.toUpperCase();
		OsKind findedOs = OsKind.UNKNOWN;
//		String osName = null;
//		osName = OsKind.UNKNOWN.getOsName();
		for( OsKind os  : OsKind.values()){
			if(agent.contains(os.name())){
				findedOs = os;
				break;
			}
		}
		return findedOs;
	}
	
	public static String findOsName(String agent) {
		OsKind findedOs = findOs(agent);
		return findedOs.getOsName();
	}
}











