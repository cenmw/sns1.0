<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><s:property value="memberInfo.account" />-吾日省身-龙爸爸成长在线</title>
    <link rel="stylesheet" type="text/css" href="/member/css/base.css" />
    <script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
    <script type="text/javascript" src="/member/js/common.js"></script>
    <script type="text/javascript">
        function checkcontentinfo(){
            var xymz = $("#xymz").val();
            if(xymz == ""){
                $("#xymz").focus();
                return;
            }
            $("#frm").submit();
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
    <!--right-->
    <div class="content-right fl">
        <h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>吾日省身</h2>
        <form id="frm" method="post" action="/membercenter/wrssinfo3?dpage=2&id=<s:property value="memberDiary.id" escape="false" />">
            <table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0">

                <tr>
                    <td colspan="2" style="line-height:25px; padding-top:10px;" height="35" valign="top"><font class="font14">第二步：我的学问有没有进步？我今天学到了什么？(500字左右)</font></td>
                </tr>

                <tr>
                    <td colspan="2" style="line-height:25px; padding-top:10px;" height="120">
                        <s:textarea name="xymz" id="xymz" rows="8" cols="70"/></td>
                </tr>

                <tr>
                    <td height="50" align="center" valign="top">&nbsp;</td>
                    <td height="50">
                        <p>学习是无止境，学习不止是书本上的学习，更多是生活上的体悟，请点击&nbsp;&nbsp;<span class="blue" style="cursor:pointer;" onclick="checkcontentinfo();">我知道了</span></p>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
