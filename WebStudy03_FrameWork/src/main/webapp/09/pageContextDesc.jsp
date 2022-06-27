<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.page"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/pageContextDesc.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>PageContext pageContext</h4>
<pre>
	: 기본객체 중 가장 먼저 생성되며, 나머지 기본 객체에 대한 레퍼런스를 가진 객체
	
	1. 기본객체 확보(EL) ${pageContext }
	2. attribute 관리 기능(setAttribute, getAttribute, removeAttribute) : 4개의 기본객체가 가진 scope 를 참조할 수 있는 기능.
		<%
// 			request.setAttribute("reqAttr", "요청 속성");
// 			request.getAttribute("reqAttr");
			pageContext.setAttribute("reqAttr", "요청 속성", PageContext.REQUEST_SCOPE);
			pageContext.getAttribute("reqAttr", PageContext.REQUEST_SCOPE);
		%>
	3. 에러 데이터 확보	
	    <%
	    	ErrorData ed = pageContext.getErrorData();
			int sc = ed.getStatusCode();
			out.println(sc);
	    %>
	4. 흐름제어 (dispatch)
		<%
// 			request.getRequestDispatcher("/09/implicitObject.jsp").include(request, response);
			
// 			pageContext.include("/09/implicitObject.jsp");
		%>  
		<jsp:include page="/09/implicitObject.jsp" />
		custom tag 사용 방법 : <prefix:태그명 속성명="속성값" />
	 추가 코드   
</pre>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>













