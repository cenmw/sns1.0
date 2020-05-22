<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<div class="left fl">
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
				<li class="left-menu4"><a href="/membercenter/contentlist"><i></i>我的文集</a></li>				
				<li class="left-menu7"><a href="/membercenter/vediolist"><i></i>我的视频</a></li>
				<li class="left-menu8"><a href="/membercenter/cfriendlist"><i></i>我的关注</a></li>				
				<li class="left-menu10"><a href="/membercenter/messagelist"><i></i>我的私信<span style="color:#FF0000">(<s:property value="ismessagenum" escape="false" />)</span></a></li>
				<li class="left-common">公共应用</li>
				<li class="left-menu11"><a href="/membercenter/consultclasslist"><i></i>咨询中心</a></li>
				<li class="left-menu14"><a href="/membercenter/laboralllist"><i></i>活动中心<span style="color:#FF0000">(<s:property value="activitynum" escape="false" />)</span></a></li>
				<li class="left-menu17"><a href="/membercenter/policelist"><i></i>警察局</a></li>
				<li class="left-menu18"><a href="/membercenter/loginout"><i></i>退出系统</a></li>
			</ul>
		</div> 
</div>		 