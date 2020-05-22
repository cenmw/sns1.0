<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>好友申请-百里挑一消费网</title>
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
	    if(title_len>60){
	    	$("#titleclenid").html("内容不能超过30个汉字长度");
	    }else if(title_len==0){
	    	$("#titleclenid").html("请输入内容");
	    }
	    $("#titlelenid").html(parseInt(title_len/2)+"/"+30);
	});
    $("#title").focus();    
	
});

function checkcontentinfo(){
	var title=$.trim($("#title").val());
	if(title==""){
		alert("内容不能为空");
		return false;
	}else if(zjlen(title)>60){
	    alert("内容不能超过30个汉字长度");
	    document.getElementById("title").focus();
		return false;
	}
		
	return true;
}	
</script>

</head>
<body>
  <s:if test="fid==memberInfo.id">
  <table width="100%" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
	   <tr align="center">
	       <strong>请不要添加自己为好友。</strong>
		   <script type="text/javascript">
		   window.parent.location.reload();
		   </script>
	   </tr>
  </table>     
  </s:if>
  <s:elseif test="fmemberInfo != null && fmemberInfo.id != null && fmemberInfo.type == 1 && mfid>0">
  <table width="100%" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
	   <tr align="center">
	       <strong>您已经成功加关注。</strong>
		   <script type="text/javascript">
		   window.parent.location.reload();
		   </script>
	   </tr>
  </table>     
  </s:elseif>
  <s:elseif test="memberFriend != null && mfid>0">
  <table width="100%" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
	   <tr align="center">
	       <strong>您已经成功添加为好友。</strong>
		   <script type="text/javascript">
		   window.parent.location.reload();
		   </script>
	   </tr>
  </table>     
  </s:elseif>
  <s:else>
  <s:form action="friendsave" namespace="/membercenter" method="post" theme="simple" onsubmit="return checkcontentinfo()">
  <s:hidden name="type"></s:hidden>
  <s:hidden name="fid"></s:hidden>  
  <table width="100%" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
	   <tr>
	      <td width="10%" height="30">内容：</td>
		  <td width="90%" height="30">
			 <s:textfield id="title" name="content" maxLength="60" cssClass="tin" style="width:260px; height:100px;"/>
			 <em>* <span id="titlelenid"></span></em></td>
	   </tr>
<script>
var title_c = document.getElementById("title");
document.getElementById("titlelenid").innerHTML = ""+parseInt(zjlen(title_c.value)/2)+"/30";				
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
		//out.println("<script language=\"javascript\">self.window.opener.location.reload();</script>");
	}
%>
<script type="text/javascript">
<s:if test="mfid>0">
	window.parent.location.reload();
</s:if>
</script>