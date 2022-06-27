package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce)  { 
		ServletContext application = sce.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		System.out.printf("%s 로딩 완료", application.getContextPath());
		
		application.setAttribute("userCount", 0);
	}
	
    public void contextDestroyed(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();
    	System.out.printf("%s 언로드\n", application.getContextPath());
    }
}
