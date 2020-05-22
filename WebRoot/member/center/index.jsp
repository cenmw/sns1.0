<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><s:property value="memberInfo.account"/>-龙爸爸成长在线</title>
    <link rel="stylesheet" type="text/css" href="/member/css/base.css"/>
    <script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
    <script type="text/javascript" src="/member/js/common.js"></script>
    <link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
    <script type="text/javascript">
        function zoom_image(obj) {
            //alert(obj.style.maxWidth);
            if (obj.style.maxWidth == "100px") {
                obj.style.maxWidth = "400px";
                obj.style.width = "400";
                obj.className = "zoomsmall";
            } else if (obj.style.maxWidth == "400px") {
                obj.style.maxWidth = "100px";
                obj.style.width = "100";
                obj.className = "zoombig";
            }
        }
    </script>
</head>
<body>
<!--top-->
<s:action name="top" namespace="/membercenter" executeResult="true">
</s:action>
<!--top end-->
<!--content-->
<div class="content layout-control">
    <!--left-->
    <s:action name="left" namespace="/membercenter" executeResult="true">
        <s:param name="isindex" value="1"></s:param>
    </s:action>
    <!--left end-->
    <!--middle-->
    <div class="middle fl">


        <s:if test="mayfriendlist != null && mayfriendlist.size()>0">
            <!--可能认识的人-->
            <div class="porbab mar-t20">
                <h2 class="com-title">可能认识的人</h2>
                <div class="porbab-add">
                    <ul id="mayfriend">
                        <s:iterator value="mayfriendlist" id="beanlist" status="beanlist1">
                            <li>
                                <a class="porbab-img"><img
                                        src="<s:property value="#beanlist.avatar_small" escape="false"/>" height="50"
                                        width="50" border="0"/></a>
                                <div class="porbab-word">
                                    <p class="porbab-name blue"><s:property value="#beanlist.account"
                                                                            escape="false"/></p>
                                    <p class="porbab-name gray"><s:property value="#beanlist.professional"
                                                                            escape="false"/></p>
                                    <p class="porbab-friend"
                                       onclick="addfriend('<s:property value="#beanlist.id" escape="false"/>','0')">
                                        好友</p>
                                </div>
                            </li>
                        </s:iterator>
                    </ul>
                    <div class="clear"></div>
                </div>
            </div>
            <!--可能认识的人 end-->
        </s:if>

        <!--个人中心分类-->
        <div class="infor-sorts mar-t20">
            <!--
    	<s:if test="type==1">
		<a href="/">全部分享</a><a class="cur" href="/membercenter/index?type=1">好友分享</a><a href="/membercenter/index?type=2">机构分享</a>
		</s:if>
		<s:elseif test="type==2">
		<a href="/">全部分享</a><a href="/membercenter/index?type=1">好友分享</a><a class="cur" href="/membercenter/index?type=2">机构分享</a>
		</s:elseif>
		<s:else>
		<a  <s:if test="type==0">class="cur"</s:if> href="/">全部分享</a><a href="/membercenter/index?type=1">好友分享</a><a href="/membercenter/index?type=2">机构分享</a>
		</s:else>
		<s:if test="id==2 || id ==23">
		<a <s:if test="type==3">class="cur"</s:if> href="/membercenter/index?type=3">特殊分享</a> 
		</s:if>
		 -->
            <a id="state1ID" class="cur" href="javascript:"
               onclick="getMoreStateAjaxDIV('<s:property value="type" escape="false"/>','stateid');">全部分享</a>
            <%--<a id="state8ID" href="javascript:" onclick="getMoreState8AjaxDIV('<s:property value="type" escape="false" />','stateid');">习惯养成</a>--%>
            <%--<a id="state9ID"href="javascript:" onclick="getMoreState9AjaxDIV('<s:property value="type" escape="false" />','stateid');">双21天</a>--%>
            <%--<a id="state88ID" href="javascript:" onclick="getMoreState88AjaxDIV('<s:property value="type" escape="false" />','stateid');">随想</a>--%>
            <%--<a id="state99ID" href="javascript:" onclick="getMoreState99AjaxDIV('<s:property value="type" escape="false" />','stateid');">提问</a>--%>
            <a id="state88ID" href="javascript:"
               onclick="getMoreState10AjaxDIV('<s:property value="type" escape="false"/>','stateid');">习惯养成</a>
            <a id="state9ID" href="javascript:"
               onclick="getMoreState9AjaxDIV('<s:property value="type" escape="false"/>','stateid');">提问</a>
        </div>
        <!--个人中心分类end-->
        <!--分类列表-->
        <div id="stateid">
            <!--
	<s:if test="type == 1">
	<s:iterator value="fstatuslist" id="beanlist" status="beanlist1">  
    <div class="person-sorts mar-t20">
    	<div class="sorts-img fl">
        	<img src="<s:property value="#beanlist.memberInfo.avatar_small" escape="false"/>" height="50" width="50" border="0" />
        </div>
        <div class="sort-content fl">
        	<div class="user-publish">
            <a class="blue"><s:property value="#beanlist.memberInfo.account" escape="false"/> :</a> <s:property value="#beanlist.content" escape="false"/>
            </div>
            <div class="user-talk">
            <div class="fl gray"><s:date name="#beanlist.ctime" format="yyyy-MM-dd HH:mm"/></div>
            <div class="fr">
			<s:if test="#beanlist.type == 1">
			    <jsp:include page="/member/center/mood/inc/fxlist.jsp"></jsp:include>
			</s:if>
			<s:elseif test="#beanlist.type == 2">
			    <jsp:include page="/member/center/blog/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 3">
			    <jsp:include page="/member/center/content/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 4">
			    <jsp:include page="/member/center/photo/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 5">
			    <jsp:include page="/member/center/vedio/inc/fxlist.jsp"></jsp:include>
			</s:elseif>	
			</div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    </s:iterator>
	</s:if>
	<s:elseif test="type == 2">
	<s:iterator value="fcstatuslist" id="beanlist" status="beanlist1">  
    <div class="person-sorts mar-t20">
    	<div class="sorts-img fl">
        	<img src="<s:property value="#beanlist.memberInfo.avatar_small" escape="false"/>" height="50" width="50" border="0" />
        </div>
        <div class="sort-content fl">
        	<div class="user-publish">
            <a class="blue"><s:property value="#beanlist.memberInfo.account" escape="false"/> :</a> <s:property value="#beanlist.content" escape="false"/>
            </div>
            <div class="user-talk">
            <div class="fl gray"><s:date name="#beanlist.ctime" format="yyyy-MM-dd HH:mm"/></div>
            <div class="fr">
			<s:if test="#beanlist.type == 1">
			    <jsp:include page="/member/center/mood/inc/fxlist.jsp"></jsp:include>
			</s:if>
			<s:elseif test="#beanlist.type == 2">
			    <jsp:include page="/member/center/blog/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 3">
			    <jsp:include page="/member/center/content/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 4">
			    <jsp:include page="/member/center/photo/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 5">
			    <jsp:include page="/member/center/vedio/inc/fxlist.jsp"></jsp:include>
			</s:elseif>	
			</div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    </s:iterator>
	</s:elseif>
	<s:else>     
	<s:iterator value="allstatuslist" id="beanlist" status="beanlist1">
	 <s:if test="#beanlist.type < 6">
    <div class="person-sorts mar-t20">
    	<div class="sorts-img fl">
        	<img src="<s:property value="#beanlist.memberInfo.avatar_small" escape="false"/>" height="50" width="50" border="0" />
        </div>
        <div class="sort-content fl">
        	<div class="user-publish">
            <a class="blue"><s:property value="#beanlist.memberInfo.account" escape="false"/> :</a> <s:property value="#beanlist.content" escape="false"/>
            </div>
            <div class="user-talk">
            <div class="fl gray"><s:date name="#beanlist.ctime" format="yyyy-MM-dd HH:mm"/></div>
            <div class="fr">
			<s:if test="#beanlist.type == 1">
			    <jsp:include page="/member/center/mood/inc/fxlist.jsp"></jsp:include>
			</s:if>
			<s:elseif test="#beanlist.type == 2">
			    <jsp:include page="/member/center/blog/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 3">
			    <jsp:include page="/member/center/content/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 4">
			    <jsp:include page="/member/center/photo/inc/fxlist.jsp"></jsp:include>
			</s:elseif>
			<s:elseif test="#beanlist.type == 5">
			    <jsp:include page="/member/center/vedio/inc/fxlist.jsp"></jsp:include>
			</s:elseif>	
			</div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    </s:if>
    </s:iterator>
	</s:else>
	-->
        </div>
        <!--分类列表 end-->
        <div class="loading-more"><a class="loading-more-a"
                                     href="javascript:getMoreStateAjaxMore('<s:property value="type" escape="false" />','stateid')">更多</a>
        </div>
        <script>
            getMoreStateAjax('<s:property value="type" escape="false" />', 'stateid');
        </script>
    </div>
    <!--middle end-->
    <!--right-->
    <div class="right fr">
        <s:if test="zxfriendlist != null && zxfriendlist.size()>0">
            <div class="add-friend">
                <h2 class="add-title"><span>在线朋友</span></h2>
                <ul class="add-list">

                    <s:iterator value="zxfriendlist" id="beanlist" status="beanlist1">
                        <li>
                            <a class="add-img">
                                <img src="<s:property value="#beanlist.avatar_small" escape="false"/>" height="50"
                                     width="50" border="0"/>
                                <span><s:property value="#beanlist.account" escape="false"/></span>
                            </a>
                            <span class="add-a"
                                  onclick="addfriend('<s:property value="#beanlist.id" escape="false"/>','0')"></span>
                        </li>
                    </s:iterator>

                </ul>
                <div class="clear"></div>
            </div>
        </s:if>

        <!--
		<s:if test="zxcfriendlist != null && zxcfriendlist.size()>0">
        <!--在线机构--
        <div class="online-teacc mar-t20">
        	<h2 class="add-title"><span>在线机构</span></h2>
            <div class="online-list">
            	<ul>
                	
					<s:iterator value="zxcfriendlist" id="beanlist" status="beanlist1"> 
					<li>
                    	<a class="online-img fl" href="javascript:void(0)"><img src="<s:property value="#beanlist.avatar_small" escape="false"/>" height="50" width="50" border="0" /></a>
                        <div class="online-title fl">
                        	<p><a class="blue" href="javascript:void(0)"><s:property value="#beanlist.account" escape="false"/></a></p>
                            <p><a class="gray" href="javascript:showfriend('<s:property value="#beanlist.id" escape="false" />')" onclick="">查看资料</a></p>
                        </div>
                        <a class="online-add" href="javascript:addfriend('<s:property value="#beanlist.id" escape="false" />','1')"></a>    
                    </li>
					</s:iterator>
				
                </ul>
                <div class="clear"></div>
            </div>
        </div>
        <!--在线机构 end--
		</s:if>
		-->

        <s:if test="hotcontentlist != null && hotcontentlist.size()>0">
            <!--热门转帖-->
            <div class="hot-news mar-t20">
                <h2 class="add-title"><span>养成排行榜</span></h2>
                <ul class="hot-news-list mar-t10">
                    <s:iterator value="hotcontentlist" id="beanlist" status="beanlist1">
                        <li><a target="_blank"
                               href="/membercenter/showxgycinfo?id=<s:property value="#beanlist.id" escape="false" />">
                            <s:property value="#beanlist.memberInfo.account" escape="false"/>：<s:property
                                value="#beanlist.ptime" escape="false"/></a></li>
                    </s:iterator>
                </ul>
            </div>
            <!--热门转帖 end-->
        </s:if>

        <p class="ad1 mar-t10"><img src="/member/images/pic4.jpg" height="259" width="201"/></p>

        <s:if test="hotccontentlist != null && hotccontentlist.size()>0">
            <!--热点推荐-->
            <div class="hot-news mar-t20">
                <h2 class="add-title"><span>热门提问</span></h2>
                <ul class="hot-news-list mar-t10">
                    <s:iterator value="hotccontentlist" id="beanlist" status="beanlist1">
                        <s:if test="#beanlist.content != null && #beanlist.content != '' && #beanlist.content.indexOf('<p>') < 0 && #beanlist.content.indexOf('<img') < 0">
                        <li><a target="_blank"
                               href="/membercenter/showmyzyfinfo?id=<s:property value="#beanlist.id" escape="false" />"><s:property
                                value="#beanlist.content" escape="false"/></a></li>
                        </s:if>
                    </s:iterator>
                </ul>
            </div>
            <!--热点推荐 end-->
        </s:if>

        <p class="ad1 mar-t10"><img src="/member/images/pic5.jpg" height="89" width="199"/></p>
    </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
<%
    Object msg = request.getSession().getAttribute("saveinfomsg");
    if (msg != null) {
        out.println("<script language=\"javascript\">alert('" + (String) msg + "');</script>");
        request.getSession().removeAttribute("saveinfomsg");
    }
%>