<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<div class="left content-left fl">
    	<div class="member">
        	<span class="member-img"><img src="<s:property value="memberInfo.avatar_small" />" height="50" width="50" /></span>
            <div class="member-right">
            	<h2><a href="javascript:void(0)" onclick="location.replace('/membercenter/basis')"><s:property value="memberInfo.account" /></a></h2>
                <p class="vip-val"><s:property value="memberInfo.sumscore" /></p>
                <p class="mar-t5">认证信息</p>
            </div>
        </div>
		<div class="left-nav mar-t10">
			 <ul>
				<li id="left4" class="left-menu4"><a href="/membercenter/contentlist"><i></i>我的文集</a></li>				
				<li id="left7" class="left-menu7"><a href="/membercenter/vediolist"><i></i>我的视频</a></li>
				<li id="left8" class="left-menu8"><a href="/membercenter/cfriendlist"><i></i>我的关注</a></li>				
				<li id="left10" class="left-menu10"><a href="/membercenter/messagelist"><i></i>我的私信<span style="color:#FF0000">(<s:property value="ismessagenum" escape="false" />)</span></a></li>
				<li class="left-common">公共应用</li>
				<li id="left11" class="left-menu11"><a href="/membercenter/consultclasslist"><i></i>咨询中心</a></li>
				<li id="left14" class="left-menu14"><a href="/membercenter/laboralllist"><i></i>活动中心<span style="color:#FF0000">(<s:property value="activitynum" escape="false" />)</span></a></li>
				<li id="left15" class="left-menu17"><a href="/membercenter/policelist"><i></i>警察局</a></li>
				<li id="left18" class="left-menu18"><a href="/membercenter/loginout"><i></i>退出系统</a></li>
			</ul>
		</div> 
</div>
<script type="text/javascript">
var _left_href = location.href;
for(var i=1;i<18;i++){
   if(document.getElementById("left"+i)){
      document.getElementById("left"+i).className="left-menu"+i;
   }   
}
if(_left_href.indexOf("mood")>0){
   document.getElementById("left1").className="left-menu1 cur";
}else if(_left_href.indexOf("photo")>0){
   document.getElementById("left2").className="left-menu2 cur";
}else if(_left_href.indexOf("blog")>0){
   document.getElementById("left3").className="left-menu3 cur";
}else if(_left_href.indexOf("content")>0){
   document.getElementById("left4").className="left-menu4 cur";
}else if(_left_href.indexOf("collection")>0){
   document.getElementById("left5").className="left-menu5 cur";
}else if(_left_href.indexOf("labor")>0){
   document.getElementById("left6").className="left-menu6 cur";
}else if(_left_href.indexOf("vedioall")>0){
   document.getElementById("left16").className="left-menu16 cur";
}else if(_left_href.indexOf("vedio")>0){
   document.getElementById("left7").className="left-menu7 cur";
}else if(_left_href.indexOf("clfriendlist")>0 || _left_href.indexOf("cafriendlist")>0){
   document.getElementById("left8").className="left-menu8 cur";
}else if(_left_href.indexOf("message")>0){
   document.getElementById("left10").className="left-menu10 cur";
}else if(_left_href.indexOf("consult")>0){
   document.getElementById("left11").className="left-menu11 cur";
}else if(_left_href.indexOf("labor")>0){
   document.getElementById("left14").className="left-menu14 cur";
}else if(_left_href.indexOf("police")>0){
   document.getElementById("left17").className="left-menu17 cur";
}
</script>		 