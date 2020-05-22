<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-活动中心-龙爸爸成长在线</title>
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>活动中心</h2>
        <div class="note-title">
		<s:if test="memberInfo.type==1"><a class="write-note fr" href="javascript:fowardBack('/membercenter/laborinfo?backUrl=','<s:property value="backUrl"/>')"><img src="/member/images/xxhd.png" height="26" width="83" border="0" /></a></s:if>
        	<a class="note-a note-cur" href="/membercenter/laboralllist">活动中心</a><a class="note-a" href="/membercenter/laborlist">参加的活动</a>
        </div>
        <div class="write-list">
        	
			<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
			<dl>
            	<dt>
                	<h3 class="write-title"><a class="blue" href="javascript:fowardBack('/membercenter/showlaborinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.title" escape="false" /></a></h3>
                    <p class="color99 mar-t5">发布企业：<s:property value="memberInfo.account" escape="false" />&nbsp;&nbsp;活动时间：<s:date name="#beanlist.starttime" format="yyyy-MM-dd"/> 至 <s:date name="#beanlist.endtime" format="yyyy-MM-dd"/><span class="mar-l10">状态：<s:if test="#beanlist.state==0">待审核</s:if><s:elseif test="#beanlist.state==1">审核通过</s:elseif></span><span class="mar-l10"><s:if test="#beanlist.state==1">报名人数：<s:property value="#beanlist.replycount" escape="false" /></s:if></span></p>
                </dt>
                <dd>
                	<div class="note-detail"><a href="javascript:fowardBack('/membercenter/showlaborinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.description" escape="false" /></a></div>
                    
					
                </dd>
            </dl>
			</s:iterator>            
			
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