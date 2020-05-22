<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-训练中心-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
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

function checkcontentinfo(){
	var content=$.trim($("#content").val());
	if(content==""){
		alert("内容不能为空");
		return false;
	}else if(zjlen(content)>600){
	    alert("内容不能超过300个汉字长度");
	    document.getElementById("content").focus();
		return false;
	}
		
	return true;
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
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>训练中心</h2>
		<div class="note-title">
        	<s:if test="memberInfo.type==0"><a class="write-note fr" href="javascript:fowardBack('/membercenter/myzyinfo?backUrl=','<s:property value="backUrl"/>')"><img src="/member/images/btn181.png" height="26" width="83" border="0" /></a></s:if>
        	<a class="note-a note-cur" href="/membercenter/consultclasslist">训练中心</a>
			<s:if test="memberInfo.type==0">
			<a class="note-a" href="/membercenter/myzylist">我的作业</a>
			<a class="note-a" href="/membercenter/myzyflist">好友作业</a>
			<a class="note-a" href="javascript:fowardBack('/membercenter/myzyinfo?backUrl=','<s:property value="backUrl"/>')">龙爸爸私教</a>
			</s:if>
        </div>
		
		<div class="arc-list">
		    <s:iterator value="consultclasslist" id="beanlist" status="beanlist1"> 
			<a <s:if test="#beanlist.id == consultInfo.cid">class="cur"</s:if> href="javascript:fowardBack('/membercenter/consultalllist?searchcid=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.title" escape="false" /></a>
			</s:iterator>        	
        </div>
		
		<div class="wenti-cn mar-t20">
        	<h3><s:property value="consultInfo.title" escape="false" /></h3>
            <div class="wenti-fen">
            	<span class="color99" style="margin-right:20px;">提问者悬赏：<s:property value="consultInfo.score" escape="false" />分</span> <span class="color99">提问者：</span><a class="blue" href="javascript:void(0)"><s:property value="consultInfo.memberInfo.account" escape="false" /></a>
            </div>
<script type="text/javascript">
function savereply(replyid){
    if(confirm("确定同意该回复吗？")){
	   document.getElementById("replyid").value=replyid;
	   document.getElementById("replyfrm").submit();
	}    
}
</script>
<form method="post" id="replyfrm" name="replyfrm" action="/membercenter/updatereply">
	<input type="hidden" name="backUrl" value="<s:property value="backUrl"/>"/>
	<input type="hidden" name="id" value="<s:property value="consultInfo.id"/>"/>
	<input type="hidden" id="replyid" name="replyid" value=""/>
</form>
  
  <s:if test="memberInfo.id != consultInfo.memberInfo.id">
  <form method="post" id="savereply" name="replyfrm" action="/membercenter/savereply" onsubmit="return checkcontentinfo()">
	<input type="hidden" name="backUrl" value="<s:property value="backUrl"/>"/>
	<input type="hidden" name="cid" value="<s:property value="consultInfo.id"/>"/>
            <p class="wenti-tiwen blue">我要回答</p>
            <textarea class="huida1" name="content" id="content"></textarea>
            <div class="huida-btn"><input type="submit" value="" /></div>
  </form>
  </s:if>	
  
  <!--分享到-->
			<div style="height:30px; margin:auto;">
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
					
            <div class="huida-list">
            	<h4><s:property value="consultreplylist.size()"/>条回答</h4>
                <!--1-->
                <s:if test="consultreplylist != null && consultreplylist.size()>0">
				<s:iterator value="consultreplylist" id="sv" status="sv1"> 
				<div class="huida-cn1">
                	<h5><span class="fl"><font class="color99">回答者：</font><a class="blue" href="javascript:void(0)"><s:property value="#sv.memberInfo.account"/></a></span><span class="fr color99"><s:date name="#sv.ctime" format="yyyy-MM-dd HH:mm:ss"/></span></h5>
                    <div class="huida-c"><s:property value="#sv.content" escape="false" /></div>
                    <s:if test="#sv.isagree == 1"><div class="huida-zan">已认可该回答</div></s:if>
   					<s:elseif test="consultInfo.replyisgreecount == 0 && #sv.mid == memberInfo.id">
					<div class="huida-zan"><a class="blue" href="javascript:savereply('<s:property value="#sv.id"/>')">同意</a></div></s:elseif>
                </div>
				</s:iterator>
				</s:if>
                
            </div>
        </div>
	
  </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>