<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-添加机构-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
//对企业加关注
function addMemberFriendAJAX(fid){
   var url="/membercenter/addMemberFriendAJAX?fid="+fid;
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

//查看企业好友
function showfriend(fid){
	$.fn.popup({iframe:true,url:'/membercenter/showfriend?fid='+fid,type:3,title:'查看企业好友',width:400,height:400});
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>添加机构</h2>
		<div class="note-title">
			<a class="write-note fr" href="javascript:fowardBack('/membercenter/cafriendlist?backUrl=','<s:property value="backUrl"/>')"><input class="inputButton" type="button" value="添加机构"/></a>
			<a class="note-a" href="/membercenter/cfriendlist">我的机构</a>
        </div>
		
        <div class="myfriend-list">
        	<ul class="myfriend-infor">
            	
				<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1"> 
				<li>
                	<div class="friend-content">
                		<a class="friend-img fl" href="javascript:void(0)"><img src="<s:property value="#beanlist.avatar_small" escape="false" />" height="50" width="50" border="0" /></a>
                        <div class="friend-word fl">
                        	<p class="friend-name"><a class="blue" href="javascript:void(0)"><strong><s:property value="#beanlist.account" escape="false" /></strong></a></p>
                            <div class="vip-val"><strong><s:property value="#beanlist.sumscore" escape="false" /></strong><span class="offer-contact"><s:property value="#beanlist.telphone" escape="false" /></span></div>
                        </div>
                    </div>
                    <div class="friend-detail friend-bottom"><font class="color99">最新动态：</font><a class="blue" href="javascript:fowardBack('<s:property value="#beanlist.rmemberStatus.url" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.rmemberStatus.classname" escape="false" /></a></div>
                    <div class="jg-infor">
                    	<font class="color99">机构介绍:</font><span><s:if test="#beanlist.rmemberInfo.introduction !=null && #beanlist.rmemberInfo.introduction.length()>70"><s:property value="#beanlist.rmemberInfo.introduction.substring(0,70)" escape="false" />...<a style="float:right; color:#999999; font-weight:normal;" href="javascript:showfriend('<s:property value="#beanlist.rmemberInfo.id" escape="false" />')">详情</a></s:if><s:else><s:property value="#beanlist.rmemberInfo.introduction" escape="false" /></s:else></span>
                    </div>
					<div class="friend-detail"><a style="float:right; color:#999999; font-weight:normal; padding-right:5px;" href="javascript:addMemberFriendAJAX('<s:property value="#beanlist.id" escape="false" />')">加关注</a></div>
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
