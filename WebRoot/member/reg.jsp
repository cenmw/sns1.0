<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/reg.js"></script>
<style type="text/css">
.ash_red{ color:#FF0000;}
.ash{ color:#666666;}
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
    	<h3 class="regester-tt">注册新用户</h3>
        <div class="regester-content">
            <ul class="regester-list">
                <li><span class="regester-l fl">E-mail:</span><div class="regester-r fl"><input onblur="checkEmailAjax()" maxLength="30" name="memberInfo.email" id="m_email" class="reg-text" type="text" value="" /><span class="ash" id="m_emailem">小贴士：请使用常用的邮箱注册</span></div></li>
                <li><span class="regester-l fl">姓名:</span><div class="regester-r fl"><input onblur="checkAccountAjax()" name="memberInfo.account" id="m_account" class="reg-text" type="text" value="" /><span class="ash" id="accountem"></span></div></li>
                <li><span class="regester-l fl">性别:</span><div class="regester-r regester-sex fl"><input name="memberInfo.sex" checked="checked" type="radio" value="1" />男&nbsp;&nbsp;&nbsp;&nbsp;<input name="memberInfo.sex" type="radio" value="0" />女</div></li>
                <li><span class="regester-l fl">密码:</span><div class="regester-r fl"><input name="memberInfo.password" type="password" maxLength="50" class="reg-text" value=""  onblur="checkPwd()" id="password"/><p class="clear-infor">6-12个字符组成，区分大小写，不能为9位以下的纯数字</p></div></li>
                <li><span class="regester-l fl">验证码:</span><div class="regester-r fl">
				        <input style="float:left;" maxlength="4" class="reg-text" ype="text" name="checkcode" id="code" value="" />
						<span class="authcode_img" style="float:left;"><img style="padding-top:10px;float:left;" id="checkcode" onclick="javascript:updateCheckcode()" src="/common/checkcode.jsp"/></span>
						<span class="see" style="padding-top:12px;padding-left:6px;float:left;"><a href="javascript:updateCheckcode()">看不清楚，换一张</a></span>
					</div>
				</li>
                <li><span class="regester-l fl">&nbsp;</span><div class="regester-r fl"><input id="regLogin" class="reg-login" type="button" onClick="checkRegMember()" value="" /></div></li>
            </ul>
        </div>
    </div>
</div>
</form>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
<%
    Object msg = request.getSession().getAttribute("regMemberInfoMsg");
    if (msg != null) {
        out.println("<script language=\"javascript\">$('.alert').html('" + (String) msg + "').addClass('alert-success').show().delay(1500).fadeOut();</script>");
        request.getSession().removeAttribute("regMemberInfoMsg");
    }
%>
