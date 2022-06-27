<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/requestDesc.jsp</title>
</head>
<body>
<h4> HTTP 요청 (command) 패키징 방식 </h4>
<form action="parameterDesc.do" method="post" enctype="application/x-www-form-urlencoded">
<!-- 	<input type="hidden" name="_method" value="PUT"> -->
	<input type="text" name="param" value="TEST">
	<input type="submit" value="전송" />
</form>
<pre>
	1. Request Line : Protocol/version URL Method
		Request Method (Http Method) : 요청의 목적, 요청의 포장 방식(body 생성 여부 결정)
		1) GET  (R) - 조회
		2) POST (C) - 생성
		3) PUT  (U) - 수정
		4) DELETE (D) - 삭제
		5) OPTIONS : 서버가 현재 method를 지원하는지 여부를 확인하는 preFlight 요청에 사용되는 메소드.
		6) HEAD : 응답데이터를 body가 없는 메타 데이터만 받기 위한 요청에 사용.
		7) TRACE :  서버를 트래킹해서 디버깅하는 경우 사용.
	2. Request Header : client에 대한 부가정보 (meta data)
	3. Request Body(Message Body, Cotent Body) : 클라이언트가 서버로 전송하는 컨텐츠(내용)
		method가 POST 인 경우에만, 한정적으로 생성되는 영역.
		** method 가 GET 인 경우, 모든 데이터는 Request line 을 통해 Query String(**) 의 형태로 전송됨
		
		
	HttpServletRequest request - http 프로토콜로 패키징된 요청에 관한 모든 정보를 캡슐화한 객체;
	line - <%=request.getProtocol() %> <%=request.getMethod() %>
			<%=request.getRequestURI() %>
			<%=request.getRequestURL() %>
	header - <%=request.getHeaderNames() %>		
	파라미터 데이터 ??? <%=request.getParameter("param") %>
<%-- 	파트 데이터 ??? <%=request.getPart("param") %> --%>
	body - <%=request.getInputStream().available() %>	
	
	클라이언트가 입력한 데이터가 파라미터 형태로 전달되는 경우(enctype="application/x-www-form-urlencoded"),
	-> getParameter 계열의 메소드를 사용함. 모든 파라미터가 Map (getParameterMap)의 형태로 관리됨.
	클라이언트가 입력한 데이터가 파트의 형태로 전달되는 경우(enctype="multipart/form-data|multipart/mixed"),
	-> getPart 계열 메소드 사용
</pre>
</body>
</html>













