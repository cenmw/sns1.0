$(function(){
	$("#login").click(function(){
		var account=$.trim($("#l_account").val());
		if(account==""){
			alert("请输入用户名!");
			return;
		}
		var password=$.trim($("#l_password").val());
		if(password==""){
			alert("请输入密码!");
			return;
		}
		var callback=$.trim($("#callback").val());
		var ajaxData='account='+account+'&password='+password;
		if(callback!=""){
			ajaxData+='&callback='+callback;
		}
		//$(this).val("绑定中...");
		//$(this).attr("disabled","disabled"); 
		var obj=$(this)[0];
		$.ajax({
			type:'POST',
			url:'/api/qq/login',
			data:ajaxData,
			success:function(data){
				if(data=="0"){
					alert("用户名或密码错误");
					//$(obj).val("绑定账号");
					//$(obj).removeAttr("disabled");
				}else if(data=="1"){
					alert("该帐号已被绑定!");
					//$(obj).val("绑定账号");
					//$(obj).removeAttr("disabled");
				}else if(data=="2"){
					location.href="/";
				}
			}
		});
	});
});