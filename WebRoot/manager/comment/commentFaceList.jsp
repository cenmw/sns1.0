<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<title>龙爸爸成长在线后台 管理系统</title>
		<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
		<script src="/common/js/jquery-1.4.2.min.js" type="text/javascript"></script>
		<script src="/manager/js/sys.js" type="text/javascript"></script>
		
	</head>
	<body>
		<div class="right">
			<div class="place">
				<span>当前位置：<s:property value="commentFaceGroup.groupname"/>->表情列表</span>
			</div>
			<div class="sub">
			<input type="button" class="tbtn" value="添加" onclick="javascript:location.href='/manager/comment/insertCommentFace.jsp?gid=<s:property value="gid"/>';"/>
			</div>
			<div class="right_con">
				<table width="100%" border="1" cellpadding="0" cellspacing="0"
					bordercolor="#0099CC">
					<tr>
						<td class="yh">
							代码
						</td>
						<td class="yh">
							表情
						</td>
						<td class="cz">
							操作
						</td>
					</tr>
					<s:iterator value="pageBean.pageList">
						<tr>
						<td class="yh00">
							<s:property value="facecode" />
						</td>
						<td class="yh00">
							<img src="<s:property value="facepic" />"/>
						</td>
							<td class="cz0">
								<a href="/manager/comment/commentFacePage?id=<s:property value="id"/>">修改</a>&nbsp;
								<a href="javascript:deleteObj('/manager/comment/deleteCommentFace?id=<s:property value="id"/>','')">删除</a>
							</td>
						</tr>
					</s:iterator>
				</table>
				<s:property value="pageBean.gopagehtml" escape="false"/>
			</div>
		</div>
	</body>
</html>
