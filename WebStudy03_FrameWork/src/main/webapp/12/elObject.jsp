<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/elObject.jsp</title>
</head>
<body>
<h4>표현 언어 기본 객체</h4>
<%
	pageContext.setAttribute("pageAttr", "페이지 속성");
	request.setAttribute("requestAttr", "요청 속성");
	session.setAttribute("sessionAttr", "세션 속성");
	application.setAttribute("applicationAttr", "어플리케이션 속성");
%>
<pre>
	1. Scope 객체(Map&lt;String, Object&gt;) : pageScope, requestScope, sessionScope, applicationScope
	  ${pageScope.pageAttr } → ${pageScope['pageAttr'] }
	  ${sessionScope.sessionAttr} -> ${sessionScope['sessionAttr'] }
	  ${requestAttr } → ${requestScope.requestAttr } → ${requestScope['requestAttr'] }
	  
	2. Parameter 객체(Map) : param(Map&lt;String, String&gt;), paramValues(Map&lt;String, String&gt;)
	  <%=request.getParameter("paramName") %>, <%=request.getParameterValues("paramName") %>
	  ${param.paramName }, ${paramValues.paramName }
	  
	3. Header 객체(Map) : header(Map&lt;String, String&gt;), headerValues(Map&lt;String, String[]&gt;)
	  <%=request.getHeader("accept") %>
	  ${header.accept } → ${header['accept'] }
	   
	  ${header['user-agent'] }
	  
	4. Cookie 객체(Map) : cookie(Map&lt;String, Cookie&gt;)
	  <%
	  	Cookie[] cookies = request.getCookies();
		Cookie finded = null;
		if(cookies != null) {
			for(Cookie tmp : cookies) {
				if("JSESSIONID".equals(tmp.getName())) {
					finded = tmp;
					break;
				}
			}
		}
		out.println("JSESSIONID : " + finded.getValue());
	  %>
	  EL → ${cookie.JSESSIONID.value } → ${cookie['JSESSIONID']['value'] } // 위의 방법보다 간단하다
	  																		  // EL 연습하기
	
	
	5. pageContext
	  ${pageContext.request.contextPath }, ${cPath }
	  ${pageContext.session.id }
	
</pre>
</body>
</html>