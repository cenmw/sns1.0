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
								<s:property value="laborInfo.memberInfo.account"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">任务分类</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="laborInfo.classname"/>		
								</td>
							</tr>
							
							
							<tr>
								<td width="20%" class="yh">标题</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="laborInfo.title"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">关键词</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="laborInfo.keyword"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">摘要</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="laborInfo.description"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">发表时间</td>
								<td width="80%" height="30">&nbsp;&nbsp;		
								<s:date name="laborInfo.ctime" format="yyyy-MM-dd" />
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">任务内容</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:property value="laborInfo.content"/>	 		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">赠送积分</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="laborInfo.score"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">参加的会员</td>
								<td width="80%" height="30">&nbsp;&nbsp;									
								<s:iterator value="memberlaborList" id="sv" status="sv1"> 
								    <s:property value="#sv.account"/>&nbsp;&nbsp;	
								</s:iterator>
								</td>
							</tr>

						</table>
					</td>
			</tr>
		</table>
	</div>
	<div style="text-align: center; margin-top:10px; margin-top:30px;">
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
			<tr>
				<td align="left">
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							
							<tr>
								<td colspan="3" class="yh">完成信息&nbsp;</td>
							</tr>
							
							<s:iterator value="laborreplylist" id="sv" status="sv1"> 
							<tr>
								<td width="20%" class="yh"><s:property value="#sv.memberInfo.account"/><br /><s:date name="#sv.ctime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td width="50%" height="30" style="padding-left:13px;">								
								<textarea readonly="readonly" class="tarea" rows="5" cols="60"><s:property value="#sv.content"/></textarea>
								</td>
								<td class="yh" width="30%" height="30" style="padding-left:13px;">
								<s:if test="#sv.isagree == 1">
								    已认可该回答
								</s:if>
								</td>
							</tr>
							</s:iterator>	
							
							<tr>
								<td style="border-top:1px solid #FDFDFD;" colspan="3" class="yh">&nbsp;</td>
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