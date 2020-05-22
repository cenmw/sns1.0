<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><s:property value="memberInfo.account"/>-头像照片-龙爸爸成长在线</title>
    <link rel="stylesheet" type="text/css" href="/member/css/base.css"/>
    <script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
    <script type="text/javascript" src="/member/js/common.js"></script>
    <script type="text/javascript" src="/common/pstrength/digitialspaghetti.password.min.js"></script>
    <script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
    <link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
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
                        imageUrl: K('#avatar').val(),
                        clickFn: function (url, title, width, height, border, align) {
                            K('#avatar').val(url);
                            var date = new Date();
                            var s = date.getTime();
                            $('#avatar_s').attr("src", url + "?v=" + s);
                            $('#avatar_s').show();
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
            <a class="note-a" href="/membercenter/basis">基本资料</a><a class="note-a" href="/membercenter/contact">联系方式</a><a
                class="note-a note-cur" href="/membercenter/head">头像照片</a><a class="note-a"
                                                                             href="/membercenter/password">修改密码</a>
        </div>
        <s:form action="saveinfo" namespace="/membercenter"
                method="post" theme="simple" onsubmit="">
            <input type="hidden" name="type" value="3"/>
            <s:hidden name="memberInfo.id"></s:hidden>
            <s:hidden name="memberInfo.type"></s:hidden>
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
            <s:hidden name="memberInfo.mobile"></s:hidden>
            <s:hidden name="memberInfo.qq"></s:hidden>
            <s:hidden name="memberInfo.a_province"></s:hidden>
            <s:hidden name="memberInfo.a_city"></s:hidden>
            <s:hidden name="memberInfo.a_county"></s:hidden>
            <s:hidden name="memberInfo.address"></s:hidden>
            <s:hidden name="memberInfo.zcode"></s:hidden>
            <s:hidden name="memberInfo.telphone"></s:hidden>
            <s:hidden name="memberInfo.cpicpath"></s:hidden>
            <s:hidden name="memberInfo.lastlogintime"></s:hidden>
            <s:hidden name="memberInfo.sign"></s:hidden>
            <s:hidden name="memberInfo.qq_uid"></s:hidden>
            <s:hidden name="memberInfo.sina_uid"></s:hidden>
            <table class="upload-table" width="620" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th width="230" align="left" valign="top" scope="row">
                        <p><strong>当前图像：</strong></p>
                        <div class="pic-position"><input id="avatar" type="hidden"
                                                         value="<s:property value="memberInfo.avatar_center" escape="false" />"
                                                         name="memberInfo.avatar"><img id="avatar_s"
                                                                                       src="<s:property value="memberInfo.avatar_center" escape="false" />"
                                                                                       height="120" width="120"
                                                                                       border="0"/></div>
                    </th>
                    <td width="390" align="left" valign="top">
                        <p><strong>修改图像：</strong></p>
                        <div class="up-load-url"><input type="button" id="imageUpload" value="选择图片"/></div>
                        <div class="pic-cut mar-t20">&nbsp;</div>
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
