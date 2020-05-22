function checkPerfectMemberInfo(){
	var t=true;
	$("#selfintroductionem").html('');
	var selfintroduction=$.trim($("#selfintroduction").val());
	if(selfintroduction.length>200){
		t=false;
		$("#selfintroductionem").html('自我介绍长度超过200位');
	}
	$("#addressem").html('');
	var address=$.trim($("#address").val());
	if(address.length>200){
		t=false;
		$("#addressem").html('详细地址长度超过200位');
	}
	$("#telephoneem").html('');
	var telephone=$.trim($("#telephone").val());
	if(telephone!=""){
		if(!checkPhone(telephone)){
			t=false;
			$("#telephoneem").html('联系电话输入有误 如:0100-88888888');
		}
	}
	$("#mobileem").html('');
	var mobile=$.trim($("#mobile").val());
	if(mobile!=""){
		if(!checkMobile(mobile)){
			t=false;
			$("#mobileem").html('手机格式输入有误');
		}
	}
	$("#zipcodeem").html('');
	var zipcode=$.trim($("#zipcode").val());
	if(zipcode!=""){
		if(!checkPost(zipcode)){
			t=false;
			$("#zipcodeem").html('邮编格式输入有误');
		}
	}
	return t;
}
//验证电话
function checkPhone(val) {
	var reg = /^(((\()?\d{2,4}(\))?[-(\s)*]){0,2})?(\d{7,8})$/;
	return reg.test(val);
}
//验证手机
function checkMobile(val) {
	var reg = /^[1][3,5,8][0-9]{9}$/;
	return reg.test(val);
}
//邮政编码判断
function checkPost(val){   
	var pattern= /^[0-9]{6}$/;
	return pattern.test(val);
} 