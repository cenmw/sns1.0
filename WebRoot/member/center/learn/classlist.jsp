<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-学习中心-龙爸爸成长在线</title>
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>学习中心</h2>
        <ul class="xueli-list">
		    
			<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1"> 
        	<li>
            	<p class="xl-img fl"><a href="javascript:fowardBack('/membercenter/learnlist?searchcid=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><img src="<s:property value="#beanlist.picpath" escape="false" />" width="100" height="100" border="0" /></a></p>
                <div class="xl-word fr">
                	<h3><a class="blue" href="javascript:fowardBack('/membercenter/learnlist?searchcid=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.title" escape="false" /></a></h3>
                    <p class="xl-word-h"><s:property value="#beanlist.description" escape="false" /></p>
                    <p class="xl-word-enter"><a class="blue" href="javascript:fowardBack('/membercenter/learnlist?searchcid=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')">进入>></a></p>
                </div>
            </li>
			</s:iterator> 
			
        </ul>
		<s:property value="pageBean.gopagehtml" escape="false"/>
		<div class="clear" style="padding-top:10px;"></div>
		
   </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>