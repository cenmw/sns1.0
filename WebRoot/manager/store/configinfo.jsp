<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>龙爸爸成长在线后台 管理系统</title>
<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<script src="/common/js/jquery-1.4.2.min.js" language="javascript"></script>
<script type="text/javascript">
function checkcontentinfo(){
	var score = $.trim($("#score").val());
	if(score==""){
		alert("金额不能为空");
		return false;
	}else{
		score=parseInt(score);
		if(isNaN(score)){
			alert("金额必须为数值型");
			return false;
		}
	}
	
	
	return true;
}
</script>
</head>
<body>
	<div style="text-align: center;">
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
			<tr>
				<td align="left">
				<s:form action="configsave" namespace="/manager/store" method="post" theme="simple" onsubmit="return checkcontentinfo()">
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">							
							<tr>
								<td colspan="2" class="yh">金额设置&nbsp;</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">52周为家庭消费金额</td>
								<td width="80%" height="30">&nbsp;&nbsp;	
								<input name="scores" id="score" class="tin" style="width:60px" maxlength="3" value="<s:property value="storeConfigVo.score1"/>"	/><em>&nbsp;&nbsp;<span>必须为数字，例如：52</span></em>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">52周为自己消费金额</td>
								<td width="80%" height="30">&nbsp;&nbsp;								
								<input name="scores" id="score" class="tin" style="width:60px" maxlength="3" value="<s:property value="storeConfigVo.score2"/>"	/><em>&nbsp;&nbsp;<span>必须为数字，例如：52</span></em>
								</td>
							</tr>
							
							<tr>
								<td height="30" colspan="2" align="center"><input
									type="submit" name="button" id="button" value="提交" class="tbtn">
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
<%
	Object msg=request.getSession().getAttribute("msg");
	String str = (String) msg;
	if(msg!=null){
	    out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("msg");	
	}
%>