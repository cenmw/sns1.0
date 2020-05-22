<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object msg=request.getSession().getAttribute("actionMsg");
if(msg!=null){
	out.print("<script>alert('"+(String)msg+"')</script>");
	request.getSession().removeAttribute("actionMsg");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:ice="http://ns.adobe.com/incontextediting">
  <head>
    <base href="<%=basePath%>">
    
    <title>龙爸爸成长在线后台 管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<script src="/common/js/jquery-1.4.js" language="javascript"></script>
<script language="javascript">
function checkAction(){
	var actionname=$.trim($("#actionname").val());
	var action=$.trim($("#action").val());
	if(actionname==""){
		alert("请输入权限名称");
		return false;
	}
	if(action==""){
		alert("请输入动作");
		return false;
	}
	return true;
}
</script>
</head>
  
  <body>
  <div style="color:#808080;text-align:center;">
    	 <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
    	 	<tr>
    	 		<td align="center">
<table width="500" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
<s:form action="saveAction" method="post" namespace="/manager/sys" theme="simple" onsubmit="return checkAction()">
      <s:hidden name="action.actionid"/>
      <input type="hidden" name="action.actioncolumnid" value="<s:property value="acid"/>"/>
    	  <tr>
    	    <td colspan="2" class="yh">权限信息</td>
   	    </tr>
    	  <tr>
    	    <td width="97" class="yh">&nbsp;&nbsp;权限名称:</td>
    	    <td width="403" height="30">&nbsp;&nbsp;
    	    <s:textfield cssClass="tin1" name="action.actionname" id="actionname" maxLength="50"></s:textfield>
   	        </td>
   	        
  	    </tr>
    	  <tr>
    	    <td class="yh">&nbsp;&nbsp;动作:</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:textfield size="50"  cssClass="tin1" name="action.action" id="action" maxLength="50"></s:textfield></td>
  	    </tr>
    	  <tr>
    	    <td height="30" colspan="2" align="center"><input type="submit" class="tbtn" name="button" id="button" value="提交">&nbsp;&nbsp;
   	        <input type="button" class="tbtn" name="button2" id="button2" onClick="javascript:location.href='/manager/sys/getActionList?acid=<s:property value="acid"/>';" value="返回"></td>
   	    </tr>
        </s:form>
</table>
    	 		</td>
    	 	</tr>
    	 </table>
   	  
    </div>
  
  
  
  
  
    
</body>
</html>
