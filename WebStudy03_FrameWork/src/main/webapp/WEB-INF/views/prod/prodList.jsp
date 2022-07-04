<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 테이블 태그를 이용하여 상품(상품코드, 상품명, 분류명, 거래처명, 입고일, 구매가, 판매가, 마일리지) 목록을 랜더링  -->

<table class='table'>
	<thead>
		<tr>
			<th>일련번호</th>
			<th>상품코드</th>
			<th>상품명</th>
			<th>분류명</th>
			<th>거래처명</th>
			<th>입고일</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
	<tfoot>
		<tr>
			<tr>
				<td colspan="9">
				<div class="d-flex justify-content-center pagingArea">
				</div>
				<div id="searchUI" class="d-flex justify-content-center">
					<select name="prodLgu">
						<option value>상품분류</option>
						<c:forEach items="${lprodList }" var="lprodMap">
							<option value="${lprodMap['lprodGu'] }">${lprodMap['lprodNm'] }</option> 
						</c:forEach>
					</select>
					<select name="prodBuyer">
						<option value>거래처명</option>
							<c:forEach items="${buyerList }" var="buyer">
							<option value="${buyer['buyerId'] }">${buyer['buyerName'] }</option> 
						</c:forEach>
					</select>
					<input type="text" name="prodName" placeholder="상품명" />
					<input id="searchBtn" type="button" value="검색" class="btn btn-success" />
				</div>
				</td>
			</tr>
		</tr>
	</tfoot>
</table>

<form id="searchForm" action="${cPath }/prod/prodList.do">
	<!-- searchUI와 searchForm의 개수는 같아야 함 -->
	<input type="text" name="prodLgu" placeholder="상품분류" />
	<input type="text" name="prodBuyer" placeholder="거래처명" />
	<input type="text" name="prodName" placeholder="상품명" />
	<input type="text" name="page" />
</form>

<script>
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea");
	let searchUI = $("#searchUI");
	<c:url value="/prod/prodView.do" var="viewURL">
		<c:param name="who" value="prodId" />
	</c:url>
	const VIEWURL = "${viewURL}";
	let makeSingleTr = function(index, prod) {
		let aTag = $("<a>").attr({
			"href" : VIEWURL.replace("prodId", prod.prodId),
			"data-bs-toggle" : "modal",
			"data-bs-target" : "#exampleModal"
		}).text(prod.prodName);
		return	$("<tr>").append(
					$("<td>").html(prod.rnum),
					$("<td>").html(prod.prodId),
					$("<td>").html(aTag),
					$("<td>").html(prod.lprodNm),
					$("<td>").html(prod.buyer.buyerName),
					$("<td>").html(prod.prodInsdate),
					$("<td>").html(prod.prodCost),
					$("<td>").html(prod.prodPrice),
					$("<td>").html(prod.prodMileage)
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
	
	$("[name='prodBuyer']").val("${pagingVO.detailCondition.prodBuyer}");
	$("[name=prodLgu]").on("change", function(event) {
		let selectedLgu = $(this).val();
		let options = $(this).siblings("[name=prodBuyer]").find("option");
		$.each(options,function(idx, opt) {
			if(idx == 0 || $(this).hasClass(selectedLgu)) {
				$(this).show();
			}
			else {
				$(this).hide();
			}
		});
	}).val("${pagingVO.detailCondition.prodLgu}").trigger("change");
	$("[name='prodName']").val("${pagingVO.detailCondition.prodName}");
	
	$(".pagingArea").on("click", "a", function(event) {
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
	});
	
	$("#searchBtn").on("click", function(event) {
		let inputs = searchUI.find(":input[name]")
		$(inputs).each(function(idx, input) {
			let name = $(this).attr("name");
			let value = $(this).val();
			searchForm.find("[name=" + name + "]").val(value);
		});
		//searchForm.submit();
	});
</script>