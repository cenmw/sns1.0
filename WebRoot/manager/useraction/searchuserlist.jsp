<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object msg=request.getSession().getAttribute("userMsg");
if(msg!=null){
	out.print("<script>alert('"+(String)msg+"')</script>");
	request.getSession().removeAttribute("userMsg");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
function checkSearch(){
	var searchname=$.trim($("#searchname").val());
	if(searchname==""){
		alert("请输入搜索条件");
		return false;
	}
	return true;
}
function success(userid,username){
	$("#popup",window.parent.document).hide();
	$("#_overlay",window.parent.document).hide();
	
	$("#userid",window.parent.document).val(userid);
	$("#username",window.parent.document).val(username);
}
</script>
  </head>
  <body background="#3079ac">
  
  <div class="right">
       
<div class="sub">
<s:form action="getUserForSearch" namespace="/manager" method="post" theme="simple" onsubmit="return checkSearch()">
	<s:textfield cssClass="tin" id="searchname" name="searchname"></s:textfield>&nbsp;
	<input type="submit" value="搜索 " class="sbtn" />
	</s:form>
</div>
<div class="right_con">
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
  <tr>
    <td class="yh">用户名</td>

    <td class="yh">真实姓名</td>
  </tr>
  <s:iterator value="pageBean.pageList">
     <tr>
       <td class="yh00"><a href="javascript:success(<s:property value="id"/>,'<s:property value="username"/>')"><s:property value="username"/></a></td>
       <td class="yh00"><a href="javascript:success(<s:property value="id"/>,'<s:property value="username"/>')"><s:property value="username"/></a></td>
     </tr>
  </s:iterator>
    
  
</table>

<s:property value="pageBean.gopagehtml" escape="false"/>
			
	   </div>
	   
   </div>
</body>

</html>
