<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import ="com.cenmw.admin.po.User" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>龙爸爸成长在线后台 管理系统</title>
<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
</head>

<body background="#ff0000" style="height:150px;">


  <div class="top">
   <%User user=(User)request.getSession().getAttribute("adminInfo");
   String username=user.getUsername();
   %>
   
	    <div class="top_wel"><p>欢迎<%=username %>登陆系统</p></div>
		<div class="top_exit">
		   <ul>
		   	  <li><a target="_top" href="/manager/index.jsp">返回</a></li>
		      <li><a target="_blank" href="/help/index.html">帮助</a></li>
			  <li><a target="mainFrame" href="/manager/updatepass.jsp">修改密码</a></li>
			  <li><a target="_top" href="/admin/loginOut">退出</a></li>
			  <li>当前版本：201106</li>
		   </ul>
		</div>
		<div class="clear"></div>
	</div>
	
	<div class="banner">
	   <div class="logo"><img src="/manager/images/logo.gif" height="94"  /></div>
	   <div class="subject">
	       <ul>
	          <li class="l05">
	          <a target="mainFrame" href="/manager/sys/getUserForPage"><p>用户管理</p></a>
	          </li>
	          <li class="l01">
	          <a target="mainFrame" href="/manager/useraction/usergroupmain.jsp"><p>角色管理</p></a>
	          </li>
	          <li class="l0">
	          <a target="mainFrame" href="/manager/useraction/actioncolumnmain.jsp"><p>权限管理</p></a>
	          </li>
		   </ul>
	   </div>
	   <div class="yingyong">
	         
	   </div>
	   <div class="clear"></div>
	</div>

</body>
</html>