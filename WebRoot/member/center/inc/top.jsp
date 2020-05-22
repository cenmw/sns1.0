<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<div class="header">
	<div class="layout-control">
    	<a class="logo fl" href="/"></a>
        <div class="header-nav fl">
        	<ul class="nav">
            	<li><a id="top01" href="/membercenter/index"><i class="first"></i>首页</a></li>
                <!-- <li><a id="top02" href="/membercenter/index">个人主页</a></li> -->
                <!-- <li><a id="top02" href="javascript:">商城</a></li> -->
                <li><a id="top03" href="/membercenter/friendlist">我的好友</a></li>
                <!-- <li><a id="top04" href="/membercenter/cfriendlist">我的机构</a></li> -->
                <!-- <li><a id="top04" href="/membercenter/topicclasslist">测试中心</a></li> -->
                <!-- <li><a id="top04" href="/membercenter/learnclasslist">学习中心</a></li> -->
            </ul>
        </div>
        <div class="header-search fr">
        	<div class="search-content">
                <form>
                    <input class="search-top-int" onclick="javascript:location.href='/membercenter/afriendlist'" value="找人,分享,咨询,作业等" type="text" />
                    <button class="search-top-btn" onclick="javascript:location.href='/membercenter/afriendlist'"></button>
                </form>
            </div>
			<div class="setup">
            	<span class="setup-icon"></span>
				<div class="setup-hidden">
					<ul>
						<li><a href="/membercenter/basis">修改资料</a></li>
						<li><a href="/membercenter/loginout"><i></i>退出</a></li>
					</ul>
				</div>
            </div>
        </div>
    </div>
</div>
<script src="/member/js/base.js"></script>
<script type="text/javascript">
var _href=location.href;
if(_href.indexOf("/membercenter/index")>0){
   document.getElementById("top01").className="cur";   
} 
if(_href.indexOf("/membercenter/friendlist")>0){
   document.getElementById("top03").className="cur";
} 
if(_href.indexOf("/membercenter/cfriendlist")>0){
   document.getElementById("top04").className="cur";
} 
</script>