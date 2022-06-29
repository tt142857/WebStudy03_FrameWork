<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
${prod }
<c:set var="memberSet" value="${prod.memberSet }" />
<c:if test="${empty memberSet }">

</c:if>
<c:if test="${not empty memberSet }">
	<c:forEach items="${memberSet }" var="user">
	<c:url value="/member/memberView.do" var="memberViewURL">
		<c:param name="who" value="${user.memId }" />
		<c:param name="layout" value="grid" />
	</c:url>
		<a href="${memberViewURL }">${user.memName }</a>
	</c:forEach>
</c:if>
</body>
</html>