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
<script language="javascript">
function deleteAction(url){
	if(confirm("确认删除吗")){
		location.href=url;
	}
}
function fowardBack(url,backurl){
	location.href=url+encodeURIComponent(backurl);
}
</script>
  </head>
  <body background="#3079ac">
  
  <div class="right">
       <div class="place"><span>当前位置：动作管理 > 动作列表</span></div>
	    <div class="sub">
	        <input type="button" onclick="javascript:location.href='/manager/sys/showAction?id=0&acid=<s:property value="acid"/>'" value="添加权限 " class="l" />
	   </div>
	   <div class="right_con">
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
     <tr>
       <td width="29%" class="yh">权限名称</td>
       <td width="29%" class="yh">动作名称</strong></td>
       <td width="42%" class="yh">操作</td>
     </tr>
     <s:iterator value="alist">
     <tr>
       <td class="yh00"><s:property value="actionname"/></td>
       <td class="yh00"><s:property value="action"/></td>
       <td class="cz0">
       <a href="javascript:fowardBack('/manager/sys/getActionGroupForPage?action=<s:property value="action"/>&actionname=<s:property value="actionname"/>','');">角色列表</a>
       &nbsp;&nbsp;<a href="javascript:deleteAction('/manager/sys/deleteAction?id=<s:property value="actionid"/>&acid=<s:property value="acid"/>')">删除</a></td>
     </tr>
     </s:iterator>
     
  </table>


			
	   </div>
	   
   </div>
</body>
  <body>
  
  
  </body>
</html>
