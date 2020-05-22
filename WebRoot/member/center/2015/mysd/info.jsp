<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的疏导-龙爸爸成长在线</title>
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
   		<h2 class="second-title"><a class="blue" href="/membercenter/mysdlist"><<返回上一页</a>我的疏导</h2>
<table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0"> 
  <tr>
    <td height="50" align="center" valign="top"><font class="font14"></font></td>
    <td height="50">欢迎来加油疏导站，情绪的出现，更多使我们迷失了自己，重新找回失去的自己，等于为自己重新加了一次油，使自己的能量重新恢复，能够更好的生活。
如果您这次的事情与孩子有关系，请点击“与孩子有关”的按钮进行，如果是只是自己的事情，请点击“与自己有关”的按钮就行。</td>
  </tr>  
  
  <tr>
    <td height="50" align="center" valign="top">&nbsp;</td>
    <td height="50"><a class="blue" style="font-weight:bold;" href="mysdinfo1?type=0">与孩子有关</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="blue" style="font-weight:bold;" href="mysdinfo1?type=1">与自己有关</a></td>
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
