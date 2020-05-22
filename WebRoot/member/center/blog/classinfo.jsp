<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的日志-龙爸爸成长在线</title>
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
	    if(title_len>12){
	    	$("#titleclenid").html("标题不能超过6个汉字长度");
	    }else if(title_len==0){
	    	$("#titleclenid").html("请输入标题");
	    }
	    $("#titlelenid").html(parseInt(title_len/2)+"/"+6);
	});
    $("#title").focus();    
	
});

function checkcontentinfo(){
	var title=$.trim($("#title").val());
	if(title==""){
		alert("标题不能为空");
		return false;
	}else if(zjlen(title)>12){
	    alert("标题不能超过6个汉字长度");
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
  <s:form action="blogclasssave" namespace="/membercenter" method="post" theme="simple" onsubmit="return checkcontentinfo()">
  <s:hidden name="backUrl"></s:hidden>
  <s:hidden name="memberBlogClass.id"></s:hidden>
  <s:hidden name="memberBlogClass.mid"></s:hidden>
  <s:hidden name="memberBlogClass.isdel"></s:hidden>
  <s:hidden name="memberBlogClass.ctime"></s:hidden>
  <s:hidden name="memberBlogClass.sort"></s:hidden>
  <s:hidden name="memberBlogClass.type"></s:hidden>
  <table width="100%" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
	   <tr>
	      <td width="10%" height="30">标题：</td>
		  <td width="90%" height="30">
			 <s:textfield id="title" name="memberBlogClass.title" maxLength="60" cssClass="tin" style="width:100px;"/>
			 <em>* <span id="titlelenid"></span></em></td>
	   </tr>
<script>
var title_c = document.getElementById("title");
document.getElementById("titlelenid").innerHTML = ""+parseInt(zjlen(title_c.value)/2)+"/6";				
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