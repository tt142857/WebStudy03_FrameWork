<%@page import="java.util.Locale"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<h4> 회원 목록 조회 </h4>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>거주지역</th>
			<th>이메일</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${empty memberList }">
			<tr>
				<td colspan='6'>회원이 아직 없음</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${memberList }" var="member">
				<tr>
					<td>${member.memId }</td>
					<!-- var는 속성명으로 주어지기 때문에 EL로 불러옴 -->
					<c:url value="/member/memberView.do" var="viewURL">
						<c:param name="who" value="${member.memId }" />
					</c:url>
					<td><a href="${viewURL }" data-bs-toggle="modal" data-bs-target="#exampleModal">${member.memName }</a></td>
					<td>${member.memHp }</td>
					<td>${member.memAdd1 }</td>
					<td>${member.memMail }</td>
					<td>
						<fmt:setLocale value="<%=Locale.US %>" />
						<fmt:formatNumber value="${member.memMileage }" type="currency" />
					</td>
				
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</tbody>
</table>

<script type="text/javascript">
	var showModal = function() {
		event.preventDefault();
 		$("#memberForm").submit(); 
	}
</script>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
	  	...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<script>
	let exampleModal = $("#exampleModal").on("show.bs.modal", function(event) {
		console.log(event);
		let url = event.relatedTarget.href;
		$.ajax({
			url : url,
			dataType : "html",
			success : function(resp, status, jqXHR) {
				exampleModal.find(".modal-body").html(resp);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	}).on("hidden.bs.modal", function(event) {
		console.log(event.target);
		$(event.target).find(".modal-body").empty();
	});
</script>



