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
    document.getElementById("searchfrm").action="/manager/content/list";
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
		    document.getElementById("searchfrm").action="/manager/content/delete";
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
       <div class="place"><span>当前位置：管理列表 >> 基础信息</span></div>
<s:form id="searchfrm" name="searchfrm" action="list" namespace="/manager/content" method="post" theme="simple">	
	   <div class="sub" align="left">		
	   <span class="subspan">
	   <input type="button" class="tbtn" style="float:left" onClick="javascript:fowardBack('/manager/content/info?id=0&backUrl=','<s:property value="backUrl"/>')" value="新建"/>
	   &nbsp;&nbsp;&nbsp;&nbsp;
	   选择类型：<select id="searchtype" name="searchtype" onChange="gohref()">
                      <option value="0"> 全部 </option>
					  <option value="1"> 关于我们 </option>
					  <option value="2"> 联系我们 </option>
					  <option value="3"> 招聘信息 </option>
					  <option value="4"> 友情链接 </option>
					  <option value="5"> 客服帮助 </option>
					  <option value="6"> 隐私说明 </option>	
					  <option value="7"> 首页大图 </option>				
                </select>
	   <script type="text/javascript">
	   document.getElementById("searchtype").value = "<s:property value="searchtype"/>";
	   </script> 	    
	   &nbsp;&nbsp;&nbsp;&nbsp;
	   标题：<input style="height:22px;" type="text" name="searchtitle" value="<s:property value="searchtitle"/>" />
       &nbsp;&nbsp;<input class="tbtn" type="submit" name="search" value="搜索" />  
	   </span>    
       </div>  
       
	   <div class="right_con">

<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
  <tr>
    <td width="10%" class="yh">选择</td>
    <td width="15%" class="yh">类型</td>
    <td width="40%" class="yh">标题</td>
    <td width="15%" class="yh">发布时间</td>
    <td width="20%" class="cz">操作</td>
  </tr>
  <s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1"> 
     <s:if test="#beanlist1.index % 2 ==1" >
	 <tr>    
	   <td class="yh00 yh00_bg"><input type="checkbox" name="ids" value="<s:property value="id"/>"/></td> 
	   <td class="yh00 yh00_bg">
	   <s:if test="#beanlist.type==1">关于我们</s:if>
	   <s:elseif test="#beanlist.type==2">联系我们</s:elseif>
	   <s:elseif test="#beanlist.type==3">招聘信息</s:elseif>
	   <s:elseif test="#beanlist.type==4">友情链接</s:elseif>
	   <s:elseif test="#beanlist.type==5">客服帮助</s:elseif>
	   <s:elseif test="#beanlist.type==6">隐私说明</s:elseif> 
	   <s:elseif test="#beanlist.type==7">首页大图</s:elseif>    
	   </td> 
       <td class="yh00 yh00_bg"><s:property value="title"/></td>
       <td class="yh00 yh00_bg"><s:date name="ptime" format="yyyy-MM-dd HH:mm:ss"/></td>
       <td class="cz0 cz0_bg">	   	   
       <a href="javascript:fowardBack('/manager/content/info?id=<s:property value="id"/>&backUrl=','<s:property value="backUrl"/>')">编辑</a>	   
       </td>
     </tr>
	 </s:if>
	 <s:else>
	 <tr>     
	   <td class="yh00"><input type="checkbox" name="ids" value="<s:property value="id"/>"/></td>
	   <td class="yh00">
	   <s:if test="#beanlist.type==1">关于我们</s:if>
	   <s:elseif test="#beanlist.type==2">联系我们</s:elseif>
	   <s:elseif test="#beanlist.type==3">招聘信息</s:elseif>
	   <s:elseif test="#beanlist.type==4">友情链接</s:elseif>
	   <s:elseif test="#beanlist.type==5">客服帮助</s:elseif>
	   <s:elseif test="#beanlist.type==6">隐私说明</s:elseif>
	   <s:elseif test="#beanlist.type==7">首页大图</s:elseif> 
	   </td> 
       <td class="yh00"><s:property value="title"/></td>
       <td class="yh00"><s:date name="ptime" format="yyyy-MM-dd HH:mm:ss"/></td>
       <td class="cz0">  	   
       <a href="javascript:fowardBack('/manager/content/info?id=<s:property value="id"/>&backUrl=','<s:property value="backUrl"/>')">编辑</a>       
       </td>
     </tr>
	 </s:else>
  </s:iterator>
     	 
	 <tr>
		<td class="yh00 yh00_bg"><input type="checkbox" id="chkSelAll" onClick="selectAll();">全选</td>
    	<td class="yh00 yh00_bg" colspan="4" align="right">
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
