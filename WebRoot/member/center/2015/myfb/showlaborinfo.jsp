<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的活动-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
//同意，拒绝
function addtosignup(cid){
   var url="/membercenter/addToSignupAJAX?cid="+cid;
		$.ajax( {
			type : "GET",
			url : url,
			success : function(data) {
				if(data == '1'){
				   alert("报名成功");
				   window.location.reload();
				}else if(data == '0'){
				   alert("此活动已经结束或还未审核通过");
				}else if(data == '2'){
				   alert("您已经成功报名，请不要重复报名");
				}			
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
   <div class="content-right fl">
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>查看活动</h2>
  
       <div class="vedio-list">
        	<div class="pl-title">
            	<h1><s:property value="laborInfo.title" escape="false" /></h1>
                <p class="title-infor"><span>活动时间：<s:date name="laborInfo.starttime" format="yyyy-MM-dd"/> 至 <s:date name="laborInfo.endtime" format="yyyy-MM-dd"/></span><span>发布者：<s:property value="laborInfo.memberInfo.account" escape="false" /></span><span>状态：<s:if test="laborInfo.state==0">待审核</s:if><s:elseif test="laborInfo.state==1">审核通过</s:elseif></span><span>点击量：<s:property value="laborInfo.viewnumber" escape="false" /></span></p>
            </div>
            <div class="pl-content"><s:property value="laborInfo.content" escape="false" /></div>
			
			<!--分享到-->
			<div style="width:710px; height:30px; margin:15px auto;">
			<!-- Baidu Button BEGIN -->
			<div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
			<span style="color:#000000;" class="bds_more">分享到：</span>
			<a class="bds_qzone"></a>
			<a class="bds_tsina"></a>
			<a class="bds_tqq"></a>
			<a class="bds_renren"></a>
			<a class="bds_t163"></a>
			<a class="shareCount"></a>
			</div>
			<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6883689" ></script>
			<script type="text/javascript" id="bdshell_js"></script>
			<script type="text/javascript">
			document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
			</script>
			<!-- Baidu Button END -->
			</div>
			<!--分享到 end-->
			
			<s:if test="laborInfo.isend==0">
			<div class="pl-content"><s:property value="laborInfo.propaganda" escape="false" /></div>
			</s:if>
			
            <div class="clear"></div>
            <!-- 
            <div style="height:30px; text-align:center;"><s:if test="laborInfo.state==1 && laborInfo.isend == 1 && laborInfo.memberInfo.id != memberInfo.id"><a class="write-note" href="javascript:addtosignup('<s:property value="laborInfo.id" escape="false" />')"><img src="/member/images/wybm.png" height="26" width="83" border="0" /></a></s:if><s:if test="laborInfo.state==1 && laborInfo.replycount > 0 && laborInfo.memberInfo.id == memberInfo.id"><a class="write-note fr" href="javascript:fowardBack('/membercenter/sendmessageinfo?rids=<s:property value="rids" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><img src="/member/images/qfxx.png" height="26" width="83" border="0" /></a></s:if></div>
			-->
			<s:if test=" laborreplylist.size()>0">
			<div class="pl-list-cn">
                	<s:iterator value="laborreplylist" id="beanlist" status="beanlist1"> 
					<!--1-->
                    <div class="pl-list">
                        <a class="pl-img fl" href="javascript:void(0)"><img src="<s:property value="memberInfo.avatar_small" />" height="31" width="31" border="0"></a>
                        <div class="pl-word fl">
                            <div class="user-publish">
                                <a class="blue" href="javascript:void(0)"><s:property value="memberInfo.account" /> :</a> 报名参加
                            </div>
                             <div class="user-talk">
                                <div class="gray"><s:date name="#beanlist.ctime" format="yyyy-MM-dd HH:mm"/></div>
                            </div>
                        </div>
                        <div class="clear"></div>
            		</div>
                    </s:iterator>				   
                </div>
                <!--评论内容 end-->
            </div>
			</s:if>
			<div class="fr pl-zan" style="height:30px;width: 710px; text-align:right;">报名人数：<s:property value="laborInfo.replycount" escape="false" /></div>	
        </div>	
  </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>