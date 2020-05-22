<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<s:property value="#request.message" escape="false"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>龙爸爸成长在线后台 管理系统</title>
<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<script src="/common/js/jquery-1.4.js" language="javascript"></script>
<script type="text/javascript">
function check(){
	
	return true;
}
</script>
</head>

<body background="#3079ac">
  
  <div class="right">
      
	    <div class="sub">
	        &nbsp;
	   </div>
	  
	   
   </div>
   <div>
	  <s:form action="importcon" namespace="/manager" method="post" enctype="multipart/form-data" onsubmit="return check()">
	  	<div>sid:<input type="text" name="sid" value="2"/></div>
	  	<div>cid:<input type="text" name="cid" value="0"/></div>
	  	<div>文件：<input type="file" id="uploadfile" name="excel"  /></div>
	  	<div><s:submit value="导入"></s:submit>&nbsp;<a href="/manager/cmsdata/dataindex.jsp">返回</a></div>
	  </s:form>
	  </div>
</body>
</html>
