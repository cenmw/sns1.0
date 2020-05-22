<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>龙爸爸成长在线后台登陆</title>
		<link href="/manager/css/backstage.css" rel="stylesheet"
			type="text/css">
			<style>
.divcenter {
	background: #fff;
	float: left;
	width: 179px;
	height: 500px;
	overflow-y: auto;
	overflow-x: hidden;
}

.divcenter ul {
	margin-top: 20px;
}

.divcenter ul li {
	width: 177px;
	text-align: center;
	margin-bottom: 5px;
	border: 1px #808080 solid;
}

.divcenter ul li a {
	padding: 5px;
	display: block;
	font-size: 16px;
	font-weight: bold;
	color: #000;
}

.divcenter ul li a:HOVER {
	background: url("/manager/product/images/daohang.png") repeat-x;
	color: white;
	font-size: 16px;
	font-weight: bold;
	text-decoration: none;
}
</style>
	</head>

	<body background="#3079ac">
		<div class="left" style="">
			<div class="left_top"></div>
			<div class="divcenter">
				<ul>
					<li>
						<a href="/manager/sys/member/memberpermpage" target="rightFrame">会员权限</a>
					</li>
				</ul>
				<ul>
					<li>
						<a href="/manager/sys/member/shoppermpage" target="rightFrame">商户权限</a>
					</li>
				</ul>
				<ul>
					<li>
						<a href="/manager/sys/member/shoptopermpage" target="rightFrame">分配商户权限</a>
					</li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
	</body>
</html>