(function($) {
	$.fn.search = function(settings) {

		options = $.extend( {}, $.fn.search.defaults, settings);

		$(this).click(function() {
			var tag = $("#" + options.tagid).val();
			var t = $("#" + options.typeid).val();
			var url = "";
			var data = "";
			if (t == 1) {
				url = "/product/psearch";
				data = "tag=" + encodeURIComponent(tag);
				location.href = url + '?' + data;
			}
			if (t == 2) {
				url = "/shop/msearch";
				data = "tag=" + encodeURIComponent(tag);
				location.href = url + '?' + data;
			}
			if (t == 0) {
				url = "/content/csearch";
				data = "tag=" + encodeURIComponent(tag) + "&sid=" + sid;
				location.href = url + '?' + data;
			}
		});

		var zuixun = options.info;
		var shangpin = options.product;
		var shangpu = options.shop;

		var zuixun_s = options.info_s;
		var shangpin_s = options.product_s;
		var shangpu_s = options.shop_s;

		var st = $("#" + options.typeid).val();

		var hotkey = options.sk;
		var sid = '2';

		if (st == 1) {
			var arr = shangpin.split(",");
			var aostr = "";
			for ( var i = 0; i < arr.length; i++) {
				var link = "/product/psearch?" + "tag=" + encodeURIComponent(arr[i]);
				var ao = "<a href=\"" + link + "\">" + arr[i] + "</a>";
				aostr += ao + "";
				//$("."+hotkey).append(ao);
			}
			var aoobj = $("." + hotkey)[0];
			var aohtml = aoobj.innerHTML;
			aostr = aohtml + aostr;
			aoobj.innerHTML = aostr;
			if ($(".hotsearch")) {
				$(".hotsearch").each(
						function() {
							var sarr = shangpin_s.split(",");
							for ( var i = 0; i < sarr.length; i++) {
								var link = "/product/psearch?" + "tag="
										+ encodeURIComponent(sarr[i]);
								var ao = $("<a href=\"" + link + "\">"
										+ sarr[i] + "</a>");
								$(this).append(ao);
							}
						});
			}
		}
		if (st == 2) {
			var arr = shangpu.split(",");
			for ( var i = 0; i < arr.length; i++) {
				var link = "/shop/msearch?" + "tag=" + encodeURIComponent(arr[i]);
				var ao = $("<a href=\"" + link + "\">" + arr[i] + "</a>");
				$("." + hotkey).append(ao);
			}
			if ($(".hotsearch")) {
				$(".hotsearch").each(
						function() {
							var sarr = shangpu_s.split(",");
							for ( var i = 0; i < sarr.length; i++) {
								var link = "/shop/msearch?" + "tag=" + encodeURIComponent(sarr[i]);
								var ao = $("<a href=\"" + link + "\">"
										+ sarr[i] + "</a>");
								$(this).append(ao);
							}
						});
			}
		}
		if (st == 0) {
			var arr = zuixun.split(",");
			for ( var i = 0; i < arr.length; i++) {
				var link = "/content/csearch?" + "tag=" + encodeURIComponent(arr[i])
						+ "&sid=" + sid;
				var ao = $("<a href=\"" + link + "\">" + arr[i] + "</a>");
				$("." + hotkey).append(ao);
			}
			if ($(".hotsearch")) {
				$(".hotsearch").each(
						function() {
							var sarr = zuixun_s.split(",");
							for ( var i = 0; i < sarr.length; i++) {
								var link = "/content/csearch?"
										+ "tag=" + encodeURIComponent(sarr[i]) + "&sid="
										+ sid;
								var ao = $("<a href=\"" + link + "\">"
										+ sarr[i] + "</a>");
								$(this).append(ao);
							}
						});
			}
		}
	};
	$.fn.search.defaults = {
		sk : 'hot_key',
		tagid : 'tag',
		typeid : 'tid',
		info : '',
		product : '',
		shop : '',
		info_s : '',
		product_s : '',
		shop_s : ''
	};
})(jQuery);
function myfavorites(ftype, fid, fname) {
	var flink = location.href;
	$.ajax( {
		type : "POST",
		url : "/member/mfavorite",
		data : "ftype=" + ftype + "&fid=" + fid + "&fname=" + fname + "&flink="
				+ flink,
		success : function(data) {
			if (data == "1") {
				alert("收藏成功");
			}
			if (data == "0") {
				alert("收藏失败,请你先登陆后再收藏");
			}
			if (data == "2") {
				alert("已经放入你的收藏夹中");
			}
		}
	});
}