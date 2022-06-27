package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class CustomRequestLifecycleListener implements ServletRequestListener {

	// *Servlet, Listener는 전부 싱글톤이라 전역변수를 설정할 수 없다.
	
	// setAtrribute할 때, 그냥 start로 Key값을 정하면 흔하기 때문에 겹칠 우려가 있다.
	// 상수로 하여 값은 클래스네임 + .start로 정한다.
	public static final String STARTATTR = "CustomRequestLifecycleListener.start";
	
	public void requestInitialized(ServletRequestEvent sre)  {
		// sre - 발생한 이벤트에 대한 정보, 요청받은 target의 정보도 있음
		ServletRequest request = sre.getServletRequest();
		HttpServletRequest req = (HttpServletRequest) request; // 캐스팅이 가능하다.
		
		System.out.printf("%s의 요청 발생\n", req.getRemoteAddr());
		long start = System.currentTimeMillis(); // 현재시간을 가져올 수 있음
		req.setAttribute(STARTATTR, new Long(start)); // long 타입인 start는 객체가 아니므로 setAttribute에 원래는 넣을 수 없지만
													  // JAVA8? 버전에서는 오토박싱/언박싱이 있기 때문에 Long타입으로 오토박싱이 된다.
	}
	
    public void requestDestroyed(ServletRequestEvent sre)  { 
    	ServletRequest request = sre.getServletRequest();
		HttpServletRequest req = (HttpServletRequest) request;
    	long end = System.currentTimeMillis();
    	long start = ((Long) req.getAttribute(STARTATTR)).longValue();
    	System.out.printf("소요 시간 : %d\n", (end - start));
    }
}
