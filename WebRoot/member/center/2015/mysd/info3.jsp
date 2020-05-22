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
function checkcontentinfo(type,id){
    if(type == '1'){
	   $("#frm").attr("action","/membercenter/mysdinfo5?type="+type+"&dpage=3&id="+id);
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>我的疏导</h2>
<form id="frm" method="post" action="/membercenter/mysdinfo4?type=<s:property value="type" escape="false" />&dpage=2&id=<s:property value="memberDiary.id" escape="false" />">  	
<table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0"> 
  <tr>
    <td colspan="2" style="line-height:25px; padding-top:10px;" height="35" valign="top"><font class="font14">第二步：事情发生的原因是什么？您的表现是什么样的？(500字左右)</font></td>
  </tr>
  
  <tr>
    <td colspan="2" style="line-height:25px; padding-top:10px;" height="120">
	<s:textarea name="hzbx" id="hzbx" rows="8" cols="70"/></td>
  </tr>
  
  <tr>
    <td height="50" align="center" valign="top">&nbsp;</td>
    <td height="50"><span class="blue" style="cursor:pointer;" onclick="checkcontentinfo('<s:property value="type" escape="false" />','<s:property value="memberDiary.id" escape="false" />');">说出来了之后，感觉舒服了，办法我也能够找到的</span></td>
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
