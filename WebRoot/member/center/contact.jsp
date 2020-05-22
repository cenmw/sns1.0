<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><s:property value="memberInfo.account"/>-联系方式-龙爸爸成长在线</title>
    <link rel="stylesheet" type="text/css" href="/member/css/base.css"/>
    <script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
    <script type="text/javascript" src="/common/js/area2013.js"></script>
    <script type="text/javascript" src="/member/js/common.js"></script>
    <link rel="stylesheet" type="text/css" href="/common/js/datepicker/ui.datepickerv1.css"></link>
    <script src="/common/js/datepicker/ui.datepicker-zh-CN.js" type="text/javascript"></script>
    <script src="/common/js/datepicker/ui.datepicker.js" type="text/javascript"></script>
    <script type="text/javascript" src="/member/js/reg.js"></script>
    <style type="text/css">
        .ash_red {
            color: #FF0000;
        }

        .ash_gray {
            color: #666666;
        }

        .ash_blue {
            color: #007ED9;
        }
    </style>
    <script>
        function updatemember() {
            var t = true;
            var mobile = $.trim($("#mobile").val());
            if (mobile != "") {
                if (!checkMobile(mobile)) {
                    $("#mobileem").html('请输入正确的手机号格式！').attr("class", "ash_red");
                    ;
                    t = false;
                }
            }
            if (t) {
                $("#saveinfo").submit();
            }
        }
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
        <h2 class="second-title second-title4"><a class="blue" href="/"><
            <返回上一页
        </a>我的设置
        </h2>
        <div class="note-title">
            <a class="note-a" href="/membercenter/basis">基本资料</a><a class="note-a note-cur"
                                                                    href="/membercenter/contact">联系方式</a><a
                class="note-a" href="/membercenter/head">头像照片</a><a class="note-a"
                                                                    href="/membercenter/password">修改密码</a>
        </div>
        <s:form action="saveinfo" namespace="/membercenter" method="post" theme="simple" onsubmit="">
            <input type="hidden" name="type" value="2"/>
            <s:hidden name="memberInfo.id"></s:hidden>
            <s:hidden name="memberInfo.type"></s:hidden>
            <s:hidden name="memberInfo.avatar"></s:hidden>
            <s:hidden name="memberInfo.password"></s:hidden>
            <s:hidden name="memberInfo.email"></s:hidden>
            <s:hidden name="memberInfo.account"></s:hidden>
            <s:hidden name="memberInfo.cname"></s:hidden>
            <s:hidden name="memberInfo.status"></s:hidden>
            <s:hidden name="memberInfo.isdel"></s:hidden>
            <s:hidden name="memberInfo.ctime"></s:hidden>
            <s:hidden name="memberInfo.sex"></s:hidden>
            <s:hidden name="memberInfo.birthday"></s:hidden>
            <s:hidden name="memberInfo.h_province"></s:hidden>
            <s:hidden name="memberInfo.h_city"></s:hidden>
            <s:hidden name="memberInfo.h_county"></s:hidden>
            <s:hidden name="memberInfo.professional"></s:hidden>
            <s:hidden name="memberInfo.introduction"></s:hidden>
            <s:hidden name="memberInfo.cpicpath"></s:hidden>
            <s:hidden name="memberInfo.lastlogintime"></s:hidden>
            <s:hidden name="memberInfo.sign"></s:hidden>
            <s:hidden name="memberInfo.qq_uid"></s:hidden>
            <s:hidden name="memberInfo.sina_uid"></s:hidden>
            <table class="infor-table" width="900" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="82" height="40" align="right" valign="top"><strong class="mar-r5">Email：</strong></td>
                    <td width="568" height="40"><s:property value="memberInfo.email"/></td>
                </tr>
                <tr>
                    <td height="40" align="right" valign="top"><strong class="mar-r5">QQ：</strong></td>
                    <td height="40"><s:textfield name="memberInfo.qq" id="qq" cssClass="infor-text"></s:textfield></td>
                </tr>
                <tr>
                    <td height="40" align="right" valign="top"><strong class="mar-r5">手机：</strong></td>
                    <td height="40"><s:textfield name="memberInfo.mobile" id="mobile" onblur="checkMobileAjax()"
                                                 cssClass="infor-text"></s:textfield>&nbsp;&nbsp;<span class="ash_gray"
                                                                                                       id="mobileem">要求：手机号只能绑定一个账号，方便登录。</span>
                    </td>
                </tr>
                <tr>
                    <td height="40" align="right" valign="top"><strong class="mar-r5">固定电话：</strong></td>
                    <td height="40"><s:textfield name="memberInfo.telphone" id="telphone"
                                                 cssClass="infor-text"></s:textfield></td>
                </tr>
                <tr>
                    <td height="40" align="right" valign="top"><strong class="mar-r5">通信地址：</strong></td>
                    <td height="40"><select class="infor-select" id="s_province" name="memberInfo.a_province"></select>&nbsp;&nbsp;
                        <select class="infor-select" id="s_city" name="memberInfo.a_city"></select>&nbsp;&nbsp;
                        <select class="infor-select" id="s_county" name="memberInfo.a_county"></select>
                        <script type="text/javascript">_init_area('<s:property value="memberInfo.a_province" escape="false" />', '<s:property value="memberInfo.a_city" escape="false" />', '<s:property value="memberInfo.a_county" escape="false" />');</script>
                        <div class="mar-t5" style="margin-bottom:20px;"><s:textfield name="memberInfo.address"
                                                                                     id="address"
                                                                                     cssClass="infor-text"></s:textfield></div>
                    </td>
                </tr>
                <tr>
                    <td height="40" align="right" valign="top"><strong class="mar-r5">邮编：</strong></td>
                    <td height="40"><s:textfield name="memberInfo.zcode" id="zcode"
                                                 cssClass="infor-text"></s:textfield></td>
                </tr>
                <tr>
                    <td height="40" align="right" valign="top">&nbsp;</td>
                    <td height="40">
                        <div class="mar-t20"><a href="javascript:updatemember()"><input class="inputButtonSave"
                                                                                        type="button" value="保存"/></a>
                        </div>
                    </td>
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
    Object msg = request.getSession().getAttribute("saveinfomsg");
    if (msg != null && msg.equals("修改成功！")) {
        out.println("<script language=\"javascript\">$('.alert').html('" + (String) msg + "').addClass('alert-success').show().delay(1500).fadeOut();</script>");
        request.getSession().removeAttribute("saveinfomsg");
    } else if (msg != null && !msg.equals("修改成功！")) {
        out.println("<script language=\"javascript\">$('.alert').html('" + (String) msg + "').addClass('alert-danger').show().delay(1500).fadeOut();</script>");
        request.getSession().removeAttribute("saveinfomsg");
    }
%>
