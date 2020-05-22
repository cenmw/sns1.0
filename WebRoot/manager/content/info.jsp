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
	    if(title_len>40){
	    	$("#titleclenid").html("标题不能超过20个汉字长度");
	    }else if(title_len==0){
	    	$("#titleclenid").html("请输入标题");
	    }
	    $("#titlelenid").html(parseInt(title_len/2)+"/"+20);
	});
    $("#title").focus();    
	
	$('#ptime').datepicker( {
		yearRange : '1990:2050', //取值范围.
		showOn : 'both', //输入框和图片按钮都可以使用日历控件。
		buttonImage : '/common/datepicker/date.gif', //日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});
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
	
	<s:if test="contentInfo==null">
	editor = KindEditor.create('#content',editorOptions);
	</s:if>
	<s:else>
		<s:if test="contentInfo.contenttype==0||contentInfo.contenttype==null">
			editor = KindEditor.create('#content',editorOptions);
		</s:if>
		<s:if test="contentInfo.contenttype==1">
			$("#content").attr("rows","1");
		</s:if>
	</s:else>
	var editorVal="";
	$("#contenttype").change(function(){
		var t=$(this).val();
		if(t==1){
			editorVal=editor.html();
			editor.remove();
			editor = null;
			$("#content").attr("value","");
			$("#content").attr("rows","1");
		}
		if(t==0){
			$("#content").attr("value",editorVal);
			$("#content").attr("rows","30");
			editor = KindEditor.create('#content',editorOptions);
		}
	});
}); 

function checkcontentinfo(){
	
	if($("#state_t").attr("checked")){
		$("#state").val($("#state_t").val());
	}
	
	var sort = $.trim($("#sort").val());
	if(sort==""){
		alert("序号不能为空");
		return false;
	}else{
		sort=parseInt(sort);
		if(isNaN(sort)){
			alert("序号必须为数值型");
			return false;
		}
	}
	
	var title=$.trim($("#title").val());
	if(title==""){
		alert("标题不能为空");
		return false;
	}else if(zjlen(title)>41){
	    alert("标题不能超过20个汉字长度");
	    document.getElementById("title").focus();
		return false;
	}
	
	if($("#contenttype").val()!=1){
		var content=editor.html();
		$("#content").val(content);
	}
	
	var description=$("#description").val();
	if(description.length>500){
		alert("文章描述不能超过500字");
		return false;
	}	
	return true;
}

function onchangetitle(obj) {
    var title_len = zjlen(obj.value);
    document.getElementById("titlelenid").innerHTML = ""+parseInt(title_len/2);
	
	if(title_len > 40){
	   $("#titleclenid").html("标题不能超过20个汉字长度");
	   document.getElementById("title").focus();
	}else{
	   $("#titleclenid").html("");
	}
}
function onchangeshowt(obj,showobj) {
    var title_len = zjlen(obj.value);
    document.getElementById(showobj).innerHTML = ""+parseInt(title_len/2);
}
</script>
</head>
<body>
	<div style="text-align: center;">
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
			<tr>
				<td align="left">
				<s:form action="save" namespace="/manager/content" method="post" theme="simple" onsubmit="return checkcontentinfo()">
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							<s:hidden name="backUrl"></s:hidden>
							<s:hidden name="contentInfo.id"></s:hidden>
							<s:hidden name="contentInfo.ctime"></s:hidden>
							<s:hidden name="contentInfo.isdel"></s:hidden>							
							<tr>
								<td colspan="2" class="yh">添加 / 修改 信息&nbsp;</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">类型</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:radio list="#{1:'关于我们&nbsp;&nbsp;',2:'联系我们&nbsp;&nbsp;',3:'招聘信息&nbsp;&nbsp;',4:'友情链接&nbsp;&nbsp;',5:'客服帮助&nbsp;&nbsp;',6:'隐私说明&nbsp;&nbsp;',7:'首页大图&nbsp;&nbsp;'}" name="contentInfo.type" value="contentInfo.type"/>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">序号</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="contentInfo.sort" id="sort" cssClass="tin" cssStyle="width:90px;" maxLength="50"/><em>*&nbsp;&nbsp;<span>越大越靠前！</span></em>
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">文章标题</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield id="title" name="contentInfo.title" maxLength="30" cssClass="tin" style="width:400px;"/>
								<em>*
								<span id="titlelenid"></span></em></td>
							</tr>
							<script>
							var title_c = document.getElementById("title");
							document.getElementById("titlelenid").innerHTML = ""+parseInt(zjlen(title_c.value)/2);				
							</script>
							
							<tr>
								<td width="20%" class="yh">发布日期</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<input id="ptime" type="text" class="tin" style="width:100px;" readonly="readonly" name="contentInfo.ptime" value="<s:date name="contentInfo.ptime" format="yyyy-MM-dd"/>" />		
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">文章图片</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:hidden name="contentInfo.picpath" id="picpath"/>
								<s:if test="contentInfo==null">
								<img  style="display:none" width="55" height="55" id="picpath_s"/>
								</s:if>
								<s:elseif test="contentInfo.picpath==''||contentInfo.picpath==null">
								<img  style="display:none" width="55" height="55" id="picpath_s"/>
								</s:elseif>
								<s:else>
								<img src="<s:property value="contentInfo.picpath"/>" width="55" height="55" id="picpath_s"/>
								</s:else>
								<input type="button" id="imageUpload" value="选择图片" />
								
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">作者</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="contentInfo.author" cssClass="tin" maxLength="200"/>
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">文章来源</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="contentInfo.source" cssClass="tin" maxLength="250"/>
								</td>
							</tr>
									
							<tr>
								<td width="20%" class="yh">关键字</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:textarea cssClass="tarea" name="contentInfo.keyword" id="keyword" rows="5" cols="60"/>(500字)
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">描述</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:textarea cssClass="tarea" name="contentInfo.description" id="description" rows="5" cols="60"/>(500字)
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">文章类型</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="contenttype" list="#{0:'内容',1:'链接'}" name="contentInfo.contenttype"></s:select>
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">文章内容</td>
								<td width="80%" height="30" style="padding-left:13px;">
								<s:textarea name="contentInfo.content" id="content" rows="30" cols="63"/>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">状态</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="state" list="#{0:'草稿',1:'立即发布'}" name="contentInfo.state"></s:select>
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
<style type="text/css">
.KeyWordListBoxCss{border:#BFCDF3 1px solid; background-color:#FFFFFF;display:none;}
.keyWordListTrCss{ cursor:pointer;width:100%;}
</style>
<%
	Object msg=request.getSession().getAttribute("msg");
	String str = (String) msg;
	if(msg!=null){
	    out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("msg");}%>	