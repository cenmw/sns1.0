<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的疏导-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
function delmysd(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/mysddelete?ids="+id;
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
   <!--right-->
   <div class="content-right fl">
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>我的疏导</h2>
        <div class="note-title">
        	<a class="write-note fr" href="javascript:fowardBack('/membercenter/mysdinfo?backUrl=','<s:property value="backUrl"/>')"><input class="inputButton" type="button" value="我要疏导"/></a>
			<a class="note-a note-cur" href="/membercenter/mysdlist">我的疏导</a>
		</div>
        
        <div style="padding:10px 0px 10px 0px ;">
        <table class="listGrid">
		<thead>
			<tr>
				<td class="th" align="center">编号</th>
				<td class="th" align="center">日期</th>
				<td class="th" align="center">目的</th>
				<td class="th" align="center">状态</th>
				<td class="th" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
			<tr>
				<td align="center"><s:property value="#beanlist.id" escape="false" /></td>
				<td align="center"><s:date name="#beanlist.ptime" format="yyyy-MM-dd"/></td>
				<td align="center"><s:if test="#beanlist.type==0">与孩子有关</s:if><s:if test="#beanlist.type==1">与自己有关</s:if></td>
				<td align="center"><s:if test="#beanlist.commentnumber==0">未评</s:if><s:if test="#beanlist.commentnumber>0">已评</s:if></td>
				<td align="center"><a class="blue" href="javascript:fowardBack('/membercenter/showmysdinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')">查看</a></td>
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
