<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-好友申请-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
//同意，拒绝
function updateMemberFriendAJAX(id,isagree){
   var url="/membercenter/updateMemberFriendAJAX?id="+id+"&isagree="+isagree;
		$.ajax( {
			type : "GET",
			url : url,
			success : function(data) {
				var _data = data;
				//alert(data);
				if(data == '1'){
				   window.location.reload();
				}					    
				//alert(data);			
			}
	});
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>好友申请</h2>
        <div class="note-title">
			<a class="write-note fr" href="javascript:fowardBack('/membercenter/afriendlist?backUrl=','<s:property value="backUrl"/>')"><input class="inputButton" type="button" value="添加好友"/></a>
			<a class="note-a" href="/membercenter/friendlist">我的好友</a>
			<a class="note-a note-cur" href="/membercenter/dfriendlist">好友申请</a>
        </div>
		
		<div class="myfriend-list">
        	<ul class="myfriend-infor">
            	<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1"> 
				<li>
                	<div class="friend-content">
                		<a class="friend-img fl" href="javascript:void(0)"><img src="<s:property value="#beanlist.rmemberInfo.avatar_small" escape="false" />" height="50" width="50" border="0" /></a>
                        <div class="friend-word fl">
                        	<p class="friend-name"><a class="blue" href="javascript:void(0)"><strong><s:property value="#beanlist.rmemberInfo.account" escape="false" /></strong></a></p>
                            <p class="vip-val"><strong><s:property value="#beanlist.rmemberInfo.sumscore" escape="false" /></strong></p>
                        </div>
                    </div>
                    <div class="friend-detail"><s:property value="#beanlist.content" escape="false" /></div>
					<div class="friend-detail"><a style="float:right; color:#999999; font-weight:normal; padding-right:15px;" href="javascript:updateMemberFriendAJAX('<s:property value="#beanlist.id" escape="false" />','2')">拒绝</a>&nbsp;&nbsp;<a style="float:right; color:#999999; font-weight:normal; padding-right:35px;" href="javascript:updateMemberFriendAJAX('<s:property value="#beanlist.id" escape="false" />','1')">同意</a></div>
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
