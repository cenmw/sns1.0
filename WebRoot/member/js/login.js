function checkLogin(){
	var account=$.trim($("#ac").val());
	var pwd=$.trim($("#pwd").val());
	//var checkcode=$.trim($("#cc").val());
	if(account==""){
		alert("请输入账号");
		return;
	}
	if(pwd==""){
		alert("请输入密码");
		return;
	}
	var issave=0;
	if($("#issave").attr("checked")){
		issave=1;
	}
	
	$("#login").val("登录中...");
	$("#login").attr("disabled","disabled"); 
	
	$.ajax( {
		type : "POST",
		url : "/front/member/login",
		data : "account=" + account+"&password="+pwd+"&issave="+issave+"&callback="+$("#callback").val(),
		success : function(data) {
				//location.replace('/member/memberloginsuccess.jsp');
			if (data=="0") {
				alert("用户名或密码错误");
			}else if(data =="2"){
				location.href="/member/memberloginsuccess.jsp";
			}else if(data =="3"){
				location.href="/member/memberloginsuccess.jsp";
			}else{
				if($("#callback").val()!=""){
					location.href=data+'&bak='+encodeURIComponent($("#callback").val());
				}else{
					location.href=data+'&bak='+encodeURIComponent('http://'+location.hostname+'/member/memberloginsuccess.jsp');
				}
			}
			$("#login").val("登录");
			$("#login").removeAttr("disabled");
		}
	});
	
}
function updateCheckcode(){
	$("#checkcode").attr("src","/common/checkcode.jsp?t="+Math.random());
}