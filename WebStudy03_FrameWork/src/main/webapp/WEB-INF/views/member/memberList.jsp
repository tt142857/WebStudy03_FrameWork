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
			<th>No.</th>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>거주지역</th>
			<th>이메일</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
	<tfoot>
		<tr>
			<tr>
				<td colspan="7">
				<div class="d-flex justify-content-center pagingArea">
				</div>
				<div id="searchUI" class="d-flex justify-content-center">
					<select name="searchType">
						<option value="">전체</option>
						<option value="name">이름</option>
						<option value="address">지역</option>
					</select>
					<input type="text" name="searchWord" />
					<input id="searchBtn" type="button" value="검색" class="btn btn-success" />
				</div>
				</td>
			</tr>
		</tr>
	</tfoot>
</table>
<form id="searchForm" action="${cPath }/member/memberList.do">
	<input type="text" name="page" />
	<input type="text" name="searchType" />
	<input type="text" name="searchWord" />
</form>


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

<script>
	$("[name='searchType']").val("${pagingVO.simpleCondition.searchType}");
	$("[name='searchWord']").val("${pagingVO.simpleCondition.searchWord}");
	
	$(".pagingArea").on("click", "a", function(event) {
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
	});
	
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea")
	let searchUI = $("#searchUI");
	<c:url value="/member/memberView.do" var="viewURL">
		<c:param name="who" value="memId" />
	</c:url>
	const VIEWURL = "${viewURL}";
	let makeSingleTr = function(index, member) {
		let aTag = $("<a>").attr({
						"href" : VIEWURL.replace("memId", member.memId),
						"data-bs-toggle" : "modal",
						"data-bs-target" : "#exampleModal"
					}).text(member.memName);
		return $("<tr>").append(
					$("<td>").html(member.rnum),
					$("<td>").html(member.memId),
					$("<td>").html(aTag),
					$("<td>").html(member.memHp),
					$("<td>").html(member.memAdd1),
					$("<td>").html(member.memMail),
					$("<td>").html(member.memMileage)
				);
	}
	
	let searchForm = $("#searchForm").on("submit", function(event) {
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json", // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
			success : function(resp, status, jqXHR) {
				let pagingVO = resp.pagingVO;
				let memberList = resp.pagingVO.dataList;
				let trTags = [];
				if(memberList && memberList.length > 0) {
					$(memberList).each(function(index, member) {
						trTags.push(makeSingleTr(index, member));
					});
				} else {
					let trTag = $("<tr>").html(
									$("<td>").attr("colspan", "6")	
											 .html("회원이 아직 없음")
								);
					trTags.push(trTag);
				}
				listBody.html(trTags);
				pagingArea.html(pagingVO.pagingHTMLBS);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		return false;
	}).submit();
	
	$("#searchBtn").on("click", function(event) {
		searchForm.get(0).reset();
		let inputs = searchUI.find(":input[name]")
		$(inputs).each(function(idx, input) {
			let name = $(this).attr("name");
			let value = $(this).val();
			
			searchForm.find("[name=" + name + "]").val(value);
		});
		searchForm.submit();
	});
</script>


