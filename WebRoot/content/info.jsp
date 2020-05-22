<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="contentInfo.typename" escape="false" />-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/common/js/area2013.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script>

</script>
</head>
<body>
<!--top-->
<div class="login-top">
	<div class="layout-control">
    	<a class="login-logo fl" href="/"></a>        
		<s:if test="memberInfo !=null && memberInfo.account !=null"><div class="fr login-r">欢迎您，<a class="blue" href="/"><s:property value="memberInfo.account" escape="false" /></a></div></s:if>
		<s:else><div class="fr login-r">已有龙爸爸帐号，<a class="blue" href="/">登录</a></div></s:else>
    </div>
</div>
<!--top end-->
<!--content-->
<div class="content1 layout-control">
    <!--left-->
    <div class="left content-left fl">
    	<ul class="ab-list">
        	<li <s:if test="contentInfo.type == 1">class='cur'</s:if>><a href="/aboutus">关于我们</a></li>
            <li <s:if test="contentInfo.type == 2">class='cur'</s:if>><a href="/contactus">联系我们</a></li>
            <li <s:if test="contentInfo.type == 3">class='cur'</s:if>><a href="/recruitment">招聘信息</a></li>
            <li <s:if test="contentInfo.type == 4">class='cur'</s:if>><a href="/link">友情链接</a></li>
            <li <s:if test="contentInfo.type == 5">class='cur'</s:if>><a href="/help">客服帮助</a></li>
            <li <s:if test="contentInfo.type == 6">class='cur'</s:if>><a href="/privacy">隐私说明</a></li>
        </ul>
    </div>
    <!--left end-->
    <div class="content-right fl">
   		<h2 class="second-title second-title5"><a class="blue" href="/"><<返回首页</a><s:property value="contentInfo.typename" escape="false" /></h2>
        <div class="about-content">
		<s:property value="contentInfo.content" escape="false" />
		</div>
   </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>