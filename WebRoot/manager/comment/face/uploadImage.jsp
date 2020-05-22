<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上传图片</title>
<script type="text/javascript" src="/common/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
function check(){
	var delPic=$("#pic",window.parent.document).val();
	$("#delPic").val(delPic);
	if($("#uploadfile").val()==""){
		alert("请选择文件(支持：bmp,jpg,png,gif,jpeg)");
		return false;
	}
	var file=$.trim($("#uploadfile").val());
	var validateFormat = new Array("bmp","jpg","png","gif","jpeg");
	var fileFormat = file.substring(file.lastIndexOf(".")+1).toLowerCase();
	var validate = false;
	for (var i = 0; i < validateFormat.length ; i++){
		if(fileFormat == validateFormat[i]){
			validate = true; break;
		}
	}
	if (validate == false){
		alert("请上传正确的文件格式(支持：bmp,jpg,png,gif,jpeg)"); 
		return false;
	}
	$("#addcon").toggle();
	$("#addconing").toggle();
	return true;
}
</script>
</head>

<body>
<s:if test="newDir!=null">
<script type="text/javascript">
var picpath='<s:property value="newDir"/>';
$("#facepic",window.parent.document).val(picpath);
var d=new Date();
$("#picSrc",window.parent.document).attr("src",picpath+"?v="+d.getTime());
$("#picSrc",window.parent.document).show();
alert("上传成功");
self.parent.tb_remove();
</script>

</s:if>
<s:else>
<div id="addconing" style="display: none;">
    <table border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td width="190" height="30">
         上传中...
         </td>
      </tr>
    </table>
</div>
<div id="addcon">
<form class="upForm" action="/manager/comment/face/uploadImage"
				enctype="multipart/form-data" method="post"
				onsubmit="return check()">
				<input type="hidden" name="delPic" value="" id="delPic"/>
<span id="errormsg" style="display:none">
<s:fielderror name="struts.messages.error.content.type.not.allowed" theme="simple" ></s:fielderror>
<s:fielderror name="struts.messages.error.file.too.large" theme="simple" ></s:fielderror>
</span>
				<script>
					var errormsg=$.trim($("#errormsg").text());
					if(errormsg!=""){
						alert("支持：bmp,jpg,png,gif,jpeg,小于5MB");
					}
				</script>
    <table border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td width="190" height="30">
         <input type="file" id="uploadfile" name="filedata"/>
         </td>
      </tr>
      <tr>
        <td height="30" align="center"><input name="" type="submit" class="btn_add" value="上传"/></td>
      </tr>
    </table>
</form>
</div>
</s:else>
</body>
</html>
<%
Object msg=request.getAttribute("uperror");
if(msg!=null){
	out.println("<script type=\"\">alert('"+msg.toString()+"');</script>");
}
%>