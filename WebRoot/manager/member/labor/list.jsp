<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="/manager/css/newbackstage.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/common/js/datepicker/ui.datepickerv1.css"></link>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script src="/manager/js/sys.js" language="javascript"></script>
<script src="/common/js/jquery-1.4.2.min.js" language="javascript"></script>
<script src="/common/js/datepicker/ui.datepicker-zh-CN.js" type="text/javascript"></script>
<script src="/common/js/datepicker/ui.datepicker.js" type="text/javascript"></script>
<script src="/common/js/popup/jquery.popup.js" type="text/javascript"></script>
<script language="javascript">
//筛选
function gohref(cid){
    document.getElementById("searchfrm").action="/manager/memberlabor/list";
	document.getElementById("searchfrm").submit();
}
//删除
function selectAll(){
	var i;
	var ids = document.getElementsByName("ids");
	if(document.getElementById("chkSelAll").checked==true){
		for(i=0;i < ids.length;i++){
			ids[i].checked = true;
		}
	}else{
		for(i=0;i < ids.length;i++){
			ids[i].checked = false;
		}
	}
}
function CheckDelAll(){
	if(!checkSelect()){		
		alert("请选择要进行操作的记录！");		
	}else{         
		if(confirm("确定要执行此操作吗？")){
		    document.getElementById("searchfrm").action="/manager/memberlabor/delete";
			document.getElementById("searchfrm").submit();
		}	
	}
}	
function checkSelect(){
	var i;
	var ids = document.getElementsByName("ids");
	for(i=0;i < ids.length;i++){
		if(ids[i].checked == true){
			return true;
		}
	}
	return false;
}
</script>
</head>
<body>
  <div class="right">
       <div class="place"><span>当前位置：管理列表 >> 会员任务</span></div>
<s:form id="searchfrm" name="searchfrm" action="list" namespace="/manager/memberlabor" method="post" theme="simple">	
	   <div class="sub" align="left">		
	   <span class="subspan">
	   &nbsp;&nbsp;&nbsp;&nbsp;
	   标题：<input style="height:22px;" type="text" name="searchtitle" value="<s:property value="searchtitle"/>" />
       &nbsp;&nbsp;<input class="tbtn" type="submit" name="search" value="搜索" />  
	   </span>    
       </div>  
       
	   <div class="right_con">

<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
  <tr>
    <td width="10%" class="yh">选择</td>
    <td width="15%" class="yh">分类</td>
    <td width="30%" class="yh">标题</td>
    <td width="10%" class="yh">参与人数</td>
	<td width="10%" class="yh">完成人数</td>
	<td width="15%" class="yh">发布时间</td>
    <td width="10%" class="cz">操作</td>
  </tr>
  <s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1"> 
     <s:if test="#beanlist1.index % 2 ==1" >
	 <tr>    
	   <td class="yh00 yh00_bg"><input type="checkbox" name="ids" value="<s:property value="id"/>"/></td>
       <td class="yh00 yh00_bg"><s:property value="classname"/></td>
       <td class="yh00 yh00_bg"><s:property value="title"/></td>
	   <td class="yh00 yh00_bg"><a href="javascript:fowardBack('/manager/memberlabor/info?id=<s:property value="id"/>&backUrl=','<s:property value="backUrl"/>')"><s:property value="midcount"/></a></td> 
	   <td class="yh00 yh00_bg"><a href="javascript:fowardBack('/manager/memberlabor/info?id=<s:property value="id"/>&backUrl=','<s:property value="backUrl"/>')"><s:property value="replycount"/></a></td> 
       <td class="yh00 yh00_bg"><s:date name="ctime" format="yyyy-MM-dd HH:mm:ss"/></td>
       <td class="cz0 cz0_bg">	   	   
       <a href="javascript:fowardBack('/manager/memberlabor/info?id=<s:property value="id"/>&backUrl=','<s:property value="backUrl"/>')">查看</a>	   
       </td>
     </tr>
	 </s:if>
	 <s:else>
	 <tr>     
	   <td class="yh00"><input type="checkbox" name="ids" value="<s:property value="id"/>"/></td>
       <td class="yh00"><s:property value="classname"/></td>
       <td class="yh00"><s:property value="title"/></td>
	    <td class="yh00"><a href="javascript:fowardBack('/manager/memberlabor/info?id=<s:property value="id"/>&backUrl=','<s:property value="backUrl"/>')"><s:property value="midcount"/></a></td> 
	   <td class="yh00"><a href="javascript:fowardBack('/manager/memberlabor/info?id=<s:property value="id"/>&backUrl=','<s:property value="backUrl"/>')"><s:property value="replycount"/></a></td> 
       <td class="yh00"><s:date name="ctime" format="yyyy-MM-dd HH:mm:ss"/></td>
       <td class="cz0">  	   
       <a href="javascript:fowardBack('/manager/memberlabor/info?id=<s:property value="id"/>&backUrl=','<s:property value="backUrl"/>')">查看</a>       
       </td>
     </tr>
	 </s:else>
  </s:iterator>
     	 
	 <tr>
		<td class="yh00 yh00_bg"><input type="checkbox" id="chkSelAll" onClick="selectAll();">全选</td>
    	<td class="yh00 yh00_bg" colspan="6" align="right">
		    <table width="100%" border="0">
              <tr>
                <td><input class="tbtn" type="button" onClick="return CheckDelAll();" value="删除" /></td>                
              </tr>
            </table>
   		</td>
    </tr>
		
</table>

<s:property value="pageBean.gopagehtml" escape="false"/>
			
	</div>
</s:form> 	
  </div>
</body>
</html>
<%
	Object msg=request.getSession().getAttribute("msg");
	String str = (String) msg;
	if(msg!=null){
	    out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("msg");	
	}
%>
