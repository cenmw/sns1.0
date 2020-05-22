<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-发信息-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
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
	$("#content").keyup(function(){
		var content_len = zjlen($("#content").val());
	    if(content_len>600){
	    	$("#contentclenid").html("消息内容不能超过150个汉字长度");
	    }else if(content_len==0){
	    	$("#contentclenid").html("请输入消息内容");
	    }
	    $("#contentlenid").html(parseInt(content_len/2)+"/"+300);
	});
    $("#content").focus();    
});

//删除
function delmessage(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/messagedelete?type=4&ids="+id;
   }
}

//提交检查
function checkcontentinfo(){
	var revicenames=$.trim($("#revicenames").val());
	if(revicenames==""){
		alert("收件人不能为空");
		return false;
	}
	var content=$.trim($("#content").val());
	if(content==""){
		alert("消息内容不能为空");
		return false;
	}else if(zjlen(content)>600){
	    alert("消息内容不能超过150个汉字长度");
	    document.getElementById("content").focus();
		return false;
	}
		
	return true;
}

//添加收件人
function addfmemberlist(){
    var reviceids = $.trim($("#reviceids").val());
	$.fn.popup({iframe:true,url:'/membercenter/fmemberlist?reviceids='+reviceids,type:3,title:'添加收件人',width:400,height:400});
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
   		<h2 class="second-title"><a class="blue" href="javascript:<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>发信息</h2>
        <div class="note-title">
			<a class="write-note fr" href="javascript:fowardBack('/membercenter/bloginfo?backUrl=','<s:property value="backUrl"/>')"><img src="/member/images/btn10.png" height="26" width="63" border="0" /></a><a class="note-a" href="/membercenter/messagelist">私信列表</a><a class="note-a" href="/membercenter/smessagelist">发件箱</a><a class="note-a" href="/membercenter/rmessagelist">收件箱</a>
        </div>
		
<s:form action="messagesave" namespace="/membercenter" method="post" theme="simple" onsubmit="return checkcontentinfo()">
  <input type="hidden" id="reviceids" name="reviceids" value="<s:property value="rids" escape="false" />" />
  <s:hidden name="backUrl"></s:hidden>
  <s:hidden name="memberMessage.memberInfo.id"></s:hidden>	
<table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="79" height="50" align="center" valign="top"><font class="font14">收件人：</font></td>
    <td width="561" height="50">
	         <input type="text" readonly="" onclick="addfmemberlist()" class="note-input1" style="width:400px;" id="revicenames" name="revicenames" value="<s:property value="accounts" escape="false" />" />
			 <em><a href="javascript:void(0)" onclick="addfmemberlist()">通讯录</a></em><br /></td>
  </tr>
  
  <tr>
    <td height="50" align="center" valign="top"><font class="font14">发内容：</font></td>
    <td height="50"><textarea name="memberMessage.content" id="content" style="width:550px;height:200px; font-size:16px;"></textarea><br /><em>* <span id="contentlenid"></span></em></td>
  </tr>
<script type="text/javascript">  
var content_c = document.getElementById("content");
document.getElementById("contentlenid").innerHTML = ""+parseInt(zjlen(content_c.value)/2)+"/300";				
</script> 
 
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