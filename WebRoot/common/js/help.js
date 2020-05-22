//收藏
function onFavorites(id,name,curlink){
   var ftype=3;
   var fid=id;
   var fname=name;	
   var flink=curlink;
   var mtype='<s:property value="#session.memberInfo.type"/>';
		if(mtype==''){
			alert("请你先登陆后再收藏");
		}
		if(mtype=='0'){
			$.ajax( {
				type : "POST",
				url : "/member/mfavorite",
				data : "ftype="+ftype+"&fid="+fid+"&fname="+fname+"&flink="+flink,
				success : function(data) {
					if(data=="1"){
						alert("收藏成功");
					}
					if(data=="0"){
						alert("收藏失败,请你先登陆后再收藏");
					}
					if(data=="2"){
						alert("已经放入你的收藏夹中");
					}
				}
		    });
		}	
}