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
            var wdgs = $("#wdgs").val();
            if(wdgs == ""){
                $("#wdgs").focus();
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
        <form id="frm" method="post" action="/membercenter/wrssinfo4?dpage=3&id=<s:property value="memberDiary.id" escape="false" />">
            <table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0">

                <tr>
                    <td colspan="2" style="line-height:25px; padding-top:10px;" height="35" valign="top"><font class="font14">第三步，我的工作有没有进步？（如果您是全职妈妈，那么今天的家务是否有进步呢？）(500字左右)</font></td>
                </tr>

                <tr>
                    <td colspan="2" style="line-height:25px; padding-top:10px;" height="120">
                        <s:textarea name="wdgs" id="wdgs" rows="8" cols="70"/></td>
                </tr>

                <tr>
                    <td height="50" align="center" valign="top">&nbsp;</td>
                    <td height="50">
                        <p>工作与生活是应征我们存在的最简单的方式，也是人生价值体现的载体，努力的工作，幸福的生活，皆是我们人生价值的载体。请点击，&nbsp;&nbsp;<span class="blue" style="cursor:pointer;" onclick="checkcontentinfo();">嗯，我明白了</span></p>
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
