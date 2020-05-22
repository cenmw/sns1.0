<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>龙爸爸成长在线后台 管理系统</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="/manager/css/backstage.css" rel="stylesheet"
			type="text/css">
	</head>
	<body background="#3079ac">

		<div class="right">
			<div class="place">
				<span>当前位置：商户权限分配管理</span>
			</div>
			<div class="right_con">
				<table width="100%" border="1" cellpadding="0" cellspacing="0"
					bordercolor="#0099CC">
					<tr>
						<td width="29%" class="yh">
							商户级别名称
						</td>
						<td width="42%" class="yh">
							操作
						</td>
					</tr>
					<s:iterator value="levelList">
						<tr>
							<td class="yh00">
								<s:property value="levelname" />
							</td>
							<td class="cz0">
								<a
									href="/manager/sys/member/shopToPermList?level=<s:property value="level"/>">分配列表</a>
							</td>
						</tr>
					</s:iterator>

				</table>



			</div>

		</div>
	</body>
	<body>
	</body>
</html>
<%
	String msg = (String) request.getSession()
			.getAttribute("actionmsg");
	if (msg != null && !msg.equals("")) {
		out.print("<script>alert('" + msg + "');</script>");
		request.getSession().removeAttribute("actionmsg");
	}
%>