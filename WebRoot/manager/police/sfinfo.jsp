<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>龙爸爸成长在线后台 管理系统</title>
<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<script src="/common/js/jquery-1.4.2.min.js" language="javascript"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
<script src="/common/js/channeloptionTree.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/common/datepicker/ui.datepickerv1.css"></link>
<script src="/common/datepicker/ui.datepicker-zh-CN.js" type="text/javascript"></script>
<script src="/common/datepicker/ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){ 
	$('#starttime').datepicker( {
		yearRange : '1990:2050', //取值范围.
		showOn : 'both', //输入框和图片按钮都可以使用日历控件。
		buttonImage : '/common/datepicker/date.gif', //日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});
	
	$('#endtime').datepicker( {
		yearRange : '1990:2050', //取值范围.
		showOn : 'both', //输入框和图片按钮都可以使用日历控件。
		buttonImage : '/common/datepicker/date.gif', //日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});
});

</script>
</head>
<body>
	<div style="text-align: center;">
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
			<tr>
				<td align="left">
				<s:form action="save" namespace="/manager/memberpolice" method="post" theme="simple">
				         <s:hidden name="backUrl"></s:hidden>
						 <s:hidden name="memberReport.id"></s:hidden>
						 <s:hidden name="memberReport.ctime"></s:hidden>
						 <s:hidden name="memberReport.isdel"></s:hidden>
						 <s:hidden name="memberReport.mid"></s:hidden>
						 <s:hidden name="memberReport.type"></s:hidden>
						 <s:hidden name="memberReport.typename"></s:hidden>
						 <s:hidden name="memberReport.cid"></s:hidden>	
						 <s:hidden name="memberReport.classname"></s:hidden>
						 <s:hidden name="memberReport.rid"></s:hidden>
						 <s:hidden name="memberReport.content"></s:hidden>
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							<tr>
								<td colspan="2" class="yh">查看信息&nbsp;</td>
							</tr>
						
							<tr>
								<td width="20%" class="yh">会员名称（ 举报 ）</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="memberReport.memberInfo.account"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">会员名称（ 被举报 ）</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="memberReport.rmemberInfo.account"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">举报时间</td>
								<td width="80%" height="30">&nbsp;&nbsp;		
								<s:date name="memberReport.ctime" format="yyyy-MM-dd" />
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">举报内容</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:if test="memberReport.type==2">（日志）</s:if><s:elseif test="memberReport.type==3">（文集）</s:elseif><s:elseif test="memberReport.type==4">（照片）</s:elseif><s:elseif test="memberReport.type==5">（视频）</s:elseif><s:property value="memberReport.content" escape="false" />&nbsp;&nbsp;<a target="_blank" href="<s:property value="memberReport.url"/>">查看原文</a>
								</td>
							</tr>							
							
							<tr>
								<td width="20%" class="yh">锁定开始日期</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:date name="memberReport.starttime" format="yyyy-MM-dd" />
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">锁定结束日期</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:date name="memberReport.endtime" format="yyyy-MM-dd" />		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">状态</td>
								<td width="80%" height="30" style="color:#FF0000">&nbsp;&nbsp;
								已被释放
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">释放时间</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:date name="memberReport.sftime" format="yyyy-MM-dd" />
								</td>
							</tr>
							
							<tr>
								<td height="30" colspan="2" align="center"><input class="tbtn" type="button" name="button2"
									id="button2"
									onClick="location.href='<s:property value="backUrl"/>'"
									value="返回"></td>
							</tr>

						</table>
						</s:form>
					</td>
			</tr>
		</table>
	</div>
</body>
</html>
<%
	Object msg=request.getSession().getAttribute("msg");
	String str = (String) msg;
	if(msg!=null){
	    out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("msg");	
	}
%>