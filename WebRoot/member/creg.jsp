<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册机构用户-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/reg.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
<style type="text/css">
.ash_red{ color:#FF0000;}
.ash{ color:#666666;}
</style>
<script>
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
				imageUrl : K('#cpicpath').val(),
				clickFn : function(url, title, width, height, border, align) {
					K('#cpicpath').val(url);
					var date=new Date();
					var s=date.getTime();
					$('#cpicpath_s').attr("src",url+"?v="+s);
					$('#cpicpath_s').show();
					imageEditor.hideDialog();
					$('#avatal_saveid').show();
				}
			});
		});
	});
});

$(function(){
	$("#m_account").keyup(function(){	    
		var account = $.trim($("#m_account").val());
		if(account != ''){
		   $("#accountem").html('');
		}
	});  
	$("#m_account").keyup(function(){	    
		var account = $.trim($("#m_account").val());
		if(account != ''){
		   $("#accountem").html('');
		}
	}); 
	$("#m_cname").keyup(function(){	    
		var cname = $.trim($("#m_cname").val());
		if(cname != ''){
		   $("#cnameem").html('');
		}
	});
	$("#m_telphone").keyup(function(){	    
		var telphone = $.trim($("#m_telphone").val());
		if(telphone != ''){
		   $("#telphoneem").html('');
		}
	}); 
	$("#cpicpath").keyup(function(){	    
		var cpicpath = $.trim($("#cpicpath").val());
		if(cpicpath != ''){
		   $("#cpicpathem").html('');
		}
	});  
});
</script>
</head>
<body>
<!--top-->
<div class="login-top">
	<div class="layout-control">
    	<a class="login-logo fl" href="/"></a>
        <div class="fr login-r">已有龙爸爸帐号，<a class="blue" href="/">登录</a></div>
    </div>
</div>
<!--top end-->
<!--content--> 
<form action="cregsave" id="regForm" namespace="/" method="post">
<input type="hidden" name="memberInfo.type" value="1" />
<s:hidden name="memberInfo.sex"></s:hidden>	 
<div class="layout-control mar-t20">
	<div class="regester">
    	<h3 class="regester-tt">注册机构用户</h3>
        <div class="regester-content">
            <ul class="regester-list">
                <li><span class="regester-l fl">E-mail:</span><div class="regester-r fl"><input onblur="checkEmailAjax()" maxLength="30" name="memberInfo.email" id="m_email" class="reg-text" type="text" value="" /><span class="ash" id="m_emailem">小贴士：请使用常用的邮箱注册</span></div></li>                              
                <li><span class="regester-l fl">密码:</span><div class="regester-r fl"><input name="memberInfo.password" type="password" maxLength="50" class="reg-text" value=""  onblur="checkPwd()" id="password"/><p class="clear-infor">6-12个字符组成，区分大小写，不能为9位以下的纯数字</p></div></li>				
				<li><span class="regester-l fl">机构名称:</span><div class="regester-r fl"><input name="memberInfo.account" id="m_account" class="reg-text" type="text" value="" /><span class="ash" id="accountem">必填</span></div></li>
				<li><span class="regester-l fl">法人名称:</span><div class="regester-r fl"><input name="memberInfo.cname" id="m_cname" class="reg-text" type="text" value="" /><span class="ash" id="cnameem">必填</span></div></li> 
				<li><span class="regester-l fl">机构电话:</span><div class="regester-r fl"><input name="memberInfo.telphone" id="m_telphone" class="reg-text" type="text" value="" /><span class="ash" id="telphoneem">必填</span></div></li> 
				<li><span class="regester-l fl">营业执照:</span><div class="regester-r fl"><input id="cpicpath" type="hidden" value="<s:property value="memberInfo.cpicpath" escape="false" />" name="memberInfo.cpicpath"><img id="cpicpath_s" src="<s:property value="memberInfo.cpicpath" escape="false" />" height="120" width="120" border="0" /><input type="button" id="imageUpload" value="选择图片" />&nbsp;&nbsp;<span class="ash" id="cpicpathem">必须上传</span></div></li> 
                <li><span class="regester-l fl">验证码:</span><div class="regester-r fl">
				        <input style="float:left;" maxlength="4" class="reg-text" ype="text" name="checkcode" id="code" value="" />
						<span class="authcode_img" style="float:left;"><img style="padding-top:10px;float:left;" id="checkcode" onclick="javascript:updateCheckcode()" src="/common/checkcode.jsp"/></span>
						<span class="see" style="padding-top:12px;padding-left:6px;float:left;"><a href="javascript:updateCheckcode()">看不清楚，换一张</a></span>
						<span class="ash" id="codeem" style="padding-top:12px;padding-left:6px;float:left;"></span>
					</div>
				</li>
                <li><span class="regester-l fl">&nbsp;</span><div class="regester-r fl"><input class="reg-login" type="button" onClick="checkCRegMember()" value="" /></div></li>
            </ul>
        </div>
    </div>
</div>
</form>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>