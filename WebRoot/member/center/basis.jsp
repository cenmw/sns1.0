<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><s:property value="memberInfo.account"/>-基本资料-龙爸爸成长在线</title>
    <link rel="stylesheet" type="text/css" href="/member/css/base.css"/>
    <script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
    <script type="text/javascript" src="/common/js/area2013.js"></script>
    <script type="text/javascript" src="/member/js/common.js"></script>
    <link rel="stylesheet" type="text/css" href="/common/js/datepicker/ui.datepickerv1.css"></link>
    <script src="/common/js/datepicker/ui.datepicker-zh-CN.js" type="text/javascript"></script>
    <script src="/common/js/datepicker/ui.datepicker.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
    <script type="text/javascript" src="/member/js/reg.js"></script>
    <link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
    <style type="text/css">
        .ash_red{ color:#FF0000; padding-left: 5px;}
        .ash{ color:#666666;}
    </style>
    <script>
        KindEditor.ready(function (K) {
            var imageEditor = K.editor({
                allowFileManager: true,
                fileManagerJson: '/keditor/browseImageLists?foldername=uploadfiles,member',
                uploadJson: '/keditor/uploadImages?foldername=uploadfiles,member'
            });
            K('#imageUpload').click(function () {
                imageEditor.loadPlugin('image', function () {
                    imageEditor.plugin.imageDialog({
                        hideImageSet: true,
                        imageUrl: K('#cpicpath').val(),
                        clickFn: function (url, title, width, height, border, align) {
                            K('#cpicpath').val(url);
                            var date = new Date();
                            var s = date.getTime();
                            $('#cpicpath_s').attr("src", url + "?v=" + s);
                            $('#cpicpath_s').show();
                            imageEditor.hideDialog();
                            $('#avatal_saveid').show();
                        }
                    });
                });
            });
        });

        function updatemember() {
            $("#saveinfo").submit();
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
            <a class="note-a note-cur" href="/membercenter/basis">基本资料</a><a class="note-a"
                                                                             href="/membercenter/contact">联系方式</a><a
                class="note-a" href="/membercenter/head">头像照片</a><a class="note-a"
                                                                    href="/membercenter/password">修改密码</a>
        </div>
        <s:form action="saveinfo" namespace="/membercenter" method="post" theme="simple" onsubmit="">
            <input type="hidden" name="type" value="1"/>
            <s:hidden name="memberInfo.id"></s:hidden>
            <s:hidden name="memberInfo.type"></s:hidden>
            <s:hidden name="memberInfo.avatar"></s:hidden>
            <s:hidden name="memberInfo.password"></s:hidden>
            <s:hidden name="memberInfo.email"></s:hidden>
            <s:hidden name="memberInfo.mobile"></s:hidden>
            <s:hidden name="memberInfo.status"></s:hidden>
            <s:hidden name="memberInfo.isdel"></s:hidden>
            <s:hidden name="memberInfo.ctime"></s:hidden>
            <s:hidden name="memberInfo.qq"></s:hidden>
            <s:hidden name="memberInfo.address"></s:hidden>
            <s:hidden name="memberInfo.a_province"></s:hidden>
            <s:hidden name="memberInfo.a_city"></s:hidden>
            <s:hidden name="memberInfo.a_county"></s:hidden>
            <s:hidden name="memberInfo.zcode"></s:hidden>
            <s:hidden name="memberInfo.telphone"></s:hidden>
            <s:hidden name="memberInfo.lastlogintime"></s:hidden>
            <s:hidden name="memberInfo.sign"></s:hidden>
            <s:hidden name="memberInfo.qq_uid"></s:hidden>
            <s:hidden name="memberInfo.sina_uid"></s:hidden>
            <table class="infor-table" width="650" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="82" height="40" align="right" valign="top"><strong class="mar-r5"><s:if
                            test="memberInfo.type==0">真实姓名</s:if><s:else>机构名称</s:else>：</strong></td>
                    <td width="568" height="40"><s:textfield name="memberInfo.account" id="m_account"
                                                             cssClass="infor-text" onblur="checkAccountAjax()"></s:textfield><span class="ash" id="accountem"></span></td>
                </tr>

                <s:if test="memberInfo.type==1">
                    <tr>
                        <td height="40" align="right" valign="top"><strong class="mar-r5">法人名称：</strong></td>
                        <td height="40"><s:textfield name="memberInfo.cname" id="cname"
                                                     cssClass="infor-text"></s:textfield></td>
                    </tr>
                </s:if>
                <s:else>
                    <input id="cname" type="hidden" value="" name="memberInfo.cname"/>
                </s:else>

                <tr>
                    <td height="40" align="right" valign="top"><strong class="mar-r5">性别：</strong></td>
                    <td height="40"><s:radio list="#{1:'男',0:'女'}" name="memberInfo.sex"
                                             value="memberInfo.sex"></s:radio></td>
                </tr>
                <tr>
                    <td height="40" align="right" valign="top"><strong class="mar-r5">出生日期：</strong></td>
                    <td height="40"><input class="infor-text" style="width:160px;" type="text" id="birthday"
                                           name="memberInfo.birthday"
                                           value="<s:date name="memberInfo.birthday" format="yyyy-MM-dd"/>"/>
                        <script type="text/javascript">
                            jQuery(function ($) {
                                $('#birthday').datepicker({
                                    yearRange: '1920:2050', //取值范围.
                                    showOn: 'both', //输入框和图片按钮都可以使用日历控件。
                                    buttonImage: '/common/js/datepicker/date.gif', //日历控件的按钮
                                    buttonImageOnly: true,
                                    showButtonPanel: true
                                });

                            });
                        </script>
                    </td>
                </tr>
                <tr>
                    <td height="40" align="right" valign="top"><strong class="mar-r5">家乡：</strong></td>
                    <td height="40"><select class="infor-select" id="s_province" name="memberInfo.h_province"></select>&nbsp;&nbsp;
                        <select class="infor-select" id="s_city" name="memberInfo.h_city"></select>&nbsp;&nbsp;
                        <select class="infor-select" id="s_county" name="memberInfo.h_county"></select>
                        <script type="text/javascript">_init_area('<s:property value="memberInfo.h_province" escape="false" />', '<s:property value="memberInfo.h_city" escape="false" />', '<s:property value="memberInfo.h_county" escape="false" />');</script>
                    </td>
                </tr>
                <tr>
                    <td height="40" align="right" valign="top"><strong class="mar-r5">职业：</strong></td>
                    <td height="40"><s:textfield name="memberInfo.professional" id="professional"
                                                 cssClass="infor-text"></s:textfield></td>
                </tr>
                <tr>
                    <td height="40" align="right" valign="top"><strong class="mar-r5"><s:if
                            test="memberInfo.type==0">自我介绍</s:if><s:else>公司介绍</s:else>：</strong></td>
                    <td height="40"><textarea name="memberInfo.introduction" id="memberInfo.introduction"
                                              class="infor-textarea"><s:property
                            value="memberInfo.introduction"/></textarea></td>
                </tr>
                <s:if test="memberInfo.type==1">
                    <tr>
                        <td height="130" align="right" valign="top"><strong class="mar-r5"><s:if
                                test="memberInfo.type==0">自我介绍</s:if><s:else>营业执照</s:else>：</strong></td>
                        <td height="130"><input id="cpicpath" type="hidden"
                                                value="<s:property value="memberInfo.cpicpath" escape="false" />"
                                                name="memberInfo.cpicpath"><img id="cpicpath_s"
                                                                                src="<s:property value="memberInfo.cpicpath" escape="false" />"
                                                                                height="120" width="120"
                                                                                border="0"/><input type="button"
                                                                                                   id="imageUpload"
                                                                                                   value="选择图片"/></td>
                    </tr>
                </s:if>
                <s:else>
                    <input id="cpicpath" type="hidden" value="" name="memberInfo.cpicpath"/>
                </s:else>

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