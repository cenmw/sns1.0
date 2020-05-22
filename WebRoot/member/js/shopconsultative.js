var shopconsultativedivid="member_inf";
function initShopConsultative(shopid){
	var zxurl="/consultative/memberGetShopConsultativeList?shopid="+shopid+"&pageSize="+20;
	var loadimg='<img src="/consultative/images/loading.gif"/>';
	$("#"+shopconsultativedivid).html(loadimg);
	$.ajax( {
		type : "GET",
		url : zxurl,
		success : function(data) {
			if(data==""){
				alert('请你先登陆');
				location.replace('/member/memberlogin.jsp');
			}else{
				$("#"+shopconsultativedivid).html(data);
			}
		}
	});
}
function showShopConsultativePage(gourl){
	if(gourl!="javascript:"){
		var loadimg='<img src="/consultative/images/loading.gif"/>';
		$("#"+shopconsultativedivid).html(loadimg);
		$.ajax( {
			type : "GET",
			url : gourl,
			success : function(data) {
				$("#"+shopconsultativedivid).html(data);
			}
		});
	}
}

