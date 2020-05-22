<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-习惯养成-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
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
   		<h2 class="second-title"><a class="blue" href="/membercenter/xgyclist"><<返回上一页</a>习惯养成</h2>
<table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0"> 
  <tr>
    <td height="50" align="center" valign="top"><font class="font14"></font></td>
    <td height="50">今天是第【<s:property value="day" />】天，一天的时间已经过去了，让我们和孩子一起快乐的总结一下今天吧，一定要公平、公正，大家要遵守之前的约定！切记：以信盟约，以诚履约，以良心护约。</td>
  </tr>  
  
  <tr>
    <td height="50" align="center" valign="top">&nbsp;</td>
    <td height="50">要开始记录分数了吗？&nbsp;&nbsp;<a class="blue" style="font-weight:bold;" href="xgycinfo1"><input class="inputButton" type="button" value="开始"/></a></td>
  </tr>
</table>
		
   </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
