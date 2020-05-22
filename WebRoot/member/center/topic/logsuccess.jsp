<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-测试中心-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
function getParamsOfShareWindow(width, height) {
	return ['toolbar=0,status=0,resizable=1,width=' + width + ',height=' + height + ',left=',(screen.width-width)/2,',top=',(screen.height-height)/2].join('');
}

$(function(){
	var link = encodeURIComponent(document.location); // 文章链接
	var title = encodeURIComponent(document.title.substring(0,76)); // 文章标题
	var source = encodeURIComponent('龙爸爸成长在线'); // 网站名称
	var windowName = 'share'; // 子窗口别称
	var site = 'http://www.longbaba.com.cn/'; // 网站链接
	$('#kaixin001-share').click(function() {
		var url = 'http://www.kaixin001.com/repaste/share.php?rurl=' + link + '&rcontent=' + link + '&rtitle=' + title;
		var params = getParamsOfShareWindow(540, 342);
		window.open(url, windowName, params);
	});
	$('#sina-share').click(function() {
		var url = 'http://v.t.sina.com.cn/share/share.php?url=' + link + '&title=' + title;
		var params = getParamsOfShareWindow(607, 523);
		window.open(url, windowName, params);
	});
	$('#netease-share').click(function() {
		var url = 'http://t.163.com/article/user/checkLogin.do?link=' + link + 'source=' + source + '&info='+ title + ' ' + link;
		var params = getParamsOfShareWindow(642, 468);
		window.open(url, windowName, params);
	});
	$('#qqzone-share').click(function(){
		var url = 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+ link+ '&title='+title;
		var params = getParamsOfShareWindow(642, 468);
		window.open(url, windowName, params);
	});
	$('#renren-share').click(function() {
		var url = 'http://share.renren.com/share/buttonshare?link=' + link + '&title=' + title;
		var params = getParamsOfShareWindow(626, 436);
		window.open(url, windowName, params);
	});
	$('#feixin-share').click(function() {
		var url = 'http://space.feixin.10086.cn/api/share?source=' + source + '&title=' + title+'&url='+link;
		var params = getParamsOfShareWindow(626, 436);
		window.open(url, windowName, params);
	});
});
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
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>测试课程结果</h2>       
		
		<div class="arc-list">
			<span class="title00">测试内容：</span>   
			<span style="color:#396298"><s:property value="topicInfo.title" escape="false" /></span>		     	
        </div>
						
		<div class="arc-list">
			<span class="title00">测试结果：</span>   
			<span style="color:#396298">正确题目数量：</span><span style="color:#FF0000"><s:property value="topicLog.cnumber" escape="false" />　　</span>
			<span style="color:#396298">错误题目数量：</span><span style="color:#FF0000"><s:property value="topicLog.enumber" escape="false" />　　</span>
			<span style="color:#396298">正确率：</span><span style="color:#FF0000"><s:property value="topicLog.correct" escape="false" />%</span>    	
        </div>
		
		<div class="arc-list">
			<span class="title00">结果分析：</span>   
			
			<s:iterator value="topicwhyloglist" id="beanlist" status="beanlist1">  
			<span style="color:#396298">原因<s:property value="#beanlist1.index+1" escape="false" />：</span><span style="color:#FF0000"><s:property value="#beanlist.learnWhy.title" escape="false" />(<s:property value="#beanlist.number" escape="false" />)　　</span>
			</s:iterator>
			
        </div>
		
		<div class="arc-list">
			<span class="title00">生活建议：</span>   
			
			<s:iterator value="topiclifeadviceloglist" id="beanlist" status="beanlist1">  
			<span style="color:#396298">建议<s:property value="#beanlist1.index+1" escape="false" />：</span><span style="color:#FF0000"><s:property value="#beanlist.topicLifeAdvice.title" escape="false" />(<s:property value="#beanlist.number" escape="false" />)　　</span>
			</s:iterator>
			
        </div>
		
		<div class="arc-list">
			<span class="title00">课程推荐：</span>   
			
			<s:iterator value="topiclearnclassloglist" id="beanlist" status="beanlist1">  
			<span style="color:#396298">推荐<s:property value="#beanlist1.index+1" escape="false" />：</span><span style="font-weight: bold;"><a class="blue" target="_blank" href="/membercenter/learnlist?searchcid=<s:property value="#beanlist.lcid" escape="false" />"><s:property value="#beanlist.learnClass.title" escape="false" />(<s:property value="#beanlist.number" escape="false" />)</a>　　</span>
			</s:iterator>
			
        </div>
		
		<div class="arc-list">
			<h2 style="float:left; width:60px;"  class="title00">分享到：</h2> 
			<div class="fenxiang00">
			<a href="javascript:" id="renren-share" title="人人网"><img width="16" height="16" src="/member/images/icon7.jpg"></a>
			<a href="javascript:" id="netease-share" title="网易微博"><img width="16" height="16" src="/member/images/icon9.jpg"></a>
			<a href="javascript:"  id="sina-share" title="新浪微博"><img width="16" height="16" src="/member/images/icon8.jpg"></a>
			<a href="javascript:"  id="kaixin001-share" title="开心网"><img width="16" height="16" src="/member/images/kaixin.gif"></a>
			<a href="javascript:" id="qqzone-share" title="QQ空间"><img width="16" height="16" src="/member/images/qq.gif"></a>
			<a href="javascript:" id="feixin-share" title="飞信"><img width="16" height="16" src="/member/images/feixin.gif"></a>
			</div>     	
        </div>
		
		<div style="margin:10px; text-align:left;"></div>
   </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>