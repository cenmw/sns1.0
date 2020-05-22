<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-视频收藏-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
function delcollection(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/collectiondelete?ids="+id+"&type=5";
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
<div class="content1 layout-control">
    <!--left-->
    <s:action name="left" namespace="/membercenter" executeResult="true">
    </s:action>
    <!--left end-->
    <!--right-->
   <div class="content-right fl">
   		<h2 class="second-title second-title1"><a class="blue" href="/"><<返回上一页</a>视频收藏</h2>
        <div class="note-title">
        	<a class="note-a note-cur" href="/membercenter/vediocollectionlist?type=5">视频收藏</a><a class="note-a" href="/membercenter/collectionlist?type=3">文章收藏</a><a class="note-a" href="/membercenter/learncollectionlist?type=6">学习内容</a>
  </div>
        <div class="vedio-list">
        	<ul class="vedio-ul">
            	
				<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
                <li>
                	<a class="vedio-img" href="javascript:fowardBack('<s:property value="#beanlist.url" escape="false" />&backUrl=','<s:property value="backUrl"/>')">
                    	<span class="vedio-play"></span>
                        <img src="<s:property value="#beanlist.opicpath" escape="false" />" width="180" height="135" border="0" />
                    </a>
                    <div class="vedio-detail">
                        <p class="vedio-title"><a class="blue" href="javascript:fowardBack('<s:property value="#beanlist.url" escape="false" />&backUrl=','<s:property value="backUrl"/>')">[<s:property value="#beanlist.oclassname" escape="false" />] <s:property value="#beanlist.title" escape="false" /></a></p>
                        <p class="vedio-comment"><a class="orange" href="javascript:fowardBack('<s:property value="#beanlist.url" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.viewnumber" escape="false" /></a><a style="float:right; color:#999999; font-weight:normal;" href="javascript:delcollection('<s:property value="#beanlist.id" escape="false" />')">删除</a></p>
                    </div>
                </li>
                </s:iterator>
				
            </ul>
			
			<s:property value="pageBean.gopagehtml" escape="false"/>
		    <div class="clear" style="padding-top:10px;"></div>
        </div>
   </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
<%
	Object msg=request.getSession().getAttribute("saveinfomsg");
	if(msg!=null){
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("saveinfomsg");
	}
%>