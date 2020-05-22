<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的动态-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
<style type="text/css">
.ke-toolbar {
    background-color:#FFFFFF;
	border:0px;
    overflow: hidden;
    padding: 2px 5px;
    text-align: left;
}

.ke-statusbar {
    background-color: #FFFFFF;
    border:0px;
    cursor: s-resize;
    font-size: 0;
    line-height: 0;
    overflow: hidden;
    position: relative;
    text-align: center;
}
</style>
<script type="text/javascript">
KindEditor.ready(function(K) {
	var imageEditor = K.editor({
		allowFileManager : true,
		fileManagerJson : '/keditor/browseImageLists?foldername=uploadfiles,content',
		uploadJson : '/keditor/uploadImages?foldername=uploadfiles,content'
	});
	K('#imageUpload').click(function() {
		imageEditor.loadPlugin('image', function() {
			imageEditor.plugin.imageDialog({
				hideImageSet : true,
				imageUrl : K('#picpath').val(),
				clickFn : function(url, title, width, height, border, align) {
					K('#picpath').val(url);
					var date=new Date();
					var s=date.getTime();
					$('#picpath_s').attr("src",url+"?v="+s);
					$('#picpath_s').show();
					imageEditor.hideDialog();
				}
			});
		});
	});
});

var editor;
$(function(){
	var editorOptions={
		items : [
			'emoticons','image'
		],
		allowFileManager : true,
		fileManagerJson : '/keditor/browseImageLists?foldername=uploadfiles',
		uploadJson : '/keditor/uploadImages?foldername=uploadfiles'
	};
	
	editor = KindEditor.create('#content',editorOptions);
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>我的发布</h2>
        <div class="note-title">
        	<a class="note-a note-cur" href="/membercenter/mydtlist">我的发布</a>
        </div>
		
		<!--发表-->
	    <div class="publish" style="margin-top:20px;margin-bottom:40px;">
	    	<ul class="publish-list">
	        	<li class="cur" onclick="location.replace('/membercenter/mydtlist')"><i class="pub-happy"></i>表心情</li>
	            <li onclick="location.replace('/membercenter/diaryinfo?backUrl=/membercenter/mydtlist')"><i class="pub-writer"></i>写日记</li>
	            <li onclick="location.replace('/membercenter/contentinfo?backUrl=/membercenter/mydtlist')"><i class="pub-article"></i>发文章</li>
	            <li onclick="location.replace('/membercenter/photoinfo?backUrl=/membercenter/mydtlist')"><i class="pub-picuture"></i>秀相片</li>
	            <li class="last" onclick="location.replace('/membercenter/vedioinfo?backUrl=/membercenter/mydtlist')"><i class="pub-video"></i>传视频</li>
	        </ul>
			
			<s:form id="moodfrm" name="moodfrm" action="moodsave" namespace="/membercenter" method="post" theme="simple">
	        <div class="pub-content">		
			    <s:hidden name="memberMood.id"></s:hidden>
				<s:textfield id="content" name="no" cssClass="pub-textarea"/>		
	        </div>
	        <div class="addword"></div><span id="mcontentclenid"></span>
	        <div class="pub-btn" style="float:left;"><input type="image" src="/member/images/btn1.png" name="" value="" /></div>
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
