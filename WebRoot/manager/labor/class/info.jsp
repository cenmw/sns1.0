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
	
});


function checkcontentinfo(){
	
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
		alert("分类名称不能为空");
		return false;
	}else if(zjlen(title)>41){
	    alert("分类名称不能超过20个汉字长度");
	    document.getElementById("title").focus();
		return false;
	}
	
	var description=$("#description").val();
	if(description.length>500){
		alert("描述不能超过500字");
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
				<s:form action="save" namespace="/manager/laborclass" method="post" theme="simple" onsubmit="return checkcontentinfo()">
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							<s:hidden name="backUrl"></s:hidden>
							<s:hidden name="laborClass.id"></s:hidden>
							<s:hidden name="laborClass.mid"></s:hidden>
							<s:hidden name="laborClass.ctime"></s:hidden>
							<s:hidden name="laborClass.isdel"></s:hidden>							
							<tr>
								<td colspan="2" class="yh">添加 / 修改 信息&nbsp;</td>
							</tr>							
							
							<tr>
								<td width="20%" class="yh">序号</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="laborClass.sort" id="sort" cssClass="tin" cssStyle="width:90px;" maxLength="50"/><em>*&nbsp;&nbsp;<span>越大越靠前！</span></em>
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">名称</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield id="title" name="laborClass.title" maxLength="30" cssClass="tin" style="width:400px;"/>
								<em>*
								<span id="titlelenid"></span></em></td>
							</tr>
							<script>
							var title_c = document.getElementById("title");
							document.getElementById("titlelenid").innerHTML = ""+parseInt(zjlen(title_c.value)/2);				
							</script>	
							
							<tr>
								<td width="20%" class="yh">关键字</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:textarea cssClass="tarea" name="laborClass.keyword" id="keyword" rows="5" cols="60"/>(500字)
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">描述</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:textarea cssClass="tarea" name="laborClass.description" id="description" rows="5" cols="60"/>(500字)
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