<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/refresh.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<h4> Auto request </h4>
<h4>현재 서버의 시간 :<%=new Date() %> </h4>
<h4>비동기 요청으로 갱신되는 시간 : <span id="timeArea"></span> </h4>
<input type="button" value="start" id="start" class="control"/>
<input type="button" value="stop"  id="stop" class="control"/>
<pre>
	1. Refresh 를 이용한 서버사이드 방식의 auto 
	<%--
		response.setIntHeader("Refresh", 1);
	--%>
	2. 클라이언트 사이드 방식 auto
<!-- 		1) HTML meta tag : <meta http-equiv="Refresh" content="3;url=https://www.naver.com" > -->
		2) JavaScript 의 scheduling 함수 : 
		
</pre>
<script type="text/javascript">
// 	setTimeout(() => {
// 		location.reload();
// 	}, 1000);
	let timeArea = $("#timeArea");
	let jobId = null;
	$(".control").on("click", function(event){
// 		this.id
		let btnId = $(this).prop("id");
		if(btnId == "start"){
			jobId = setInterval(function(){
				$.ajax({
					url : "getServerTime.jsp",
					dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
					,
					success : function(resp, status, jqXHR) {
						timeArea.html(resp.time);
					},
					error : function(jqXHR, status, error) {
						console.log(jqXHR);
						console.log(status);
						console.log(error);
					}
				});
			}, 1000);
		}else{
			clearInterval(jobId);	
		}
	});
	
</script>
</body>
</html>












