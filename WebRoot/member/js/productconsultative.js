var productconsultativedivid="member_inf1";
function initProductConsultative(shopid){
	var zxurl="/consultative/memberGetProductConsultativeList?shopid="+shopid+"&pageSize="+20;
	var loadimg='<img src="/consultative/images/loading.gif"/>';
	$("#"+productconsultativedivid).html(loadimg);
	$.ajax( {
		type : "GET",
		url : zxurl,
		success : function(data) {
			$("#"+productconsultativedivid).html(data);
		}
	});
}
function showProductConsultativePage(gourl){
	if(gourl!='javascript:'){
		var loadimg='<img src="/consultative/images/loading.gif"/>';
		$("#"+productconsultativedivid).html(loadimg);
			$.ajax( {
			type : "GET",
			url : gourl,
			success : function(data) {
				$("#"+productconsultativedivid).html(data);
			}
		});
	}
	
	
}

