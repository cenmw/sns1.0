<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
				<meta http-equiv="expires" content="0">
		<title>龙爸爸成长在线后台 管理系统</title>
		<link href="/manager/css/backstage.css" rel="stylesheet"
			type="text/css">
			<script src="/common/js/jquery-1.4.2.min.js" type="text/javascript">
</script>
			<script src="/manager/js/sys.js" type="text/javascript">
</script>
	</head>
	<body>
		<div class="right">
			<div class="place">
				<span>当前位置：评论表情组</span>
			</div>
			<div class="sub">
				<input type="button" onclick="javascript:location.href='/manager/comment/commentFaceGroupPage';" value="添加" class="tbtn" />
			</div>
			<div class="right_con">
				<table width="100%" border="1" cellpadding="0" cellspacing="0"
					bordercolor="#0099CC">
					<tr>
						<td class="yh">
							名称
						</td>
						<td class="cz">
							操作
						</td>
					</tr>
					<s:iterator value="commentFaceGroupList">
						<tr>
							<td class="yh00">
								<s:property value="groupname" />
							</td>

							<td class="cz0">
								<a href="/manager/comment/commentFaceList?gid=<s:property value="id"/>">表情</a>&nbsp;
								<a href="/manager/comment/commentFaceGroupPage?id=<s:property value="id"/>">修改</a>&nbsp;
								<a href="javascript:deleteObj('/manager/comment/deleteCommentFaceGroup?id=<s:property value="id"/>','')">删除</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
	</body>
</html>
