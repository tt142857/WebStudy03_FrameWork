<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01/resourceIdentify.jsp</title>
</head>
<body>
<h4> 리소스의 종류와 식별 방법 </h4>
<pre>
	1. 파일 시스템 리소스 : 파일시스템 상의 절대 경로( D:\contents\cat1.jpg ) 
	2. class path 리소스 : class path 이후의 절대 경로 ( /kr/or/ddit/images/cat1.jpg )
	3. web 리소스 : 서버에 의해 사용되고, 서버에 의해 경로가 결정, URL 갖고있음.
				(http://IP[domain]:port/WebStudy01/resources/images/cat1.jpg)
				
	** web resource 식별 방법(URL)
	URI (Unified Resource Identifier)
	**URL (Unified Resource Locator)	
	URC (Unified Resource Content)
	URN	(Unified Resource Naming)		
	
	** URL 표현 방법
	http://IP[Domain]:port/contextPath/depth..../resourceName
	
	http://localhost:80/WebStudy01/resources/images/cat1.jpg
	
	1. 상대 경로 : 현재 브라우저가 가지고 있는 URL을 기준으로 상대적 경로 표기.
	2. 절대 경로 : 자원의 위치에서 최상위 경로부터 전체 경로가 표기. (이미 인지하고 있는 경로는 표현하지 않는다.)
		1) client side : <%=request.getContextPath() %>/resources/images/cat1.jpg
			반드시, contextPath부터 시작되는 경로 형태.
		2) server side : /resources/images/cat1.jpg (서버사이드에서는 반드시 절대경로만 사용할 것.)
			contextPath 이후의 경로 형태.
		<%
			String path = "/resources/images/cat1.jpg";
			URL url = application.getResource(path);
			out.print(url);
		%>
	http://localhost:80/WebStudy01/resources/images/cat1.jpg
</pre>
<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg" />
<img src="../resources/images/cat1.jpg" />
<img src="http://localhost:80/WebStudy01/resources/images/cat1.jpg" />
</body>
</html>









