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
function checkcontentinfo(){
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
				<s:form action="save" namespace="/manager/zhou" method="post" theme="simple" onsubmit="return checkcontentinfo()">
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							  <s:hidden name="backUrl"></s:hidden>
							  <s:hidden name="memberDiary52.id"></s:hidden>
							  <s:hidden name="memberDiary52.mid"></s:hidden>
							  <s:hidden name="memberDiary52.isdel"></s:hidden>
							  <s:hidden name="memberDiary52.ctime"></s:hidden>
							  <s:hidden name="memberDiary52.viewnumber"></s:hidden>
							  <s:hidden name="memberDiary52.rcid"></s:hidden>
							  <s:hidden name="memberDiary52.rcnumber"></s:hidden> 
							  <s:hidden name="memberDiary52.day"></s:hidden>
							  <s:hidden name="memberDiary52.praisenumber"></s:hidden>
							  <s:hidden name="memberDiary52.commentnumber"></s:hidden>
							<tr>
								<td colspan="2" class="yh">修改 信息&nbsp;</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">会员名称</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="memberDiary52.memberInfo.account"/>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">类型</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="type" list="#{0:'为家庭',1:'为自己'}" name="memberDiary52.type"></s:select>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">日期</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<input name="memberDiary52.ptime" id="ptime" class="tin" maxLength="50" style="width:200px;" value="<s:date name="memberDiary52.ptime" format="yyyy-MM-dd"/>" />
								<script type="text/javascript">
							        jQuery(function($) {
							            $('#ptime').datepicker( {
							                yearRange : '2011:2050', //取值范围.
							                showOn : 'both', //输入框和图片按钮都可以使用日历控件。
							                buttonImage : '/common/js/datepicker/date.gif', //日历控件的按钮
							                buttonImageOnly : true,
							                showButtonPanel : true
							            });
							        
							        });
							    </script> 
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">地点</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="memberDiary52.address" cssClass="tin" maxLength="200"/>
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">天气</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="memberDiary52.weather" cssClass="tin" maxLength="250"/>
								</td>
							</tr>
									
							<tr>
								<td width="20%" class="yh">发生的事件</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:textarea cssClass="tarea" name="memberDiary52.fssq" id="keyword" rows="5" cols="60"/>(100字)
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">当时的感受</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:textarea cssClass="tarea" name="memberDiary52.wdgs" id="description" rows="5" cols="60"/>(500字)
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">权限</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="qx" list="#{0:'任何人可见',1:'仅好友可见',2:'仅自己可见'}" name="memberDiary52.qx"></s:select>
								</td>
							</tr>
										
							<tr>
								<td height="30" colspan="2" align="center"><input
									type="submit" name="button" id="button" value="提交" class="tbtn">
									&nbsp;&nbsp; <input class="tbtn" type="button" name="button2"
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
<style type="text/css">
.KeyWordListBoxCss{border:#BFCDF3 1px solid; background-color:#FFFFFF;display:none;}
.keyWordListTrCss{ cursor:pointer;width:100%;}
</style>
<%
	Object msg=request.getSession().getAttribute("msg");
	String str = (String) msg;
	if(msg!=null){
	    out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("msg");	
	}
%>
