<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的日记-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<link rel="stylesheet" type="text/css" href="/common/js/datepicker/ui.datepickerv1.css"></link>
<script src="/common/js/datepicker/ui.datepicker-zh-CN.js" type="text/javascript"></script>
<script src="/common/js/datepicker/ui.datepicker.js" type="text/javascript"></script>

<script type="text/javascript" src="/member/js/common.js"></script>

<script type="text/javascript">
//判断字节长度
function zjlen(s) {
	var l = 0;
	var a = s.split("");
	for (var i=0;i<a.length;i++) {
		if (a[i].charCodeAt(0)<299) {
			l++;
		} else {
			l+=2;
		}
	}
	return l;
}

function checkcontentinfo(){

	return true;
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
   		<h2 class="second-title"><a class="blue" href="javascript:location.href='<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>'"><<返回上一页</a>新日记</h2>

<s:form action="diarysave" namespace="/membercenter" method="post" theme="simple" onsubmit="return checkcontentinfo()">
  <s:hidden name="backUrl"></s:hidden>
  <s:hidden name="memberDiary.id"></s:hidden>
  <s:hidden name="memberDiary.mid"></s:hidden>
  <s:hidden name="memberDiary.isdel"></s:hidden>
  <s:hidden name="memberDiary.ctime"></s:hidden>
  <s:hidden name="memberDiary.viewnumber"></s:hidden>
  <s:hidden name="memberDiary.rcid"></s:hidden>
  <s:hidden name="memberDiary.rcnumber"></s:hidden>
<table class="note-tabs1" width="710" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td width="149" height="50" align="center" valign="top"><font class="font14">日期：</font></td>
	<td width="561" height="50">
	<input name="memberDiary.ptime" id="ptime" class="note-input1" maxLength="50" style="width:100px;" value="<s:date name="memberDiary.ptime" format="yyyy-MM-dd"/>" />
	<script type="text/javascript">
        jQuery(function($) {
            $('#ptime').datepicker( {
                yearRange : '2011:2050', //取值范围.
                showOn : 'both', //输入框和图片按钮都可以使用日历控件。
                buttonImage : '/common/js/datepicker/date.gif', //日历控件的按钮
                buttonImageOnly : true,
                showButtonPanel : true
            });
        
        });
    </script> 
	</td>
  </tr>
  
  <tr>
    <td style="line-height:25px; padding-top:10px;" height="50" align="center" valign="top"><font class="font14">发生的事情是什么？：</font></td>
    <td height="120">
	<s:textarea name="memberDiary.fssq" id="fssq" rows="4" cols="62"/></td>
  </tr>
  
  <tr>
    <td style="line-height:25px; padding-top:10px;" height="50" align="center" valign="top"><font class="font14">孩子的表现是什么？：</font></td>
    <td height="120">
	<s:textarea name="memberDiary.hzbx" id="hzbx" rows="4" cols="62"/></td>
  </tr>
  
  <tr>
    <td style="line-height:25px; padding-top:10px;" height="50" align="center" valign="top"><font class="font14">表现积极的地方是什么？：</font></td>
    <td height="120">
	<s:textarea name="memberDiary.bxjj" id="bxjj" rows="4" cols="62"/></td>
  </tr>
  
  <tr>
    <td style="line-height:25px; padding-top:10px;" height="50" align="center" valign="top"><font class="font14">发展空间是什么？：</font></td>
    <td height="120">
	<s:textarea name="memberDiary.fzkj" id="fzkj" rows="4" cols="62"/></td>
  </tr>
  
  <tr>
    <td style="line-height:25px; padding-top:10px;" height="50" align="center" valign="top"><font class="font14">我的感受是什么？：</font></td>
    <td height="120">
	<s:textarea name="memberDiary.wdgs" id="wdgs" rows="4" cols="62"/></td>
  </tr>
  
  <tr>
    <td style="line-height:25px; padding-top:10px;" height="50" align="center" valign="top"><font class="font14">我此时的内心需求是什么？：</font></td>
    <td height="120">
	<s:textarea name="memberDiary.nxxq" id="nxxq" rows="4" cols="62"/></td>
  </tr>
  
  <tr>
    <td height="50" align="center" valign="top"><font class="font14">权限：</font></td>
    <td height="50">
	<select name="memberDiary.qx" id="qx">
		<option value="0">任何人可见</option>
		<option value="1">仅好友可见</option>
		<option value="2">仅自己可见</option>
	</select>
	
	<script type="text/javascript">document.getElementById("qx").value='<s:property value="memberDiary.qx" escape="false" />'</script>
	</td>
  </tr>
  
  <tr>
    <td height="50" align="center" valign="top">&nbsp;</td>
    <td height="50"><input class="note-saved" type="submit" value="" /></td>
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
	Object msg=request.getSession().getAttribute("saveinfomsg");
	if(msg!=null){
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("saveinfomsg");
	}
%>
