<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-学习中心-龙爸爸成长在线</title>
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
<style type="text/css">
.button_pbolg{
    background: url("/member/images/btn1.png") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    border: 0 none;
    cursor: pointer;
    height: 25px;
    width: 71px;
}
</style>
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
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>学习课程结果</h2>       
		
		<div id="xxtcjgid">
		<div class="arc-list">
			<span class="title00">学习课程：</span>   
			<span style="color:#396298"><s:property value="learnInfo.title" escape="false" /></span>		     	
        </div>
						
		<div class="arc-list">
			<span class="title00">学习结果：</span>   
			<span style="color:#396298">正确题目数量：</span><span style="color:#FF0000"><s:property value="learnLog.cnumber" escape="false" />　　</span>
			<span style="color:#396298">错误题目数量：</span><span style="color:#FF0000"><s:property value="learnLog.enumber" escape="false" />　　</span>
			<span style="color:#396298">正确率：</span><span style="color:#FF0000"><s:property value="learnLog.correct" escape="false" />%</span>    	
        </div>
		
		<div class="arc-list">
			<span class="title00">结果分析：</span>   
			
			<s:iterator value="learnwhyloglist" id="beanlist" status="beanlist1">  
			<span style="color:#396298">原因<s:property value="#beanlist1.index+1" escape="false" />：</span><span style="color:#FF0000"><s:property value="#beanlist.learnWhy.title" escape="false" />(<s:property value="#beanlist.number" escape="false" />)　　</span>
			</s:iterator>
			
        </div>
		
		<div class="arc-list01">
			<span class="title00">学习建议：</span>   
			<s:if test="learnLog.correct>=learnInfo.correct"><p><span style="color:#FF0000;">正确率已超过 <s:property value="learnInfo.correct" escape="false" />%，请进入下一课程学习>></span><span><a href="javascript:fowardBack('/membercenter/showlearninfo?id=<s:property value="nextlearnInfo.id"/>&backUrl=','<s:property value="backUrl"/>')"><s:property value="nextlearnInfo.title" escape="false" /></a></span></p></s:if><s:else><s:if test="learnLog.correct<=learnInfo.lcorrect"><p><span style="color:#FF0000;">正确率低于 <s:property value="learnInfo.lcorrect" escape="false" />%，请进入上一课程学习>></span><span><a href="javascript:fowardBack('/membercenter/showlearninfo?id=<s:property value="lastlearnInfo.id"/>&backUrl=','<s:property value="backUrl"/>')"><s:property value="lastlearnInfo.title" escape="false" /></a></span></p></s:if><s:else><p><span style="color:#FF0000;">正确率不足 <s:property value="learnInfo.correct" escape="false" />%，建议继续学习>></span><span><a href="javascript:fowardBack('/membercenter/showlearninfo?id=<s:property value="learnInfo.id"/>&backUrl=','<s:property value="backUrl"/>')"><s:property value="learnInfo.title" escape="false" /></a></span></p></s:else></s:else>
        </div>
		</div>

<form id="pblog" name="pblog" action="/membercenter/learnlogblogsave">
<input type="hidden" name="title" value="<s:property value="learnInfo.title" escape="false" /> 学习结果分享" />
<input type="hidden" name="content" id="fcontent" value="" />
<input type="hidden" name="lsid" value="<s:property value="learnLog.id" escape="false" />" />	
<input type="hidden" name="backUrl" value="<s:property value="backUrl" escape="false" />" />	
</form>	

		<div class="arc-list">
			<span class="title00">学习感受：</span>
			 
			<div style="color:#396298">
			<textarea id="pcontent" rows="8" cols="78" name="pcontent"></textarea>
			</div>
			<div style="padding-top:6px;">
			<input class="button_pbolg" type="button" onclick="p_blog()" value="">
			</div>
			
        </div>
<script type="text/javascript">
function p_blog(){
    var content = "";
    var p_content = document.getElementById("pcontent");
	var xxtcjg = document.getElementById("xxtcjgid");
	content = xxtcjg.innerHTML;
	content += "<div class=\"arc-list01\"><span class=\"title00\">学习感受：</span><p>";
	content += p_content.value;
	content += "</p></div>";
	document.getElementById("fcontent").value = content;
	document.getElementById("pblog").submit();
}
</script>	
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
<%
	Object msg=request.getSession().getAttribute("saveinfomsg");
	if(msg!=null){
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("saveinfomsg");
	}
%>