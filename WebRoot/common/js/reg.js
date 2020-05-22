function $obj(id){
	return document.getElementById(id);
}
function checkusername()
{
	var user_name = $obj("username").value;
	var chemkName = new RegExp("^[a-zA-Z][a-z0-9]+$");
	var re= /^[a-zA-Z]+$/; 
	var div=$obj("divusername");
	if(user_name=="" || user_name==null){
		div.innerHTML ="&nbsp;请输入登陆名";
		return false;
	}else if(!chemkName.test(user_name)){
		div.innerHTML ="&nbsp;请您输入由英文字母开头的6-20字母和数字，不支持中文和特殊字符（@、#、$、%等）。";
		return false;
	}else if(user_name.length<6){
		div.innerHTML ="&nbsp;登陆名至少由6个写英文字母和数字字符组成!";
	  	return false;
	}else{
		div.innerHTML ="";
	}
	return true;
}
function checkemail(){
	var mail=$obj("email").value;
	var div = $obj("divemail");
	var chemEmail=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	if(mail==""||mail.length<=0){
	  div.innerHTML ="&nbsp;请输入邮箱";
	  return false;
	}else if (!chemEmail.test(mail)){
		div.innerHTML="&nbsp;您输入的电子邮箱格式不正确，请重新填写。如：abc@yahoo.cn";
		return false;
	}else{
		div.innerHTML ="";
	}
	return true;
}
function checkpassword(state, value)
{
	var pwd = null;
	if(value==0){
		pwd=$obj("password");
	}
	if(value==1){
		pwd=$obj("confirmpass");
	}
	
	var div = $obj("divpassword" + value);
	var passwordLowcase = pwd.value.toLowerCase();
	
	if(state)
	{
		div.innerHTML = value ? "&nbsp;请再输入一遍您上面填写的密码!" : "&nbsp;6-20个英文字母或数字，不要包含登录名、姓名等个人信息，不要使用相同和连续的字母或数字。";
		return false;
	}
	else
	{  
		div.innerHTML = ""
		var az = 'abcdefghijklmnopqrstuvwxyz';
		if(pwd.value.length == 0){
			if(value==0){
				div.innerHTML = "&nbsp;请输入密码";
			}
			if(value==1){
				div.innerHTML = "&nbsp;请输入确认密码";
			}
		}else if(!/\w/.test(pwd.value)){
			div.innerHTML = "&nbsp;" + (value ? "" : "确认") + "密码由6-20个英文字母(区分大小写)或数字组成 !";
			return false;
		}
		else if(value == 1 && $obj("password").value != $obj("confirmpass").value){
			div.innerHTML = "&nbsp;两次密码输入不一致!";
			return false;
		}
		else if(pwd.value.length < 6){
			div.innerHTML = "&nbsp;" + ("密码长度不得小于6位，请重新输入!");
			return false;
		}
		//连续的数字	
		else if('0123456789'.indexOf(passwordLowcase) > -1 || '9876543210'.indexOf(passwordLowcase)>-1) {
			div.innerHTML = "&nbsp;请不要使用连续的数字做为密码!";
			return false;
		}
		//连续的大写字母
		else if(pwd.value.toUpperCase() == pwd.value && (az.indexOf(passwordLowcase)>-1 || az.indexOf(passwordLowcase.split('').reverse().join(''))>-1)){
			div.innerHTML = "&nbsp;请不要使用连续的大写字母做为密码!";
			return false;
		}
		//连续的小写字母
		else if(passwordLowcase == pwd.value && (az.indexOf(passwordLowcase)>-1 || az.indexOf(passwordLowcase.split('').reverse().join(''))>-1)) {
			
			div.innerHTML = "&nbsp;请不要使用连续的小写字母做为密码!";
			return false;
		}
		//相同的数字
		else if(/^(\d)\1+$/.test(passwordLowcase)) {
			div.innerHTML = "&nbsp;请不要使用相同的数字做为密码!";
			return false;
		}
		//相同的字母
		else if(/^([a-zA-Z])\1+$/.test(passwordLowcase)) {
			div.innerHTML = "&nbsp;请不要使用相同的字母做为密码!";
			return false;
		}
		else{
			if(value==0){
				div.innerHTML="";
			}
		}
		
	}
	return true;
}