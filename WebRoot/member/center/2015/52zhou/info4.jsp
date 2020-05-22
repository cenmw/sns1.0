<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的52周-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
// 初始化数组信息
var size = <s:property value="contentList.size" escape="false"/>;
var arrayObj = new Array([size]);　//创建一个数组并指定长度，注意不是上限，是长度
<s:iterator value="contentList" id="contentlist" status="contentlist1">  
    arrayObj[<s:property value="#contentlist1.index" escape="false"/>] = '<s:property value="contentlist" escape="false"/>';
</s:iterator>

var numberc=0;  //次数
var z_dataHtmlArr ;
var z_add_content ;
var for_i =0;
// 调用开始，显示信息主方法
function add(add_content){
   var dataHtmlArr = add_content.split("||");
   if(dataHtmlArr.length >1){
       z_dataHtmlArr = dataHtmlArr;
       add_infos();
   }else{
       z_add_content = add_content;
       add_info();
   }
}

function add_infos(){ //一行行显示
	if(numberc<z_dataHtmlArr.length && z_dataHtmlArr[numberc] != ''){
	    $("#r_textid").html(z_dataHtmlArr[numberc]+'<br />');
	    setTimeout("add_infos()", 3000); //设置3000毫秒以后执行一次本函数
	    ++numberc; //次数变量自增1
	}else{
		numberc=0;
		$("#r_textid").html("");
		z_dataHtmlArr = "";
		for_i++;
		//alert(for_i +"="+size);
		if(for_i < size){
		   add(arrayObj[for_i]);
		}else if(for_i == size){
	   	   $("#curpagetr").css('display','');
	    }
	}
}

function add_info(){ //逐字显示
	if(numberc<z_add_content.length){
	    $("#r_textid").append(z_add_content[numberc]);
	    setTimeout("add_info()", 500); //设置500毫秒以后执行一次本函数
	    ++numberc; //次数变量自增1
	}else{
	    numberc=0;
		$("#r_textid").html("");
		z_add_conten = "";
		for_i++;
		//alert(for_i +"="+size);
	    if(for_i < size){
		   add(arrayObj[for_i]);
		}else if(for_i == size){
	   	   $("#curpagetr").css('display','');
	    }
	}
}

$(document).ready(function () {
	add(arrayObj[0]);
});
</script>
</head>
<body>
<!--top-->
<s:action name="top" namespace="/membercenter" executeResult="true">
</s:action>
<!--top end-->
<!--content-->
<div class="content1 layout-control">
    <!--left-->
    <s:action name="left" namespace="/membercenter" executeResult="true">
    </s:action>
    <!--left end-->
    <!--right-->
   <!--right-->
   <div class="content-right fl">
   		<h2 class="second-title"><a class="blue" href="/membercenter/zhouinfo"><<返回上一页</a>我的52周</h2>
<table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0" style="margin:30px auto;font-size:14px;"> 
  
  <tr>
    <td height="50" align="center" valign="top">&nbsp;</td>
    <td height="50" align="center" id="r_textid"></td>
  </tr>
  
  <tr id="curpagetr" style="display:none;">
    <td height="50" align="center" valign="top">&nbsp;</td>
    <td height="50">这周完成了。</td>
  </tr>
  
</table>
		
   </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
