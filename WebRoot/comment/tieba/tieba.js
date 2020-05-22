//动态加载 js,css
$.extend({
    CommentIncludePath: '',
    CommentInclude: function(file)
    {
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++)
        {
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='" + $.CommentIncludePath + name + "'";
            if ($(tag + "[" + link + "]").length == 0) document.write("<" + tag + attr + link + "></" + tag + ">");
        }
    }
});
$.CommentInclude(['/ajax/login/thickbox.css','/ajax/login/thickbox.js']);

function To_loginWindow(s){
	var t = null;
	var a = '/ajax/login.jsp?call='+s+'&placeValuesBeforeTB_=savedValues&TB_iframe=true&height=330&width=505&modal=true';
	var g = false;
	tb_show(t,a,g);
	$(document).blur();
}

function tb_callback(){//回复 -2
	
}
function tb2_callback(){//发表  -4
	publishTiezi();
}