f<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<div class="header">
	<div class="layout-control">
    	<a class="logo fl" href="/"></a>
        <div class="header-nav fl">
        	<ul class="nav">
            	<li><a id="top01" href="/"><i class="first"></i>首页</a></li>
                <li><a id="top02" href="/membercenter/index">企业主页</a></li>
                <li><a id="top03" href="/membercenter/cfriendlist">我的关注</a></li>
            </ul>
        </div>
        <div class="header-search fr">
        	<div class="search-content">
                <form>
                    <input class="search-top-int" onclick="javascript:location.href='/membercenter/fvediolist'" value="视频" type="text" />
                    <button class="search-top-btn" onclick="javascript:location.href='/membercenter/fvediolist'"></button>
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
   document.getElementById("top02").className="cur";   
} 
if(_href.indexOf("/membercenter/friendlist")>0){
   document.getElementById("top03").className="cur";
} 
</script>