<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String msg=(String)request.getSession().getAttribute("adminMsg");
    if(msg!=null&&!msg.equals("")){
	out.print("<script>alert('"+msg+"');</script>"); 
	request.getSession().removeAttribute("adminMsg");
	out.print("<script>location.href='/admin/index.jsp';</script>");
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>龙爸爸成长在线网后台登陆</title>
<link href="/admin/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/admin/js/admin.js"></script>
<script language="javascript">
document.onkeydown=function(e){
	e=e||window.event;
	var key=e.keyCode;
	if(key=="13"){
		adminlogin();
	}
}
</script>
</head>
<body>
<s:form name="frm" id="managerLoginForm" action="login" namespace="/admin" method="post" theme="simple" onsubmit="return check()">
<div id="main">
    <div class="content">
	   <div class="con_01"></div>
	   <div class="con_02"></div>
	   <div class="con_03"></div>
	   <div class="con_04"></div>
	   <div class="con_05"></div>
	   <div class="con_06">
	      <div class="text">
		      <ul>
			    <li class="left">用户名：</li>
				<li class="right">
				   <label>  
					  <input id="username" name="username" type="text" class="yonghu"  maxlength="30" value=""/>
                   </label>
				</li>
				<li class="left">密码：</li>
				<li class="right">
				   <label>
					<input id="userpassword" name="password" type="password" class="yonghu" maxlength="20" />
                   </label>
				</li>
				<li class="left">验证码：</li>
				<li class="right">
				   <label>
					  <input id="usercheckcode" name="checkcode" type="text" class="yzm" maxlength="4" />
					<img id="checkcode" src="/common/checkcode.jsp" onclick="updateCheckcode()" style="cursor:pointer" />
                   </label>
				</li>
				<li class="left0"></li>
				<li class="right0">
				   <p class="button"><a id="sub" href="javascript:adminlogin();">登  录</a></p>
				</li>
			  </ul>
		  </div>
	   </div>
	   <div class="con_07"></div>
	   <div class="con_08"></div>
	</div>
</div>
</s:form>
</body>
</html>
