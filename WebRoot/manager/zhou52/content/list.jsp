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
		    document.getElementById("searchfrm").action="/manager/learnwhy/delete";
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
       <div class="place"><span>当前位置：管理列表 >> 52周书籍内容</span></div>
       <div class="sub" align="left">		
	   <span class="subspan">
	   <input type="button" class="tbtn" style="float:left" onClick="javascript:fowardBack('/manager/zhoucontent/info?id=0&backUrl=','<s:property value="backUrl"/>')" value="新建"/>
	   </span>    
       </div>
	   <div class="right_con">

<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#0099CC">
  <tr>
    <td width="10%" class="yh">选择</td>
    <td width="10%" class="yh">排序号</td>
    <td width="15%" class="yh">类型</td>
    <td width="15%" class="yh">位置</td>
    <td width="20%" class="yh">开始天数-结束天数</td>
    <td width="15%" class="yh">创建时间</td>
    <td width="15%" class="cz">操作</td>
  </tr>
  <s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1"> 
     <s:if test="#beanlist1.index % 2 ==1" >
	 <tr>    
	   <td class="yh00 yh00_bg"><input type="checkbox" name="ids" value="<s:property value="id"/>"/></td> 	   
       <td class="yh00 yh00_bg"><s:property value="sort"/></td>
       <td class="yh00 yh00_bg">
       <s:if test="type==1">为自己</s:if><s:else>为家庭</s:else>
       </td>
       <td class="yh00 yh00_bg">
       <s:if test="zposition==1">写日志之后</s:if><s:else>写日志之前</s:else>
       </td>
       <td class="yh00 yh00_bg"><s:property value="zbegin"/> - <s:property value="zend"/></td>
       <td class="yh00 yh00_bg"><s:date name="ctime" format="yyyy-MM-dd HH:mm"/></td>
       <td class="cz0 cz0_bg">	   	   
       <a href="javascript:fowardBack('/manager/zhoucontent/info?id=<s:property value="id"/>&backUrl=','<s:property value="backUrl"/>')">编辑</a>	   
       </td>
     </tr>
	 </s:if>
	 <s:else>
	 <tr>     
	   <td class="yh00"><input type="checkbox" name="ids" value="<s:property value="id"/>"/></td>
       <td class="yh00"><s:property value="sort"/></td>
       <td class="yh00">
       <s:if test="type==1">为自己</s:if><s:else>为家庭</s:else>
       </td>
       <td class="yh00">
       <s:if test="zposition==1">写日志之后</s:if><s:else>写日志之前</s:else>
       </td>
       <td class="yh00"><s:property value="zbegin"/> - <s:property value="zend"/></td>
       <td class="yh00"><s:date name="ctime" format="yyyy-MM-dd HH:mm"/></td>
       <td class="cz0">  	   
       <a href="javascript:fowardBack('/manager/zhoucontent/info?id=<s:property value="id"/>&backUrl=','<s:property value="backUrl"/>')">编辑</a>       
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
