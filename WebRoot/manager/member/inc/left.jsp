<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>龙爸爸成长在线后台登陆</title>
<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<style>
.divcenter{
	background:#fff; float:left; width:179px;height:500px; overflow-y: auto;overflow-x:hidden;
}
.divcenter ul{
margin-top:10px;
}
.divcenter ul li{
width:177px;
text-align: center;
margin-bottom:5px;
border: 1px #808080 solid;
}
.divcenter ul li a{
padding:5px;
display: block;
font-size: 16px;
font-weight: bold;
color:#000;
}
.divcenter ul li a:HOVER {
	background: url("/manager/images/daohang.png") repeat-x;
	background-color: #666;
	color:#ffffff;
	font-size: 16px;
	font-weight: bold;
	text-decoration:none;
}

.divcenter .current a{
	background: url("/manager/images/daohang.png") repeat-x;
	background-color: #666;
	color:#FFFFFF;
	font-size: 16px;
	font-weight: bold;
	text-decoration:none;
}
</style>
<script type="text/javascript">
function changeleft(obj){
   var leftdiv = document.getElementById("leftdiv");
   var lis = leftdiv.getElementsByTagName("li");
   for(var i=0;i<lis.length;i++){
       lis[i].className="";
   }
   obj.className="current";
}
</script>
</head>

<body background="#3079ac">
<div class="left" style="">
	        <div class="left_top"></div>
			<div class="divcenter" style="height:480px;" id="leftdiv">
						
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/member/list" target="rightFrame">会员信息管理<s:property value="backUrl"/></a></li>
				</ul>	
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/membermood/list" target="rightFrame">会员心情管理</a></li>
				</ul>
				
				<!--
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberblogclass/list" target="rightFrame">会员日志分类管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberblog/list" target="rightFrame">会员日志管理</a></li>
				</ul>
				-->
				
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberdiary/list" target="rightFrame">会员日记管理</a></li>
				</ul>
				
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/membercontentclass/list" target="rightFrame">会员文章分类管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/membercontent/list" target="rightFrame">会员文章管理</a></li>
				</ul>	
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberphotogroup/list" target="rightFrame">会员相册管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberphoto/list" target="rightFrame">会员相片管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/membervedioclass/list" target="rightFrame">会员视频组管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/membervedio/list" target="rightFrame">会员视频管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberlabor/list" target="rightFrame">会员任务管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/membercollection/list" target="rightFrame">会员收藏管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/membermessage/list" target="rightFrame">会员私信管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberpraise/list" target="rightFrame">会员赞管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberstatus/list" target="rightFrame">会员动态管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberlog/list" target="rightFrame">会员访问记录管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberfriendclass/list" target="rightFrame">会员好友组管理</a></li>
				</ul>
				<ul>
				   <li onclick="changeleft(this)"><a href="/manager/memberfriend/list" target="rightFrame">会员好友管理</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
	   </div>
</body>
</html>