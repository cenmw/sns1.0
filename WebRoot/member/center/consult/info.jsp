<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的咨询-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript" charset="utf-8"
	src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="/common/newkindeditor/themes/default/default.css"></link>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet"
	type="text/css">
	<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
	<link rel="stylesheet" type="text/css"
		href="/common/js/datepicker/ui.datepickerv1.css"></link>
	<script src="/common/js/datepicker/ui.datepicker-zh-CN.js"
		type="text/javascript"></script>
	<script src="/common/js/datepicker/ui.datepicker.js"
		type="text/javascript"></script>
	<script type="text/javascript">

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
	
	var starttime=$.trim($("#starttime").val());
	if(starttime==""){
		alert("开始时间不能为空");
		return false;
	}
	var endtime=$.trim($("#endtime").val());
	if(endtime==""){
		alert("结束时间不能为空");
		return false;
	}
		
	return true;
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
			<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>我的咨询</h2>
	        <div class="note-title">
	        	<a class="note-a" href="/membercenter/consultclasslist">咨询中心</a>
				<a class="note-a" href="/membercenter/consultlist">我的咨询</a>
				<a class="note-a" href="/membercenter/consultflist">好友咨询</a>
				<a class="note-a note-cur" >我要咨询</a>
	        </div>

			<s:form action="consultsave" namespace="/membercenter" method="post"
				theme="simple" onsubmit="return checkcontentinfo()">
				<s:hidden name="backUrl"></s:hidden>
				<s:hidden name="consultInfo.id"></s:hidden>
				<s:hidden name="consultInfo.keyword"></s:hidden>
				<s:hidden name="consultInfo.description"></s:hidden>
				<s:hidden name="consultInfo.mid"></s:hidden>
				<s:hidden name="consultInfo.isdel"></s:hidden>
				<s:hidden name="consultInfo.ctime"></s:hidden>
				<s:hidden name="consultInfo.sort"></s:hidden>
				<s:hidden name="consultInfo.type"></s:hidden>
				<table class="note-tabs1" width="640" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td width="79" height="50" align="center" valign="top"><font
							class="font14">标题：</font>
						</td>
						<td width="561" height="50"><s:textfield id="title"
								name="consultInfo.title" maxLength="60" cssClass="note-input1"
								style="width:400px;" /> <em>* <span id="titlelenid"></span>
						</em>
						</td>
					</tr>
					<script>
var title_c = document.getElementById("title");
document.getElementById("titlelenid").innerHTML = ""+parseInt(zjlen(title_c.value)/2)+"/30";				
</script>
					<tr>
						<td height="50" align="center" valign="top"><font
							class="font14">内容：</font>
						</td>
						<td height="120"><s:textarea name="consultInfo.content"
								id="content" style="width:550px;height:400px;" />
						</td>
					</tr>
					<tr>
						<td height="50" align="center" valign="top"><font
							class="font14">分类：</font>
						</td>
						<td height="50"><select id="cid" name="consultInfo.cid">
								<s:iterator value="classlist" id="beanlist" status="beanlist1">
									<option
										value="<s:property value="#beanlist.id" escape="false" />">
										<s:property value="#beanlist.title" escape="false" />
									</option>
								</s:iterator>
								<script>document.getElementById("cid").value='<s:property value="consultInfo.cid" escape="false" />'</script>
						</select>
						</td>
					</tr>
					<tr>
						<td height="50" align="center" valign="top"><font
							class="font14">是否发布：</font>
						</td>
						<td height="50"><s:radio list="#{1:'是',0:'否'}"
								name="consultInfo.state" value="consultInfo.state">
							</s:radio></td>
					</tr>

					<tr>
						<td height="50" align="center" valign="top"><font
							class="font14">奖励积分：</font>
						</td>
						<td height="50"><s:textfield name="consultInfo.score"
								maxLength="60" cssClass="tin" style="width:200px;" /></td>
					</tr>

					<tr>
						<td height="50" align="center" valign="top"><font
							class="font14">开始时间：</font>
						</td>
						<td height="50"><input class="infor-text"
							style="width:160px;" type="text" id="starttime"
							name="consultInfo.starttime"
							value="<s:date name="consultInfo.starttime" format="yyyy-MM-dd"/>" />
							<script type="text/javascript">
jQuery(function($) {
	$('#starttime').datepicker( {
		yearRange : '1920:2050', //取值范围.
		showOn : 'both', //输入框和图片按钮都可以使用日历控件。
		buttonImage : '/common/js/datepicker/date.gif', //日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});

});
</script>
						</td>
					</tr>

					<tr>
						<td height="50" align="center" valign="top"><font
							class="font14">结束时间：</font>
						</td>
						<td height="50"><input class="infor-text"
							style="width:160px;" type="text" id="endtime"
							name="consultInfo.endtime"
							value="<s:date name="consultInfo.endtime" format="yyyy-MM-dd"/>" />
							<script type="text/javascript">
jQuery(function($) {
	$('#endtime').datepicker( {
		yearRange : '1920:2050', //取值范围.
		showOn : 'both', //输入框和图片按钮都可以使用日历控件。
		buttonImage : '/common/js/datepicker/date.gif', //日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});

});
</script>
						</td>
					</tr>

					<tr>
					    <td height="50" align="center" valign="top"><font class="font14">权限：</font></td>
					    <td height="50">
						<select name="consultInfo.qx" id="qx">
							<option value="0">所有人解答</option>
							<option value="1">仅好友解答</option>
							<option value="2">龙爸爸解答</option>
						</select>
						
						<script type="text/javascript">document.getElementById("qx").value='<s:property value="consultInfo.qx" escape="false" />'</script>
						</td>
					</tr>
  
					<tr>
						<td height="50" align="center" valign="top">&nbsp;</td>
						<td height="50"><input class="note-saved" type="submit"
							value="" />
						</td>
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
	Object msg = request.getSession().getAttribute("saveinfomsg");
	if (msg != null) {
		out.println("<script language=\"javascript\">alert('"
				+ (String) msg + "');</script>");
		request.getSession().removeAttribute("saveinfomsg");
	}
%>
