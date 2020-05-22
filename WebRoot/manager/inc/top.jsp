<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import ="com.cenmw.admin.po.User" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>龙爸爸成长在线后台 管理系统</title>
<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<script src="/common/js/jquery-1.4.2.min.js" type="text/javascript">
</script>
</head>

<body background="#ff0000" style="height:150px;">


  <div class="top">
   <%User user=(User)request.getSession().getAttribute("adminInfo");
   String username=user.getUsername();
   %>
   	 
	    <div class="top_wel"><p>欢迎<%=username %>登陆系统</p></div>
		<div class="top_exit">
		   <ul>
		   <%if(user.getStatus()==1){ %>
		   	  <li><a target="_top" href="/manager/sys/index.jsp">系统管理</a></li>
		   	  <%} %>
		      <li><a target="_blank" href="/help/index.html">帮助</a></li>
			  <li><a target="mainFrame" href="/manager/updatepass.jsp">修改密码</a></li>
			  <li><a target="_top" href="/admin/loginOut">退出</a></li>
			  <li>当前版本：201106</li>
		   </ul>
		</div>
		<div class="clear"></div>
	</div>
	
	<div class="banner">
	   <div class="logo" style="margin-right: 10px;"><img src="/manager/images/logo.gif" height="94"  /></div>
<style type="text/css">
.subject1{height:90px;overflow: height;overflow:auto;width:760px; float:left;}
.subject1 ul{margin-top:3px;width:740px; }
.subject1 ul li{float:left;height:40px;line-height:40px;margin-right:5px;margin-top:3px; background-color: #FFFFFF;display: block;}
.subject1 ul li a{display: block;color:#000;height:40px;line-height:40px;width:87px;text-decoration: none;font-size:14px}
.subject1 ul li a:hover{background-color: #666;color:#ffffff;font-weight:bold;text-decoration: none;font-size:14px}
</style>
	   <div class="subject1">
			<ul>
				<s:iterator value="actionColumnList" status="acl">
					<li>
						<a target="mainFrame" href="<s:property value="actioncolumnlink"/>"> 
							<s:property value="actioncolumnname"/>
						</a>
					</li>
				</s:iterator>
				
			</ul> 
	   </div>
	   
	 
	   <div class="clear"></div>
	</div>
	
		
</body>
</html>