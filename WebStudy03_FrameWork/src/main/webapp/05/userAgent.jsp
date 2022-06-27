<%@page import="kr.or.ddit.enumpkg.OsKind"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/userAgent.jsp</title>
</head>
<body>
<pre>
<!-- 	클라이언트의 시스템을 파악하고, -->
<!-- 	해당 OS에 대한 정보를 alert 창으로 랜더링. -->
<!-- 	(당신의 OS 는 "%s" 입니다.) -->
	<%	
		String pattern = "당신의 OS 는 \"%s\" 입니다.";
		String agent = request.getHeader("User-Agent");
		String osName = OsKind.findOsName(agent);
		String message = String.format(pattern, osName);
	%>
	<%=OsKind.IPHONE %>
</pre>
<script type="text/javascript">
	alert('<%=message %>');
</script>
</body>
</html>










