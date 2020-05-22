<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>找回密码-龙爸爸成长在线</title>
    <link rel="stylesheet" type="text/css" href="/member/css/base.css"/>
    <script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
    <script type="text/javascript" src="/member/js/common.js"></script>
    <script>
        //验证邮箱
        function checkEmail(email) {
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
            return reg.test(email);
        }

        function checkPwd() {
            var s = true;
            var at = '';
            var email = $.trim($("#m_email").val());
            if (email == "") {
                $("#m_emailem").html('邮箱不能为空！').attr("class", "ash_red");
                s = false;
            } else {
                if (!checkEmail(email)) {
                    $("#m_emailem").html('邮箱格式不正确！').attr("class", "ash_red");
                    s = false;
                }
            }
            var code = $.trim($("#code").val());
            if (code == "") {
                document.getElementById("code").focus();
                s = false;
            }
            if (s) {
                $("#findpwdForm").submit();
            }
        }

    </script>
    <style type="text/css">
        .ash_red {
            color: #FF0000;
        }
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
<form action="initpwd" id="findpwdForm" namespace="/" method="post">
    <div class="layout-control mar-t20">
        <div class="regester">
            <h3 class="regester-tt">找回密码</h3>
            <div class="regester-content">
                <ul class="regester-list">
                    <li><span class="regester-l fl">E-mail:</span>
                        <div class="regester-r fl"><input maxLength="30" name="email" id="m_email" class="reg-text"
                                                          type="text" value=""/><span class="ash" id="m_emailem">注意：请填写注册时填写的邮箱</span>
                        </div>
                    </li>
                    <li><span class="regester-l fl">验证码:</span>
                        <div class="regester-r fl">
                            <input style="float:left;" class="reg-text" ype="text" maxlength="4" name="checkcode"
                                   id="code" value=""/>
                            <span class="authcode_img" style="float:left;"><img style="padding-top:10px;float:left;"
                                                                                id="checkcode"
                                                                                onclick="javascript:updateCheckcode()"
                                                                                src="/common/checkcode.jsp"/></span>
                            <span class="see" style="padding-top:12px;padding-left:6px;float:left;"><a
                                    href="javascript:updateCheckcode()">看不清楚，换一张</a></span>
                            <span class="ash" id="codeem"></span>
                        </div>
                    </li>
                    <li style="padding-left:120px;">
                        <div class="mar-t20"><a href="javascript:void(0)" onclick="checkPwd()"><img
                                src="/member/images/queren.png" height="26" width="83" border="0"/></a></div>
                    </li>
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
    Object msg = request.getSession().getAttribute("findwordmsg");
    if (msg != null) {
        out.println("<script language=\"javascript\">$('.alert').html('" + (String) msg + "').addClass('alert-danger').show().delay(1500).fadeOut();</script>");
        request.getSession().removeAttribute("findwordmsg");
        Object findwordEmail = request.getSession().getAttribute("findwordEmail");
		if (findwordEmail != null) {
        out.println("<script language=\"javascript\">$(\"#m_email\").val('" + findwordEmail + "');</script>");
        request.getSession().removeAttribute("findwordEmail");
		}
    }
%>
