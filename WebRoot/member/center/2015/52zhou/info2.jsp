<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的52周-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link rel="stylesheet" type="text/css" href="/common/js/datepicker/ui.datepickerv1.css"></link>
<script src="/common/js/datepicker/ui.datepicker-zh-CN.js" type="text/javascript"></script>
<script src="/common/js/datepicker/ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
function checkcontentinfo(){
    var address=$.trim($("#address").val());
	if(address==""){
		alert("地点不能为空");
		return false;
	}
	var weather=$.trim($("#weather").val());
	if(weather==""){
		alert("天气不能为空");
		return false;
	}
    var fssq=$.trim($("#fssq").val());
	if(fssq==""){
		alert("发生的事情不能为空");
		return false;
	}
	var wdgs=$.trim($("#wdgs").val());
	if(wdgs==""){
		alert("感受不能为空");
		return false;
	}
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
   <!--right-->
   <div class="content-right fl">
   		<h2 class="second-title"><a class="blue" href="/membercenter/zhouinfo1?type=<s:property value="memberDiary52.type" escape="false" />"><<返回上一页</a>我的52周</h2>
<form id="frm" method="post" action="/membercenter/savezhouinfo" onsubmit="return checkcontentinfo()">
  <s:hidden name="backUrl"></s:hidden>
  <s:hidden name="memberDiary52.id"></s:hidden>
  <s:hidden name="memberDiary52.mid"></s:hidden>
  <s:hidden name="memberDiary52.isdel"></s:hidden>
  <s:hidden name="memberDiary52.ctime"></s:hidden>
  <s:hidden name="memberDiary52.viewnumber"></s:hidden>
  <s:hidden name="memberDiary52.rcid"></s:hidden>
  <s:hidden name="memberDiary52.rcnumber"></s:hidden> 
  <s:hidden name="memberDiary52.day"></s:hidden>
  <s:hidden name="memberDiary52.type"></s:hidden>
  <input type="hidden" id="type" name="type" value="<s:property value="type" escape="false" />"/>   
 <table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0" id="table2"  style="margin:30px auto;"> 
  
  <tr>
    <td width="149" height="50" align="center" valign="top"><font class="font14">日期：</font></td>
	<td width="561" height="50">
	<input name="memberDiary52.ptime" id="ptime" class="note-input1" maxLength="50" style="width:200px;" value="<s:date name="memberDiary52.ptime" format="yyyy-MM-dd"/>" />
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
    <td width="149" height="50" align="center" valign="top"><font class="font14">地点：</font></td>
	<td width="561" height="50">
	<input name="memberDiary52.address" id="address" class="note-input1" maxLength="50" style="width:200px;" value="<s:property value="memberDiary52.address" escape="false" />" />
	</td>
  </tr>
  
  <tr>
    <td width="149" height="50" align="center" valign="top"><font class="font14">天气：</font></td>
	<td width="561" height="50">
	<input name="memberDiary52.weather" id="weather" class="note-input1" maxLength="50" style="width:200px;" value="<s:property value="memberDiary52.weather" escape="false" />" />
	</td>
  </tr>
  
  <tr>
    <td style="line-height:25px; padding-top:10px;" height="50" align="center" valign="top"><font class="font14"></font></td>
    <td height="120">
    <font class="font14" style="line-height:28px;">今天是第【<s:property value="day" escape="false" />】天，一天已经过去，让我们静下心来，回味今天和孩子在一起的生活，不管是“好的”还是“坏的”都可以写下来（100字）</font><br />
	<s:textarea name="memberDiary52.fssq" id="fssq" rows="6" cols="62"/></td>
  </tr>
  
  <tr>
    <td style="line-height:25px; padding-top:10px;" height="50" align="center" valign="top"><font class="font14"></font></td>
    <td height="120">
    <font class="font14">这件事情给您带来的感受什么呢？当时的表现是什么呢？（200字）</font><br />
	<s:textarea name="memberDiary52.wdgs" id="wdgs" rows="6" cols="62"/></td>
  </tr>
  
  <tr>
    <td height="50" align="center" valign="top"><font class="font14">权限：</font></td>
    <td height="50">
	<select name="memberDiary52.qx" id="qx">
		<option value="0">任何人可见</option>
		<option value="1">仅好友可见</option>
		<option value="2">仅自己可见</option>
	</select>
	
	<script type="text/javascript">document.getElementById("qx").value='<s:property value="memberDiary52.qx" escape="false" />'</script>
	</td>
  </tr>
  
  <tr>
    <td height="50" align="center" valign="top">&nbsp;</td>
    <td height="50"><input class="note-saved" type="submit" value="" />&nbsp;&nbsp;如果需要疏导，请点击<a class="blue" style="font-weight:bold;" href="/membercenter/mysdinfo1?type=<s:property value="type" escape="false" />">疏导</a>，我们一起仔细分析一下。<a class="blue" style="font-weight:bold;" href="/membercenter/mysdinfo1?type=<s:property value="type" escape="false" />">进入疏导的页面</a></td>
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
<s:if test="day == 0 && id==0">
<%
	Object msg=request.getSession().getAttribute("saveinfomsg");
	if(msg!=null){
	    request.getSession().removeAttribute("saveinfomsg");
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');location.href='/membercenter/zhouinfo'</script>");
		
	}
%>
</s:if>
<s:if test="id > 0 && day%7 == 0">
<%
	Object msg=request.getSession().getAttribute("saveinfomsg");
	String type = request.getParameter("type");
	if(msg!=null){
		request.getSession().removeAttribute("saveinfomsg");
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');location.href='/membercenter/zhouinfo3?type="+type+"'</script>");
	}
%>
</s:if>
<s:else>
<%
	Object msg=request.getSession().getAttribute("saveinfomsg");
	if(msg!=null){
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("saveinfomsg");
	}
%>
</s:else>
