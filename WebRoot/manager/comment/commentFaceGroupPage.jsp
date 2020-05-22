<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>龙爸爸成长在线后台 管理系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="/manager/css/backstage.css" rel="stylesheet"
			type="text/css">
		<script src="/common/js/jquery-1.4.js" type="text/javascript"></script>
		<script type="text/javascript">
		function check(){
			var groupname=$.trim($("#groupname").val());
			if(groupname==""){
				alert(";请输入组名称");
				return false;
			}
			return true;
		}
		
		</script>
	</head>
	<body>
		<div style="color: #808080; text-align: center;">
<s:form action="saveCommentFaceGroup" namespace="/manager/comment" method="post" onsubmit="return check()">
			<table width="100%" border="1" cellpadding="0" cellspacing="0"
				bordercolor="#0099CC">
				<tr>
					<td align="center">
						<table width="600" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							<input type="hidden" class="tin" name="id" value="<s:property value="commentFaceGroup.id"/>"/>
							<tr>
								<td colspan="2" class="yh">
									表情组信息
								</td>
							</tr>
							<tr>
								<td width="97" class="yh">
									&nbsp;&nbsp;组名称：
								</td>
								<td height="30">
									&nbsp;&nbsp;<input type="text" class="tin" name="groupname" id="groupname" value="<s:property value="commentFaceGroup.groupname"/>"/>
								</td>
							</tr>
							<tr>
								<td height="30" colspan="2" align="center">
									<input type="submit" value="提交" class="tbtn" />
									<input type="button" name="button" id="button" value="返回"
										class="tbtn"
										onclick="javascript:location.href='/manager/comment/faceGroupList'" />
								</td>
							</tr>

						</table></s:form>
					</td>
				</tr>
			</table>

		</div>

	</body>
</html>
