<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我要提问-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet"
	type="text/css">
	<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="/common/newkindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="/common/newkindeditor/lang/zh_CN.js"></script>
	<link rel="stylesheet" type="text/css"
		href="/common/newkindeditor/themes/default/default.css"></link>
	<style type="text/css">
.ke-toolbar {
	background-color: #FFFFFF;
	border: 0px;
	overflow: hidden;
	padding: 2px 5px;
	text-align: left;
}

.ke-statusbar {
	background-color: #FFFFFF;
	border: 0px;
	cursor: s-resize;
	font-size: 0;
	line-height: 0;
	overflow: hidden;
	position: relative;
	text-align: center;
}
</style>
	<script type="text/javascript">
		KindEditor
				.ready(function(K) {
					var imageEditor = K
							.editor({
								allowFileManager : true,
								fileManagerJson : '/keditor/browseImageLists?foldername=uploadfiles,content',
								uploadJson : '/keditor/uploadImages?foldername=uploadfiles,content'
							});
					K('#imageUpload').click(
							function() {
								imageEditor.loadPlugin('image', function() {
									imageEditor.plugin.imageDialog({
										hideImageSet : true,
										imageUrl : K('#picpath').val(),
										clickFn : function(url, title, width,
												height, border, align) {
											K('#picpath').val(url);
											var date = new Date();
											var s = date.getTime();
											$('#picpath_s').attr("src",
													url + "?v=" + s);
											$('#picpath_s').show();
											imageEditor.hideDialog();
										}
									});
								});
							});
				});

		var editor;
		$(function() {
			var editorOptions = {
				items : [ 'emoticons', 'image' ],
				allowFileManager : true,
				fileManagerJson : '/keditor/browseImageLists?foldername=uploadfiles',
				uploadJson : '/keditor/uploadImages?foldername=uploadfiles'
			};

			editor = KindEditor.create('#content', editorOptions);
		});
	</script>
</head>
<body>
	<!--top-->
	<s:action name="top" namespace="/membercenter" executeResult="true">
	</s:action>
	<!--top end-->
	<!--content-->
	<div class="content1 layout-control">
		<!--left-->
		<s:action name="left" namespace="/membercenter" executeResult="true">
		</s:action>
		<!--left end-->
		<!--right-->
		<!--right-->
		<div class="content-right fl">
			<h2 class="second-title">
				<a class="blue" href="/"><<返回上一页
				</a>问题区
			</h2>
			<div class="note-title">
				<s:if test="memberInfo.type==0"><a class="write-note fr" href="javascript:fowardBack('/membercenter/myzyinfo?backUrl=','<s:property value="backUrl"/>')"><input class="inputButton" type="button" value="我要提问"/></a></a></s:if>
				<s:if test="memberInfo.type==0">
				<a class="note-a" href="/membercenter/myzylist">我的提问</a>
				<a class="note-a" href="/membercenter/myzyflist">好友提问</a>
				</s:if>
	        </div>

			<!--发表-->
			<div class="publish" style="margin-top:20px;margin-bottom:40px;">
				<ul class="publish-list">
					<li class="cur"><i
						class="pub-happy"></i>我要提问</li>
				</ul>

				<s:form id="moodfrm" name="moodfrm" action="myzysave"
					namespace="/membercenter" method="post" theme="simple">
					<div class="pub-content">
						<s:hidden name="memberMood.id"></s:hidden>
						<s:hidden name="memberMood.type" value="1"></s:hidden>
						<s:hidden name="memberMood.mid"></s:hidden>
						<s:hidden name="memberMood.isdel"></s:hidden>
						<s:hidden name="memberMood.ctime"></s:hidden>
						<s:hidden name="memberMood.viewnumber"></s:hidden>
					    <s:textarea name="memberMood.content" id="content" cssClass="pub-textarea" />
					</div>
					<div style="padding-top: 10px;">
						<font class="font14">权限：</font> <select style="height: 25px;" name="memberMood.qx"
							id="qx">
							<option value="0">免费咨询</option>
							<option value="1">@好友</option>
							<option value="2">@龙爸爸</option>
						</select>

						<script type="text/javascript">
						    if('<s:property value="memberMood.qx" escape="false" />' != ''){
						      document.getElementById("qx").value = '<s:property value="memberMood.qx" escape="false" />'
						    }							
						</script>
					</div>
					<div class="addword"></div>
					<span id="mcontentclenid"></span>
					<div class="pub-btn" style="float:left;">
						<input type="image" src="/member/images/btn1.png" name="" value="" />
					</div>
				</s:form>
			</div>
			<!--发表end-->


		</div>
		<!--right end-->
		<div class="clear"></div>
	</div>
	<!--content end-->
	<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
<%
	Object msg=request.getSession().getAttribute("saveinfomsg");
	if(msg!=null){
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("saveinfomsg");
	}
%>
