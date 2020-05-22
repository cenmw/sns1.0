<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的日记-龙爸爸成长在线</title>
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

$(function(){
	$("#title").keyup(function(){
		var title_len = zjlen($("#title").val());
	    if(title_len>60){
	    	$("#titleclenid").html("标题不能超过30个汉字长度");
	    }else if(title_len==0){
	    	$("#titleclenid").html("请输入标题");
	    }
	    $("#titlelenid").html(parseInt(title_len/2)+"/"+30);
	});
    $("#title").focus();    
});

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

function checkcontentinfo(){
	var title=$.trim($("#title").val());
	if(title==""){
		alert("标题不能为空");
		return false;
	}else if(zjlen(title)>60){
	    alert("标题不能超过30个汉字长度");
	    document.getElementById("title").focus();
		return false;
	}
	
	var cid=$.trim($("#cid").val());
	if(cid==""){
		alert("分类不能为空");
		return false;
	}
		
	return true;
}

//添加分类
function addclass(){
	$.fn.popup({iframe:true,url:'/membercenter/blogclassinfo',type:3,title:'添加日志分类',width:400,height:200});
}
		
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
   		<h2 class="second-title"><a class="blue" href="javascript:location.href='<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>'"><<返回上一页</a>新日记</h2>

<s:form action="blogsave" namespace="/membercenter" method="post" theme="simple" onsubmit="return checkcontentinfo()">
  <s:hidden name="backUrl"></s:hidden>
  <s:hidden name="memberBlog.id"></s:hidden>
  <s:hidden name="memberBlog.keyword"></s:hidden>
  <s:hidden name="memberBlog.description"></s:hidden>
  <s:hidden name="memberBlog.mid"></s:hidden>
  <s:hidden name="memberBlog.isdel"></s:hidden>
  <s:hidden name="memberBlog.ctime"></s:hidden>
  <s:hidden name="memberBlog.sort"></s:hidden>
  <s:hidden name="memberBlog.type"></s:hidden>
  <s:hidden name="memberBlog.viewnumber"></s:hidden>
  <s:hidden name="memberBlog.rcid"></s:hidden>
<table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="79" height="50" align="center" valign="top"><font class="font14">标题：</font></td>
    <td width="561" height="50"><s:textfield id="title" name="memberBlog.title" maxLength="60" cssClass="note-input1" style="width:400px;"/>
			 <em>* <span id="titlelenid"></span></em></td>
  </tr>
<script>
var title_c = document.getElementById("title");
document.getElementById("titlelenid").innerHTML = ""+parseInt(zjlen(title_c.value)/2)+"/30";				
</script>  
  <tr>
    <td height="50" align="center" valign="top"><font class="font14">内容：</font></td>
    <td height="50"><s:textfield id="content" name="memberBlog.content" style="width:550px;height:600px;"/></td>
  </tr>
  <tr>
    <td height="50" align="center" valign="top"><font class="font14">分类：</font></td>
    <td height="50">
	<select id="cid" name="memberBlog.cid">
			 <s:iterator value="classlist" id="beanlist" status="beanlist1"> 
			     <option value="<s:property value="#beanlist.id" escape="false" />"><s:property value="#beanlist.title" escape="false" /></option>
			 </s:iterator>
<script>document.getElementById("cid").value='<s:property value="memberBlog.cid" escape="false" />'</script>  	 
	</select>&nbsp;&nbsp;<a class="blue" href="javascript:addclass()">添加分类</a></td>
  </tr>
  <tr>
    <td height="50" align="center" valign="top"><font class="font14">权限：</font></td>
    <td height="50">
	<select name="memberBlog.qx" id="qx">
		<option value="0">任何人可见</option>
		<option value="1">仅好友可见</option>
		<option value="2">仅自己可见</option>
		<option value="3">仅龙爸爸可见</option>
	</select>
	
	<script type="text/javascript">document.getElementById("qx").value='<s:property value="memberBlog.qx" escape="false" />'</script>
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
