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
  <frame src="/manager/sys/inc/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <!-- <frameset rows="*" cols="194,*" id="bframe" framespacing="0" frameborder="no" border="0">
    <frame src="/manager/sys/inc/left.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="/manager/sys/inc/right.jsp" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" />
  </frameset>
   -->
   
    <frame src="/manager/sys/inc/main.jsp" name="mainFrame" scrolling="auto" noresize="noresize" id="mainFrame" title="mainFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>