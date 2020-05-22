function deleteObj(url,backurl){
	if(confirm("确认删除吗")){
		location.replace(url+encodeURIComponent(backurl));
	}
}

function nodeleteObj(url,backurl){
	if(confirm("确认找回吗")){
		location.replace(url+encodeURIComponent(backurl));
	}
}
function fowardBack(url,backurl){
	location.href=url+encodeURIComponent(backurl);
}
//验证正整数
function checkZZS(val){
	var reg= /^[0-9]*[1-9][0-9]*$/;
	return reg.test(val);
}
//验证正数 精确两位小数
function checkZXX(t)
{
	var reg=/^[0-9]+([.]{1}[0-9]{1,2})?$/;
	return reg.test(t);
}
//验证正数 精确一位小数
function checkZX(t)
{
	var reg=/^[0-9]+([.]{1}[0-9])?$/;
	return reg.test(t);
}
//验证非负整数
function checkFFZS(t){
	var reg=/^\d+$/;
	return reg.test(t);
}
//计算天数
function compareDay(startDateStr,endDateStr){
	var start=Date.parse(startDateStr.replace(/-/g, "/"));
	var end=Date.parse(endDateStr.replace(/-/g, "/"));
	var days = parseInt((end - start) / 1000 / 60 / 60 /24);
	return days;
}
//验证是否为空
function checknull(id){
	var v=$.trim($("#"+id).val());
	if(v==''){
		return true;
	}
	return false;
}
