<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-我的52周-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
function delzhou(id){
   if(confirm("确定删除吗？")){
      location.href="/membercenter/zhoudelete?ids="+id+"&type=0";
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
   		<h2 class="second-title">我的52周</h2>
		 <div class="note-title">
			 <a class="write-note fr" href="javascript:fowardBack('/membercenter/zhouinfo?backUrl=','<s:property value="backUrl"/>')"><input class="inputButton" type="button" value="添加52周"/></a>
			 <a class="note-a note-cur" href="/membercenter/zhoulist">我的52周</a>
        </div>
        
        <div style="padding:10px 0px 10px 0px ;">
        <table class="listGrid">
		<thead>
			<tr>
				<td class="th" align="center">天数</th>
				<td class="th" align="center">日期</th>
				<td class="th" align="center">目的</th>
				<td class="th" align="center">应得</th>
				<td class="th" align="center">实得</th>
				<td class="th" align="center">倒计时</th>
				<td class="th" align="center">状态</th>
				<td class="th" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
			<tr>
				<td align="center"><s:property value="#beanlist.day" escape="false" /></td>
				<td align="center"><s:date name="#beanlist.ptime" format="yyyy-MM-dd"/></td>
				<td align="center"><s:if test="#beanlist.type==0">与家庭有关</s:if><s:if test="#beanlist.type==1">与自己有关</s:if></td>
				<td align="center">10点</td>
				<td align="center">8点</td>
				<td align="center"><s:property value="365-#beanlist.day" escape="false" /></td>
				<td align="center"><s:if test="#beanlist.commentnumber==0">未批</s:if><s:if test="#beanlist.commentnumber>0">已批</s:if></td>
				<td align="center"><a class="blue" href="javascript:fowardBack('/membercenter/showzhouinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')">查看</a></td>
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
