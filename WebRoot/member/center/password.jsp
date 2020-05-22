<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-修改密码-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript" src="/common/pstrength/digitialspaghetti.password.min.js"></script>
<script>
function checkPwd() {
	var s = true;
	var at = '';
	var pwd = $.trim($("#pwd").val());

	if (pwd == "") {
		at += '请输入原密码\n';
		s = false;
	}

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
	    $("#updatepassword").submit();
	}
	//return s;
}
jQuery(document).ready(function() {
	jQuery('#newpwd').pstrength();
});

</script>
</head>
<body>
<!--top-->
<s:action name="top" namespace="/membercenter" executeResult="true">
</s:action>
<!--top end-->
<!--content-->
<div class="content1 layout-control">
    <!--left-->
    <s:action name="left" namespace="/membercenter" executeResult="true">
    </s:action>
    <!--left end-->
    <!--right-->
   <div class="content-right fl">
   		<h2 class="second-title second-title4"><a class="blue" href="/"><<返回上一页</a>我的设置</h2>
        <div class="note-title">
			<a class="note-a" href="/membercenter/basis">基本资料</a><a class="note-a" href="/membercenter/contact">联系方式</a><a class="note-a" href="/membercenter/head">头像照片</a><a class="note-a note-cur" href="/membercenter/password">修改密码</a>
  </div>
  <s:form action="updatepassword" namespace="/membercenter"
								method="post" theme="simple" onsubmit="">
  <table class="infor-table" width="650" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th width="94" height="40" align="right" scope="row">当前密码：</th>
    <td width="486"><input id="pwd" name="pwd" class="mima-input" value="" type="password" /></td>
  </tr>
  <tr>
    <th height="40" align="right" scope="row">新密码：</th>
    <td><input id="newpwd" name="newpwd" class="mima-input" value="" type="password" /></td>
  </tr>
  <tr>
    <th height="40" align="right" scope="row">重复密码：</th>
    <td><input id="renewpwd" name="renewpwd" class="mima-input" value="" type="password" /></td>
  </tr>
  <tr>
    <th height="40" align="right" scope="row">&nbsp;</th>
    <td><div class="mar-t20"><a href="javascript:checkPwd()"><input class="inputButtonSave" type="button" value="保存"/></a></div></td>
  </tr>
</table>
</s:form>
  </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
<%
	Object msg=request.getSession().getAttribute("passwordmsg");
	if(msg!=null && msg.equals("修改成功！")){
		out.println("<script language=\"javascript\">$('.alert').html('" + (String) msg + "').addClass('alert-success').show().delay(1500).fadeOut();</script>");
		request.getSession().removeAttribute("passwordmsg");
	} else if (msg != null && !msg.equals("修改成功！")) {
		out.println("<script language=\"javascript\">$('.alert').html('" + (String) msg + "').addClass('alert-danger').show().delay(1500).fadeOut();</script>");
		request.getSession().removeAttribute("passwordmsg");
	}
%>