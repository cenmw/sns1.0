<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>转载--我的视频-龙爸爸成长在线</title>
<link href="/member/css/zc.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
function checkcontentinfo(){
	var cid=$.trim($("#id").val());
	if(cid==""){
		alert("分类不能为空");
		return false;
	}
		
	return true;
}

// 关闭
function closepopup(){
	$("#popup", window.parent.document).hide();
	$("#_overlay", window.parent.document).hide();
}
</script>
</head>
<body>
  <s:if test="vedioInfo != null && vedioInfo.id != null && vedioInfo.id>0">
  <table width="100%" cellpadding="0" cellspacing="0" align="center" style="padding-top:20px;">
	   <tr align="center" style="text-align:center">
	       <td height="50" valign="top"><font class="font14">您已经成功转载，<span id="dd">3</span>&nbsp;秒钟后自动关闭。</font>
		   <script type="text/javascript">
		      function run(){
				var s = document.getElementById("dd");
				s.innerHTML = s.innerHTML * 1 - 1;
				if(s.innerHTML == 0){
					closebox2();
					return false;
				}
				
			  }		   
		      window.setInterval("run();",1000);
		   </script>
		   </td>
	   </tr>
  </table>     
  </s:if>
  <s:else>
  <s:form action="zzsave" namespace="/membercenter" method="post" theme="simple" onsubmit="return checkcontentinfo()">
  <s:hidden name="backUrl"></s:hidden>
  <s:hidden name="type"></s:hidden>
  <s:hidden name="cid"></s:hidden>
  <table width="100%" cellpadding="0" cellspacing="0">
	   <tr>
		<td height="50" align="center" valign="top"><font class="font14">分类：</font></td>
		<td height="50" valign="top">
		<select id="id" name="id">
		<s:iterator value="classlist" id="beanlist" status="beanlist1"> 
			 <option value="<s:property value="#beanlist.id" escape="false" />"><s:property value="#beanlist.title" escape="false" /></option>
		</s:iterator>
		</select></td>
	   </tr>
	  
	   <tr>
		<td height="50" align="center" valign="top"><font class="font14">权限：</font></td>
		<td height="50" valign="top">
		<select name="qx" id="qx">
			<option value="0">任何人可见</option>
			<option value="1">仅好友可见</option>
			<option value="2">仅自己可见</option>
		</select>
		</td>
	   </tr>
	   
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
		out.println("<script language=\"javascript\">closepopup();</script>");
	}
%>