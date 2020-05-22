<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-通讯录-龙爸爸成长在线</title>
<link href="/member/css/zc.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
//初始化
function init_checkrid(){
	var reviceids = '<s:property value="reviceids" escape="false" />';
	var _reviceids=reviceids+",";
	var cidarr = document.getElementsByName("ridcheckbox");
	if(cidarr){
	   for(var i=0;i<cidarr.length;i++){
		  if(_reviceids.indexOf(cidarr[i].value+",")>=0){
			 cidarr[i].checked = true;
		  } 
	   }
	}
}

//选择收件人
function select_checkrid(){
    var reviceids="";
	var revice_accounts="";
    var cidarr = document.getElementsByName("ridcheckbox");
	if(cidarr){
	   for(var i=0;i<cidarr.length;i++){
		  if(cidarr[i].checked == true){
			 reviceids+=","+cidarr[i].value;
			 revice_accounts+=";"+cidarr[i].title;
		  } 
	   }
	} 
	if(reviceids.length>0){
	   reviceids = reviceids.substring(1,reviceids.length);
	   revice_accounts = revice_accounts.substring(1,revice_accounts.length);
	   //alert(reviceids);
	   //alert(revice_accounts);
	   window.parent.document.getElementById("reviceids").value = reviceids;
	   window.parent.document.getElementById("revicenames").value = revice_accounts;
	   //window.parent.location.reload();
	   closebox2();
	} else{
	   alert("请选择收件人");
	}
}

//window.parent.document.getElementById("cid").innerHTML = cidselect;
</script>
</head>
<body>
  <div style="height:270px; overflow-y:auto">
  <table width="100%" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC" height="80">
	   
	   <s:iterator value="fmemberlist" id="beanlist" status="beanlist1">  
	   <tr>
	      <td width="10%" height="30"><input type="checkbox" id="rid" title="<s:property value="#beanlist.rmemberInfo.account" escape="false" />" name="ridcheckbox" value="<s:property value="#beanlist.rmemberInfo.id" escape="false" />" /></td>
		  <td width="90%" height="30">
			 <s:property value="#beanlist.rmemberInfo.account" escape="false" />
		  </td>
	   </tr>
	   </s:iterator>
							
  </table>
  </div>
  <div>
  <table width="100%" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC" height="80">
		<tr>
		  <td height="30" colspan="2" align="center">
		  <input type="button" name="button" onclick="select_checkrid()" id="button" value="提交" class="tbtn">
		  </td>
	   </tr>
  </table> 
  </div> 
</body>
</html>
<script>
//初始化
init_checkrid();
</script>
<%
	Object msg=request.getSession().getAttribute("saveinfomsg");
	if(msg!=null){
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("saveinfomsg");
	}
%>