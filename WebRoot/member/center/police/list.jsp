<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-拘留记录-龙爸爸成长在线</title>
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>拘留记录</h2>
        <div class="note-title">
        	<a class="note-a note-cur" href="/membercenter/policelist">拘留记录</a>
			<a class="note-a" href="/membercenter/fpolicelist">好友拘留记录</a>
        </div>
        <div class="write-list">
        	
			<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
			<dl>
            	<dt>
                	<h3 class="write-title"><span class="note-editor"><a class="blue" target="_blank" href="<s:property value="#beanlist.curl"/>">查看原文</a></span><a class="blue" href=""><s:if test="#beanlist.type==2">（日志）</s:if><s:elseif test="#beanlist.type==3">（文集）</s:elseif><s:elseif test="#beanlist.type==4">（照片）</s:elseif><s:elseif test="#beanlist.type==5">（视频）</s:elseif><s:property value="#beanlist.content" escape="false" /></a></h3>
                    <p class="color99 mar-t5">拘留时间：<s:date name="#beanlist.starttime" format="yyyy-MM-dd HH:mm"/> 至 <s:date name="#beanlist.endtime" format="yyyy-MM-dd HH:mm"/><s:if test="#beanlist.state == 2"><span class="mar-l10">释放时间：<s:date name="#beanlist.sftime" format="yyyy-MM-dd HH:mm"/></span></s:if></p>
                </dt>
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