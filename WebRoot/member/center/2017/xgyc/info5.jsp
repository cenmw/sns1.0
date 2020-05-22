<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="dpage == 5 ">
    <script>
        location.href="showxgycinfo?id="+<s:property value="memberDiaryXGYC.id" escape="false" />;
    </script>
</s:if>
<s:if test="dpage == 4 ">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-习惯养成-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
function checkcontentinfo(){
    var hzbx = $("#hzbx").val();
    if(hzbx == ''){
        $("#hzbx").focus();
        return ;
    }
    var fqbx = $("#fqbx").val();
    if(fqbx == ''){
        $("#fqbx").focus();
        return ;
    }
    var mqbx = $("#mqbx").val();
    if(mqbx == ''){
        $("#mqbx").focus();
        return ;
    }
    var wdgs = $("#wdgs").val();
    if(wdgs == ''){
        $("#wdgs").focus();
        return ;
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>习惯养成</h2>
<form id="frm" method="post" action="xgycinfo5?dpage=5&id=<s:property value="memberDiaryXGYC.id" escape="false" />">
    <table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0" id="table2">

        <tr>
            <td colspan="2" style="line-height:25px; padding-top:10px;" height="35" valign="top"><font class="font14">
                今天家里每个人做得最好，进步最大地方是什么？(500字左右)</font>
            </td>
        </tr>

        <tr>
            <td style="line-height:120px; padding-top:10px;" height="120" valign="top">孩子：</td>
            <td style="line-height:25px; padding-top:10px;" height="120">
                <s:textarea name="hzbx" id="hzbx" rows="8" cols="70"/></td>
        </tr>

        <tr>
            <td style="line-height:120px; padding-top:10px;" height="120" valign="top">父亲：</td>
            <td style="line-height:25px; padding-top:10px;" height="120">
                <s:textarea name="fqbx" id="fqbx" rows="8" cols="70"/></td>
        </tr>

        <tr>
            <td style="line-height:120px; padding-top:10px;" height="120" valign="top">母亲：</td>
            <td style="line-height:25px; padding-top:10px;" height="120">
                <s:textarea name="mqbx" id="mqbx" rows="8" cols="70"/></td>
        </tr>

        <tr>
            <td colspan="2" style="line-height:25px; padding-top:10px;" height="35" valign="top"><font class="font14">
                今天一天我们一家人的感受是什么呢？(500字左右)</font>
            </td>
        </tr>

        <tr>
            <td colspan="2" style="line-height:25px; padding-top:10px;padding-left:40px;" height="120">
                <s:textarea name="wdgs" id="wdgs" rows="8" cols="70"/></td>
        </tr>

        <s:if test="dpage == 4 ">
        <tr>
            <td colspan="2" align="center" style="font-size: 14px;height: 50px;">
                <span class="blue" style="cursor:pointer;" onclick="checkcontentinfo();">晚安，早点休息！</span>
            </td>
        </tr>
        </s:if>

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
</s:if>