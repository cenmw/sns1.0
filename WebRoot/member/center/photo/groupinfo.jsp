<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的相册-百里挑一消费网</title>
<link href="/member/css/zc.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
//判断字节长度
function zjlen(s) {
	var l = 0;
	var a = s.split("");
	for (var i=0;i<a.length;i++) {
		if (a[i].charCodeAt(0)<299) {
			l++;
		} else {
			l+=2;
		}
	}
	return l;
}

$(function(){
	$("#title").keyup(function(){
		var title_len = zjlen($("#title").val());
	    if(title_len>24){
	    	$("#titleclenid").html("标题不能超过12个汉字长度");
	    }else if(title_len==0){
	    	$("#titleclenid").html("请输入标题");
	    }
	    $("#titlelenid").html(parseInt(title_len/2)+"/"+12);
	});
    $("#title").focus();   
	
	$("#description").keyup(function(){
		var description_len = zjlen($("#description").val());
	    if(description_len>200){
	    	$("#descriptionclenid").html("摘要不能超过100个汉字长度");
	    }else if(description_len==0){
	    	$("#descriptionclenid").html("请输入摘要");
	    }
	    $("#descriptionlenid").html(parseInt(description_len/2)+"/"+100);
	});
    $("#description").focus();  
	 
	
});

function checkcontentinfo(){
	var title=$.trim($("#title").val());
	if(title==""){
		alert("标题不能为空");
		return false;
	}else if(zjlen(title)>24){
	    alert("标题不能超过12个汉字长度");
	    document.getElementById("title").focus();
		return false;
	}
		
	return true;
}	
</script>

<script type="text/javascript">
var cidselect = '';
<s:if test="classlist != null && classlist.size()>0">
	<s:iterator value="classlist" id="beanlist" status="beanlist1"> 
	cidselect += '<option value="<s:property value="#beanlist.id" escape="false" />"><s:property value="#beanlist.title" escape="false" /></option>';
	</s:iterator>
	$('#cid', parent.document).html(cidselect);
	alert("添加成功");
	closebox2();
</s:if>
</script>
</head>
<body>
  <s:form action="photogroupsave" namespace="/membercenter" method="post" theme="simple" onsubmit="return checkcontentinfo()">
  <s:hidden name="backUrl"></s:hidden>
  <s:hidden name="memberPhotoGroup.id"></s:hidden>
  <s:hidden name="memberPhotoGroup.mid"></s:hidden>
  <s:hidden name="memberPhotoGroup.isdel"></s:hidden>
  <s:hidden name="memberPhotoGroup.ctime"></s:hidden>
  <s:hidden name="memberPhotoGroup.sort"></s:hidden>
  <s:hidden name="memberPhotoGroup.keyword"></s:hidden>
  <table width="100%" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
	   <tr>
	      <td width="20%" height="30">相册名称：</td>
		  <td width="80%" height="30">
			 <s:textfield id="title" name="memberPhotoGroup.title" maxLength="60" cssClass="tin" style="width:100px;"/>
			 <em>* <span id="titlelenid"></span></em></td>
	   </tr>
<script>
var title_c = document.getElementById("title");
document.getElementById("titlelenid").innerHTML = ""+parseInt(zjlen(title_c.value)/2)+"/12";				
</script>	

       <tr>
	      <td width="20%" height="30">相册描述：</td>
		  <td width="80%" height="30">
			 <s:textfield id="description" name="memberPhotoGroup.description" maxLength="60" cssClass="tin" style="width:100px;"/>
			 <em>* <span id="descriptionlenid"></span></em></td>
	   </tr>
<script>
var description_c = document.getElementById("description");
document.getElementById("descriptionlenid").innerHTML = ""+parseInt(zjlen(description_c.value)/2)+"/100";				
</script>	
   
	   <tr>
		  <td height="30" colspan="2" align="center">
		  <input type="submit" name="button" id="button" value="提交" class="tbtn">
		  </td>
	   </tr>
							
  </table>
  </s:form>
  
</body>
</html>
<%
	Object msg=request.getSession().getAttribute("saveinfomsg");
	if(msg!=null){
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("saveinfomsg");
	}
%>