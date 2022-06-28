<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>마이페이지</h4>
	<c:if test="${empty authMember }">
		<a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인</a>
	</c:if>
	<c:if test="${not empty authMember }">
		<table class='table'>
			<tr>
		     	<th>회원아이디</th>
		     	<td>${member.memId}</td>
		   </tr>
		   <tr>
		     	<th>비밀번호</th>
		     	<td>${member.memPass}</td>
		   </tr>
		   <tr>
		      	<th>회원명</th>
		      	<td>${member.memName}</td>
		   </tr>
		   <tr>
		      	<th>주민번호1</th>
		      	<td>${member.memRegno1}</td>
		   </tr>
		   <tr>
		     	 <th>주민번호2</th>
		     	 <td>${member.memRegno2}</td>
		   </tr>
		   <tr>
		      	<th>생일</th>
		      	<td>${member.memBir}</td>
		   </tr>
		   <tr>
		      	<th>우편번호</th>
		      	<td>${member.memZip}</td>
		   </tr>
		   <tr>
		      	<th>주소1</th>
		      	<td>${member.memAdd1}</td>
		   </tr>
		   <tr>
		      	<th>주소2</th>
		      	<td>${member.memAdd2}</td>
		   </tr>
		   <tr>
		      	<th>집전화번호</th>
		      	<td>${member.memHometel}</td>
		   </tr>
		   <tr>
		      	<th>회사번호</th>
		      	<td>${member.memComtel}</td>
		   </tr>
		   <tr>
		      	<th>휴대폰</th>
		      	<td>${member.memHp}</td>
		   </tr>
		   <tr>
		      <th>이메일</th>
		      <td>${member.memMail}</td>
		   </tr>
		   <tr>
		      	<th>직업</th>
		      	<td>${member.memJob}</td>
		   </tr>
		   <tr>
		     	<th>취미</th>
		     	<td>${member.memLike}</td>
		   </tr>
		   <tr>
		      	<th>기념일</th>
		      	<td>${member.memMemorial}</td>
		   </tr>
		   <tr>
		      	<th>기념일자</th>
		      	<td>${member.memMemorialday}</td>
		   </tr>
		   <tr>
		      	<th>마일리지</th>
		   		<td>${member.memMileage}</td>
		   </tr>
		   <tr>
		   		<th>탈퇴여부</th>
		   		<td>${member.memDelete}</td>
		   </tr>
		   <tr>
		      	<td colspan="2">
		        	<input type="button" class="btn btn-primary" value="수정">
		        	<input type="button" class="btn btn-danger" value="탈퇴">
				</td>
			</tr>
			<tr>
				<th>구매기록</th>
				<td>
					<table class='table'>
						<thead>
							<tr>
								<th>상품명</th>
								<th>분류명</th>
								<th>거래처명</th>
								<th>구매가</th>
								<th>판매가</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="buyList" value="${member.buyList }" />
							<c:if test="${not empty buyList }">
								<c:forEach items="${buyList }" var="prod">
									<tr>
										<td>${prod.prodName }</td>
										<td>${prod.lprodNm }</td>
										<td>${prod.buyer.buyerName }</td>
										<td>${prod.prodCost }</td>
										<td>${prod.prodPrice }</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty buyList }">
								<tr>
									<td colspan="5">구매기록이 없음</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>

<script>
	$("input").on("click", function() {
		location.href = "${cPath}/member/memberUpdate.do";
	});
</script>