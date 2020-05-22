<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object msg=request.getSession().getAttribute("actionGroupMsg");
if(msg!=null){
	out.print("<script>alert('"+(String)msg+"')</script>");
	request.getSession().removeAttribute("actionGroupMsg");
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
	<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
	<script src="/common/js/jquery-1.4.js" language="javascript"></script>
<script language="javascript">
function deleteObj(url,backurl){
	if(confirm("确认删除吗")){
		location.href=url+encodeURIComponent(backurl);
	}
}
function fowardBack(url,backurl){
	location.href=url+encodeURIComponent(backurl);
}
</script>
  </head>
  <body background="#3079ac">
  
  <div class="right">
       <div class="place"><span>当前位置：权限管理 > 权限关联组列表</span></div>
	    <div class="sub">
	        <input type="button" onclick="javascript:fowardBack('/manager/sys/showActionGroup?action=<s:property value="action"/>&actionname=<s:property value="actionname"/>&backurl=','<s:property value="pageBean.backurl"/>');" value="添加角色 " class="l" />
	   </div>
	   <div class="right_con">
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
  <tr>
    <td class="yh">角色名称</td>

    <td class="cz">操作</td>
  </tr>
  <s:iterator value="pageBean.pageList">
     <tr>
       <td class="yh00"><s:property value="groupname"/></td>
       <td class="cz0"><a href="javascript:fowardBack('/manager/sys/showActionGroup?id=<s:property value="id"/>&backurl=','<s:property value="pageBean.backurl"/>');">修改</a>&nbsp;&nbsp;<a href="javascript:deleteObj('/manager/sys/deleteActionGroup?id=<s:property value="id"/>&backurl=','<s:property value="pageBean.backurl"/>')">删除</a></td>
     </tr>
  </s:iterator>
    
  
</table>

<s:property value="pageBean.gopagehtml" escape="false"/>
			
	   </div>
	   
   </div>
</body>

</html>
