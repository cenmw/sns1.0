<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object msg=request.getSession().getAttribute("groupManagerMsg");
if(msg!=null){
	out.print("<script>alert('"+(String)msg+"')</script>");
	request.getSession().removeAttribute("groupManagerMsg");
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
function checkGroupManager(){
	var actionname=$.trim($("#groupname").val());
	var action=$.trim($("#groupinfo").val());
	if(actionname==""){
		alert("请输入角色名称");
		return false;
	}
	if(action.length>200){
		alert("角色说明在200字以内");
		return false;
	}
	return true;
}
</script>
</head>
<body>
    <div style="color:#808080;text-align:center;">
    	 <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
    	 	<tr>
    	 		<td align="center">
    	<table width="500" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
      <s:form action="saveGroupManager" method="post" namespace="/manager/sys" theme="simple" onsubmit="return checkGroupManager()">
      <s:hidden name="groupManager.groupid"/>
      
    	  <tr>
    	    <td colspan="2" class="yh">角色信息</td>
   	    </tr>
    	  <tr>
    	    <td width="110" class="yh">&nbsp;&nbsp;角色名称:</td>
    	    <td width="390" height="30">
    	    <s:textfield cssClass="tin1" name="groupManager.groupname" id="groupname" maxLength="50"></s:textfield>
   	        </td>
   	        
  	    </tr>
    	  <tr>
    	    <td class="yh">&nbsp;&nbsp;角色说明:</td>
    	    <td>
   	        <s:textarea name="groupManager.groupinfo" id="groupinfo" cols="50" rows="5"></s:textarea>
   	        </td>
  	    </tr>
    	  <tr>
    	    <td height="30" colspan="2" align="center"><input class="tbtn" type="submit" name="button" id="button" value="提交">&nbsp;&nbsp;
   	        <input class="tbtn" type="button" name="button2" id="button2" onClick="javascript:location.href='/manager/sys/getGroupManagerList';" value="返回"></td>
   	    </tr>
        </s:form>
  	  </table> 		
	
    	 		
    	 		
    	 		
    	 		</td>
    	 	</tr>
    	 </table>
   	  
    </div>
</body>
  
  <body>
    <div style="color:#808080; margin:0 auto; text-align:center">
   	  
    </div>
</body>
</html>
