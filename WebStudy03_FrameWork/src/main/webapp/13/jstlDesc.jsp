<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/jstlDesc.jsp</title>
</head>
<body>
<h4>JTL(Jsp Standard Tag Library)</h4>
<pre>
	: 커스텀 태그들 중 널리 쓰이는 것들을 모아놓은 라이브러리
	  taglib로 사용할 커스텀 태그의 라이브러리 파일을 로딩

	1. core : 제어문에서 사용
	  조건문
	    - 단일 조건문 : if-else -> if문을 한번 더 따로 씀
	    - 다중 조건문 : choose(switch)~when(case)~otherwise(default)
	  반복문
	    - 일반 반복문 : foreach(begin, step, end) -> for(int i = 0; i < 10; i++) 
	    - 향상된 반복문 : foreach(items, var) -> for(element 참조 블럭 변수:collection)
	  속성 제어 : set, remove
	    - 속성 생성 : set(속성명-var, 속성값-value, 영역-scope)
	    - 속성 제거 : remove(속성명-var, 영역-scope)
	  기타 : url(url rewriting), import, out
	  <c:url value="/02/first.jsp" var="newURL">
	  	  <!-- queryString -->
		  <c:param name="name1" value="value1"></c:param> 
		  <c:param name="name2" value="value2"></c:param>
	  </c:url> 
	  ${newURL }
	  <a href="${newURL }">first.jsp</a>
	  
	2. fmt : parsing, formatting
		<c:set var="today" value="<%=new Date() %>"></c:set>
		<fmt:formatDate value="${today }" dateStyle="long" timeStyle="long" type="both" var="todayStr" />
		before parsing : ${todayStr }
		<fmt:parseDate value="${todayStr }" dateStyle="long" timeStyle="long" type="both" var="today2" />
		after parsing : ${today2 }, millisecond : ${today2.time }
		
	3. functions : ${fn:replace("abc", "b", "B") }
</pre>

<!-- 단일 조건문 -->
<c:if test="${empty sample }">
	"sample 속성 없음"
</c:if>
<c:if test="${not empty sample } ">
	"sample 속성 있음"
</c:if>

<!-- 다중 조건문 -->
<c:choose>
	<c:when test="${empty sample }">
		"sample 속성 없음"
	</c:when>
	<c:otherwise>
		"sample 속성 있음"
	</c:otherwise>
</c:choose>

<!-- 일반 반복문 -->
<!-- step이 1이면 생략 가능, 음수로 만들어서 감소시킬 수 없음 -->
<!-- i는 속성이기 때문에 EL을 사용함 -->
<c:forEach begin="1" step="1" end="10" var="i">
	${i }
</c:forEach>

<!-- 향상된 반복문 -->
<!-- 속성 생성 -->
<%--
	pageContext.setAttribute("array", new String[]{"value1", "value2"});
--%> → <c:set var="array" value='<%=new String[]{"value1", "value2"} %>' scope="page" ></c:set>
<c:forEach items="${array }" var="element">
	${element }
</c:forEach>
<c:remove var="array" scope="page" />
<c:if test="${not empty array }">
	배열 있음
</c:if>
<c:if test="${empty array }">
	배열 없음
</c:if>

<c:import url="https://www.naver.com" var="naver"></c:import>
<c:out value="${naver }" escapeXml="true"></c:out>

</body>
</html>