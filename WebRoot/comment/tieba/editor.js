var insertReplyFaceRange;
var em_pageSize = 70;
(function($) {
	var insertFaceRange;
	$.fn.extend( {
		insertFace : function(title, value) {
			$(this).focus();
			if (document.selection) {
				var sel;
				if (insertFaceRange) {
					sel = insertFaceRange;
				} else {
					sel = document.selection.createRange();
				}
				if($(this)[0].innerHTML==""){
					sel.pasteHTML('<img class="imgface" src="' + value + '" title="'+title+'" alt="' + title+ '"/> ');
				}else{
					sel.pasteHTML('<img class="imgface" src="' + value + '" title="'+title+'" alt="' + title+ '"/>');
				}
				sel.collapse(false);
				sel.select();
				sel = null;
			} else {
				var r;
				if (insertFaceRange) {
					r=insertFaceRange;
				}else{
					r = window.getSelection().getRangeAt(0);
				}
				var img = document.createElement("img");
				img.src = value;
				img.alt = title;
				img.title = title;
				img.className="imgface";
				//r.deleteContents();
				r.insertNode(img);
				r.setEndAfter(img);
				r.collapse(false);
				window.getSelection().removeAllRanges();//清除range
　　				window.getSelection().addRange(r);//设置range 
			}
		},
		insertReplyFace : function(title, value) {
			$(this).focus();
			if (document.selection) {
				var sel;
				if (insertReplyFaceRange) {
					sel = insertReplyFaceRange;
				} else {
					sel = document.selection.createRange();
				}
				if($(this)[0].innerHTML==""){
					sel.pasteHTML('<img class="imgface" src="' + value + '" alt="' + title+ '"/> ');
				}else{
					sel.pasteHTML('<img class="imgface" src="' + value + '" alt="' + title+ '"/>');
				}
				sel.collapse(false);
				sel.select();
				sel = null;
			} else {
				var r;
				if (insertReplyFaceRange) {
					r=insertReplyFaceRange;
				}else{
					r = window.getSelection().getRangeAt(0);
				}
				var img = document.createElement("img");
				img.src = value;
				img.alt = title;
				img.className="imgface";
				//r.deleteContents();
				r.insertNode(img);
				r.setEndAfter(img);
				r.collapse(false);
				window.getSelection().removeAllRanges();//清除range
　　				window.getSelection().addRange(r);//设置range 
			}
		},
		getCommentContentLen : function() {
			var obj = $(this)[0];
			var str = obj.innerHTML.replace(
					/<img(\s*\w*?\s*=\s*".*?")*(\s*?>)/gi, "*");
			str = str.replace(/<br(\s*\w*?\s*=\s*".*?")*(\s*?>)/gi, "");
			var len = str.replace(/[^\x00-\xff]/g, "aa").length;
			len = Math.round(len / 2);
			return len;
		}
	})
	$.fn.CommentEditor = function(settings) {
		options = $.extend($.fn.CommentEditor.defaults, settings);
		$(this).attr("contentEditable", "true");
		$(this)[0].innerHTML = "";
		var _oninput = options.onInput;
		$(this).bind("keyup", function(event) {
			$.updateInsertFaceRange();
			if ($.isFunction(_oninput)) {
				var _txtLen = $(this).getCommentContentLen();
				_oninput.call(this, event, {
					txtLen : _txtLen
				});
			}
		})
		
		$(this).bind("mouseup", function(event) {
			$.updateInsertFaceRange();
		})
		$(this).bind("keydown",function(event){
	  		if (event.keyCode == 13) { 
				if (document.selection) {
					var sel;
					if (insertFaceRange) {
						sel = insertFaceRange;
					} else {
						sel = document.selection.createRange();
					}
					sel.pasteHTML('<br/>');
					sel.collapse(false);
					sel.select();
					sel = null;
					return false;
				} 
	  		}
	  	});
		$.updateInsertFaceRange=function(){
			if (document.selection) {
				insertFaceRange = document.selection.createRange();
			} else {
				insertFaceRange = window.getSelection().getRangeAt(0);
			}
			$(".comentFace_Box").remove();
		}
	};
	$.fn.CommentEditor.defaults = {
		onInput : null
	};

})(jQuery);
(function($) {
	$.extend({
		commentFaceHTML:null
	});
	$.fn.CommentFace = function(settings) {
		options = $.extend( {}, $.fn.CommentFace.defaults, settings);
		var $commentFaceTar=$("#"+options.insertFaceId);
		
		var currentPage = 1;
		var $_cf = $(this);
		var currentPageCount = 0;
		$.createCommentFaceHTML = function(data, cpage) {
			currentPage = cpage;
			var _str = '<div class="comentFace_bg">';
			_str += '<div class="comentFace_listbox">';
			_str += '<ul class="commentFace_panel_default">';
			if (data) {
				var rows = data.length;
				var pageSize = em_pageSize;
				var pageCount = Math.floor((rows + pageSize - 1) / pageSize);
				currentPageCount = pageCount;
				var offset = (cpage - 1) * pageSize;
				var end = offset + pageSize;
				if (end > rows) {
					end = rows;
				}
				for ( var i = offset; i < end; i++) {
					var title = data[i].title;
					var pic = data[i].pic;
					_str += '<li><a href="#"><img class="faceimg" src="' + pic
							+ '" alt="' + title + '" title="'+title+'"/></a></li>';
				}
			}
			_str += '</ul>';
			_str += '</div>';
			_str += '<div class="comentFace_page">';
			_str += '<a class="face_page" href="#">上一页</a><span>' + cpage + '/'
					+ pageCount
					+ '</span><a class="face_page" href="#">下一页</a>';
			_str += '</div>';
			_str += '</div>';
			return _str;
		}
		$.showCommentFace = function(data, $cf, cpage) {
			var _str = $.createCommentFaceHTML(data, cpage);
			$("#comentFace_Box").empty();
			$("#comentFace_Box").append(_str);
			var t = $cf.outerHeight(true);
			var ot=$cf.offset();
			var faceBodyHeight=ot.top+t+$("#comentFace_Box").outerHeight(true);
			var bodyHeight=$("#publish_reply_con").outerHeight(true);
			if(faceBodyHeight>bodyHeight){
				t=-$("#comentFace_Box").outerHeight(true);
			}
			
			$("#comentFace_Box").css("top", t + "px");
			$("#comentFace_Box").css("left", "0px");
			$("#comentFace_Box").show();
			$(".face_page").one("click", function() {
				var s = $(this).text();
				if (s == "上一页") {
					var cpage = currentPage - 1;
					if (cpage > 0) {
						$.showCommentFace($.commentFaceHTML, $_cf, cpage);
					}
				} else if (s == "下一页") {
					var cpage = currentPage + 1;
					if (cpage <= currentPageCount) {
						$.showCommentFace($.commentFaceHTML, $_cf, cpage);
					}
				}
				return false;
			});
			$(".faceimg").bind("click", function() {
				var alt = $(this).attr("alt");
				var pic = $(this).attr("src");
				$commentFaceTar.insertFace(alt, pic);
				$("#comentFace_Box").remove();
				return false;
			});
		}
		$(this).bind(
				"click",
				function(event) {
					$(this).css("position", "relative");
					if (!$("#comentFace_Box")[0]) {
						$(this).append('<div id="comentFace_Box" tabindex="1"></div>');
					}
					if ($.commentFaceHTML) {
						$.showCommentFace($.commentFaceHTML, $_cf, currentPage);
					} else {
						$("#comentFace_Box").append(
								"<img src=\"/common/images/ajax-load.gif\"/>");
						$.ajax( {
							type : 'get',
							url : '/comment/facelist',
							success : function(data) {
								$.commentFaceHTML = data;
								$.showCommentFace(data, $_cf, currentPage);
							}
						});
					}
					event = event || window.event;
					event.stopPropagation();
				});
		$(document).click(function() {
			$("#comentFace_Box").remove();
		});
	};
	$.fn.CommentFace.defaults = {insertFaceId:'commentEditor'};
	
	$.extend({
		CommentReplyEditorObj:{id:0,isshow:false}
	});
	$.fn.extend({
		CommentReplyEditor:function(){
			var commentReplyEdt="commentReplyEdt";
			var commentReplyConID="commentReply_";
			var faceBtn="biaoqing";
			$.createCommentReplyHTML=function(replyid){
				var str='<div class="con" id="commentReplyEdtBox">';
				str+='<div class="tied" id="'+commentReplyEdt+'" contentEditable="true"></div>';
				str+='</div>';
				str+='<div class="con">';
				str+='<div class="left_con">';
				str+='<span class="'+faceBtn+'"><b><a href="javascript:void(0);" class="face"></a></b></span>';
				str+='</div>';
				str+='<div class="right_con"><a href="#" rel="'+replyid+'" class="reply_cancel">取消</a><b><a href="#" rel="'+replyid+'" class="release ct_reply"></a></b></div>';
				str+='</div>';
				//var str='<div id="commentReplyEdtBox"><div class="huifu_con" id="'+commentReplyEdt+'" contentEditable="true"></div>';
				//str+='<div class="huifu_con0">';
				//str+='<div class="biaoqing"><img src="images/lian.jpg" width="16" height="16" />表情</div>';
				//str+='<div class="fabu"><input type="button" rel="'+replyid+'" name="button" id="button" value=" " class="fabu_btn" /></div>';
				//str+='<div class="clear"></div>';
				//str+='</div></div>';
				return str;
			}
			$(this).live("click",function(e){
				var replyid_str="rel";
				if($.CommentReplyEditorObj.id==0&&!$.CommentReplyEditorObj.isshow){
					var replyid=$(this).attr(replyid_str);
					$.CommentReplyEditorObj.id=replyid;
					$("#"+commentReplyConID+replyid).append($.createCommentReplyHTML(replyid));
					$.CommentReplyEditorObj.isshow=true;
				}else if($.CommentReplyEditorObj.id>0&&!$.CommentReplyEditorObj.isshow){
					var replyid=$(this).attr(replyid_str);
					$.CommentReplyEditorObj.id=replyid;
					var _s=$.createCommentReplyHTML(replyid);
					$("#"+commentReplyConID+replyid).append(_s);
					$.CommentReplyEditorObj.isshow=true;
				}else if($.CommentReplyEditorObj.id>0&&$.CommentReplyEditorObj.isshow){
					var replyid=$(this).attr(replyid_str);
					$("#"+commentReplyConID+$.CommentReplyEditorObj.id).empty();
					if($.CommentReplyEditorObj.id==replyid){
						$.CommentReplyEditorObj.isshow=false;
					}else{
						$.CommentReplyEditorObj.id=replyid;
						$("#"+commentReplyConID+replyid).append($.createCommentReplyHTML(replyid));
						$.CommentReplyEditorObj.isshow=true;
					}
				}
				
				$("#"+commentReplyEdt).bind("postpaste",function(){
					var newData=$(this).html();
					newData=filterPasteData(newData);
					$(this).empty();
					$(this).append(newData);
					return false;
				}).pasteEvents();
				
				$("#"+commentReplyEdt).focus();
				$.updateInsertReplyFaceRange();
				$("#"+commentReplyEdt).bind("keyup", function(event) {
					$.updateInsertReplyFaceRange();
					
				})
				
				$("#"+commentReplyEdt).bind("mouseup", function(event) {
					$.updateInsertReplyFaceRange();
				})
				$("#"+commentReplyEdt).bind("keydown",function(event){
			  		if (event.keyCode == 13) { 
						if (document.selection) {
							var sel;
							if (insertReplyFaceRange) {
								sel = insertReplyFaceRange;
							} else {
								sel = document.selection.createRange();
							}
							sel.pasteHTML('<br/>');
							sel.collapse(false);
							sel.select();
							sel = null;
							return false;
						} 
			  		}
			  	});
				$(".reply_cancel").bind("click",function(){
					var replyid=$(this).attr(replyid_str);
					$.CommentReplyEditorObj.id=0;
					$("#"+commentReplyConID+replyid).empty();
					$.CommentReplyEditorObj.isshow=false;
					return false;
				});
				$(".ct_reply").bind("click",function(){
					var replyid=$(this).attr(replyid_str);
					var postUrl='/smallregion/xk/commentreply';
					var info=$.trim($("#commentReplyEdt").html());
					if(info==""){
						alert("请输入回复内容 !");
						$("#commentReplyEdt").focus();
						return;
					}
					var postData='info='+encodeURIComponent(info)+'&qid='+replyid;
					$.ajax({
						type:'post',
						url:postUrl,
						data:postData,
						success:function(state){
							if(state==0){
								To_loginWindow(2);
							}else if(state==200){
								alert('回复成功!');
								window.location.reload();
							}
						}
					});
					return false;
				});
				$("."+faceBtn).bind("click",function(event){
					//$(this).css("position", "relative");
					
					$faceBtnObj=$(this);
					if (!$(".comentFace_Box")[0]) {
						$(this).append('<div class="comentFace_Box" tabindex="1"></div>');
					}
					if ($.commentFaceHTML) {
						$.showCommentReplyFace($.commentFaceHTML, $faceBtnObj, currentPage);
					}else{
						$(".comentFace_Box").append(
									"<img src=\"/common/images/ajax-load.gif\"/>");
						$.ajax( {
							type : 'get',
							url : '/comment/facelist',
							success : function(data) {
								$.commentFaceHTML = data;
								$.showCommentReplyFace(data, $faceBtnObj, currentPage);
							}
						});
					}
					event = event || window.event;
					event.stopPropagation();
				});
				return false;
			})
//			var $commentReplyFaceTarget=$("#"+commentReplyEdt);
			var currentPage = 1;
			var $_cf = $(this);
			var currentPageCount = 0;
			$.createCommentReplyFaceHTML = function(data, cpage) {
				currentPage = cpage;
				var _str = '<div class="comentFace_bg">';
				_str += '<div class="comentFace_listbox">';
				_str += '<ul class="commentFace_panel_default">';
				if (data) {
					var rows = data.length;
					var pageSize = em_pageSize;
					var pageCount = Math.floor((rows + pageSize - 1) / pageSize);
					currentPageCount = pageCount;
					var offset = (cpage - 1) * pageSize;
					var end = offset + pageSize;
					if (end > rows) {
						end = rows;
					}
					for ( var i = offset; i < end; i++) {
						var title = data[i].title;
						var pic = data[i].pic;
						_str += '<li><a href="#"><img class="replyfaceimg" src="' + pic
								+ '" alt="' + title + '" title="'+title+'"/></a></li>';
					}
				}
				_str += '</ul>';
				_str += '</div>';
				_str += '<div class="comentFace_page">';
				_str += '<a class="replyface_page" href="javascript:void(0);">上一页</a><span>' + cpage + '/'
						+ pageCount
						+ '</span><a class="replyface_page" href="javascript:void(0);">下一页</a>';
				_str += '</div>';
				_str += '</div>';
				return _str;
			}
			$.showCommentReplyFace = function(data, $cf, cpage) {
				var _str = $.createCommentReplyFaceHTML(data, cpage);
				$(".comentFace_Box").empty();
				$(".comentFace_Box").append(_str);
				var t = $cf.outerHeight(true);
				
				var ot=$cf.offset();
				var top=ot.top+20;
				var left=ot.left;
				var bodyHeight=$("body").outerHeight(true);
				var faceHeight=223;
				if((top+faceHeight)>bodyHeight){
					top=top-faceHeight-20;
				}
				$(".comentFace_Box").css("top", top + "px");
				$(".comentFace_Box").css("left", left+"px");
				$(".comentFace_Box").show();
				$(".replyface_page").one("click", function() {
					var s = $(this).text();
					if (s == "上一页") {
						var cpage = currentPage - 1;
						if (cpage > 0) {
							$.showCommentReplyFace($.commentFaceHTML, $_cf, cpage);
						}
					} else if (s == "下一页") {
						var cpage = currentPage + 1;
						if (cpage <= currentPageCount) {
							$.showCommentReplyFace($.commentFaceHTML, $_cf, cpage);
						}
					}
					return false;
				});
				$(".replyfaceimg").bind("click", function() {
					var alt = $(this).attr("alt");
					var pic = $(this).attr("src");
					$("#"+commentReplyEdt).insertReplyFace(alt, pic);
					$(".comentFace_Box").remove();
					return false;
				});
			}
			$.updateInsertReplyFaceRange=function(){
				if (document.selection) {
					insertReplyFaceRange = document.selection.createRange();
				} else {
					insertReplyFaceRange = window.getSelection().getRangeAt(0);
				}
				
			}
			
			$(document).click(function() {
				$(".comentFace_Box").remove();
			});
		}
	});
	
$.fn.pasteEvents = function( delay ) {
    if (delay == undefined) delay = 20;
    return $(this).each(function() {
        var $el = $(this);
        $el.bind("paste", function() {
            $el.trigger("prepaste");
            setTimeout(function() { $el.trigger("postpaste"); }, delay);
        });
    });
};
	
})(jQuery);





function filterPasteWord(str)
{
    //remove link break
    str = str.replace(/\r\n|\n|\r/ig, "");
    //remove &nbsp; entities at the start of contents
    str = str.replace(/^\s*(&nbsp;)+/ig,"");
    //remove &nbsp; entities at the end of contents
    str = str.replace(/(&nbsp;|<br[^>]*>)+\s*$/ig,"");
    // Remove comments, scripts (e.g., msoShowComment), XML tag, VML content, MS Office namespaced tags, and a few other tags
    str = str.replace(/<(!|script[^>]*>.*?<\/script(?=[>\s])|\/?(\?xml(:\w+)?|xml|img|meta|link|style|\w:\w+)(?=[\s\/>]))[^>]*>/gi,"");

    //convert word headers to strong
    str = str.replace(/<p [^>]*class="?MsoHeading"?[^>]*>(.*?)<\/p>/gi, "<p><strong>$1</strong></p>");
    //remove lang attribute
    str = str.replace(/(lang)\s*=\s*([\'\"]?)[\w-]+\2/ig, "");
    // Examine all styles: delete junk, transform some, and keep the rest
    str = str.replace(/(<[a-z][^>]*)\sstyle="([^"]*)"/gi,function(str, tag, style) {
                                var n = [],
                                    i = 0,
                                    s = style.trim().replace(/&quot;/gi, "'").split(";");

                                // Examine each style definition within the tag's style attribute
                                for(var i=0;i<s.length;i++)
                                {
                                    v=s[i];
                                    var name, value,
                                    parts = v.split(":");

                                    if (parts.length == 2) {
                                        name = parts[0].toLowerCase();
                                        value = parts[1].toLowerCase();
                                        // Translate certain MS Office styles into their CSS equivalents
                                        switch (name) {
                                            case "mso-padding-alt":
                                            case "mso-padding-top-alt":
                                            case "mso-padding-right-alt":
                                            case "mso-padding-bottom-alt":
                                            case "mso-padding-left-alt":
                                            case "mso-margin-alt":
                                            case "mso-margin-top-alt":
                                            case "mso-margin-right-alt":
                                            case "mso-margin-bottom-alt":
                                            case "mso-margin-left-alt":
                                            case "mso-table-layout-alt":
                                            case "mso-height":
                                            case "mso-width":
                                            case "mso-vertical-align-alt":
                                                n[i++] = name.replace(/^mso-|-alt$/g, "") + ":" + ensureUnits(value);
                                                continue;

                                            case "horiz-align":
                                                n[i++] = "text-align:" + value;
                                                continue;

                                            case "vert-align":
                                                n[i++] = "vertical-align:" + value;
                                                continue;

                                            case "font-color":
                                            case "mso-foreground":
                                                n[i++] = "color:" + value;
                                                continue;

                                            case "mso-background":
                                            case "mso-highlight":
                                                n[i++] = "background:" + value;
                                                continue;

                                            case "mso-default-height":
                                                n[i++] = "min-height:" + ensureUnits(value);
                                                continue;

                                            case "mso-default-width":
                                                n[i++] = "min-width:" + ensureUnits(value);
                                                continue;

                                            case "mso-padding-between-alt":
                                                n[i++] = "border-collapse:separate;border-spacing:" + ensureUnits(value);
                                                continue;

                                            case "text-line-through":
                                                if ((value == "single") || (value == "double")) {
                                                    n[i++] = "text-decoration:line-through";
                                                }
                                                continue;

                                            case "mso-zero-height":
                                                if (value == "yes") {
                                                    n[i++] = "display:none";
                                                }
                                                continue;
                                        }
                                        // Eliminate all MS Office style definitions that have no CSS equivalent by examining the first characters in the name
                                        if (/^(mso|column|font-emph|lang|layout|line-break|list-image|nav|panose|punct|row|ruby|sep|size|src|tab-|table-border|text-(?:align|decor|indent|trans)|top-bar|version|vnd|word-break)/.test(name)) {
                                            continue;
                                        }
                                        // If it reached this point, it must be a valid CSS style
                                        n[i++] = name + ":" + parts[1];        // Lower-case name, but keep value case
                                    }
                                }
                                // If style attribute contained any valid styles the re-write it; otherwise delete style attribute.
                                if (i > 0) {
                                    return tag + ' style="' + n.join(';') + '"';
                                } else {
                                    return tag;
                                }
                            });

    return str;
}
function filterPasteText(str)
{
	str = str.replace(/\r\n|\n|\r/ig, "");
	//remove html body form
	str = str.replace(/<\/?(html|body|form)(?=[\s\/>])[^>]*>/ig, "");
	//remove doctype
	str = str.replace(/<(!DOCTYPE)(\n|.)*?>/ig, "");
	// Word comments like conditional comments etc
    	str = str.replace(/<!--[\s\S]*?-->/ig, "");    
	//remove xml tags
	str = str.replace(/<(\/?(\?xml(:\w+)?|xml|\w+:\w+)(?=[\s\/>]))[^>]*>/gi,"");
	//remove head
	str = str.replace(/<head[^>]*>(\n|.)*?<\/head>/ig, "");
	//remove <xxx /> 
	str = str.replace(/<(script|style|link|title|meta|textarea|option|select|iframe|hr)(\n|.)*?\/>/ig, "");
	//remove empty span
	str = str.replace(/<span[^>]*?><\/span>/ig, "");
	//remove <xxx>...</xxx>
	str = str.replace(/<(head|script|style|textarea|button|select|option|iframe)[^>]*>(\n|.)*?<\/\1>/ig, "");
	//remove table and <a> tag, <img> tag,<input> tag (this can help filter unclosed tag)
	//str = str.replace(/<\/?(a|table|tr|td|tbody|thead|th|img|input|iframe|div)[^>]*>/ig, "");
	str = str.replace(/<\/?(a|table|tr|td|tbody|thead|th|input|iframe|div)[^>]*>/ig, "");
	//remove bad attributes
	do {
		len = str.length;
		str = str.replace(/(<[a-z][^>]*\s)(?:id|name|language|type|class|on\w+|\w+:\w+)=(?:"[^"]*"|\w+)\s?/gi, "$1");
	} while (len != str.length);
	
	return str;
}
function isWordDocument(strValue)
{
			var re=new RegExp(/(class=\"?Mso|style=\"[^\"]*\bmso\-|w:WordDocument)/ig);
			return re.test(strValue);
}
function filterPasteData(originalText)
{
	if(isWordDocument(originalText))
	{
		originalText=filterPasteWord(originalText);
	}
	return filterPasteText(originalText);
}



$(function(){
	//初始化编辑器和表情窗口
	$("#commentEditor").CommentEditor({});

	
	$("#commentEditor").bind("postpaste",function(){
		var newData=$("#commentEditor").html();
		newData=filterPasteData(newData);
		$("#commentEditor").empty();
		$("#commentEditor").append(newData);
		return false;
	}).pasteEvents();
	
	
	
	$("#commentFace").CommentFace({insertFaceId:'commentEditor'});
});


//加载列表区缩略图的函数，用于onload调用
function loadPic(mImg) {
	var tempImg = document.createElement('img');
	mImg.onload = null;
	mImg.loading = true;
	tempImg.onload = function() {
		resizePic_temp(tempImg,400,75);
		mImg.style.width = tempImg.style.width;
		mImg.style.height = tempImg.style.height;
		mImg.src = tempImg.src;
		mImg.loaded = true;
		mImg.loading = null;
		this.onload = null;
	}
	tempImg.src = mImg.getAttribute('original');
}
function resizePic_temp(o, Mw, Mh, need_margin) {
    var _Mw = Mw || 120;
    var _Mh = Mh || 120;
    var need_resize = false;
    var _image = new Image();
    _image.src = o.src;
    function getRightWH(Rw, Rh, Mw, Mh) {
        var index = 0,
        _Rw = Rw,
        _Rh = Rh;
        if (Rw > Mw) index += 1;
        if (Rh > Mh) index += 2;
        switch (index) {
        case 1:
            _Rw = Mw;
            _Rw = Rh * Mw / Rw;
        case 2:
            _Rh = Mh;
            _Rw = Rw * Mh / Rh;
        case 3:
            _Rh = (Rh / Mh > Rw / Mw) ? Mh: Rh * Mw / Rw;
            _Rw = (Rh / Mh > Rw / Mw) ? Rw * Mh / Rh: Mw
        }
        if (index != 0) {
            need_resize = true
        }
        return [_Rw, _Rh]
    }
    var wh = getRightWH(_image.width, _image.height, _Mw, _Mh);
    o.style.width = wh[0] + 'px';
    o.style.height = wh[1] + 'px';
    o.style.visibility = 'visible';
    if (need_margin == true) {
        o.style.marginTop = (Mh - parseInt(wh[1])) / 2 + 'px'
    }
    _image = null;
    return need_resize
}

