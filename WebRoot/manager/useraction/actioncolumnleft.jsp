<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>

.acl ul{width:100px;}
.acl ul li{font-size:14px; list-style:none; text-align: center}
</style>
  </head>
  
  <body bgcolor="#D7E0DF">
  		<div class="acl">
	       <ul>
	        <s:iterator value="actionColumnList" status="acl">
	         
	          <li class="<s:property value="actioncolumnpic"/>"><a  target="rightFrame" href="/manager/sys/getActionList?acid=<s:property value="actioncolumnid"/>"><p><s:property value="actioncolumnname"/></p></a></li>
	         
  			</s:iterator>
		   </ul>
	   </div>
  
 
  </body>
</html>
