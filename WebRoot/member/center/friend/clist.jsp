<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-<s:if test="memberInfo.type==1">我的小组</s:if><s:else>我的机构</s:else>-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
function delfriend(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/frienddelete?ids="+id+"&type=1";
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a><s:if test="memberInfo.type==1">我的小组</s:if><s:else>我的机构</s:else></h2>
		<s:if test="memberInfo.type==0">
		<div class="note-title">
            <a class="write-note fr" href="javascript:fowardBack('/membercenter/cafriendlist?backUrl=','<s:property value="backUrl"/>')"><input class="inputButton" type="button" value="添加机构"/></a>
            <a class="note-a note-cur" href="/membercenter/cfriendlist">我的机构</a>
        </div>
		</s:if>
        <div class="myfriend-list">
        	<ul class="myfriend-infor">
            	
				<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1"> 
				<li>
                	<div class="friend-content">
                		<a class="friend-img fl" href="javascript:void(0)"><img src="<s:property value="#beanlist.rmemberInfo.avatar_small" escape="false" />" height="50" width="50" border="0" /></a>
                        <div class="friend-word fl">
                        	<p class="friend-name"><a class="blue" href="javascript:void(0)"><strong><s:property value="#beanlist.rmemberInfo.account" escape="false" /></strong></a></p>
                            <div class="vip-val"><strong><s:property value="#beanlist.rmemberInfo.sumscore" escape="false" /></strong><s:if test="memberInfo.type==0"><span class="offer-contact"><s:property value="#beanlist.rmemberInfo.telphone" escape="false" /></span></s:if></div>
                        </div>
                    </div>
					
					<div class="friend-detail friend-bottom"><font class="color99">最新动态：</font><a class="blue" href="javascript:fowardBack('<s:property value="#beanlist.rmemberStatus.url" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.rmemberStatus.classname" escape="false" /></a></div>
					<s:if test="memberInfo.type==0">
                    <div class="jg-infor">
                    	<font class="color99">机构介绍:</font><span><s:if test="#beanlist.rmemberInfo.introduction !=null && #beanlist.rmemberInfo.introduction.length()>60"><s:property value="#beanlist.rmemberInfo.introduction.substring(0,60)" escape="false" />...<a style="color:#999999; font-weight:normal;" href="javascript:showfriend('<s:property value="#beanlist.rmemberInfo.id" escape="false" />')">详情</a></s:if><s:else><s:property value="#beanlist.rmemberInfo.introduction" escape="false" /></s:else></span>
                    </div>
					</s:if>
					
					<s:if test="#beanlist.fid !=24 && #beanlist.fid != 25">
					<div class="friend-detail"><a style="float:right; color:#999999; font-weight:normal; padding-right:5px;" href="javascript:delfriend('<s:property value="#beanlist.id" escape="false" />')">删除</a></div>
					</s:if>
                </li>				
				</s:iterator>
                
            </ul>
            <div class="clear"></div>
			<s:property value="pageBean.gopagehtml" escape="false"/>
        </div>
   </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
