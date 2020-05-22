<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的说说-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
//判断字节长度
function zjlen(s) {
	var l = 0;
	var a = s.split("");
	for (var i=0;i<a.length;i++) {
		if (a[i].charCodeAt(0)<299) {
			l++;
		} else {
			l+=2;
		}
	}
	return l;
}

function checkcontentinfo(){
	var title=$.trim($("#content").val());
	if(title==""){
		alert("内容不能为空");
		return false;
	}else if(zjlen(title)>2000){
	    alert("内容不能超过1000个汉字长度");
	    document.getElementById("title").focus();
		return false;
	}
		
	return true;
}

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
			'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
			'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
			'anchor', 'link', 'unlink'
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
   <div class="content-right fl">
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>我的说说</h2>

<s:form action="mmoodsave" namespace="/membercenter" method="post" theme="simple">
  <s:hidden name="backUrl"></s:hidden>
  <s:hidden name="memberMood.id"></s:hidden>
  <s:hidden name="memberMood.mid"></s:hidden>
  <s:hidden name="memberMood.isdel"></s:hidden>
  <s:hidden name="memberMood.ctime"></s:hidden>
  <s:hidden name="memberMood.rcid"></s:hidden>
  <s:hidden name="memberMood.viewnumber"></s:hidden>
<table class="note-tabs1" width="710" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="50" align="center" width="80" valign="top"><font class="font14">内容：</font></td>
    <td height="50">
    <s:textarea name="memberMood.content" id="content" style="width:620px;height:500px;"/>
    </td>
  </tr>

  <tr>
    <td height="50" align="center" valign="top">&nbsp;</td>
    <td height="50"><input class="note-saved" type="submit" value="" /></td>
  </tr>
</table>
</s:form>

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
