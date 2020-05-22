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
		<script
			src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"
			language="javascript"></script>
	<script language="javascript">
  		var uploadMsg="<s:property value="#request.uploadMsg" escape="false"/>";
  		if(uploadMsg!=""){
  			alert(uploadMsg);
  			if(uploadMsg=="上传成功"){
  				
  			}else{
  				
  			}
  			window.close();
  		}
  		
	</script>
	</head>
	<body>
		<s:form name="frm" id="frm" action="upload" namespace="/"
			method="post" enctype="multipart/form-data" theme="simple">
			<table width="300" border="0" cellspacing="0" cellpadding="0"
				style="background-color: #5A9CC9;" id="up">
				<tr>
					<td align="center" >
						<s:file name="myFile" id="myFile"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:submit value="上传" />
					</td>
				</tr>
			</table>
		</s:form>
		
	</body>
</html>
