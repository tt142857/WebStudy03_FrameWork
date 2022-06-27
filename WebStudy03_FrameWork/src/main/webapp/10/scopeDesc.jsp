<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/scopeDesc.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>Scope(영역)</h4>
<pre>

	:웹 어플리케이션에서 데이터를 공유하는 방법, 공유할 데이터(attribute)를 저장하는 공간(scope)
	- 뚜렷한 생명주기를 가진 기본 객체들이 자기 생명주기와 동일하게 관리하고 있는 map(name/value).
	setAttribute(name, value), getAttribute(name), removeAttribute(name)
	Enumeration getAttributeNames
	
	1. page scope : PageContext와 동일한 생명주기를 갖고, 해당 jsp 페이지 내에서만 사용되는 영역(custom 태그에서 주로 사용).
	2. request scope : HttpServletRequest 와 생명주기가 동일, 한요청에서만 제한적으로 사용되는 영역.
	3. session scope : HttpSession와 생명주기가 동일하며, 해당 세션의 클라이언트가 사용할수있는 영역.
	4. application scope : ServletContext와 생명주기가 동일한 저장구조로 어플리케이션 전체에 걸쳐 공유되는 영역.
	<%
		pageContext.setAttribute("pageAttr", "페이지 속성");
		request.setAttribute("requestAttr", "요청 속성");
		session.setAttribute("sessionAttr", "세션 속성"); // flash attribute -> FlashMap Manager
		application.setAttribute("applicationAttr", "어플리케이션 속성");
		
// 		pageContext.include("/10/destination.jsp");
		response.sendRedirect(request.getContextPath()+"/10/destination.jsp");
	%>
</pre>

<jsp:include page="/includee/postScript.jsp" />	
</body>
</html>















