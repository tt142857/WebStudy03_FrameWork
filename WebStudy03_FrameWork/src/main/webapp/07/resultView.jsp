<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
	<%--
		//3. 속성데이터를 전달하는 영역에 무관하게 속성데이터에 접근하려면???
		String message = (String) session.getAttribute("message");
		DDITStudentVO vo = (DDITStudentVO) session.getAttribute("student");
	--%>
	전달된 메시지 : <%--=message --%>, ${message }
	등록 완료된 학생의 이름 : <%--=vo.getName() --%>, ${student.name }
</pre>
</body>
</html>