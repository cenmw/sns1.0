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
	<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
	<script src="/common/js/jquery-1.4.js" language="javascript"></script>
	<script src="/manager/js/sys.js" language="javascript"></script>
	<script src="/common/thickbox/thickbox.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/common/thickbox/thickbox.css"></link>
<script type="text/javascript">
function reflush(){
	location.href=location.href;
}
</script>
  </head>
  <body background="#3079ac">
  
  <div class="right">
       <div class="place"><span>当前位置：权限管理 > 权限关联列表</span></div>
	    <div class="sub">
	    <a href="/manager/sys/selectactionpage?groupid=<s:property value="groupid"/>&TB_iframe=true&height=500&width=390" title="选择权限" class="thickbox">添加权限</a>
	   </div>
	   <div class="right_con">
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
  <tr>
    <td class="yh">权限名称</td>
    <td class="cz">操作</td>
  </tr>
  <s:iterator value="pageBean.pageList">
     <tr>
       <td class="yh00"><s:property value="actionname"/></td>
       <td class="cz0"><a href="javascript:deleteObj('/manager/sys/deleteActionGroup?id=<s:property value="id"/>&backurl=','<s:property value="pageBean.backurl"/>')">删除权限</a></td>
     </tr>
  </s:iterator>
</table>

<s:property value="pageBean.gopagehtml" escape="false"/>
			
	   </div>
	   
   </div>
</body>

</html>
