<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-添加好友-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
//添加好友
function addfriend(fid,type){
	$.fn.popup({iframe:true,url:'/membercenter/addfriend?fid='+fid+'&type='+type,type:3,title:'添加好友申请',width:400,height:200});
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>添加好友</h2>
        <div class="note-title">
            <a class="write-note fr" href="javascript:fowardBack('/membercenter/afriendlist?backUrl=','<s:property value="backUrl"/>')"><input class="inputButton" type="button" value="添加好友"/></a>
            <a class="note-a" href="/membercenter/friendlist">我的好友</a>
            <a class="note-a" href="/membercenter/dfriendlist">好友申请</a>
        </div>
		
		<div class="myfriend-list">
        	<ul class="myfriend-infor">
            	<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1"> 
				<li>
                	<div class="friend-content">
                		<a class="friend-img fl" href="javascript:void(0)"><img src="<s:property value="#beanlist.avatar_small" escape="false" />" height="50" width="50" border="0" /></a>
                        <div class="friend-word fl">
                        	<p class="friend-name"><a class="blue" href="javascript:void(0)"><strong><s:property value="#beanlist.account" escape="false" /></strong></a></p>
                            <p class="vip-val"><strong><s:property value="#beanlist.sumscore" escape="false" /></strong></p>
                        </div>
                    </div>
                    <div class="friend-detail">最新动态：<a class="blue" href="javascript:fowardBack('<s:property value="#beanlist.memberStatus.url" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.memberStatus.classname" escape="false" /></a></div>
					<div class="friend-detail"><span class="add-a" onclick="addfriend('<s:property value="#beanlist.id" escape="false" />','0')"></span></div>
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
