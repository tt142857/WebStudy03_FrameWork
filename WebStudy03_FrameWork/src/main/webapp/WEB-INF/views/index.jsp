<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String message = request.getParameter("message");
	if(StringUtils.isBlank(message)){
		message = (String)session.getAttribute("message");
	}
	if(StringUtils.isNotBlank(message)){
		%>
		<script type="text/javascript">
			alert("<%=message %>");
		</script>
		<%
		session.removeAttribute("message"); // FLASH ATTRIBUTE
	}
%>
<h4>웰컴 페이지</h4>
<%
	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
	if(authMember==null){
		%>
		<a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인</a>
		<a href="<%=request.getContextPath() %>/member/memberInsert.do">회원가입</a>
		<%
	}else{
		%>
		<%=authMember.getMemName() %>님
		<form id="logoutForm" method="post" action="${pageContext.request.contextPath }/login/logout.do"></form> 
		<a id="logoutBtn" href="${pageContext.request.contextPath }/login/logout.do">로그아웃</a>
		<script type="text/javascript">
			$("#logoutBtn").on("click", function(event){
				event.preventDefault();
				$(this).prev("form:first").submit();
				return false;
			});
		</script>
		<%
	}
%>
현재 누적 방문자 수 : <%=application.getAttribute("userCount") %>, ${userCount }














