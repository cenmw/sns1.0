<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Object msg = request.getSession().getAttribute("groupManagerMsg");
	if (msg != null) {
		out.print("<script>alert('" + (String) msg + "')</script>");
		request.getSession().removeAttribute("groupManagerMsg");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>龙爸爸成长在线后台 管理系统</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="/manager/css/backstage.css" rel="stylesheet"
			type="text/css">
			<script src="/common/js/jquery-1.4.js" language="javascript"></script>
		<script language="javascript">
function success(groupid,groupname){
	$("#groupid",window.parent.document).val(groupid);
	$("#groupname",window.parent.document).val(groupname);
	$("#popup", window.parent.document).hide();
	$("#_overlay", window.parent.document).hide();
}
</script>
	</head>
	<body background="#3079ac">

		<div class="right">
			<div class="sub">
			</div>
			<div class="right_con">
				<table width="100%" border="1" cellpadding="0" cellspacing="0"
					bordercolor="#0099CC">
					<tr>
						<td width="35%" class="yh">
							角色名称
						</td>

					</tr>
					<s:iterator value="gmList">
						<tr>
							<td class="yh00">
								<a href="javascript:success(<s:property value="groupid" />,'<s:property value="groupname" />');"><s:property value="groupname" /></a>
							</td>
						</tr>
					</s:iterator>
				</table>



			</div>

		</div>
	</body>


</html>
