<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/elDesc.jsp</title>
</head>
<body>
<h4>표현 언어(Expression Language) : Model2 구조에서 활용</h4>
<pre>
	: 값을 출력하기 위한 기능만을 갖고 있는 언어. 제어문의 형태가 없음. \${속성명 }, 스크립트 형태 언어
	<%
		String sample = "1000";
		pageContext.setAttribute("sample", sample);
		pageContext.setAttribute("blank", "");
		pageContext.setAttribute("list", Arrays.asList());
		pageContext.setAttribute("test", "ab");
		
		MemberVO member1 = new MemberVO();
		pageContext.setAttribute("member", member1);
		MemberVO member2 = new MemberVO();
		member2.setMemName("신규");
		request.setAttribute("member", member2);
	%>
	<%=sample %>, ${sample }
	
	1. EL 연산자 - 스크립트 언어에서 주의할 것, 연산의 중심은 연산자(1이 연산자임)
	  1) 산술연산자 : +-/*%
	  	${1+1 }, ${"1"+1 }, \${"a"+1 }, ${a+1 }, ${sample+1 }
	  	
	  2) 논리연산자 : &&(and), ||(or), !(not)
	    ${true and true }, ${"true" and true }, ${a or true }
	  
	  3) 단항연산자 : empty(비어있는지 안비어있는지, length로 판단해서 boolean으로 반환함)
	  	- 속성 존재 여부 → null 여부 → String(length), Collection(size)
	   	- 객체가 존재해도 안이 비어있으면 true를 반환함
	   	
	  	${empty sample }, ${empty a }, ${empty blank }, ${empty list }
	  	
	  4) 3항 연산자	: 조건식? 참 : 거짓
	    ${not empty test? "있다" : "없다" }
	    
	2. 객체 접근 방법
	  ${requestScope.member.getMemName() }, ${requestScope.member.memName } // 오른쪽 방식 사용, 왼쪽은 최신버전부터만 되기 때문
	  ${requestScope.member.getMemTest() }, ${requestScope.member.memTest }
	  ${requestScope.member["memName"] }
	  
	3. 집합 객체 접근 방법 : <a href="elCollection.jsp">집합객체</a>
	
	4. EL 기본 객체(11가지)
	  1) scope 객체 : pageScope, requestScope, sessionScope, applicationScope
	
</pre>
</body>
</html>