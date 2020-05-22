<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-测试中心-龙爸爸成长在线</title>
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
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>测试中心</h2>
        		
		<div class="arc-list">
		    <s:iterator value="classlist" id="beanlist" status="beanlist1"> 
			<a <s:if test="#beanlist.id == searchcid">class="cur"</s:if> href="javascript:fowardBack('/membercenter/topiclist?searchcid=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')"><s:property value="#beanlist.title" escape="false" /></a>
			</s:iterator>        	
        </div>
		
        <div style="padding:10px 0px 10px 0px ;">
        <table class="listGrid" summary="学历类小学教育考试一年级语文试卷">
		<thead>
			<tr>
				<td class="th" align="center">编号</th>
				<td class="th">标题</th>
				<td class="th" align="center">费用（元）</th>
				<td class="th" align="center">总次数</th>
				<td class="th" align="center">最高正确率</th>			
				<td class="th" align="center">我的学习次数</th>
				<td class="th" align="center">我的最高正确率</th>
				<td class="th" align="center">创建时间</th>
				<td class="th">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">  
			<tr>
				<td align="center"><s:property value="#beanlist.code" escape="false" /></td>
				<td>
				<a class="blue" href="javascript:fowardBackchild('/membercenter/showtopicinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=')"><s:property value="#beanlist.title" escape="false" /></a>
				</td>
				<td align="center"><s:property value="#beanlist.cost" escape="false" /></td>
				<td align="center"><s:property value="#beanlist.countsum" escape="false" /></td>
				<td align="center"><s:property value="#beanlist.maxcorrect" escape="false" />%</td>			
				<td align="center"><s:property value="#beanlist.m_countsum" escape="false" /></td>
				<td align="center"><s:property value="#beanlist.m_maxcorrect" escape="false" />%</td>
				<td align="center"><s:date name="#beanlist.ctime" format="yyyy-MM-dd"/></td>
				<td>
				<a class="blue" href="javascript:fowardBackchild('/membercenter/showtopicinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=')">进入</a>
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