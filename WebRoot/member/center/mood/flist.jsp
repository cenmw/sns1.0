<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-好友说说-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
function delmood(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/mooddelete?ids="+id;
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>好友说说</h2>
        <div class="note-title">
        	<a class="note-a" href="/membercenter/moodlist">我的说说</a><a class="note-a note-cur" href="/membercenter/fmoodlist">好友说说</a>
        </div>
				
		<div class="infor-list">
        	<ul class="infor-ul">
            	<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
				<li>
                	<a class="infor-l fl"><img src="<s:property value="memberInfo.avatar_small" escape="false" />" height="50" width="50" border="0" /></a>
                    <div class="infor-m fl">
                    	<p><a class="blue"><strong><s:property value="memberInfo.account" escape="false" /></strong></a></p>
                        <p><a href="javascript:fowardBack('/membercenter/showmoodinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.content" escape="false" /></a></p>
                        <p class="color99"><s:date name="#beanlist.ctime" format="yyyy-MM-dd HH:mm"/></p>						
                    </div>
                    <div class="infor-r fr">
						<span class="note-editor">
						<jsp:include page="/member/center/mood/inc/fxlist.jsp"></jsp:include>
						</span>
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