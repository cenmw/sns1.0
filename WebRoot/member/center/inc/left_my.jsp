<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<div class="left content-left fl">
    	<div class="member">
        	<span class="member-img"><img src="<s:property value="memberInfo.avatar_small" />" height="50" width="50" /></span>
            <div class="member-right">
            	<h2><a href="javascript:void(0)" onclick="location.replace('/membercenter/basis')"><s:property value="memberInfo.account" /></a></h2>
                <p class="vip-val"><s:property value="memberInfo.sumscore" /></p>
                <h2 style="font-size:12px;"><a href="javascript:void(0)" onclick="location.replace('/membercenter/storelist')">交易记录</a></h2>
            </div>
        </div>
		<div style="height:40px;">
			<b >余额：</b>
<span class="wel"><s:property value="memberInfo.sumprice" escape="false"/></span>
<span>元</span>
<input type="button" style=" margin:5px 0px; width:68px; height:20px; background:url(/member/images/but_zxcz.gif) no-repeat; border:none; cursor:pointer;" onclick="window.open('/membercenter/storeinfo','_blank');" name="Submit">
        </div>
		<div class="left-nav mar-t10">
			 <ul>
				<!-- <li id="left1" class="left-menu1"><a href="/membercenter/myfblaborlist"><i></i>所有发布</a></li> 
				<li id="left2" class="left-menu2"><a href="/membercenter/allfxlist"><i></i>所有分享</a></li> -->
			
				<li id="left1" class="left-menu12"><a href="/membercenter/friendlist"><i></i>我的好友</a></li>
				<li id="left2" class="left-menu16"><a href="/membercenter/mysdlist"><i></i>我要疏导</a></li>
                <li id="left19" class="left-menu4"><a href="/membercenter/wrsslist"><i></i>吾日省身</a></li>
				<!-- <li id="left3" class="left-menu5"><a href="/membercenter/myfxlist"><i></i>我要分享</a></li> -->
                <li id="left4" class="left-menu4"><a href="/membercenter/consultclasslist"><i></i>训练中心</a></li>
                <li id="left17" class="left-menu9"><a href="/membercenter/xgyclist"><i></i>习惯养成</a></li>
                <li id="left11" class="left-menu10"><a href="/membercenter/zhoulist"><i></i>我的52周</a></li>
                <li id="left18" class="left-menu14"><a href="/membercenter/myzylist"><i></i>问题区</a></li>
                 <!-- <li id="left5" class="left-menu8"><a href="/membercenter/laborlist"><i></i>我要活动</a></li> -->
				<!-- <li id="left6" class="left-menu6"><a href="/membercenter/myzylist"><i></i>我的作业</a></li>  -->
				<li id="left7" class="left-menu3"><a href="/membercenter/messagelist"><i></i>我的消息<span style="color:#FF0000">(<s:property value="ismessagenum" escape="false" />)</span></a></li>
                 <!-- <li id="left8" class="left-menu4"><a href="/membercenter/lljllist"><i></i>浏览记录</a></li> -->
                 <li id="left9" class="left-menu15"><a href="/membercenter/vediocollectionlist?type=5"><i></i>我的收藏 </a></li>
                 <!-- <li id="left9" class="left-menu9"><a href="javascript:"><i></i>我要学习</a></li> -->
				<!-- <li id="left10" class="left-menu3"><a href="/membercenter/orderlist"><i></i>我的订单</a></li> -->
				<!-- <li id="left9" class="left-menu4"><a href="/membercenter/topicclasslist"><i></i>我要测试</a></li> -->
				<!-- <li id="left10" class="left-menu6"><a href="/membercenter/learnclasslist"><i></i>我要学习</a></li> -->
				<!-- <li id="left12" class="left-menu11"><a href="/membercenter/familylist"><i></i>我的家庭</a></li> -->
				<!-- <li id="left13" class="left-menu12"><a href="/membercenter/zxzxlist"><i></i>我的“众校之星”</a></li> -->
				<li id="left14" class="left-menu11"><a href="/membercenter/basis"><i></i>修改个人资料</a></li>
                 <!-- <li id="left15" class="left-menu17"><a href="/membercenter/policelist"><i></i>警察局</a></li> -->
                 <li id="left16" class="left-menu18"><a href="/membercenter/loginout"><i></i>退出系统</a></li>
             </ul>
         </div>
 </div>
 <script type="text/javascript">
 var _left_href = location.href;

 if(_left_href.indexOf("friend")>0){
    document.getElementById("left1").className="left-menu12 cur";
 }else if(_left_href.indexOf("mysd")>0){
    document.getElementById("left2").className="left-menu16 cur";
 }else if(_left_href.indexOf("myfx")>0){
    document.getElementById("left3").className="left-menu5 cur";
 }else if(_left_href.indexOf("consult")>0 || _left_href.indexOf("myzy")>0){
    document.getElementById("left4").className="left-menu4 cur";
 }else if(_left_href.indexOf("labor")>0){
    document.getElementById("left5").className="left-menu8 cur";
 }else if(_left_href.indexOf("myzy")>0){
    document.getElementById("left6").className="left-menu6 cur";
 }else if(_left_href.indexOf("message")>0){
    document.getElementById("left7").className="left-menu3 cur";
 }else if(_left_href.indexOf("lljl")>0){
    document.getElementById("left8").className="left-menu4 cur";
 }else if(_left_href.indexOf("topic")>0){
    document.getElementById("left9").className="left-menu15 cur";
 }else if(_left_href.indexOf("learn")>0){
    document.getElementById("left10").className="left-menu3 cur";
 }else if(_left_href.indexOf("zhou")>0){
    document.getElementById("left11").className="left-menu10 cur";
 }else if(_left_href.indexOf("family")>0){
    document.getElementById("left12").className="left-menu11 cur";
 }else if(_left_href.indexOf("zxzx")>0){
    document.getElementById("left13").className="left-menu12 cur";
 }else if(_left_href.indexOf("basis")>0){
    document.getElementById("left14").className="left-menu11 cur";
 }else if(_left_href.indexOf("police")>0){
    document.getElementById("left15").className="left-menu17 cur";
 }else if(_left_href.indexOf("xgyc")>0){
     document.getElementById("left17").className="left-menu9 cur";
 }else if(_left_href.indexOf("myzy")>0){
     document.getElementById("left18").className="left-menu14 cur";
 }else if(_left_href.indexOf("wrss")>0){
     document.getElementById("left19").className="left-menu4 cur";
 }
 </script>