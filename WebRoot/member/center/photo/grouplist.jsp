<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的相册-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
function delphotogroup(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/photogroupdelete?ids="+id+'&backUrl=<s:property value="backUrl" escape="false" />';
   }
}

//修改相册
function updateclass(id){
	$.fn.popup({iframe:true,url:'/membercenter/photogroupinfo?id='+id,type:3,title:'修改相册',width:400,height:200});
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
   		<h2 class="second-title second-title1"><a class="blue" href="/"><<返回上一页</a>我的相册</h2>
        <div class="note-title">
        	<a style="margin-right:10px; display:inline-block;" class="write-note fr" href="javascript:fowardBack('/membercenter/photoinfo?backUrl=','<s:property value="backUrl"/>')"><img src="/member/images/btn12.png" height="26" width="83" border="0" /></a><a class="note-a note-cur" href="/membercenter/photogrouplist">我的相册</a><a class="note-a" href="/membercenter/fphotogrouplist">好友相册</a>
  </div>
        <div class="picture-list">
        	<ul class="picture-ul">
            	
				<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">
				<li>
                	<a class="picture-img" href="javascript:fowardBack('/membercenter/photolist?cid=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')">
                        <img src="<s:property value="#beanlist.picpath_center" escape="false" />" width="154" height="130" border="0" />
                    </a>
                  <p class="picture-title"><a class="blue" href="javascript:fowardBack('/membercenter/photolist?cid=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.title" escape="false" /></a></p>
				  <p style="padding-top:8px;"><a class="blue" href="javascript:fowardBack('/membercenter/photolist?cid=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.pcount" escape="false" />张</a><a style="float:right; color:#999999; font-weight:normal; padding-right:15px;" href="javascript:delphotogroup('<s:property value="#beanlist.id" escape="false" />')">删除</a>&nbsp;&nbsp;<a style="float:right; color:#999999; font-weight:normal; padding-right:35px;" href="javascript:updateclass('<s:property value="#beanlist.id" escape="false" />')">编辑</a></p>
				  
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