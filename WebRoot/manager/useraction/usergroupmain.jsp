<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

	</head>

	
		<frameset rows="*" cols="50%,*" id="bframe" framespacing="1"
			frameborder="yes" border="2" bordercolor="blue">
			<frame src="/manager/sys/getGroupManagerList" name="leftFrame"
				scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
			<frame src="/manager/sys/inc/main.jsp" name="rightFrame"
				id="rightFrame" title="rightFrame" scrolling="auto" />
		</frameset>
	<noframes><body>
</body>
</noframes>
</html>
