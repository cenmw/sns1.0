<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>龙爸爸成长在线后台 管理系统</title>
<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<script src="/common/js/jquery-1.4.js" language="javascript"></script>
<script src="/manager/js/sys.js" language="javascript"></script>
<script src="/common/loading/loading-min.js" language="javascript">
</script>
<script src="/common/loading/jquery.bgiframe.min.js" language="javascript">
</script>
		<link href="/common/loading/loading.css" rel="stylesheet"
			type="text/css">

<script type="text/javascript">
function saveshoptoperm(act){
	var lv='<s:property value="level"/>';
	var link='/manager/sys/member/saveshopperm';
	var loading=new ol.loading({id:"loadbody"});//初始化对象
	loading.show();//显示遮罩
	$.ajax({
		type:'POST',
		url:link,
		data:'act='+act+'&lv='+lv,
		success:function(msg){
			alert('添加成功');
			loading.hide();//隐藏遮罩
			self.parent.tb_remove();
			self.parent.reflush();
		}
	});
}
</script>
</head>

<body>
  
  <div class="right">
	   <div class="right_con">
<table id="loadbody" width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
  <tr>
    <td class="yh">
  	权限名称
    </td>
    <td class="yh">动作</td>
  </tr>
  <s:iterator value="spList">
     <tr>
       <td class="yh00"><a href="javascript:saveshoptoperm('<s:property value="action"/>')"><s:property value="actionname"/></a></td>
       <td class="yh00"><a href="javascript:saveshoptoperm('<s:property value="action"/>')"><s:property value="action"/></a></td>
     </tr>
  </s:iterator>
    
  
</table>
	   </div>
   </div>
</body>
</html>
