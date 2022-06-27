package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class CustomSessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se)  { 
         ServletContext application = se.getSession().getServletContext();
         int userCount = (int) application.getAttribute("userCount");
         application.setAttribute("userCount", ++userCount);
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
