<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="${cPath }/resources/js/jquery.validator/jquery.validate.min.js"></script>

<form method="post" id="insertForm" enctype="multipart/form-data"> <!-- 안되면 다시 action 넣어보기 -->
	<table class="table table-bordered">
		<tr>
			<th>상품명</th>
			<td><input type="text" name="prodName" value="${prod['prodName'] }" required /><span class="error">${errors['prodName'] }</span></td></td>
		</tr>
		<tr>
			<th>분류</th>
			<td>
			<select name="prodLgu">
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<option value="${lprod['lprodGu'] }">${lprod.lprodNm }</option>
					</c:forEach>
			</select>
			<span class="error">${errors['prodLgu'] }</span>
			</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
			<select name="prodBuyer">
					<option value>상품거래처</option>
					<c:forEach items="${buyerList }" var="buyer">
						<option value="${buyer.buyerId }" class="${buyer.buyerLgu }">${buyer.buyerName }</option>
					</c:forEach>
			</select>
			<span class="error">${errors['prodBuyer'] }</span></td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><input type="number" name="prodCost" value="${prod['prodCost'] }" /></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input type="number" name="prodPrice" value="${prod['prodPrice'] }"/></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><input type="number" name="prodSale" value="${prod['prodSale'] }" /></td>
		</tr>
		<tr>
			<th>상품개요</th>
			<td><input type="text" name="prodOutline" value="${prod['prodOutline'] }"/><span class="error">${errors['prodOutline'] }</span></td>
		</tr>
		<tr>
			<th>상세설명</th>
			<td><input type="text" name="prodDetail" value="${prod['prodDetail'] }"/></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<input type="file" class="form-control" name="prodImage" />
				<span class="error">${errors["prodImg"] }</span>
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td><input type="number" name="prodTotalstock" value="${prod['prodTotalstock'] }" /></td>
		</tr>
		<tr>
			<th>PROD_PROPERSTOCK</th>
			<td><input type="number" name="prodProperstock" value="${prod['prodProperstock'] }" /></td>
		</tr>
		<tr>
			<th>사이즈</th>
			<td><input type="text" name="prodSize" value="${prod['prodSize'] }" /></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input type="text" name="prodColor" value="${prod['prodColor'] }" /></td>
		</tr>
		<tr>
			<th>PROD_DELIVERY</th>
			<td><input type="text" name="prodDelivery" value="${prod['prodDelivery'] }" /></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><input type="text" name="prodUnit" value="${prod['prodUnit'] }" /></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><input type="number" name="prodQtyin" value="${prod['prodQtyin'] }" /></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><input type="number" name="prodQtysale" value="${prod['prodQtysale'] }" /></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input type="number" name="prodMileage" value="${prod['prodMileage'] }" /></td>
		</tr>
	</table>

	<td colspan="2"><input type="submit" class="btn btn-primary" value="등록" /> </td>
</form>

<script>

	$("#insertForm").validate({
		rules: {
			prodName : "required",
			prodLgu : "required",
			prodBuyer : "required"
		},	
		messages: {
			prodName : "상품명 필수",
			prodLgu : "분류 필수",
			prodBuyer : "거래처 필수"
	    }
	});
	
	let prodBuyerTag = $("[name=prodBuyer]").val("${prod.prodBuyer}");
	$("[name=prodLgu]").on("change", function(event) {
		let selectedLgu = $(this).val();
		let options = $(this).siblings("[name=prodBuyer]").find("option");
		
		console.log(options);
		$.each(options, function(idx, opt) {
			console.log(idx, opt);
			if (!selectedLgu || idx == 0 || $(this).hasClass(selectedLgu)) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
	}).val("${prod.prodLgu}").trigger("change");
</script>