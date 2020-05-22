<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>上传文件</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script language="javascript">
		function go(){
		var url="/download?downloadPath=/uploadfiles/2010/12/10/1291980571890.png&fileName="+encodeURIComponent("中国");
		alert(url);
		location.href=url;
		}
		//go();
		</script>
	</head>
	<body>
		
			<table width="300" border="0" cellspacing="0" cellpadding="0"
				style="background-color: #5A9CC9;" id="up">
				<tr>
					<td align="center" >
					<a href="/download?downloadPath=/uploadfiles/2010/12/10/1291980571890.png&fileName=中国">中国</a>	
					</td>
				</tr>
				
			</table>
		
		
	</body>
</html>
