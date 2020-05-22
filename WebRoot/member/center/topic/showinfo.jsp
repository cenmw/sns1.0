<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-测试中心-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<style type="text/css">
.inpjs {
    border: 1px solid #000000;
    text-align: center;
	height:26px;
}

.inpxhx {
    text-align: center;
	height:26px;
	-moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    background-color: #FFFFFF;
    border-color: -moz-use-text-color -moz-use-text-color #000000;
    border-image: none;
    border-style: none none solid;
    border-width: 0 0 1px;
    color: #333333;
    font: 16px 宋体,Arial,sans-serif;
    margin: 0 5px;
    text-align: center;
}
</style>
<script type="text/javascript">
var curpage = 1;
var page = <s:property value="topicInfo.page" escape="false" />;
//显示上一页，下一页，保存按钮的控制
function showlastnext(){
	if(page == 1){   
	   document.getElementById("lastpageid").style.display="none";
	   document.getElementById("nextpageid").style.display="none";
	   document.getElementById("submitpageid").style.display="";
	}else if(curpage < page){
	   document.getElementById("lastpageid").style.display="none";
	   document.getElementById("nextpageid").style.display="";
	   document.getElementById("submitpageid").style.display="none";
	   if(curpage>1){
	      document.getElementById("lastpageid").style.display="";
	   }
	}else if(curpage == page){
	   document.getElementById("lastpageid").style.display="";
	   document.getElementById("nextpageid").style.display="none";
	   document.getElementById("submitpageid").style.display="";
	}  
}
//下一页
function nextpage(){
   if(page > 1){
      for(var i=1;i<=page;i++){
         document.getElementById("topicpage"+i).style.display="none";
      }
   }
   ++curpage;
   if(curpage<=page){
      document.getElementById("topicpage"+curpage).style.display=""; 
   }
   showlastnext();
}
//上一页
function lastpage(){  
   if(page > 1){
      for(var i=1;i<=page;i++){
         document.getElementById("topicpage"+i).style.display="none";
      }
   }
   --curpage;
   if(curpage<=page){
      document.getElementById("topicpage"+curpage).style.display=""; 
   }
   showlastnext();
}

function checkcontentinfo(){
	var topictext=document.getElementsByName("topictext");
	var result = "";
	if(topictext.length>0){
	   for(i=0;i<topictext.length;i++){
	       var lti = topictext[i].value;
		   if(lti == ""){
		      lti = " ";
		   }
	       if(i>0){
		      result+="||"+lti;
		   }else{
		      result+=lti;
		   }
	   }
	}	
	document.getElementById("resultid").value = result;
	//alert(document.getElementById("resultid").value);
	document.getElementById("topiclogfrmid").submit();
}
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
   <div class="content-right fl">
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>测试中心</h2>
	
	  <div class="vedio-list">
        	<div class="pl-title">
            	<h1><s:property value="topicInfo.title" escape="false" /></h1>
                <p class="title-infor"><span><s:date name="topicInfo.ctime" format="yyyy-MM-dd"/></span><span>点击量：<s:property value="topicInfo.viewnumber" escape="false" /></span></p>
            </div>
			<form name="topiclogfrm" id="topiclogfrmid" method="post" action="/membercenter/topiclogsave">
			<input type="hidden" name="tid" value="<s:property value="topicInfo.id" escape="false" />" />
			<input type="hidden" name="backUrl" value="<s:property value="backUrl" escape="false" />" />
			<input type="hidden" name="result" id="resultid" value="" />
            <div class="pl-content" style="padding-left:30px; line-height:35px;"><s:property value="topicInfo.tcontent" escape="false" /></div>        
            <div class="clear"></div>
            </form> 
			
			<!--分享到-->
			<div style="width:240px; height:30px; margin:15px auto;padding-left:560px;">
			<!-- Baidu Button BEGIN -->
			<div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
			<span style="color:#000000;" class="bds_more">分享到：</span>
			<a class="bds_qzone"></a>
			<a class="bds_tsina"></a>
			<a class="bds_tqq"></a>
			<a class="bds_renren"></a>
			<a class="bds_t163"></a>
			<a class="shareCount"></a>
			</div>
			<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6883689" ></script>
			<script type="text/javascript" id="bdshell_js"></script>
			<script type="text/javascript">
			document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
			</script>
			<!-- Baidu Button END -->
			</div>
			<!--分享到 end-->
			
			<div style="margin:10px; text-align:left; padding-left:30px;">
			<a id="lastpageid" class="blue" href="javascript:" onclick="lastpage()"><<上一页</a>
			&nbsp;&nbsp;<a id="nextpageid" class="blue" href="javascript:" onclick="nextpage()">下一页>></a>	
			&nbsp;&nbsp;<input type="image" src="/member/images/queren.png" id="submitpageid" onclick="checkcontentinfo()"/>	   
			</div>
			   
        </div>
<script>//初始化
showlastnext();
if(page > 1){
   for(var i=2;i<=page;i++){
      document.getElementById("topicpage"+i).style.display="none";
   }
}
</script>
  </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
<jsp:include page="/common/scrollfollow.jsp"></jsp:include>
</body>
</html>