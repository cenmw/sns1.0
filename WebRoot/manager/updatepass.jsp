<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		
		<title>龙爸爸成长在线后台 管理系统 修改密码</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="/manager/css/backstage.css" rel="stylesheet"
			type="text/css">
        <script src="/common/js/jquery-1.6.min.js" language="javascript"></script>
        <script type="text/javascript">
        function check(){
        	var password=$.trim($("#pwd").val());
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
		    var repassword=$.trim($("#cfpwd").val());
			if(repassword==""){
				alert("确认密码不能为空");
				return false;	
			}else{
				if(password!=repassword){
					alert("密码确认不一致");
					return false;	
				}	
			}
        	return true;
        }
        </script>
	</head>
	<body>
		<div style="color: #808080; text-align: center;">
			<table width="100%" border="1" cellpadding="0" cellspacing="0"
				bordercolor="#0099CC">
				<tr>
					<td align="center">
							<s:form action="updatepass" method="post"
								namespace="/manager" theme="simple" onsubmit="return check()">
						<table width="700" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">

								<tr>
									<td width="97" class="yh">
										&nbsp;&nbsp;新密码：
									</td>
									<td width="503" height="30">
										&nbsp;&nbsp;
									<input type="password" name="pwd" class="v_tin" id="pwd" style="width:200px;" value=""/>
									</td>
								</tr>
								<tr>
									<td width="97" class="yh">
										&nbsp;&nbsp;确认新密码：
									</td>
									<td width="503" height="30">
										&nbsp;&nbsp;
									<input type="password" name="cfpwd" class="v_tin" id="cfpwd" style="width:200px;" value=""/>
									</td>
								</tr>
                                
								<tr>
									<td height="30" colspan="2" align="center">
										<input type="submit" name="button" id="button" value="确认修改"
											class="tbtn">
									</td>
								</tr>
						</table>
							</s:form>
					</td>
				</tr>
			</table>

		</div>

	</body>
</html>
