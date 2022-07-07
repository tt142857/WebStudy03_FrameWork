<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>전송 이후 서버사이드 처리</h4>
<pre>
	1. uploader 파라미터는 로깅 프레임워크를 이용하여 콘솔에 출력함
	2. fileSystemResource 이름의 파일 데이터는 D:/contents 폴더에 저장하고, 저장 경로를 확보
	3. webResource 이름의 파일 데이터는 /resources/images 폴더에 저장하고, 저장 경로를 확보
	모든 요청 처리가 완료되면 /fileUploadForm 으로 다시 redirect 하되,
	처리하는 과정에서 확보한 데이터들을 폼 하단, printArea에 출력함
</pre>

<pre>
	- 추가미션 -
	업로드 처리 후 현재 폼 페이지에 출력되는 데이터(저장경로)를 클릭한 경우,
	1. fileSystemResource는 다운로드 처리
	2. webSource는 브라우저에 해당 파일이 바로 출력되도록 처리함
	단, webResource는 반드시 이미지 파일만 업로드할 수 있도록 검증 구조를 적용할 것
</pre>
	<form method="post" action="${cPath }/file/upload_3.do" enctype="multipart/form-data">
		<input type="text" name="uploader" placeholder="업로더의 이름" />
		<input type="file" name="fileSystemResource" />

		<!-- accept 이녀석은 브라우저에서 수정이 되기 때문에 서버사이드 검증이 따로 필요함 -->
		<input type="file" name="webResource" accept="image/*" /> 
		<input type="submit" value="업로드" />
	</form>
<div id="printArea">
	<c:forEach items="${savePathList }" var="savePath">
		<c:if test="${fn:startsWith(savePath, 'd:')}">
			<c:url value="/file/download.do" var="downloadURL">
				<c:param name="file" value="${savePath }" />
			</c:url>
			<a href="${downloadURL }" >${savePath} 다운로드</a> 		
		</c:if>
		<c:if test="${not fn:startsWith(savePath, 'd:')}">
			<a href="${cPath }${savePath }" >${savePath}</a> 
		</c:if>
		<br/>
	</c:forEach>
	<c:remove var="savePathList" scope="session" />
</div>
</body>
<script>

</script>
</html>