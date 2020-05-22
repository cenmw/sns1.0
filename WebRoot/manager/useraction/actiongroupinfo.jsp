<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object msg=request.getSession().getAttribute("actionGroupMsg");
if(msg!=null){
	out.print("<script>alert('"+(String)msg+"')</script>");
	request.getSession().removeAttribute("actionGroupMsg");
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
<script type="text/javascript" src="/manager/js/popup/jquery.popup.js"></script>
<link href="/manager/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script language="javascript">
function checkActionGroup(){
	var groupname=$.trim($("#groupname").val());
	if(groupname==""){
		alert("请选择组");
		return false;
	}
	return true;
}
function showGroupSearch(){
	var url="/manager/sys/getGroupManagerList?status=1";
	$.fn.popup({iframe:true,url:url,type:9,title:'选择组',width:460,height:500,scrolling:'auto'});
}
</script>
</head>
  
  <body>
    <div style="color:#808080;text-align:center;">
    	 <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
    	 	<tr>
    	 		<td align="center">
    	 		
	<table width="500" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
   	  
      <s:form action="saveActionGroup" method="post" namespace="/manager/sys" theme="simple" onsubmit="return checkActionGroup()">
      <s:hidden name="actionGroup.id"/>
   	  <s:hidden name="actionGroup.ctime"/>
      <s:hidden name="actionGroup.createid"/>
      <s:hidden name="actionGroup.createname"/>
      <s:hidden name="backurl"/>
      
      <s:hidden name="actionGroup.action"/>
      <s:hidden name="actionGroup.actionname"/>
    	  <tr>
    	    <td colspan="2" class="yh">权限组关联信息</td>
   	    </tr>
    	  <tr>
    	    <td width="97" class="yh">&nbsp;&nbsp;组名称：</td>
    	    <td width="403" height="30">&nbsp;&nbsp;
    	    <s:hidden name="actionGroup.groupid" id="groupid"></s:hidden>
    	    <s:textfield cssClass="tin" readonly="true" name="actionGroup.groupname" id="groupname" maxLength="50"></s:textfield>
   	        <a href="javascript:showGroupSearch()">选择</a>
   	        </td>
   	        
  	    </tr>
    	 
        
    	  <tr>
    	    <td height="30" colspan="2" align="center"><input type="submit" name="button" id="button" value="提交" class="tbtn">&nbsp;&nbsp;
   	        <input class="tbtn" type="button" name="button2" id="button2" onClick="javascript:location.href='<s:property value="backurl"/>';" value="返回"></td>
   	    </tr>
        </s:form>
  	  </table>
    	 		
    	 		
    	 		
    	 		</td>
    	 	</tr>
    	 </table>
   	  
    </div>
</body>
</html>
