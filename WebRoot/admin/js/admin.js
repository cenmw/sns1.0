function updateCheckcode(){
	$("#checkcode").attr("src","/common/checkcode.jsp?t="+Math.random());
}
function check(){
	var username=$("#username").val();
	var userpassword=$("#userpassword").val();
	var usercheckcode=$("#usercheckcode").val();
	if(username==""){
		$("#username").focus();
		return false;
	}
	if(userpassword==""){
		$("#userpassword").focus();
		return false;
	}
	if(usercheckcode==""){
		$("#usercheckcode").focus();
		return false;
	}
	return true;
}

function adminlogin(){
  if(check()){
    	$("#managerLoginForm").submit();
  }
}