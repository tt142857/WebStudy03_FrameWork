<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty message }">
	<script>
		alert("${message }");
	</script>
</c:if>
<h4>회원 가입 양식</h4>
<form method="post" enctype="application/x-www-form-urlencoded">
	<table>
		<tr>
			<th>회원아이디</th>
			<td><input type="text" name="memId" value="${member.memId }" />
				<span class="error">${errors['memId'] }</span></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="memPass" value="${member.memPass }" />
				<span class="error">${errors['memPass'] }</span></td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><input type="text" name="memName" value="${member.memName }" /></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><input type="text" name="memRegno1"
				value="${member.memRegno1 }" /></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><input type="text" name="memRegno2"
				value="${member.memRegno2 }" /></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input type="date" name="memBir" value="${member.memBir }" /></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="memZip" value="${member.memZip }" /></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input type="text" name="memAdd1" value="${member.memAdd1 }" /></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input type="text" name="memAdd2" value="${member.memAdd2 }" /></td>
		</tr>
		<tr>
			<th>집전화번호</th>
			<td><input type="text" name="memHometel"
				value="${member.memHometel }" /></td>
		</tr>
		<tr>
			<th>회사번호</th>
			<td><input type="text" name="memComtel"
				value="${member.memComtel }" /></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><input type="text" name="memHp" value="${member.memHp }" /></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="memMail" value="${member.memMail }" /></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" name="memJob" value="${member.memJob }" /></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input type="text" name="memLike" value="${member.memLike }" /></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><input type="text" name="memMemorial"
				value="${member.memMemorial }" /></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input type="date" name="memMemorialday"
				value="${member.memMemorialday }" /></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input type="number" name="memMileage"
				value="${member.memMileage }" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" class="btn btn-primary" value="저장" /> 
			<input type="reset" class="btn btn-warning" value="취소" /></td>
		</tr>
	</table>
</form>










