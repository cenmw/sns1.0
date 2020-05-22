<%@ page language="java" pageEncoding="UTF-8" %>
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
<!--content-->
<div class="content1 layout-control">
    <!--right-->
    <div class="layout-control mar-t20">
        <div class="regester">
            <h2 class="second-title">在线充值</h2>

            <s:form action="storesave2020" namespace="/membercenter" method="post" theme="simple">
                <s:hidden name="memberStore.id"></s:hidden>
                <s:hidden name="memberStore.mid"></s:hidden>
                <s:hidden name="memberStore.isdel"></s:hidden>
                <s:hidden name="memberStore.ctime"></s:hidden>
                <s:hidden name="memberStore.type"></s:hidden>
                <s:hidden name="memberStore.state"></s:hidden>
                <s:hidden name="memberStore.code"></s:hidden>
                <s:hidden name="memberStore.tid"></s:hidden>
                <input type="hidden" name="memberStore.price" value="<s:property value="price"/>"/>
                <table class="note-tabs1" width="1120" cellspacing="0" cellpadding="0">

                    <tr>
                        <td height="50" align="left" valign="top" style="border-bottom: 1px solid #CCCCCC;"><font
                                class="font14">支付方式：</font></td>
                        <td height="50" style="border-bottom: 1px solid #CCCCCC;">
                            <img src="/member/images/alipay.jpg" width="126" height="37"/>
                        </td>
                    </tr>

                    <tr>
                        <td width="79" height="50" align="left" valign="top" style="border-bottom: 1px solid #CCCCCC;">
                            <font class="font14">金额：</font></td>
                        <td width="561" height="50" style="border-bottom: 1px solid #CCCCCC;"><s:property
                                value="price"/></td>
                    </tr>

                    <tr>

                    </tr>
                    <td colspan="2" style="line-height:22px;">
                        <p>
                            <font color="red">
                                <strong>※ </strong>
                                在线支付的过程中请不要关闭任何弹出的窗口,有时在线支付网站速度较慢,请您耐心等待;
                                <br>
                                <strong>※ </strong>
                                支付成功后会立即入帐,返回“我的账户”页面可察看到入账情况,若出现实际已经扣款但我司会员帐户仍然没有到账等情况,请联系01051796526-8066为您手工充值;
                                <br>
                                <strong>※ </strong>
                                通过网上银行支付的会员,支付完毕后请点击“返回支付网站”,不要关闭支付窗口以免不能正常充值;
                                <br>
                                <img width="500" height="311" src="/member/images/pay.jpg">
                                <br>
                                <br>
                            </font>
                        </p>
                    </td>
                    <tr>
                        <td height="50" align="center" valign="top">&nbsp;</td>
                        <td height="50"><input class="note-queren" type="submit" value=""/></td>
                    </tr>
                </table>
            </s:form>

        </div>
        <!--right end-->
        <div class="clear"></div>
    </div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>