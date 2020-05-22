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
<script type="text/javascript">
function delfriend(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/frienddelete?ids="+id+"&type=0";
   }
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
   		<h2 class="second-title"><a class="blue" href="/membercenter/zhoulist"><<返回上一页</a>我的52周</h2>
<table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0"> 
  <tr>
    <td height="50" align="center" valign="top"><font class="font14"></font></td>
    <td height="50">欢迎您选择52周成长，在52周的时间里会有感动，会有收获，更多是每天给自己一个宁静的时间，重新审视自己的生活，平复内心的世界，收获阳光幸福的生活。</td>
  </tr>  
  
  <tr>
    <td height="50" align="center" valign="top">&nbsp;</td><!-- <a class="blue" style="font-weight:bold;" href="zhoulist1?type=1">为自己</a> -->
    <td height="50">为自己，开始的是一个关于自己的成长路程；&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="blue" style="font-weight:bold;" href="zhouinfo1?type=0">为家庭</a>，开始的是一个关于孩子以及家庭成长路程。</td>
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
