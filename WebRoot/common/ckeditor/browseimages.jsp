<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>浏览图片</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
	font-size: 14px;
}

.imgbox {
	padding: 3px;
	margin: 5px;
	border: 1px #F30 solid;
	float: left;
}

.imgbox img {
	border: 0px;
	float: left;
	cursor: pointer;
}

.imgbox span {
	clear: left;
	text-align: center;
	width: 100px;
	line-height: 16px;
	overflow: hidden;
}

.box {
	overflow: scroll;
	width: 630px;
	height: 410px;
}
</style>
		<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
		<script type="text/javascript"
			src="/common/contextmenu/jquery.contextmenu.r2.js"></script>
		<script type="text/javascript">
	//这段函数是重点，不然不能和CKEditor互动了
	function funCallback(funcNum, fileUrl) {
		fileUrl=$.trim(fileUrl);
		var parentWindow = (window.parent == window) ? window.opener
				: window.parent;
		parentWindow.CKEDITOR.tools.callFunction(funcNum, fileUrl);
		window.close();
	}
	$(document).ready(function() {
		$('.imgbox').contextMenu('myMenu1',{
			bindings : {
				'delete' : function(t) {
					var src = t.id;
					var id = $("#pid").val();
					if (confirm("确认删除吗？")) {
						location
								.replace("/ckeditor/deleteImage?src="
										+ src
										+ "&id=<s:property value="id"/>&CKEditorFuncNum=<s:property value="CKEditorFuncNum"/>&foldername=<s:property value="foldername"/>");
					}
				}
			}
		});
	});
</script>
	</head>
	<body>
		<div class="contextMenu" id="myMenu1">
			<ul>
				<li id="delete">
					<img src="/common/contextmenu/delete.gif" />
					删除
				</li>
			</ul>
		</div>
		<div class="box">
			<s:iterator value="imap">
				<div class="imgbox" id="<s:property value="key"/>">
					<img title="<s:property value="value"/>"
						onclick="funCallback('<s:property value="CKEditorFuncNum"/>','<s:property value="key"/>')"
						alt="<s:property value="value"/>" src="<s:property value="key"/>"
						width="100" height="100" />
					<span style="float: left"><a
						href="javascript:funCallback('<s:property value="CKEditorFuncNum"/>','<s:property value="key"/>')"
						title="<s:property value="value"/>"
						alt="<s:property value="value"/>"><s:property value="value" />
					</a>
					</span>
				</div>
			</s:iterator>
		</div>
	</body>
</html>
