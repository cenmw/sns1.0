<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object msg=request.getSession().getAttribute("userMsg");
if(msg!=null){
	out.print("<script>alert('"+(String)msg+"')</script>");
	request.getSession().removeAttribute("userMsg");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:ice="http://ns.adobe.com/incontextediting">
  <head>
    <base href="<%=basePath%>">
    
    <title>龙爸爸成长在线后台 管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/common/js/datepicker/ui.datepicker.css"></link>
    <script src="/common/js/jquery-1.4.js" language="javascript"></script>
    <script src="/common/js/datepicker/ui.datepicker-zh-CN.js" type="text/javascript"></script>
    <script src="/common/js/datepicker/ui.datepicker.js" type="text/javascript"></script>
    
    <script src="/common/thickbox/thickbox.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/common/thickbox/thickbox.css"></link>
    
     <script type="text/javascript">
			jQuery(function($){
			$('#datepicker').datepicker({
					yearRange: '1900:2099', //取值范围.
					showOn: 'both', //输入框和图片按钮都可以使用日历控件。
					buttonImage: '/common/js/datepicker/calendar.gif', //日历控件的按钮
					buttonImageOnly: true,
					showButtonPanel: true
				});	
				
			});
	</script>
    

<script language="javascript">
function checkUser(){
	var username=$.trim($("#username").val());
	var password=$.trim($("#password").val());
	
	if(username==""){
		alert("用户名不能为空");
		return false;
	}else{
		if(username.length>30){
			alert("用户名不能超过30位");
			return false;	
		}
	}
	
	
	
	<s:if test="user.id>0">
    </s:if>
    <s:else>
    	if(password==""){
			alert("密码不能为空");
			return false;
		}else{
			if(password.length<6){
				alert("密码大于6位小于30位");
				return false;	
			}
			if(password.length>30){
				alert("密码大于6位小于30位");
				return false;	
			}
		}
		var repassword=$.trim($("#repassword").val());
        if(repassword==""){
			alert("确认密码不能为空");
			return false;	
		}else{
			if(password!=repassword){
				alert("密码确认不一致");
				return false;	
			}	
		}
    </s:else>
	return true;
}
</script>
</head>
  
  <body>
    <div style="color:#808080;text-align:center;">
    	 <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
    	 	<tr>
    	 		<td align="center">
    	 		
	<table width="700" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
   	  
      <s:form action="saveUser" method="post" namespace="/manager/sys" theme="simple" onsubmit="return checkUser()">
      <s:hidden name="user.id"/>
   	  <s:hidden name="user.ctime"/>
      <s:hidden name="user.createid"/>
      <s:hidden name="user.createname"/>
      <s:hidden name="user.status" value="0"/>
      <s:hidden name="backurl"/>
    	  <tr>
    	    <td colspan="2" class="yh">用户信息</td>
   	    </tr>
    	  <tr>
    	    <td width="97" class="yh">&nbsp;&nbsp;用户名称：</td>
    	    <td width="403" height="30">&nbsp;&nbsp;
    	    <s:textfield cssClass="tin" name="user.username" id="username" maxLength="50"></s:textfield>
   	        </td>
   	        
  	    </tr>
    	  
        <s:if test="user.id>0">
        	<s:hidden name="user.password"></s:hidden>
        </s:if>
        <s:else>
        <tr>
    	    <td class="yh">&nbsp;&nbsp;密码：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:password  cssClass="tin" name="user.password" id="password" maxLength="50" showPassword="true"></s:password>
   	        </td>
  	    </tr>
    	  <tr>
    	    <td class="yh">&nbsp;&nbsp;确认密码：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:password  cssClass="tin" name="repassword" id="repassword" maxLength="50"></s:password></td>
  	    </tr>
        </s:else>
        <tr>
    	    <td class="yh">&nbsp;&nbsp;真实姓名：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:textfield  cssClass="tin" name="user.truename" id="truename" maxLength="50"></s:textfield></td>
  	    </tr>
        <tr>
    	    <td class="yh">&nbsp;&nbsp;出生日期：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <input class="tin0" type="text" name="user.birthday" id="datepicker" value="<s:date name="user.birthday" format="yyyy-MM-dd"></s:date>"></td>
  	    </tr>
         <tr>
    	    <td class="yh">&nbsp;&nbsp;手机：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:textfield  cssClass="tin" name="user.mobile" id="mobile" maxLength="50"></s:textfield></td>
  	    </tr>
        <tr>
    	    <td class="yh">&nbsp;&nbsp;家庭电话：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:textfield  cssClass="tin" name="user.homePhone" id="home_phone" maxLength="50"></s:textfield></td>
  	    </tr>
        <tr>
    	    <td class="yh">&nbsp;&nbsp;邮箱：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:textfield  cssClass="tin" name="user.email" id="email" maxLength="50"></s:textfield></td>
  	    </tr>
        <tr>
    	    <td class="yh">&nbsp;&nbsp;部门：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:textfield  cssClass="tin" name="user.dept" id="dept" maxLength="50"></s:textfield></td>
  	    </tr>
        <tr>
    	    <td class="yh">&nbsp;&nbsp;职位：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:textfield  cssClass="tin" name="user.position" id="position" maxLength="50"></s:textfield></td>
  	    </tr>
        <tr>
    	    <td class="yh">&nbsp;&nbsp;办公电话：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:textfield  cssClass="tin" name="user.officePhone" id="office_phone" maxLength="50"></s:textfield></td>
  	    </tr>
        <tr>
    	    <td class="yh">&nbsp;&nbsp;角色：</td>
    	    <td height="30">&nbsp;&nbsp;
   	        <s:hidden name="user.groupid" id="groupid"></s:hidden>
   	        <s:textfield name="user.groupManager.groupname" cssClass="tin1" id="groupname" />&nbsp;
   	        <a href="/manager/sys/getGroupManagerList?status=1&TB_iframe=true&height=500&width=390" title="<s:property value="name"/>" class="thickbox">选择角色</a>
   	        </td>
  	    </tr>
        
    	  <tr>
    	    <td height="30" colspan="2" align="center"><input type="submit" name="button" id="button" value="提交" class="tbtn">&nbsp;&nbsp;
   	        <input class="tbtn" type="button" name="button2" id="button2" onClick="javascript:location.href='<s:property value="backurl"/>';" value="返回"></td>
   	    </tr>
        </s:form>
  	  </table>
	</td>
</tr>
    	</table>
   	  
    </div>
</body>
</html>
