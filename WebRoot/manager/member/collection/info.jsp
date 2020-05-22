<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>龙爸爸成长在线后台 管理系统</title>
<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<script src="/common/js/jquery-1.4.2.min.js" language="javascript"></script>
</head>
<body>
	<div style="text-align: center;">
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
			<tr>
				<td align="left">
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							<tr>
								<td colspan="2" class="yh">查看信息&nbsp;</td>
							</tr>
						
							<tr>
								<td width="20%" class="yh">会员名称（ 机构名称）</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="memberCollection.memberInfo.account"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">收藏分类</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="memberCollection.typename"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">收藏时间</td>
								<td width="80%" height="30">&nbsp;&nbsp;		
								<s:date name="memberCollection.ctime" format="yyyy-MM-dd" />
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">收藏名称</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:property value="memberCollection.title"/>	
								</td>
							</tr>
							
							<tr>
								<td height="30" colspan="2" align="center"><input class="tbtn" type="button" name="button2"
									id="button2"
									onClick="location.href='<s:property value="backUrl"/>'"
									value="返回"></td>
							</tr>

						</table>
					</td>
			</tr>
		</table>
	</div>
</body>
</html>