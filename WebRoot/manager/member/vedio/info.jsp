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
								<s:property value="vedioInfo.memberInfo.account"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">视频分类</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="vedioInfo.classname"/>		
								</td>
							</tr>
							
							
							<tr>
								<td width="20%" class="yh">标题</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="vedioInfo.title"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">关键词</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="vedioInfo.keyword"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">摘要</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="vedioInfo.description"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">作者</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="vedioInfo.author"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">来源</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="vedioInfo.source"/>		
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">发表时间</td>
								<td width="80%" height="30">&nbsp;&nbsp;		
								<s:date name="vedioInfo.ptime" format="yyyy-MM-dd" />
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">创建时间</td>
								<td width="80%" height="30">&nbsp;&nbsp;		
								<s:date name="vedioInfo.ctime" format="yyyy-MM-dd" />
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">视频代表图</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<img width="135" height="135" src="<s:property value="vedioInfo.picpath"/>" /> 		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">视频 内容</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:property value="vedioInfo.content" escape="false" />	 		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">权限</td>
								<td width="80%" height="30">&nbsp;&nbsp;		
								<s:property value="vedioInfo.qxname"/>
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