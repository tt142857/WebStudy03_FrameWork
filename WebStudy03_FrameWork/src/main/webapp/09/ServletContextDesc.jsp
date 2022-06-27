<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/ServletContextDesc.jsp</title>
</head>
<body>
CAC - Context Aware Computing
<h4>ServletContext application</h4>
ServletConfig(1vs1) - 서블릿이 컨테이너에 의해 운영되는 과정에서 생성되는 메타 정보를 가진 객체.
<pre>
	: 서블릿이 운영되는 어플리케이션과 해당 어플리케이션이 운영되는 서버에 대한 정보를 가진 객체.
	  컨텍스트 하나당 하나의 싱글턴 객체가 운영됨.
	  
	  *** 컨테이너와 대화하는 과정에서 사용.
	  1. MIME 데이터 사용
	  	<%=application.getMimeType("test.jpg") %>
	  2. 서버의 정보
	  	<%=application.getServerInfo() %>
	  	servlet spec version : <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
	  3. 로깅(log 기록)
	  	<%
	  		application.log("로그 메시지");
	  	%>
	  4(****) 웹리소스 확보 : /WebStudy02_MVN/src/main/webapp/resources/images/cat1.jpg
	  (개발 환경)D:\B_Util\eGovFrameDev-3.9.0-64bit\workspace\WebStudy02_MVN\src\main\webapp\resources\images\cat1.jpg
	  (실행 환경)D:\B_Util\eGovFrameDev-3.9.0-64bit\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WebStudy02_MVN\resources\images\cat01.jpg
	  		   D:\B_Util\eGovFrameDev-3.9.0-64bit\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0
	  		   <%=application.getRealPath("/resources/images/cat1.jpg") %>
	  <%
	  	String imageUrl = "/resources/images/cat1.jpg";
	  	File imageFile = new File(application.getRealPath(imageUrl));
	  	out.println("파일 크기 : "+imageFile.length());
	  %>		   
</pre>
<h4>servlet Context hashcode : <%=application.hashCode() %></h4>
<a href="<%=request.getContextPath() %>/desc.do">/desc.do</a>
</body>
</html>
















