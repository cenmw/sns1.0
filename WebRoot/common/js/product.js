jQuery.fn.extend({
	scrollTo:function(speed) {
		var targetOffset = $(this).offset().top;
		$('html,body').stop().animate({scrollTop:targetOffset}, speed);
		return this;
	}
});
//产品页中。点击分类查询所有信息
function gotolinkpage(id,par){
	var _href = location.href;
	var hrefarr = _href.split(par);
    if(_href.indexOf(par)>0){
		 _href = _href.substring(0,_href.indexOf(par));
	 }
	 _href += par+id;

	if(hrefarr.length ==2){
		 if(hrefarr[1].length >1){
			//alert(hrefarr[1]);
		    var cc = hrefarr[1].split('&');
			//alert(cc.length);
		    if(cc.length >1){
				for(var i=1;i<cc.length;i++){
					_href += "&" + cc[i];
				}
			}
		 }
	 }
	location.href = _href ;
}

function gotosortpage(str1,str2){
	var _href = location.href;
	var hrefarr = _href.split("&sortname=");
	if(_href.indexOf("&sortname=")>0){
		_href = _href.substring(0,_href.indexOf("&sortname="));
	}
	_href += "&sortname="+str1;
	 
	if(hrefarr.length ==2){
		 if(hrefarr[1].length >1){
			 _href += hrefarr[1].substring(1,hrefarr[1].length);
		 }
	 }

	var hrefarr2 = _href.split("&sortvalue=");
	if(_href.indexOf("&sortvalue=")>0){
		_href = _href.substring(0,_href.indexOf("&sortvalue="));
	}
	_href += "&sortvalue="+str2;

	if(hrefarr2.length ==2){
		 if(hrefarr2[1].length >1){
			 _href += hrefarr2[1].substring(1,hrefarr2[1].length);
		 }
	 }
	location.href = _href ;
}

function gotopage(str){
	var _href = location.href;
	var hrefarr = _href.split("&mode=");
	if(str == 'list'){
		if(_href.indexOf("&mode=")>0){
			_href = _href.substring(0,_href.indexOf("&mode="));
		}
		_href += "&mode=1";
	}
	else if(str == 'biglist'){
		if(_href.indexOf("&mode=")>0){
			_href = _href.substring(0,_href.indexOf("&mode="));
		}
		_href += "&mode=0";
	}
	
	if(hrefarr.length ==2){
		 if(hrefarr[1].length >1){
			 _href += hrefarr[1].substring(1,hrefarr[1].length);
		 }
	 }
	//alert(_href);
	location.href = _href ;
}

//展开 收起 
function showqi(obj){
	var ppobj = document.getElementById("ppid");
	var shouqiobj = document.getElementById("shouqiid");
	var zhankaiobj = document.getElementById("zhankaiid");
	if(ppobj && shouqiobj && zhankaiobj){
		if(obj == '1'){ //品牌展开操作
		    ppobj.className = "tiaojian1";
			shouqiobj.style.display = "";
			zhankaiobj.style.display = "none";
		}else{
			ppobj.className = "tiaojian";
			shouqiobj.style.display = "none";
			zhankaiobj.style.display = "";
		}
	}
}