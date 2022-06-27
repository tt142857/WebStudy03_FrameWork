<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/skin-win8/ui.fancytree.min.css" rel="stylesheet">
<script src="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/jquery.fancytree-all-deps.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/jquery.fancytree-all.min.js"></script>
<style type="text/css">
	#tree1{
		height:500px;
	}
	/* PlainScrollbar-specific Styles */
    .plain-scrollbar[data-scrollable="false"] {
      visibility: hidden;
    }
</style>
<script type="text/javascript">
	$(function(){
		var commandProcess = function(param){
			let command = param.command;
			let srcFile = param.data.otherNode;
			let destFolder = param.node;
			console.log(srcFile);
			$.ajax({
				url:"<%=request.getContextPath() %>/serverExplorer.do",
				method:"post",
				data:{
					srcFile:srcFile.getKeyPath()
					, destFolder:destFolder.getKeyPath()
					, command:command
				}, 
				success:function(resp){
					if(!resp.success) return;
					console.log(srcFile);
// 					서버상의 자원으로 node 전체 reload
// 					srcFile.parent.load(true);
// 					destFolder.load(true);
// 					이동 후 노드의 키가 변경되어야 함
					if(command=="COPY"){
						srcFile.copyTo(destFolder);
					}else if(command=="MOVE"){
						srcFile.moveTo(destFolder);
					}else if(command=="DELETE"){
						srcFile.remove();
					}
// 					console.log("path : "+srcFile.getPath()); // spec 상에 있음.
					console.log("Keypath : "+srcFile.getKeyPath()); // 자기 자신을 제외할때, false
				},
				error:function(errResp){
					alert(errResp);
				}
			});
			
		}

		$("#tree1").fancytree({
    	  extensions: ["dnd", "edit"],
	      selectMode: 1,
	      source:{
	    	  url:"<%=request.getContextPath() %>/serverExplorer.do"
	    	  , data:{
	    		  url:"${param.url}"
	    	  }
	      },
	      lazyLoad: function(event, data){
	        data.result = {url: "<%=request.getContextPath() %>/serverExplorer.do?url="+data.node.key};
	      },
	      init:function(event, data){
	    	  let tree = data.tree;
	    	  let rootNode =  tree.getRootNode();
	    	  rootNode.key = "/";
	      },
	      dnd: {
	          autoExpandMS: 400,
	          focusOnClick: true,
	          preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
	          preventRecursiveMoves: true, // Prevent dropping nodes on own descendants
	          dragStart: function(node, data) {
	             return !node.folder;
	          },
	          dragEnter: function(node, data) {
	        	return node.folder && (node != data.otherNode.parent);
	          },
	          dragDrop: function(node, data) {
	        	let command = null;
	        	if(data.originalEvent.ctrlKey){
	        		command = "COPY";
	        	}else{
	        		command = "MOVE";
	        	}
	        	commandProcess({
	        		command:command,
	        		node:node,
	        		data:data
	        	});
	          }
	        }
	    });
		$(document).on("keyup", function(event){
			if(event.keyCode!=46) return true;
			let tree = $.ui.fancytree.getTree();
			let node = tree.getActiveNode();
			if(node && !node.folder){
				if(confirm("정말 지워요?")){
					commandProcess({
		        		command:"DELETE",
		        		node:node,
		        		data:{otherNode:node}
		        	});
				}
			}
		});
	});
</script>
</head>
<body>
<h4>Design pattern(Adapter pattern) 과 jquery 플러그인(FancyTree)을 이용한 웹 리소스 파일 브라우징</h4>
<div id="tree1">
</div>
</body>
</html>