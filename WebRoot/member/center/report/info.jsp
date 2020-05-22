<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>举报-龙爸爸成长在线</title>
<link href="/member/css/zc.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
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
	    $("#titlelenid").html(parseInt(title_len/2)+"/"+12);
	});
    $("#title").focus();    
	
});

function checkcontentinfo(){
    var rcons = document.getElementsByName("rcon");
	
	var content;
	for(var i=0;i<rcons.length;i++){
	    if(rcons[i].checked == true){
		    content = rcons[i].value;
		}
	}
	if(content == "其他"){
	    var title=$.trim($("#title").val());
		if(title==""){
			alert("说明不能为空");
			return false;
		}else if(zjlen(title)>24){
			alert("说明不能超过12个汉字长度");
			document.getElementById("title").focus();
			return false;
		}
		content = title;
	}
	document.getElementById("content").value=content;		
	return true;
}
</script>
</head>
<body>
  <s:if test="memberReport != null && memberReport.id != null && memberReport.id>0">
  <table width="100%" cellpadding="0" cellspacing="0">
	   <tr align="center" style="text-align:center">
	       <strong>您已经成功举报。</strong>
	   </tr>
  </table>     
  </s:if>
  <s:else>
  <s:form action="reportsave" namespace="/membercenter" method="post" theme="simple" onsubmit="return checkcontentinfo()">
  <s:hidden name="type"></s:hidden>
  <s:hidden name="cid"></s:hidden>
  <s:hidden name="rid"></s:hidden>
  <input type="hidden" id="content" name="content" value="" />
  <table width="100%" cellpadding="0" cellspacing="0">
	   <tr height="30">
		<td colspan="2" align="center" valign="top"><font class="font14"><strong>您为什么要举报此信息</strong></font></td>
	   </tr>
	  
	   <tr height="30">
		<td align="center" valign="top"><input type="radio" name="rcon" value="色情暴力" />色情暴力</td>
		<td valign="top"><input type="radio" name="rcon" value="骚扰谩骂" />骚扰谩骂</td>
	   </tr>	
	   
	   <tr height="30">
		<td align="center" valign="top"><input type="radio" name="rcon" value="广告欺诈" />广告欺诈</td>
		<td valign="top"><input type="radio" name="rcon" value="病毒木马" />病毒木马</td>
	   </tr>
	   
	   <tr height="30">
		<td align="center" valign="top"><input type="radio" name="rcon" value="反动政治" />反动政治</td>
		<td valign="top"><input type="radio" name="rcon" checked="checked" value="其他" />其他</td>
	   </tr>
			   
	   <tr height="30">
		<td align="center" valign="top"><font class="font14">举报说明（可选）</font></td>
		<td valign="top"><input type="text" name="con" id="title" maxlength="24" class="tin" style="widows:100px;" /><em>* <span id="titlelenid"></span></em></td>
	   </tr>
<script>
var title_c = document.getElementById("title");
document.getElementById("titlelenid").innerHTML = ""+parseInt(zjlen(title_c.value)/2)+"/12";				
</script>
	   <tr>
		  <td height="30" colspan="2" align="center">
		  <input type="submit" name="button" id="button" value="提交" class="tbtn">
		  </td>
	   </tr>
							
  </table>
  </s:form>
  </s:else>
</body>
</html>
<%
	Object msg=request.getSession().getAttribute("saveinfomsg");
	if(msg!=null){
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("saveinfomsg");
	}
%>