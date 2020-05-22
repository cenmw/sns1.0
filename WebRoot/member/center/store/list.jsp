<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-交易记录-龙爸爸成长在线</title>
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
    <!--left-->
    <s:action name="left" namespace="/membercenter" executeResult="true">
    </s:action>	
    <!--left end-->
    <!--right-->
   <div class="content-right fl">
   		<h2 class="second-title">交易记录</h2>
		
<table class="note-tabs1" width="740" border="0" cellspacing="0" cellpadding="0">
  
  <tr style="">
    <td width="450" height="50" align="left" valign="top" style="border-bottom: 1px solid #CCCCCC;"><font class="font14">交易类型</font></td>
	<td width="90" height="50" style="border-bottom: 1px solid #CCCCCC;">
	<font class="font14">交易金额</font>
    <td width="130" height="50" style="border-bottom: 1px solid #CCCCCC;">
	<font class="font14">交易时间</font>
	</td>
	<td width="70" height="50" style="border-bottom: 1px solid #CCCCCC;">
	<font class="font14">交易状态</font>
	</td>
  </tr>
  
  <s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
  <tr>
    <td height="50" align="left" valign="top" style="border-bottom: 1px solid #CCCCCC;"><font class="font14"><s:if test="type==1">消费（学习中心：<s:property value="learnInfo.title" escape="false"/>）</s:if><s:elseif test="type==2">消费（测试中心：<s:property value="topicInfo.title" escape="false"/>）</s:elseif><s:elseif test="type==3 || type==4">消费（<s:property value="title" escape="false"/>）</s:elseif><s:else>充值（支付编码：<s:property value="code" escape="false"/>）</s:else></font></td>
    <td  height="50" style="border-bottom: 1px solid #CCCCCC;"><s:property value="price" escape="false"/></td>
	<td  height="50" style="border-bottom: 1px solid #CCCCCC;"><s:date name="ctime" format="yyyy-MM-dd HH:mm:ss"/></td>
	<td  height="50" style="border-bottom: 1px solid #CCCCCC; text-align:center;"><s:if test="state==1"><span style="color:#007ED9">完成</span></s:if><s:else><span style="color:#FF0000">未完成</span></s:else></td>
  </tr>
  </s:iterator>  
	   
 </table>
  
  <div style="padding-bottom:10px; height:60px;"><s:property value="pageBean.gopagehtml" escape="false"/></div>
   
  </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>