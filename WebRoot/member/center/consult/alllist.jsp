<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-训练中心-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
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
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>'"><<返回上一页</a>训练中心</h2>
        <div class="note-title">
		<s:if test="memberInfo.type==0"><a class="write-note fr" href="javascript:fowardBack('/membercenter/myzyinfo?backUrl=','<s:property value="backUrl"/>')"><img src="/member/images/btn181.png" height="26" width="83" border="0" /></a></s:if>
        	<a class="note-a note-cur" href="/membercenter/consultclasslist">训练中心</a>
			<s:if test="memberInfo.type==0">
			<a class="note-a" href="/membercenter/myzylist">我的作业</a>
			<a class="note-a" href="/membercenter/myzyflist">好友作业</a>
			<a class="note-a" href="javascript:fowardBack('/membercenter/myzyinfo?backUrl=','<s:property value="backUrl"/>')">龙爸爸私教</a>
			</s:if>
        </div>
		<div class="arc-list">
		    <s:iterator value="consultclasslist" id="beanlist" status="beanlist1"> 
			<a <s:if test="#beanlist.id == searchcid">class="cur"</s:if> href="javascript:fowardBack('/membercenter/consultalllist?searchcid=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.title" escape="false" /></a>
			</s:iterator>        	
        </div>
		
        <div class="arc-cn-list">
          <ul>	
			<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1"> 
			<li><a class="span-l blue fl" href="javascript:fowardBack('/membercenter/showconsultinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.title" escape="false" />　(<s:property value="#beanlist.memberInfo.account" escape="false" />:<s:if test="#beanlist.qx==1">仅好友解答</s:if><s:elseif test="#beanlist.qx==2">仅龙爸爸解答</s:elseif><s:else>所有人解答</s:else>)</a><span class="span-m fl"><s:property value="#beanlist.replycount" escape="false" />条回答</span><span class="span-r fr"><s:date name="#beanlist.ctime" format="yyyy-MM-dd HH:mm"/></span></li> 
			</s:iterator>            
			
			</ul>
			<s:property value="pageBean.gopagehtml" escape="false"/>
		    <div class="clear" style="padding-top:10px;"></div>
			
        </div>
		
   </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>