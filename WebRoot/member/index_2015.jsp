<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta property="qc:admins" content="05163075076476721216375636716450" />
<meta name="Description" content="龙爸爸成长在线 是一个真实的社交网络，联络你和你周围的朋友。 加入龙爸爸成长在线你可以:联络朋友，了解他们的最新动态；和朋友分享相片、音乐和电影；找到老同学，结识新朋友；用照片和日志记录生活,展示自我。" />
<meta name="Keywords" content="longbaba,龙爸爸成长在线,幼儿园,小学,中学,大学,同学,同事,白领,个人主页,相册,群组,社区,交友,聊天,音乐,视频,龙爸爸,成长在线,龙爸爸成长在线" />
<title>龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<style type="text/css">
.pwdtip {
    background: none repeat scroll 0 0 transparent;
    color: #888888;
    cursor: text;
    font-size: 14px;
    font-weight: normal;
    left: 12px;
    position: absolute;
    text-indent: 0;
    visibility: visible;
    width: 100px;
/*	padding-top:7px;*/
	top:28px;
}

.pwdTipDiv {
    height: 32px;
    padding: 1px;
    position: relative;
    width: 186px;
}
</style>
<script language="javascript">
function check(){
	var accountobj = document.getElementById("ac");
	if(accountobj.value =='邮箱/手机号' || accountobj.value == ''){
	   document.getElementById("ac").value="";
	   document.getElementById("ac").focus();
	   return false;
	}
	var passwordobj = document.getElementById("password");
	if(passwordobj.value == ''){
	   hidepassword();
	   document.getElementById("password").focus();
	   return false;
	}
	return true;
}

$(function(){
	$("#ac").keyup(function(){	    
		if(document.getElementById("password").value != ''){
		  hidepassword();
	    }
	});   
});

$(function(){
	$("#password").keyup(function(){	    
		if(document.getElementById("password").value != ''){
		  hidepassword();
	    }
	});   
});

function hidepassword(){
   document.getElementById("pwdTip").style.visibility = "hidden";
}

function showpassword(){
   document.getElementById("pwdTip").style.visibility = "visible";
}

</script>
</head>
<body>
<!--top-->
<div class="login-top">
	<div class="layout-control">
    	<a class="login-logo fl" href="/"></a>
        <div class="fr login-r">您没有龙爸爸帐号，<a class="blue" href="/reg">注册</a></div>
    </div>
</div>
<!--top end-->
<!--content-->
<form action="login" id="loginForm" namespace="/" method="post" onsubmit="return check()">
<div class="layout-control">
	<div class="login-enter-l fl">
    	<div class="login-content">
        	<div class="login-input"><input type="text" maxlength="30" name="account" id="ac" value="邮箱/手机号" onclick="javascript:if(value=='邮箱/手机号'){value=''};" onblur="javascript:if(value==''){value='邮箱/手机号'}"/></div>
            <div class="login-input pwdTipDiv"><input type="password" maxlength="20" name="password" id="password" value="" onclick="hidepassword()" onblur="javascript:if(value==''){showpassword();}"/><label id="pwdTip" class="pwdtip" for="password">请输入密码</label></div>
            <div class="jizhu">
            	<span class="jizhu-l fl"><input type="checkbox" checked="checked" />下次自动登录</span>
                <a class="jizhu-r fr blue" href="/member/findpwd.jsp">忘记密码?</a>
            </div>
            <div class="login-login"><input id="login" type="submit" class="login-input-btn" value="" /></div>
            <div class="login-login"><input type="button" onclick="location.href='/reg'" class="login-input-btn1"  value="" /></div>
			<div class="login-login"><input type="button" onclick="location.href='/creg'" class="login-input-btn1_1"  value="" /></div>
            <div class="other-login">
            	<h2>使用其它帐号登录：</h2>
                <ul>
                	<li><a href="/api/qq/index"><img src="/member/images/login1.png" height="22" width="82" border="0" /></a></li>
                    <li><a href="/api/sina/index"><img src="/member/images/login3.png" height="22" width="89" border="0" /></a></li>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="login-enter-r fr"><img src="/member/images/pic9.jpg" height="399" width="710" border="0" /></div>
	<div class="clear"></div>
</div>

<style>
.sm_index{ margin-top:10px; padding-top:15px; padding-bottom:15px; height:30px;border-top: 1px solid #EDEDED;}
.sm_index2{ margin-top:10px; padding-top:15px; padding-bottom:15px; height:50px;border-top: 1px solid #EDEDED;}
.sm_index_left{float:left; width:238px;font-weight: bold;font-size: 14px;color:#007ED9; line-height:22px;}
.sm_index_right{float:right; width:710px;line-height:22px;}
.sm_index_right_span{ width:305px;float:left;}
</style>
<div class="layout-control">
     <div class="sm_index2">
		 <div class="sm_index_left">
		    在龙爸爸成长在线，我们能得到什么？		 
		 </div> 
		 <div class="sm_index_right">
			<span class="sm_index_right_span">在这里，可以帮您找回自己……</span>
			<span class="sm_index_right_span">在这里，可以找到家庭生活的乐趣……</span>
			<span class="sm_index_right_span">在这里，可以找到教育孩子的更好的方法……</span>
			<span class="sm_index_right_span">在这里，可以有更多的朋友帮助您……</span>
			<span class="sm_index_right_span">在这里，您可以可以帮助更多别人……</span>
			<span class="sm_index_right_span">在这里，我们就是陪您成长，陪伴孩子的家庭私人教师。</span>
		 </div>		
	 </div>
	 
	 <div class="sm_index">
		 <div class="sm_index_left">
		    希望能够随时看到孩子变化？		 
		 </div> 
		 <div class="sm_index_right">
			龙爸爸成长在线上的【日记】中可以按照设计的内容填写，可以不断的发现孩子的变化。
		 </div>		
	 </div>
	 
	 <div class="sm_index">
		 <div class="sm_index_left">
		     考试前要是能够更清楚孩子的学习问题那该有多好呀？		 
		 </div> 
		 <div class="sm_index_right">
			龙爸爸成长在线上的【测试中心】在线测试孩子的不同时期的问题，在测试之后，会给一个科学、准确的建议和学习方案。
		 </div>		
	 </div>
	 
	 <div class="sm_index">
		 <div class="sm_index_left">
		    天天练习基础知识，孩子越练越烦，该怎么办呀？	 
		 </div> 
		 <div class="sm_index_right">
			在龙爸爸成长在线上的【学习中心】中可以找到很多适合孩子学习训练课程，简单、实用、效果好。
		 </div>		
	 </div>
	 
	 <div class="sm_index">
		 <div class="sm_index_left">
		    平时全家在一起出去吃吃玩玩，好像缺少了很多有意义的事情？		 
		 </div> 
		 <div class="sm_index_right">
			在龙爸爸成长在线上的【活动中心】里您可以找到属于与您的生活的方式，给您提供更多的生活活动，活动之后希望您展示出你的活动过程，与大家一起分享幸福。
		 </div>		
	 </div>
	 
	 <div class="sm_index2">
		 <div class="sm_index_left">
		    孩子天天都会给我带来很多麻烦，现在的竞争又强，孩子的教育又不能疏忽，真的不知道怎么办才好？		 
		 </div> 
		 <div class="sm_index_right">
			在龙爸爸成长在线上，为您已经想到了，有【咨询中心】，在这里，您可以随时发布您教育孩子时遇到的一切问题，分享给大家，这样大家就可以为您解决，及时解决您的烦恼。
		 </div>		
	 </div>
	 
	 <div class="sm_index">
		 <div class="sm_index_left">
		    我没孩子，这个网站我也能够用吗？		 
		 </div> 
		 <div class="sm_index_right">
			能的，龙爸爸成长在线上的【日记】、【文集】、【相册】、【视频】都是可以作为做为个人的成长记录，尤其是在【日记】【活动中心】中会有很多关于个人成长的训练内容，个人完全可以参与。
		 </div>		
	 </div>
	 

	 
</div>			
</form>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html> 
<%
	Object msg=request.getSession().getAttribute("regMemberInfoMsg");

	if(msg!=null){
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("regMemberInfoMsg");
	}
%>
