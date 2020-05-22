<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的相片-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
function fixImage(i,w,h){ 
    var ow = i.width; 
    var oh = i.height; 
	if(ow>w || oh>h){
	    var rw = w/ow; 
		var rh = h/oh; 
		var r = Math.min(rw,rh); 
		if (w ==0 && h == 0){ 
			r = 1; 
		}else if (w == 0){ 
			r = rh<1?rh:1; 
		}else if (h == 0){ 
			r = rw<1?rw:1; 
		} 
		if (ow!=0 && oh!=0){ 
		i.width = ow * r; 
		i.height = oh * r; 
		}else{ 
		  var __method = this, args = $A(arguments); 
			window.setTimeout(function() { 
			  fixImage.apply(__method, args); 
			}, 200); 
		} 
		i.onload = function(){} 
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
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>查看相片</h2>
	
	<div class="vedio-list">
        	<div class="pl-title">
            	<h1><s:property value="memberPhoto.title" escape="false" /></h1>
                <p class="title-infor"><span><s:date name="memberPhoto.ctime" format="yyyy-MM-dd"/></span><span>作者：<s:property value="memberPhoto.memberInfo.account" escape="false" /></span><span>点击量：<s:property value="memberPhoto.viewnumber" escape="false" /></span></p>
            </div>
            <div class="pl-content"><img style="max-width:710px;" onload="fixImage(this,710)" id="pic_id" src="<s:property value="memberPhoto.picpath" escape="false" />"></div>
            <div class="fr pl-zan"><jsp:include page="/member/center/photo/inc/fxinfo.jsp"></jsp:include></div>
            <div class="clear"></div>
            
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
			
			<s:action name="inccomment" namespace="/membercenter" executeResult="true">
				<s:param name="type" value="4"></s:param>
				<s:param name="cid" value="memberPhoto.id"></s:param>
				<s:param name="backUrl" value="backUrl"></s:param>
			</s:action>
	
        </div>
		
  </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
<input type="hidden" name="lnkprev" id="lnkprev" value="/membercenter/showphotoinfo?id=<s:property value="memberPhoto.id" escape="false" />&ln=lnkprev&backUrl=<s:property value="backUrl" escape="false" />" />
<input type="hidden" name="lnknext" id="lnknext" value="/membercenter/showphotoinfo?id=<s:property value="memberPhoto.id" escape="false" />&ln=lnknext&backUrl=<s:property value="backUrl" escape="false" />" />

<script language="javascript">
 var new_name = '';
 curprev = "/common/images/pre.cur";
 curnext = "/common/images/next.cur";
 var bigimg = $("#pic_id");
 function showCursor(e) {
    var o = bigimg;
    if (e.target != o.get(0)) return;
        var offset = o.offset();
        if (e.clientX < offset.left + o.attr("width") / 2) {
            if (o.attr("onside") != 0) {
                 o.css("cursor", "url(" + curprev + "),auto");
                 o.attr("onside", 0);
            }
        }
        else {
            if (o.attr("onside") != 1) {
                 o.css("cursor", "url(" + curnext + "),auto");
                 o.attr("onside", 1);
             }
        }
 }				
                      
     bigimg.mousemove(function(e) { showCursor(e); });
     bigimg.click(function(event) { showCursor(event); window.location.href = $(this).attr("onside") == 0 ? $("#lnkprev").val() : $("#lnknext").val() });
</script>