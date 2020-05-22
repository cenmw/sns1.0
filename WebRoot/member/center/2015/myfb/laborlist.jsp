<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-学习中心-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
<script type="text/javascript">
//返回操作
function fowardBackchild(url){
    var _href = location.href;
	location.href=url+encodeURIComponent(_href);
}

//刷新
function pageReload(){
   location.reload();
}

//关闭
function colse(id,replycount){
   if(confirm("确定关闭吗？")){
     location.href = "/membercenter/updatemyfblaborinfo?ids="+id; 
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
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>发布的活动</h2>
        		
		<div class="note-title">
        	<a class="blue write-note fr" href="javascript:fowardBack('/membercenter/myfblaborinfo?backUrl=','<s:property value="backUrl"/>')">添加活动</a>
        	<a class="note-a note-cur" href="/membercenter/myfblaborlist">发布的活动</a>
        	<a class="note-a" href="/membercenter/myfbconsultlist">发布的互动</a>
        </div>
		
        <div style="padding:10px 0px 10px 0px ;">
        <table class="listGrid">
		<thead>
			<tr>
				<td class="th" align="center">编号</th>
				<td class="th" align="center">标题</th>
				<td class="th" align="center">浏览人数</th>
				<td class="th" align="center">报名人数</th>
				<td class="th" align="center">审核状态</th>			
				<td class="th" align="center">过期与否</th>
				<td class="th" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
			<tr>
				<td align="center"><s:property value="#beanlist.code" escape="false" /></td>
				<td align="center">
				<a class="blue" href="javascript:fowardBackchild('/membercenter/showmyfblaborinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=')"><s:property value="#beanlist.title" escape="false" /></a>
				</td>
				<td align="center"><s:property value="#beanlist.viewnumber" escape="false" /></td>
				<td align="center"><a class="blue" href="javascript:fowardBackchild('/membercenter/showmyfblaborinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=')"><s:property value="#beanlist.replycount" escape="false" /></a></td>
				<td align="center"><s:property value="#beanlist.statename" escape="false" /></td>			
				<td align="center"><s:property value="#beanlist.gqname" escape="false" /></td>
				<td align="center">
				<a class="blue" href="javascript:pageReload();">刷新</a>
				&nbsp;&nbsp;<a class="blue" href="javascript:fowardBackchild('/membercenter/myfblaborinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=')">修改</a>
				&nbsp;&nbsp;<a class="blue" href="javascript:colse('<s:property value="#beanlist.id" escape="false" />')">关闭</a>
				<s:if test="#beanlist.replycount>0 && #beanlist.state==1">
				&nbsp;&nbsp;<a target="_blank" class="blue" href="/membercenter/sendmessageinfo?rids=<s:property value="#beanlist.rids" escape="false" />">发送短信</a>
				</s:if>
				</td>
			</tr>
			</s:iterator>
			
		</tbody>
		</table>        	
			
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
	Object msg=request.getSession().getAttribute("msg");
	if(msg!=null){
		out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("msg");
	}
%>