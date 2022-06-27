<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/skin-win8/ui.fancytree.min.css" rel="stylesheet">
<script src="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/jquery.fancytree-all-deps.min.js"></script>
<body>
<div id="tree"></div>
<script type="text/javascript">
	$("#tree").fancytree({
		source: {
			url:"${cPath}/server/browsing.do"
		},
		lazyLoad:function(event, data){
			console.log(event);
			console.log(data);
			var node = data.node;
	      // Load child nodes via Ajax GET /getTreeData?mode=children&parent=1234
	      data.result = {
	        url: "${cPath}/server/browsing.do",
	        data: {mode: "children", parent: node.key},
	        cache: false
	      };
		}
	});
</script>


















