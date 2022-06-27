<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/jdbcDesc.jsp</title>
</head>
<body>
<h4>JavaDataBaseConnectivity - JDBC Driver</h4>
<pre>
	JDBC 단계
	1. 벤더가 제공하는 드라이버를 빌드패스에 추가(pom.xml)
	2. 드라이버 로딩
	
	3. Connection 수립
	4. Query 객체 생성
		Statement : 기본 쿼리 객체로 모든 쿼리 객체의 상위.
		PreparedStatement(선컴파일된 쿼리 객체) : 쿼리 객체 생성시 미리 고정된 쿼리문에 의해 컴파일된 쿼리 객체가 생성.
		CallableStatement : procedure/function 등 일련의 명령 집합객체를 실행할때.
	5. Query 실행
		ResultSet executeQuery : SELECT
		(rowcount) int executeUpdate : INSERT/UPDATE/DELETE
	6. 자원 해제(close)
</pre>
<table>
	<c:forEach items="${dataList }" var="data">
		<tr>
			<td>${data['propertyName'] }</td>
			<td>${data['propertyValue'] }</td>
			<td>${data['propertyDescription'] }</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
