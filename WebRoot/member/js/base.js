var conf=conf || {};
var defalutWord=window.conf ||{};
defalutWord={
	topSearch:"找人,视频,日志,照片"
};
conf={
	searchForm:function(){
		$('.search-top-int').focus(function(){
			var me=$(this);
			var me_val=me.val();
			if(me_val==defalutWord.topSearch){
				me.val(' ');
			}
		}).blur(function(){
			var me=$(this);
			var me_val=me.val();
			if(me_val===" "){
				me.val(defalutWord.topSearch);	
			}	
		});
		
	},
	commentsEvent:function(){
		//头部树状菜单
		$(".setup").mouseenter(function(){
			$(this).addClass("set-back");
		}).mouseleave(function(){
			$(this).removeClass("set-back");
		});
	},
	init:function(){
		conf.searchForm();
		conf.commentsEvent();
	}
	}
conf.init();