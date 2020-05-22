<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><s:property value="memberInfo.account"/>-在线充值-龙爸爸成长在线</title>
    <link rel="stylesheet" type="text/css" href="/member/css/base.css"/>
    <script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
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
<div class="content1 layout-control">
    <!--right-->
    <div class="layout-control mar-t20">
        <div class="regester">
            <h2 class="second-title">在线充值</h2>

            <table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0">

                <tr style="">
                    <td height="50" align="left" valign="top" style="border-bottom: 1px solid #CCCCCC;"><font
                            class="font14">支付方式：</font></td>
                    <td height="50" style="border-bottom: 1px solid #CCCCCC;">
                        <img src="/member/images/alipay.jpg" width="126" height="37"/>
                    </td>
                </tr>

                <tr>
                    <td width="79" height="50" align="left" valign="top" style="border-bottom: 1px solid #CCCCCC;"><font
                            class="font14">金额：</font></td>
                    <td width="561" height="50" style="border-bottom: 1px solid #CCCCCC;"><s:property
                            value="memberStore.price" escape="false"/></td>
                </tr>

                <tr>
                    <td width="79" height="50" align="left" valign="top" style="border-bottom: 1px solid #CCCCCC;"><font
                            class="font14">支付编码：</font></td>
                    <td width="561" height="50" style="border-bottom: 1px solid #CCCCCC;"><s:property
                            value="memberStore.code" escape="false"/></td>
                </tr>
                <tr>
                <tr>
                    <td colspan="2">&nbsp;

                        <form name="alipayment" action="/store/alipayapi.jsp" method="post" target="_blank">
                            <input type="hidden" name="WIDseller_email" value="xiuluo99@163.com">
                            <input type="hidden" name="WIDout_trade_no"
                                   value="<s:property value="memberStore.code" escape="false"/>">
                            <input type="hidden" name="WIDsubject" value="龙爸爸成长在线 在线充值">
                            <input type="hidden" name="WIDprice"
                                   value="<s:property value="memberStore.price" escape="false"/>">
                            <input type="hidden" name="WIDbody"
                                   value="订单ID=<s:property value="memberStore.code" escape="false"/> <s:date name="memberStore.ctime" format="yyyy-MM-dd HH:mm:ss"/> Member(id=) pays <s:property value="memberStore.price" escape="false"/> 元.">
                            <input type="hidden" name="WIDshow_url"
                                   value="http://www.longbaba.com.cn/membercenter/showstoreinfo?id=<s:property value="memberStore.id" escape="false"/>">
                            <input type="hidden" name="WIDreceive_name"
                                   value="<s:property value="memberInfo.account" escape="false"/>">
                            <input type="hidden" name="WIDreceive_address"
                                   value="<s:property value="memberInfo.address" escape="false"/>">
                            <input type="hidden" name="WIDreceive_zip"
                                   value="<s:property value="memberInfo.zcode" escape="false"/>">
                            <input type="hidden" name="WIDreceive_phone"
                                   value="<s:property value="memberInfo.telphone" escape="false"/>">
                            <input type="hidden" name="WIDreceive_mobile"
                                   value="<s:property value="memberInfo.mobile" escape="false"/>">

                            <div style="margin-top:10px;">
                                <input class="note-queren" type="submit" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a class="blue" onclick="location.href='/membercenter/index'" href="javascript:"><<完成支付</a>
                            </div>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>