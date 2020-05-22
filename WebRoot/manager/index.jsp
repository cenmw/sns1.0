<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title>龙爸爸成长在线后台 管理系统</title>
</head>

<frameset rows="120,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="/manager/showActionColumnList" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frame src="/manager/inc/main.jsp" name="mainFrame" scrolling="auto"" noresize="noresize" id="mainFrame" title="mainFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>