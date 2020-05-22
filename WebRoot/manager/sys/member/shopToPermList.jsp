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
	<script src="/common/js/jquery-1.6.min.js" language="javascript"></script>
	<script src="/manager/js/sys.js" language="javascript"></script>
	<script src="/common/thickbox/thickbox.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/common/thickbox/thickbox.css"></link>
	<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
$(function(){
	$("#mlv").change(function(){
		var level=$(this).val();
		location.href='/manager/sys/member/shopToPermList?level='+level;
	});
});
function reflush(){
	location.href=location.href;
}
</script>
  </head>
  <body background="#3079ac">
  
  <div class="right">
       <div class="place"><span>当前位置：<s:select id="mlv" list="levelList" listKey="level" listValue="levelname" name="level"></s:select>权限列表</span></div>
       <div class="sub" style="text-align:left;">
	       <a href="/manager/sys/member/selectshoppermpage?TB_iframe=true&height=500&width=390" title="选择权限" class="thickbox">关联权限</a>
	   </div>
	   <div class="right_con">
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
     <tr>
       <td width="29%" class="yh">权限名称</td>
       <td width="42%" class="yh">操作</td>
     </tr>
     <s:iterator value="permList">
     <tr>
       <td class="yh00"><s:property value="action"/></td>
       <td class="cz0">
       <a href="javascript:deleteObj('/manager/sys/member/deleteshopperm?id=<s:property value="id"/>&backurl=','/manager/sys/member/shopToPermList?level=<s:property value="level"/>')">删除</a>
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
