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
<script type="text/javascript" src="/member/js/reg.js"></script>
<style type="text/css">
.ash_red{ color:#FF0000;}
.ash{ color:#666666;}
</style>
<script type="text/javascript">
$(function(){ 
	$('#birthday').datepicker( {
		yearRange : '1930:2050', //取值范围.
		showOn : 'both', //输入框和图片按钮都可以使用日历控件。
		buttonImage : '/common/datepicker/date.gif', //日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});
});

function checkcontentinfo(){
	var account=$.trim($("#account").val());
	if(account==""){
		alert("姓名不能为空");
		return false;
	}	
	return true;
}

KindEditor.ready(function(K) {
	var imageEditor = K.editor({
		allowFileManager : true,
		fileManagerJson : '/keditor/browseImageLists?foldername=uploadfiles,member',
		uploadJson : '/keditor/uploadImages?foldername=uploadfiles,member'
	});
	K('#imageUpload').click(function() {
		imageEditor.loadPlugin('image', function() {
			imageEditor.plugin.imageDialog({
				hideImageSet : true,
				imageUrl : K('#avatar').val(),
				clickFn : function(url, title, width, height, border, align) {
					K('#avatar').val(url);
					var date=new Date();
					var s=date.getTime();
					$('#avatar_s').attr("src",url+"?v="+s);
					$('#avatar_s').show();
					imageEditor.hideDialog();
				}
			});
		});
	});
	
	K('#cimageUpload').click(function() {
		imageEditor.loadPlugin('image', function() {
			imageEditor.plugin.imageDialog({
				hideImageSet : true,
				imageUrl : K('#cpicpath').val(),
				clickFn : function(url, title, width, height, border, align) {
					K('#cpicpath').val(url);
					var date=new Date();
					var s=date.getTime();
					$('#cpicpath_s').attr("src",url+"?v="+s);
					$('#cpicpath_s').show();
					imageEditor.hideDialog();
				}
			});
		});
	});
});

function checkManagerEmailAjax() {
    var mid = '<s:property value="memberInfo.id" />';
	if(mid == '' || mid == 'null'){
	   mid = 0;
	}
	var suc='<span class="succeed"></span>';
	var failure='<span class="failure"></span>';
	var t = true;
	var email = $.trim($("#m_email").val());
	if (email == "") {
		$("#m_emailem").html('邮箱不能为空'+failure).attr("class","ash_red");
		t = false;
	} else {
		if (!checkEmail(email)) {
			$("#m_emailem").html('邮箱格式不正确'+failure).attr("class","ash_red");
			t = false;
		}
	}
	if (t) {
		$("#m_emailem").html('验证中...');
		$.ajax( {
			type : "GET",
			url : "/manager/member/checkEmail",
			data : "email=" + email+"&mid="+mid+"&v="+new Date().getTime(),
			success : function(data) {
				if (data == 1) {
					$("#m_emailem").html('该邮箱已被注册'+failure).attr("class","ash_red");
				} else {
					$("#m_emailem").html('邮箱地址可用于登录您的账户和找回密码。'+suc).attr("class","ash");
				}
			}
		});
	}
}

function checkManagerMobileAjax() {
    var mid = '<s:property value="memberInfo.id" />';
	if(mid == '' || mid == 'null'){
	   mid = 0;
	}
	var t = true;
	var mobile = $.trim($("#m_mobile").val());
	if (mobile != "") {
	    if (!checkMobile(mobile)) {
		    $("#m_mobileem").html('请输入正确的手机号格式！').attr("class","ash_red");;
			t = false;
		}
		if (t) {
			$("#mobileem").html('验证中...');		
			$.ajax( {
				type : "GET",
				url : "/manager/member/checkMobile",
				data : "mobile=" + mobile +"&mid="+mid+"&v="+new Date().getTime(),
				success : function(data) {
					if (data == 1) {
						$("#m_mobileem").html('该手机号已经绑定其他用户，请更换手机号。').attr("class","ash_red");
					} else {
						$("#m_mobileem").html('手机号可用于登录您的账户。').attr("class","ash_blue");
					}
				}
			});
		}
	}
}

function initpwdAjax(){
   var mid = '<s:property value="memberInfo.id" />';
   if(mid != '' && mid != 'null'){
      if(confirm("确定初始化密码？")){
	     $.ajax( {
				type : "GET",
				url : "/manager/member/initpwd",
				data : "mid="+mid+"&v="+new Date().getTime(),
				success : function(data) {
					if (data == 1) {
						alert("初始化成功，密码为：123123");
					} else {
						alert("初始化失败");
					}
				}
			});
	  }
   }
}
</script>
</head>
<body>
	<div style="text-align: center;">
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
			<tr>
				<td align="left">
				<s:form action="save" namespace="/manager/member" method="post" theme="simple" onsubmit="return checkcontentinfo()">
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							<s:hidden name="backUrl"></s:hidden>
							<s:hidden name="memberInfo.id"></s:hidden>
							<s:hidden name="memberInfo.ctime"></s:hidden>
							<s:hidden name="memberInfo.isdel"></s:hidden>	
							<s:hidden name="memberInfo.lastlogintime"></s:hidden>
							<s:hidden name="memberInfo.password"></s:hidden>	
							<s:hidden name="memberInfo.sign"></s:hidden>	
							<s:hidden name="memberInfo.qq_uid"></s:hidden>
							<s:hidden name="memberInfo.sina_uid"></s:hidden>					
							<tr>
								<td colspan="2" class="yh">添加 / 修改 信息&nbsp;</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">类型</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:radio list="#{0:'普通会员&nbsp;&nbsp;',1:'会员&nbsp;&nbsp;'}" name="memberInfo.type" value="memberInfo.type"/>
								</td>
							</tr>
						    
							<tr>
								<td width="20%" class="yh">头像</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:hidden name="memberInfo.avatar" id="avatar"/>
								<s:if test="memberInfo==null">
								<img  style="display:none" width="60" height="60" id="avatar_s"/>
								</s:if>
								<s:elseif test="memberInfo.avatar==''||memberInfo.avatar==null">
								<img  style="display:none" width="60" height="60" id="avatar_s"/>
								</s:elseif>
								<s:else>
								<img src="<s:property value="memberInfo.avatar"/>" width="60" height="60" id="avatar_s"/>
								</s:else>
								<input type="button" id="imageUpload" value="选择图片" />								
								</td>
							</tr>	
							
							<tr>
								<td width="20%" class="yh">邮箱</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield id="m_email" onblur="checkManagerEmailAjax()" name="memberInfo.email" maxLength="30" cssClass="tin" style="width:400px;"/><span class="ash" id="m_emailem">小贴士：请使用常用的邮箱注册</span>
								&nbsp;&nbsp;<s:if test="memberInfo.id != null && memberInfo.id > 0"><a href="javascript:void(0);" onclick="initpwdAjax();">初始化密码（123123）</a></s:if></td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">真实姓名（名称）</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield id="account" name="memberInfo.account" maxLength="30" cssClass="tin" style="width:400px;"/>
								<em>*</em></td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">法人姓名</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield id="cname" name="memberInfo.cname" maxLength="30" cssClass="tin" style="width:400px;"/>
								<em>*</em></td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">性别</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:radio list="#{0:'男&nbsp;&nbsp;',1:'女&nbsp;&nbsp;'}" name="memberInfo.sex" value="memberInfo.sex"/>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">出生日期</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<input id="birthday" type="text" class="tin" style="width:100px;" readonly="readonly" name="memberInfo.birthday" value="<s:date name="memberInfo.birthday" format="yyyy-MM-dd"/>" />		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">家乡</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								 <s:hidden name="memberInfo.h_province"></s:hidden>
								 <s:hidden name="memberInfo.h_city"></s:hidden>
								 <s:hidden name="memberInfo.h_county"></s:hidden>
								<s:property value="memberInfo.h_province" /><s:property value="memberInfo.h_city" /><s:property value="memberInfo.h_county" />
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">职业</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="memberInfo.professional" cssClass="tin" maxLength="200"/>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">个人（）介绍</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:textarea cssClass="tarea" name="memberInfo.introduction" id="introduction" rows="5" cols="60"/>(500字)
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">营业执照</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:hidden name="memberInfo.cpicpath" id="cpicpath"/>
								<img src="<s:property value="memberInfo.cpicpath"/>" width="60" height="60" id="cpicpath_s"/>
								<input type="button" id="cimageUpload" value="选择图片" />		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">QQ</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="memberInfo.qq" cssClass="tin" maxLength="200"/>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">电话</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="memberInfo.telphone" cssClass="tin" maxLength="200"/>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">手机</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield id="m_mobile" onblur="checkManagerMobileAjax()" name="memberInfo.mobile" maxLength="30" cssClass="tin" style="width:400px;"/><span class="ash" id="m_mobileem">要求：手机号只能绑定一个账号，方便登录。</span>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">通信地址</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								 <s:hidden name="memberInfo.a_province"></s:hidden>
								 <s:hidden name="memberInfo.a_city"></s:hidden>
								 <s:hidden name="memberInfo.a_county"></s:hidden>
								 <s:hidden name="memberInfo.address"></s:hidden>
								<s:property value="memberInfo.a_province" /><s:property value="memberInfo.a_city" /><s:property value="memberInfo.a_county" /><s:property value="memberInfo.address" />
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">邮编</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield id="email" name="memberInfo.zcode" maxLength="30" cssClass="tin" style="width:400px;"/>
								</td>
							</tr>		
							
							<tr>
								<td width="20%" class="yh">状态</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="status" list="#{0:'未审核',1:'审核通过'}" name="memberInfo.status"></s:select>
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