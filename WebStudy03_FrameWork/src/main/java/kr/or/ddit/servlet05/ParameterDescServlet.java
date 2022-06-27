package kr.or.ddit.servlet05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/05/parameterDesc.do")
@MultipartConfig
public class ParameterDescServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(request.getProtocol()                    );
		System.out.println(request.getMethod()                      );
		System.out.println(request.getRequestURI()                  );
		System.out.println(request.getRequestURL()                  );
		System.out.println(request.getHeaderNames()		            );
		System.out.println("파라미터 : "+request.getParameter("param")            );
		System.out.println("파트 : " +request.getPart("param")                 );
		System.out.println(request.getInputStream().available()	    );
	}
}
