<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册成功-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/reg.js"></script>
<style type="text/css">
.ash_red{ color:#FF0000;}
</style>
</head>
<body>
<!--top-->
<div class="login-top">
	<div class="layout-control">
    	<a class="login-logo fl" href="/"></a>
        <div class="fr login-r">已有龙爸爸帐号，<a class="blue" href="/">登录</a></div>
    </div>
</div>
<!--top end-->
<!--content--> 
<form action="regsave" id="regForm" namespace="/" method="post">
<input type="hidden" name="memberInfo.type" value="0" />	 
<div class="layout-control mar-t20">
	<div class="regester">
    	<h3 class="regester-tt">注册成功</h3>
        <div class="regester-content">
            <ul class="regester-list">
                <li><span style="font-size: 14px;">恭喜您，注册成功。我们会在1-2个工作日内审核您的资料，审核后，会给您邮件通知。</span></li>
                
				<li style="font-size: 14px;">通知邮箱为：<span style="color:#0000FF;"><s:property value="email" escape="false" /></span>，请牢记该邮箱。</li>                
            </ul>
        </div>
    </div>
</div>
</form>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
