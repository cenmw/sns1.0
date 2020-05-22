function checkMemberLogin(){
	var account=$.cookie('M_account');
	var pwd=$.cookie('M_password');
	$.ajax( {
		type : "GET",
		url : "/front/member/checkHaveMember",
		data : "account="+account+"&password="+pwd,
		success : function(data) {
			if(data!=""){
				var json=eval('('+data+')');
				var id=json.id;
				var account=json.account;
				var pwd=json.password;
				$("#ldiv").hide();
				var loginstr='欢迎您,<a href="/member/showMemberCenter">'+account+'</a>';
				var shopid=json.shopid;
				if(shopid>0){
					loginstr+='&nbsp;&nbsp;<a href="/shop/findShopInfo?id='+shopid+'">查看商铺</a>';
				}
				$("#mdiv").prepend(loginstr);
				$("#mdiv").show();
				$("#mdiv1").show();				
			}
		}
	});
}

function checkCar(){
	$.ajax( {
		type : "GET",
		url : "/cart/indexAJAX",
		data : "",
		success : function(data) {
			if(data!=""){
				var dataarr = data.split("-||-");
				//alert(dataarr[0]);	
				//alert(dataarr[1]);
				$("#carnumberid").prepend(dataarr[0]);
				$("#topcar").prepend(dataarr[1]);
			}
		}
	});
}
$(function(){  
	checkMemberLogin();
	checkCar();
	$("#g_login").click(function(){
		location.href='http://www.100tiao1.net/member/memberlogin.jsp?callback='+encodeURIComponent(location.href);
	});
	$("#g_reg").click(function(){
		location.href="http://www.100tiao1.net/member/reg.jsp";
	});
	$("#g_loginOut").click(function(){
		location.href="http://www.100tiao1.net/front/member/loginOut?callback="+encodeURIComponent(location.href);
	});
});