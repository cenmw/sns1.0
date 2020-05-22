<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线充值-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
</head>
<body>
<!--top-->
<s:action name="top" namespace="/membercenter" executeResult="true">
</s:action>
<!--top end-->
<!--content-->
<div class="content1 layout-control">
   <!--right-->
   <div class="content-right fl" style="width:940px;">
   		<h2 class="second-title">充值记录</h2>
		
<table class="note-tabs1" width="880" border="0" cellspacing="0" cellpadding="0">
  
  <tr style="">
    <td height="50" align="left" width="80px;" valign="top"><font class="font14">充值状态：</font></td>
    <td height="50">
	<font class="font14" style="color:#FF0000;"><s:property value="result" escape="false"/></font>
	</td>
  </tr>
	   
  <tr>
  
</table>

  </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>