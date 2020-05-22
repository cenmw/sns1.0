<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的咨询-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
function delconsult(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/consultdelete?ids="+id;
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
   <div class="content-right fl">
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>我的咨询</h2>
        <div class="note-title">
		<a class="write-note fr" href="javascript:fowardBack('/membercenter/consultinfo?backUrl=','<s:property value="backUrl"/>')"><img src="/member/images/btn18.png" height="26" width="83" border="0" /></a>
        	<a class="note-a" href="/membercenter/consultclasslist">咨询中心</a>
			<a class="note-a note-cur">我的咨询</a>
			<a class="note-a" href="/membercenter/consultflist">好友咨询</a>
			<a class="note-a" href="javascript:fowardBack('/membercenter/consultinfo?backUrl=','<s:property value="backUrl"/>')">我要咨询</a>
        </div>
        <div class="write-list">
        	
			<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
			<dl>
            	<dt>
                	<h3 class="write-title"><span class="note-editor"><a class="blue" href="javascript:fowardBack('/membercenter/consultinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')">编辑</a>┊<a class="blue" href="javascript:delconsult('<s:property value="#beanlist.id" escape="false" />')">删除</a></span><a class="blue" href="javascript:fowardBack('/membercenter/showconsultinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.title" escape="false" /></a></h3>
                    <p class="color99 mar-t5">
                    	<s:date name="#beanlist.ctime" format="yyyy-MM-dd HH:mm"/>
                    	<span class="mar-l10">分类：<s:property value="#beanlist.classname" escape="false" /></span>
                    	<span class="mar-l10"><s:property value="#beanlist.memberInfo.account" escape="false" />　权限：<s:if test="#beanlist.qx==1">仅好友解答</s:if><s:elseif test="#beanlist.qx==2">仅龙爸爸解答</s:elseif><s:else>所有人解答</s:else></span>
                    </p>
                </dt>
                <dd>
                	<div class="note-detail"><a href="javascript:fowardBack('/membercenter/showconsultinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.description" escape="false" /></a></div>
                    					
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