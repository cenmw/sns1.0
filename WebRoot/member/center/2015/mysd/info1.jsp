<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的疏导-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
function delfriend(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/frienddelete?ids="+id+"&type=0";
   }
}

var timerc=0; //全局时间变量（秒数）
var numberc=0;  //次数
function add(){ //加时函数
	if(numberc<7){
        if(timerc < 3){ //如果不到7秒
            ++timerc; //时间变量自增1
            $("#min").html(parseInt(timerc)); //写入秒数
            setTimeout("add()", 1000); //设置1000毫秒以后执行一次本函数
        }else{
		    ++numberc; //次数变量自增1
			if(numberc==1){
			   $("#r_textid").html('<h3>再放松，深呼吸第2次</h3>'); 
			   timerc=0;
			   add();
			}else if(numberc==2){
			   $("#r_textid").html('<h3>继续，深呼吸第3次</h3>'); 
			   timerc=0;
			   add();
			}else if(numberc==3){
			   $("#r_textid").html('<h3>再继续，深呼吸第4次</h3>'); 
			   timerc=0;
			   add();
			}else if(numberc==4){
			   $("#r_textid").html('<h3>再继续放松，深呼吸第5次</h3>'); 
			   timerc=0;
			   add();
			}else if(numberc==5){
			   $("#r_textid").html('<h3>放松，深呼吸第6次</h3>'); 
			   timerc=0;
			   add();
			}else if(numberc==6){
			   $("#r_textid").html('<h3>再放松，深呼吸第7次</h3>'); 
			   timerc=0;
			   add();
			}else{
			   $("#r_textid").html("<a class='blue' href='mysdinfo2?type=<s:property value="type" escape="false" />'>下一页>></a>");
			}
			
		}
	}
};
	
$(document).ready(function () {
	$("#reading").click(function(){
		$("#r_textid").html('<h3>请轻松，深呼吸第1次</h3>');
	    add();
	})
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>我的疏导</h2>
<table class="note-tabs1" width="640" border="0" cellspacing="0" cellpadding="0"> 
  <tr>
    <td height="50" align="center" valign="top"><font class="font14"></font></td>
    <td height="50">事情已经发生了，请您先放松，我们一起来面对，请点击<span class="blue" style="font-weight:bold; cursor:pointer;" id="reading">准备</span>，我就开始了</td>
  </tr>  
  
  <tr>
    <td height="50" align="center" valign="top">&nbsp;</td>
    <td height="50" id="r_textid"></td>
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
