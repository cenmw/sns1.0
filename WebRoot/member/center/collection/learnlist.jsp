<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-学习收藏-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
function delcollection(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/collectiondelete?ids="+id+"&type=6";
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>学习收藏</h2>
        <div class="note-title">
        	<a class="note-a" href="/membercenter/vediocollectionlist?type=5">视频收藏</a><a class="note-a" href="/membercenter/collectionlist?type=3">文章收藏</a><a class="note-a note-cur" href="/membercenter/learncollectionlist?type=6">学习内容</a>
        </div>
        <div class="write-list">
        	
			<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
			<dl>
            	<dt>
                	<h3 class="write-title"><span class="note-editor"><a class="blue" href="javascript:delcollection('<s:property value="#beanlist.id" escape="false" />')">删除</a></span><a class="blue" href="javascript:fowardBack('<s:property value="#beanlist.url" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.title" escape="false" /></a></h3>
                    <p class="color99 mar-t5"><s:date name="#beanlist.octime" format="yyyy-MM-dd HH:mm"/><span class="mar-l10">分类：<s:property value="#beanlist.oclassname" escape="false" /></span><span class="mar-l10">权限：任何人可见</span></p>
                </dt>
                <dd>
                	<div class="note-detail"><a href="javascript:fowardBack('<s:property value="#beanlist.url" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.description" escape="false" /></a></div>
                </dd>
            </dl>
			</s:iterator>            
			
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