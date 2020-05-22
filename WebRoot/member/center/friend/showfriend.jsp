<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看企业详情-百里挑一消费网</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
</head>
<body> 
  <div style="overflow-x: auto; overflow-y: auto; height: 325px; width:580px;">
  <table width="550" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC" style="padding-right:10px;">
	   <tr>
	      <td width="70" height="30">企业名称：</td>
		  <td width="510" height="30">
			 <s:property value="fmemberInfo.account" escape="false" />
		  </td>
	   </tr>
	   
	   <s:if test="fmemberInfo.telphone != null && fmemberInfo.telphone.length()>0">
	   <tr>
	      <td width="70" height="30">固定电话：</td>
		  <td width="510" height="30">
			 <s:property value="fmemberInfo.telphone" escape="false" />
		  </td>
	   </tr>
	   </s:if>
	   
	   <s:if test="fmemberInfo.mobile != null && fmemberInfo.mobile.length()>0">
	   <tr>
	      <td width="70" height="30">移动电话：</td>
		  <td width="510" height="30">
			 <s:property value="fmemberInfo.mobile" escape="false" />
		  </td>
	   </tr>
	   </s:if>	
	   
	   <tr>
	      <td width="70" height="30">介绍：</td>
		  <td width="510" height="30" style="line-height: 24px;">
			 <s:property value="fmemberInfo.introduction" escape="false" />
		  </td>
	   </tr>				
  </table>
  </div>  
</body>
</html>