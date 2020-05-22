<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>重置密码-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/common/pstrength/digitialspaghetti.password.min.js"></script>
<script>
function checkPwd() {
	var s = true;
	var at = '';

	var newpwd = $.trim($("#newpwd").val());
	if (newpwd == "") {
		at += '请输入新密码\n';
		s = false;
	} else {
		if (newpwd.length < 6 || newpwd.length > 15) {
			at += '新密码最少6个字符，不超过15位！\n';
			s = false;
		}else{
			var renewpwd = $.trim($("#renewpwd").val());
			if (renewpwd == "") {
				at += '请输入确认密码\n';
				s = false;
			} else {
	
				if (newpwd != renewpwd) {
					at += '新密码与确认密码不一致';
					s = false;
				}
			}
		}
	}
	if (!s) {
		$('.alert').html(at).addClass('alert-danger').show().delay(1500).fadeOut();
	}else{
	    $("#saveuppwdForm").submit();
	}
	//return s;
}
jQuery(document).ready(function() {
	jQuery('#newpwd').pstrength();
});

</script>
<style type="text/css">
.ash_red{ color:#FF0000;}
</style>
</head>
<body>
<!--top-->
<div class="login-top">
	<div class="layout-control">
    	<a class="login-logo fl" href=""></a>
        <div class="fr login-r">已有龙爸爸帐号，<a class="blue" href="/">登录</a></div>
    </div>
</div>
<!--top end-->
<!--content--> 
<form action="saveuppwd" id="saveuppwdForm" namespace="/" method="post">	
<input type="hidden" name="email" value="<s:property value="email"/>" /> 
<input type="hidden" name="sign" value="<s:property value="sign"/>" /> 
<div class="layout-control mar-t20">
	<div class="regester">
    	<h3 class="regester-tt">重置密码</h3>
        <div class="regester-content">
            <ul class="regester-list">
                <li><span class="regester-l fl">新密码:</span><div class="regester-r fl"><input id="newpwd" name="newpwd" maxlength="15" class="mima-input" value="" type="password" /></div></li>                
                <li><span class="regester-l fl">重复密码:</span><div class="regester-r fl"><input id="renewpwd" name="renewpwd" maxlength="15" class="mima-input" value="" type="password" /></div>
				</li>
               <li style="padding-left:120px;"><div class="mar-t20"><a href="javascript:void(0)" onclick="checkPwd()"><img src="/member/images/queren.png" height="26" width="83" border="0" /></a></div></li>
            </ul>
        </div>
    </div>
</div>
</form>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
