<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>성능 향상 요소</h4>
<pre>
	공간적 요소(memory 소요량) : String -> StringBuffer
		String str = "ab";
		str += "dc";
		String.format("%s dc","ab")
		StringBuffer sb = new StringBuffer("ab");
		ab.append("dc");
	시간적 요소(소요 시간) : latency time + processing time
	
	<a href="OneConn100Proccess.jsp">한번의 처리와 한번의 연결지연: 17ms</a>
	<a href="100Conn100Proccess.jsp">백번의 처리와 백번의 연결지연: 1184ms</a>
	<a href="100ConnOneProccess.jsp">백번의 처리와 한번의 연결지연: 21ms</a>
	<a href="OneConn100Proccess.jsp">한번의 처리와 백번의 연결지연: 1118ms</a>
</pre>
</body>
</html>