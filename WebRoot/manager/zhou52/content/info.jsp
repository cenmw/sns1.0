<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>龙爸爸成长在线后台 管理系统</title>
<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<script src="/common/js/jquery-1.4.2.min.js" language="javascript"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
<script src="/common/js/channeloptionTree.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/common/datepicker/ui.datepickerv1.css"></link>
<script src="/common/datepicker/ui.datepicker-zh-CN.js" type="text/javascript"></script>
<script src="/common/datepicker/ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
// 编辑器
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
			'anchor', 'link', 'unlink','fillblank'
		],
		allowFileManager : true,
		fileManagerJson : '/keditor/browseImageLists?foldername=uploadfiles',
		uploadJson : '/keditor/uploadImages?foldername=uploadfiles'
	};
	
	editor = KindEditor.create('#content',editorOptions);
}); 

function checkcontentinfo(){
	
	return true;
}

</script>
</head>
<body>
	<div style="text-align: center;">
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
			<tr>
				<td align="left">
				<s:form action="save" namespace="/manager/zhoucontent" method="post" theme="simple" onsubmit="return checkcontentinfo()">
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							<s:hidden name="backUrl"></s:hidden>
							<s:hidden name="memberDiary52Content.id"></s:hidden>
							<s:hidden name="memberDiary52Content.ctime"></s:hidden>
							<s:hidden name="memberDiary52Content.isdel"></s:hidden>	
							<s:hidden name="memberDiary52Content.sort"></s:hidden>							
							<tr>
								<td colspan="2" class="yh">添加 / 修改 信息&nbsp;</td>
							</tr>							
							
							<tr>
								<td width="20%" class="yh">类型</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="type" list="#{0:'为家庭',1:'为自己'}" name="memberDiary52Content.type"></s:select>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">位置</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="zposition" list="#{0:'写日志之前',1:'写日志之后'}" name="memberDiary52Content.zposition"></s:select>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">开始天数-结束天数</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="memberDiary52Content.zbegin" id="zbegin" cssClass="tin" cssStyle="width:60px;" maxLength="50"/> - 
								<s:textfield name="memberDiary52Content.zend" id="zend" cssClass="tin" cssStyle="width:60px;" maxLength="50"/>
								<em>&nbsp;&nbsp;<span></span></em>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">书籍内容</td>
								<td width="80%" height="30" style="padding-left:13px;">
								<s:textarea name="memberDiary52Content.content" id="content" rows="30" cols="63"/>
								</td>
							</tr>							
														
							<tr>
								<td height="30" colspan="2" align="center"><input
									type="submit" name="button" id="button" value="提交" class="tbtn">
									&nbsp;&nbsp; <input class="tbtn" type="button" name="button2"
									id="button2"
									onClick="location.href='<s:property value="backUrl"/>'"
									value="返回"></td>
							</tr>

						</table>
						</s:form>
					</td>
			</tr>
		</table>
	</div>
</body>
</html>
<%
	Object msg=request.getSession().getAttribute("msg");
	String str = (String) msg;
	if(msg!=null){
	    out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("msg");}%>	