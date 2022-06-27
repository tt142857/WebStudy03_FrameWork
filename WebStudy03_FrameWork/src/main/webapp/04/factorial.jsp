<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4>Factorial 연산 처리</h4>
<!-- 1. Model1 -> Model2 -> ajax -> XML/JSON(Marshalling) -->
2! -> 2*1 = 2, 3! -> 3*2*1 =  6
10! 
스크립틀릿 기호만으로 반복 곱하기연산 수행.
<!-- <form action="명령어_요청URL" method="파리미터 전송 방법 & 요청의 목적" enctype="요청 데이터의 표현 방식"> -->
<input type="radio"  name="datatype" value="html"/>HTML 
<input type="radio"  name="datatype" value="json"/>JSON
<input type="radio"  name="datatype" value="xml"/>XML

<form action="<%=request.getContextPath() %>/04/factorial.do" name="facForm" method="">
<input type="number" name="number" min="0" value=""/>
<input type="text" name="dummy" value="adfsasdfa" />
<input type="submit" value="=" />
</form>

<div id="resultArea">

</div>

<script type="text/javascript">
	let resultArea = $("#resultArea");
	let successMap = {
		json:function(resp){
			resultArea.html(resp.expression);
		},	
		xml:function(resp){
			console.log("==========XML=============");
			console.log(resp);
			let expr = $(resp).find("expression");
			resultArea.html(expr);
		},	
		html:function(resp){
			resultArea.html(resp);
		}	
	}
	let facForm = $("form[name]").on("submit", function(event){
		event.preventDefault();
		// form 의 submit 이벤트의 기본 특성은 동기 요청.
		console.log(event.target);
		console.log(this);
// 		$(this) -> jQuery 객체화
// 		XMLHttpRequest 객체 활용한 비동기 요청.
		let action = this.action; // $(this).attr("action");
		let method = this.method;
		let data = $(this).serialize(); // Query String 생성 ex) param1=value1&param2=value2
		console.log(data);
		let settings = {
				url : action,
				method : method,
				data : data,
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			}
		settings.dataType = $("[name='datatype']:checked").val();
		console.log("datatype : "+settings.dataType);
		settings.success=successMap[settings.dataType];
		$.ajax(settings);
		return false;
	});
	console.log(facForm);
</script>
r













