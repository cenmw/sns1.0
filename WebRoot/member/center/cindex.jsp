<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
//判断字节长度
function zjlen(s) {
	var l = 0;
	var a = s.split("");
	for (var i=0;i<a.length;i++) {
		if (a[i].charCodeAt(0)<299) {
			l++;
		} else {
			l+=2;
		}
	}
	return l;
}

$(function(){
	$("#mcontent").keyup(function(){
		var topic_len = zjlen($("#mcontent").val());
	    if(topic_len>60){
	    	$("#mcontentclenid").html("标题不能超过30个汉字长度");
	    }else if(topic_len==0){
		    $("#mcontentclenid").html("");
		}else{
		    $("#mcontentclenid").html("");
		}
	    $("#mcontentlenid").html(parseInt(topic_len/2)+"/"+30);
	});
    $("#mcontent").focus();
});

function pubmood(){
   var no = $("#mcontent").val();
   var topic_len = zjlen(no);
   if(topic_len>60 || topic_len==0){
	    $("#mcontent").focus();
   }else{ 
     $("#mcontent").val("");  
     $.ajax({
			type:'post',
			url:'/membercenter/moodsave',
			data:'no='+encodeURIComponent(no),
			success:function(status){
			if(status==1){
			   alert("发表成功");
			}
			}
	 });		
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
<div class="content layout-control">
    <!--left-->
    <s:action name="left" namespace="/membercenter" executeResult="true">
		<s:param name="isindex" value="1"></s:param>
    </s:action>
    <!--left end-->
    <!--middle-->
    <div class="middle fl">    
    <!--个人中心分类-->
    <div class="infor-sorts mar-t20">
    	<a class="cur" href="javascript:void(0)">好友动态</a>
    </div>
    <!--个人中心分类end-->
    <!--分类列表-->
	<div id="stateid">
    <s:iterator value="fstatuslist" id="beanlist" status="beanlist1">  
    <!--<s:property value="#beanlist1.index+1" escape="false"/>-->
    <div class="person-sorts mar-t20">
    	<div class="sorts-img fl">
        	<img src="<s:property value="#beanlist.memberInfo.avatar_small" escape="false"/>" height="50" width="50" border="0" />
        </div>
        <div class="sort-content fl">
        	<div class="user-publish">
            <a class="blue"><s:property value="#beanlist.memberInfo.account" escape="false"/> :</a> <s:property value="#beanlist.content" escape="false"/>
            </div>
            <div class="user-talk">
            <div class="fl gray"><s:date name="#beanlist.ctime" format="yyyy-MM-dd HH:mm"/></div>
            <div class="fr">
			<s:if test="#beanlist.type == 1">
			    <jsp:include page="/member/center/mood/inc/fxlist.jsp"></jsp:include>
			</s:if>
			<s:elseif test="#beanlist.type == 2">
			    <jsp:include page="/member/center/blog/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 3">
			    <jsp:include page="/member/center/content/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 4">
			    <jsp:include page="/member/center/photo/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 5">
			    <jsp:include page="/member/center/vedio/inc/fxlist.jsp"></jsp:include>
			</s:elseif>	
			</div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <!--<s:property value="#beanlist1.index+1" escape="false"/> end-->
    </s:iterator>
	</div>
    <!--分类列表 end-->
    <div class="loading-more" onclick="getMoreStateAjax('<s:property value="type" escape="false" />','stateid')">更多好友动态</div>
    </div>
    <!--middle end-->
    <!--right-->
    <div class="right fr">
    	
        <s:if test="hotcontentlist != null && hotcontentlist.size()>0">
        <!--热门转帖-->
        <div class="hot-news mar-t20">
        	<h2 class="add-title"><span>热门转帖</span></h2>
            <ul class="hot-news-list mar-t10">
			    <s:iterator value="hotcontentlist" id="beanlist" status="beanlist1"> 
            	<li><a target="_blank" href="<s:property value="#beanlist.url" escape="false" />"><s:property value="#beanlist.title" escape="false" /></a></li>
                </s:iterator>
            </ul>
        </div>
        <!--热门转帖 end-->
		</s:if>
        <p class="ad1 mar-t10"><img src="/member/images/pic4.jpg" height="259" width="201" /></p>
        <s:if test="hotccontentlist != null && hotccontentlist.size()>0">
		<!--热点推荐-->
        <div class="hot-news mar-t20">
        	<h2 class="add-title"><span>热点推荐</span></h2>
            <ul class="hot-news-list mar-t10">
            	<s:iterator value="hotccontentlist" id="beanlist" status="beanlist1"> 
            	<li><a target="_blank" href="<s:property value="#beanlist.url" escape="false" />"><s:property value="#beanlist.title" escape="false" /></a></li>
                </s:iterator>
            </ul>
        </div>
        <!--热点推荐 end-->
		</s:if>
        <p class="ad1 mar-t10"><img src="/member/images/pic5.jpg" height="89" width="199" /></p>
    </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>