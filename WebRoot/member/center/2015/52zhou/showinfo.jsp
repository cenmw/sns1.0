<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的52周-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
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
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>查看52周</h2>
  
       <div class="vedio-list">
        	<div class="pl-title">
            	<h1><s:if test="memberDiary52.rcid>0">[转]</s:if><s:date name="memberDiary52.ptime" format="yyyy-MM-dd"/></h1>
                <p class="title-infor"><span><s:date name="memberDiary52.ctime" format="yyyy-MM-dd"/></span><span>作者：<s:property value="memberDiary52.memberInfo.account" escape="false" /></span><span>点击量：<s:property value="memberDiary52.viewnumber" escape="false" /></span></p>
            </div>
            <div class="pl-content">
			<table class="note-tabs1" width="710" border="0" cellspacing="0" cellpadding="0">
			  
			  <tr style="padding-bottom:10px;">
				<td width="189" height="50" align="center"><font class="font14">地点：</font></td>
				<td width="521" height="50" style="line-height:25px;"><s:property value="memberDiary52.address" escape="false" /></td>
			  </tr>
			  
			  <tr style="padding-bottom:10px;">
				<td width="189" height="50" align="center"><font class="font14">天气：</font></td>
				<td width="521" height="50" style="line-height:25px;"><s:property value="memberDiary52.weather" escape="false" /></td>
			  </tr>
			  
			  <tr style="padding-bottom:10px;">
				<td width="289" height="50" align="center"><font class="font14">第【<s:property value="memberDiary52.day" escape="false" />】天，发生的事情：</font></td>
				<td width="421" height="50" style="line-height:25px;"><s:property value="memberDiary52.fssq" escape="false" /></td>
			  </tr>	
			 
			  <tr style="padding-bottom:10px;">
				<td width="289" height="50" align="center"><font class="font14" style="line-height:26px;">感受及当时的表现：</font></td>
				<td width="421" height="50" style="line-height:25px;"><s:property value="memberDiary52.wdgs" escape="false" /></td>
			  </tr>	
			  			  
			</table>			

			</div>
            <div class="fr pl-zan"><jsp:include page="/member/center/diary/inc/zhouinfo.jsp"></jsp:include></div>
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
				<s:param name="type" value="2"></s:param>
				<s:param name="cid" value="memberDiary52.id"></s:param>
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