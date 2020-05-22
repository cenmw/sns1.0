function createXMLHttpRequest(){   
  
if(window.XMLHttpRequest){   
  
XMLHttpR = new XMLHttpRequest();   
  
}else if(window.ActiveXObject){   
  
try{   
  
XMLHttpR = new ActiveXObject("Msxml2.XMLHTTP");   
  
}catch(e){   
  
try{   
  
XMLHttpR = new ActiveXObject("Microsoft.XMLHTTP");   
  
}catch(e){   
  
}   
  
}   
  
}   
  
}   
  
function sendRequest(url){   
  
createXMLHttpRequest();   
  
XMLHttpR.open("GET",url,true);   
  
XMLHttpR.setRequestHeader("Content-Type","text/html;charset=utf-8");   
  
XMLHttpR.onreadystatechange = processResponse;   
  
XMLHttpR.send(null);   
  
}   
  
function processResponse(){   
  
if(XMLHttpR.readyState ==4 && XMLHttpR.status == 200){   
  
document.write(XMLHttpR.responseText);   
  
}   
  
}

//返回操作
function fowardBack(url,backurl){
	location.href=url+encodeURIComponent(backurl);
}

//赞，取消赞
function updateMemberPraiseAJAX(type,cid,domid){
   var url="/membercenter/updateMemberPraiseAJAX?type="+type+"&cid="+cid;
		$.ajax( {
			type : "GET",
			url : url,
			success : function(data) {
				var _data = data;
				//alert(data);
				if(_data.indexOf("-")){
					var _data_arr = _data.split("-");
					var isdel = _data_arr[0];
					var status = _data_arr[1];
					var isdels="赞";
					if(isdel==0){
						isdels="取消赞";
					}
					$("#"+domid).html(isdels+"("+status+")");	
				}
										    
				//alert(data);			
			}
	});
}

//转载
function zz(type,id,mid,curmid){
	if(curmid == mid){
		alert("不允许转载自己发布的信息");
	}else{
	    $.fn.popup({iframe:true,url:'/membercenter/zz?type='+type+'&cid='+id,type:3,title:'转载',width:400,height:200});
	}
}

//举报
function jb(type,cid,mid,rid){
	if(rid != '' && rid == mid){
		alert("不允许举报自己发布的信息");
	}else if(rid != ''){
		$.fn.popup({iframe:true,url:'/membercenter/reportinfo?type='+type+'&cid='+cid+'&mid='+mid+'&rid='+rid,type:3,title:'举报',width:400,height:260});
	}
}

//收藏
function addMemberCollectionAJAX(type,cid,mid,curmid){
   if(curmid == mid){
		alert("不允许收藏自己发布的信息");
   }else{	
        var url="/membercenter/addMemberCollectionAJAX?type="+type+"&cid="+cid;
		$.ajax( {
			type : "GET",
			url : url,
			success : function(data) {
				var _data = data;
				//alert(data);
				if(data=='1'){
					alert("已经成功收藏");
				}else{
				    alert("您已经成功收藏");	
				}
										    
				//alert(data);			
			}
	});
	}
}

//内容页评论
function conpl(){
    document.getElementById("commentEditor").focus();	
}

//控制照片显示比例问题
function fixImage(i,w,h){ 
    var ow = i.width; 
    var oh = i.height; 
	if(ow>w || oh>h){
	    var rw = w/ow; 
		var rh = h/oh; 
		var r = Math.min(rw,rh); 
		if (w ==0 && h == 0){ 
			r = 1; 
		}else if (w == 0){ 
			r = rh<1?rh:1; 
		}else if (h == 0){ 
			r = rw<1?rw:1; 
		} 
		if (ow!=0 && oh!=0){ 
		i.width = ow * r; 
		i.height = oh * r; 
		}else{ 
		  var __method = this, args = $A(arguments); 
			window.setTimeout(function() { 
			  fixImage.apply(__method, args); 
			}, 200); 
		} 
		i.onload = function(){} 
	}
}

//控制照片显示比例问题
function fixImage(i,w){ 
    var ow = i.width; 
    var oh = i.height; 
	if(ow>w){
		i.width = w; 
		i.onload = function(){} 
	}
} 

//添加好友
function addfriend(fid,type){
	$.fn.popup({iframe:true,url:'/membercenter/addfriend?fid='+fid+'&type='+type,type:3,title:'添加好友申请',width:400,height:200});
}

//判断字节长度
function zjlen(s) {
	var l = 0;
	var a = s.split("");
	for (var i=0;i<a.length;i++) {
		if (a[i].charCodeAt(0)<299) {
			l++;
		} else {
			l+=2;
		}
	}
	return l;
}

//查看机构详情
function showfriend(fid){
	$.fn.popup({iframe:true,url:'/membercenter/showfriend?fid='+fid,type:3,title:'查看机构详情',width:600,height:400});
}

function closebox2(){
	window.parent.document.body.removeChild(window.parent.document.getElementById("_overlay"));
	window.parent.document.body.removeChild(window.parent.document.getElementById("popup"));
}	

//更多动态
var page = 1;
var curhover = 1;


function getMoreStateAjax(type,curmid){
	$(".loading-more-a").attr('href', 'javascript:');
	$(".loading-more-a").html("数据加载中......请稍等");
    var url="/membercenter/getMoreStateAjax?type="+type+"&page="+page;
	page++;
	$.ajax( {
		type : "GET",
		url : url,
		success : function(data) {
			$("#"+curmid).append(data);	
			$(".loading-more-a").attr('href', "javascript:getMoreStateAjaxMore("+type+",'stateid')");
			$(".loading-more-a").html("更多");
		}
	});
	
}

function getMoreState9Ajax(type,curmid,qx){
	$(".loading-more-a").attr('href', 'javascript:');
	$(".loading-more-a").html("数据加载中......请稍等");
    var url="/membercenter/getMoreState9Ajax?type="+type+"&page="+page+"&qx="+qx;
	page++;
	$.ajax( {
		type : "GET",
		url : url,
		success : function(data) {
			$("#"+curmid).append(data);	
			$(".loading-more-a").attr('href', "javascript:getMoreStateAjaxMore("+type+",'stateid')");
			$(".loading-more-a").html("更多");
		}
	});
	
}

function getMoreStateAjaxDIV(type,curmid){
	$("#state1ID").addClass("cur");
	$("#state8ID").removeClass();
	$("#state9ID").removeClass();
	$("#state88ID").removeClass();
	$("#state99ID").removeClass();
	page = 1;
	curhover = 1;
	$("#"+curmid).html("");	
	getMoreStateAjax(type,curmid);
}

function getMoreState9AjaxDIV(type,curmid){
	$("#state1ID").removeClass();
	$("#state8ID").removeClass();
	$("#state9ID").addClass("cur");
	$("#state88ID").removeClass();
	$("#state99ID").removeClass();
	page = 1;
	curhover = 9;
	$("#"+curmid).html("");	
	getMoreState9Ajax(9,curmid,-1);
}

function getMoreState99AjaxDIV(type,curmid){
	$("#state1ID").removeClass();
	$("#state8ID").removeClass();
	$("#state99ID").addClass("cur");
	$("#state88ID").removeClass();
	$("#state9ID").removeClass();
	page = 1;
	curhover = 99;
	$("#"+curmid).html("");	
	getMoreState9Ajax(9,curmid,2);
}

function getMoreState8AjaxDIV(type,curmid){
	$("#state1ID").removeClass();
	$("#state8ID").removeClass();
	$("#state9ID").addClass("cur");
	$("#state88ID").removeClass();
	$("#state99ID").removeClass();
	page = 1;
	curhover = 8;
	$("#"+curmid).html("");	
	getMoreState9Ajax(8,curmid,-1);
}

function getMoreState10AjaxDIV(type,curmid){
	$("#state1ID").removeClass();
	$("#state88ID").addClass("cur");
	$("#state9ID").removeClass();
	$("#state8ID").removeClass();
	$("#state99ID").removeClass();
	page = 1;
	curhover = 10;
	$("#"+curmid).html("");
	getMoreState9Ajax(10,curmid,-1);
}

function getMoreState88AjaxDIV(type,curmid){
	$("#state1ID").removeClass();
	$("#state8ID").removeClass();
	$("#state99ID").addClass("cur");
	$("#state88ID").removeClass();
	$("#state9ID").removeClass();
	page = 1;
	curhover = 88;
	$("#"+curmid).html("");	
	getMoreState9Ajax(8,curmid,2);
}

function getMoreStateAjaxMore(type,curmid){
	if(curhover ==1){
		getMoreStateAjax(type,curmid);
	}else if(curhover ==9){
		getMoreState9Ajax(type,curmid, -1);
	}else if(curhover ==99){
		getMoreState99Ajax(type,curmid);
	}else if(curhover ==8){
		getMoreState8Ajax(type,curmid);
	}else if(curhover ==88){
		getMoreState88Ajax(type,curmid);
	}else if(curhover ==10){
		getMoreState9Ajax(9,curmid, -1);
	}
}