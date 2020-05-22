<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>龙爸爸成长在线后台 管理系统</title>
<link href="/manager/css/backstage.css" rel="stylesheet" type="text/css">
<script src="/common/js/jquery-1.4.2.min.js" language="javascript"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
<script src="/common/js/channeloptionTree.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/common/datepicker/ui.datepickerv1.css"></link>
<script src="/common/datepicker/ui.datepicker-zh-CN.js" type="text/javascript"></script>
<script src="/common/datepicker/ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
//判断字节长度
function zjlen(s) {
	var l = 0;
	var a = s.split("");
	for (var i=0;i<a.length;i++) {
		if (a[i].charCodeAt(0)<299) {
			l++;
		} else {
			l+=2;
		}
	}
	return l;
}

$(function(){
	$("#title").keyup(function(){
		var title_len = zjlen($("#title").val());
	    if(title_len>40){
	    	$("#titleclenid").html("标题不能超过20个汉字长度");
	    }else if(title_len==0){
	    	$("#titleclenid").html("请输入标题");
	    }
	    $("#titlelenid").html(parseInt(title_len/2)+"/"+20);
	});
    $("#title").focus();    
	
	$('#ptime').datepicker( {
		yearRange : '1990:2050', //取值范围.
		showOn : 'both', //输入框和图片按钮都可以使用日历控件。
		buttonImage : '/common/datepicker/date.gif', //日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});
});

KindEditor.ready(function(K) {
	var imageEditor = K.editor({
		allowFileManager : true,
		fileManagerJson : '/keditor/browseImageLists?foldername=uploadfiles,content',
		uploadJson : '/keditor/uploadImages?foldername=uploadfiles,content'
	});
	K('#imageUpload').click(function() {
		imageEditor.loadPlugin('image', function() {
			imageEditor.plugin.imageDialog({
				hideImageSet : true,
				imageUrl : K('#picpath').val(),
				clickFn : function(url, title, width, height, border, align) {
					K('#picpath').val(url);
					var date=new Date();
					var s=date.getTime();
					$('#picpath_s').attr("src",url+"?v="+s);
					$('#picpath_s').show();
					imageEditor.hideDialog();
				}
			});
		});
	});
});

var editor;
$(function(){
	var editorOptions={
		items : [
			'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
			'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
			'anchor', 'link', 'unlink','fillblank'
		],
		allowFileManager : true,
		fileManagerJson : '/keditor/browseImageLists?foldername=uploadfiles',
		uploadJson : '/keditor/uploadImages?foldername=uploadfiles'
	};
	
	<s:if test="learnInfo==null">
	editor = KindEditor.create('#content',editorOptions);
	</s:if>
	<s:else>
		<s:if test="learnInfo.contenttype==0||learnInfo.contenttype==null">
			editor = KindEditor.create('#content',editorOptions);
		</s:if>
		<s:if test="learnInfo.contenttype==1">
			$("#content").attr("rows","1");
		</s:if>
	</s:else>
	var editorVal="";
	$("#contenttype").change(function(){
		var t=$(this).val();
		if(t==1){
			editorVal=editor.html();
			editor.remove();
			editor = null;
			$("#content").attr("value","");
			$("#content").attr("rows","1");
		}
		if(t==0){
			$("#content").attr("value",editorVal);
			$("#content").attr("rows","30");
			editor = KindEditor.create('#content',editorOptions);
		}
	});
}); 

// 过滤空格
function trim(str){ //删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function getRWvalue(){
	var v_result=document.getElementsByName("learntext");
	var result = "";
	if(v_result.length>0){
	   for(i=0;i<v_result.length;i++){
	       var lti = trim(v_result[i].value);
		   if(lti == ""){
		      lti = " ";
		   }
	       if(i>0){
		      result+="||"+lti;
		   }else{
		      result+=lti;
		   }
	   }
	}	
	document.getElementById("resultid").value = result;
	//alert(document.getElementById("resultid").value);
	
	var v_whyid=document.getElementsByName("v_whyid");
	var whyids = "";
	if(v_whyid.length>0){
	   for(i=0;i<v_whyid.length;i++){
	       var lti = v_whyid[i].value;
		   if(lti == ""){
		      lti = " ";
		   }
	       if(i>0){
		      whyids+="||"+lti;
		   }else{
		      whyids+=lti;
		   }
	   }
	}	
	document.getElementById("whyids").value = whyids;
	//alert(document.getElementById("whyids").value);
	
}

function checkcontentinfo(){
	getRWvalue();
	//return false;
	if($("#state_t").attr("checked")){
		$("#state").val($("#state_t").val());
	}
	
	var title=$.trim($("#title").val());
	if(title==""){
		alert("标题不能为空");
		return false;
	}else if(zjlen(title)>41){
	    alert("标题不能超过20个汉字长度");
	    document.getElementById("title").focus();
		return false;
	}
	
	var code=$("#code").val();
	if(code==""){
		alert("编号不能为空");
		return false;
	}else{
		code=parseInt(code);
		if(isNaN(code)){
			alert("编号必须为数值型");
			return false;
		}
	}
	
	if($("#contenttype").val()!=1){
		var content=editor.html();
		$("#content").val(content);
	}
	
	var description=$("#description").val();
	if(description.length>500){
		alert("文章描述不能超过500字");
		return false;
	}
	
	return true;
}

function onchangetitle(obj) {
    var title_len = zjlen(obj.value);
    document.getElementById("titlelenid").innerHTML = ""+parseInt(title_len/2);
	
	if(title_len > 40){
	   $("#titleclenid").html("标题不能超过20个汉字长度");
	   document.getElementById("title").focus();
	}else{
	   $("#titleclenid").html("");
	}
}
function onchangeshowt(obj,showobj) {
    var title_len = zjlen(obj.value);
    document.getElementById(showobj).innerHTML = ""+parseInt(title_len/2);
}
</script>
</head>
<body>
	<div style="text-align: center;">
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
			<tr>
				<td align="left">
				<s:form action="save" namespace="/manager/learn" method="post" theme="simple" onsubmit="return checkcontentinfo()">
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							<s:hidden name="backUrl"></s:hidden>
							<s:hidden name="learnInfo.id"></s:hidden>
							<s:hidden name="learnInfo.ctime"></s:hidden>
							<s:hidden name="learnInfo.isdel"></s:hidden>
							<s:hidden name="learnInfo.sort"></s:hidden>
							<input type="hidden" name="learnInfo.result" id="resultid" value="<s:property value="learnInfo.result" escape="false" />" />	
							<input type="hidden" name="learnInfo.whyids" id="whyids" value="<s:property value="learnInfo.whyids" escape="false" />" />							
							<tr>
								<td colspan="2" class="yh">添加 / 修改 信息&nbsp;</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">选择分类</td>
								<td width="80%" height="30">&nbsp;&nbsp;								
								<select id="searchcid" name="learnInfo.cid">
								<s:iterator value="learnclasslist" id="sv" status="sv1"> 
							         <option value="<s:property value="#sv.id"/>"> <s:property value="#sv.title"/> </option>
							    </s:iterator>	
								</select>	
<script>
if('<s:property value="learnInfo.cid"/>' != ''){
	document.getElementById("searchcid").value = '<s:property value="learnInfo.cid"/>';	
}			
</script>								
								</td>
							</tr>							
							
							<tr>
								<td width="20%" class="yh">编号</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="learnInfo.code" id="code" cssClass="tin" cssStyle="width:90px;" maxLength="50"/><em>*&nbsp;&nbsp;<span>不能为空！</span></em>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">费用</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="learnInfo.cost" id="cost" cssClass="tin" cssStyle="width:90px;" maxLength="50"/><em>*&nbsp;&nbsp;<span>必须为整数！单位是"元"</span></em>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">文章标题</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield id="title" name="learnInfo.title" maxLength="30" cssClass="tin" style="width:400px;"/>
								<em>*
								<span id="titlelenid"></span></em></td>
							</tr>
							<script>
							var title_c = document.getElementById("title");
							document.getElementById("titlelenid").innerHTML = ""+parseInt(zjlen(title_c.value)/2);				
							</script>
							
							<tr>
								<td width="20%" class="yh">发布日期</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<input id="ptime" type="text" class="tin" style="width:100px;" readonly="readonly" name="learnInfo.ptime" value="<s:date name="learnInfo.ptime" format="yyyy-MM-dd"/>" />		
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">文章图片</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:hidden name="learnInfo.picpath" id="picpath"/>
								<s:if test="learnInfo==null">
								<img  style="display:none" width="55" height="55" id="picpath_s"/>
								</s:if>
								<s:elseif test="learnInfo.picpath==''||learnInfo.picpath==null">
								<img  style="display:none" width="55" height="55" id="picpath_s"/>
								</s:elseif>
								<s:else>
								<img src="<s:property value="learnInfo.picpath"/>" width="55" height="55" id="picpath_s"/>
								</s:else>
								<input type="button" id="imageUpload" value="选择图片" />
								
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">作者</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="learnInfo.author" cssClass="tin" maxLength="200"/>
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">文章来源</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="learnInfo.source" cssClass="tin" maxLength="250"/>
								</td>
							</tr>
									
							<tr>
								<td width="20%" class="yh">关键字</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:textarea cssClass="tarea" name="learnInfo.keyword" id="keyword" rows="5" cols="60"/>(500字)
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">描述</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:textarea cssClass="tarea" name="learnInfo.description" id="description" rows="5" cols="60"/>(500字)
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">文章类型</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="contenttype" list="#{0:'内容',1:'链接'}" name="learnInfo.contenttype"></s:select>
								</td>
							</tr>
							<tr>
								<td width="20%" class="yh">文章内容</td>
								<td width="80%" height="30" style="padding-left:13px;">
								<s:textarea name="learnInfo.content" id="content" rows="30" cols="63"/>
								</td>
							</tr>				
							
							<tr>
								<td width="20%" class="yh">空格个数</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:textfield name="learnInfo.knumber" id="knumber" cssClass="tin" cssStyle="width:90px;" maxLength="50"/><em>*&nbsp;&nbsp;<span>不能为空！特别声明：当修改个数时，需要保存两次，才能生效。</span></em>
								</td>
							</tr>
							
							<s:if test="resultlist.size()>0">
							<tr>
								<td width="20%" class="yh">空值信息<br />(及关联情况)</td>
								<td width="80%" height="30">&nbsp;&nbsp;	
<div style="overflow:auto; height:500px; width:537px; border:1px solid #BFCDF3; margin-left:13px;">															
<table width="100%" cellpadding="0" cellspacing="0"> 								
	    <tr>
			<td width="80" align="center" style="font-weight: bold;">空</td>
			<td width="100" align="center" height="30" style="font-weight: bold;">结果</td>
			<td height="30" style="font-weight: bold;">原因</td>
		</tr>
	<s:iterator value="resultlist" id="beanlist" status="beanlist1"> 
		<tr>
			<td width="80" align="center"><s:property value="#beanlist1.index + 1"/></td>
			<td width="100" height="30">
			    <input type="text" class="tin" style="width:90px;text-align:center;" maxlength="50" id="v_result" name="learntext" onclick="clearTitle(this)" value="<s:property value="#beanlist.result"/>" />
			</td>
			<td height="30">
			<select style="height:25px; width:300px;" name="v_whyid" id="v_whyid">
			<s:iterator value="#beanlist.whylist" id="sv" status="sv1">
			<option <s:if test="#sv.id == #beanlist.whyid">selected="selected"</s:if> value="<s:property value="#sv.id"/>"><s:property value="#sv.title"/></option>
			</s:iterator>
			</select>
			</td>
		</tr>
	</s:iterator>
</table>	
</div>
							    </td>
							</tr> 
							</s:if>
							   
							<tr>
								<td width="20%" class="yh">正确率上线</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="correct" list="#{60:'60%',62:'62%',64:'64%',66:'66%',66:'66%',70:'70%',72:'72%',74:'74%',75:'75%',76:'76%',77:'77%',80:'80%',82:'82%',84:'84%',86:'86%',88:'88%',90:'90%',92:'92%',94:'94%',96:'96%',98:'98%',100:'100%'}" name="learnInfo.correct"></s:select>超过正确率，下一个课程编号：<s:textfield name="learnInfo.nextcode" id="nextcode" cssClass="tin" cssStyle="width:90px;" maxLength="50"/><em>*&nbsp;&nbsp;<span>当有下一期课程时，不能为空！</span></em>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">正确率下限</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="lcorrect" list="#{60:'60%',62:'62%',64:'64%',66:'66%',66:'66%',70:'70%',72:'72%',74:'74%',75:'75%',76:'76%',77:'77%',80:'80%',82:'82%',84:'84%',86:'86%',88:'88%',90:'90%',92:'92%',94:'94%',96:'96%',98:'98%',100:'100%'}" name="learnInfo.lcorrect"></s:select>低于正确率，上一期课程编号：<s:textfield name="learnInfo.lastcode" id="lastcode" cssClass="tin" cssStyle="width:90px;" maxLength="50"/><em>*&nbsp;&nbsp;<span>当有上一期课程时，不能为空！</span></em>
								</td>
							</tr>
<script>
	document.getElementById("correct").value = '<s:property value="learnInfo.correct" escape="false" />';		
	document.getElementById("lcorrect").value = '<s:property value="learnInfo.lcorrect" escape="false" />';				
</script>						
							<tr>
								<td width="20%" class="yh">状态</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="state" list="#{0:'草稿',1:'立即发布'}" name="learnInfo.state"></s:select>
								</td>
							</tr>
							
							<tr>
								<td height="30" colspan="2" align="center"><input
									type="submit" name="button" id="button" value="提交" class="tbtn">
									&nbsp;&nbsp; <input class="tbtn" type="button" name="button2"
									id="button2"
									onClick="location.href='<s:property value="backUrl"/>'"
									value="返回"></td>
							</tr>

						</table>
						</s:form>
					</td>
			</tr>
		</table>
	</div>
</body>
</html>
<style type="text/css">
.KeyWordListBoxCss{border:#BFCDF3 1px solid; background-color:#FFFFFF;display:none;}
.keyWordListTrCss{ cursor:pointer;width:100%;}
</style>
<%
	Object msg=request.getSession().getAttribute("msg");
	String str = (String) msg;
	if(msg!=null){
	    out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("msg");	
	}
%>
<jsp:include page="/common/scrollfollow_learn.jsp"></jsp:include>