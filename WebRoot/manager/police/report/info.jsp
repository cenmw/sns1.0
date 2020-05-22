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
$(function(){ 
	$('#starttime').datepicker( {
		yearRange : '1990:2050', //取值范围.
		showOn : 'both', //输入框和图片按钮都可以使用日历控件。
		buttonImage : '/common/datepicker/date.gif', //日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});
	
	$('#endtime').datepicker( {
		yearRange : '1990:2050', //取值范围.
		showOn : 'both', //输入框和图片按钮都可以使用日历控件。
		buttonImage : '/common/datepicker/date.gif', //日历控件的按钮
		buttonImageOnly : true,
		showButtonPanel : true
	});
});

function checkcontentinfo(){
	var starttime=$.trim($("#starttime").val());
	var endtime=$.trim($("#endtime").val());
	if(starttime==""){
		alert("锁定开始时间不能为空");
		return false;
	}
	if(endtime==""){
		alert("锁定结束时间不能为空");
		return false;
	}
	document.getElementById("stime").value = document.getElementById("starttime").value +" "+ document.getElementById("shh").value +":"+ document.getElementById("smm").value+":00";
	
	document.getElementById("etime").value = document.getElementById("endtime").value +" "+ document.getElementById("ehh").value +":"+ document.getElementById("emm").value+":00";
	return true;
}
</script>
</head>
<body>
	<div style="text-align: center;">
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolor="#0099CC">
			<tr>
				<td align="left">
				<s:form action="save" namespace="/manager/memberreport" method="post" theme="simple" onsubmit="return checkcontentinfo()">
				         <s:hidden name="backUrl"></s:hidden>
						 <s:hidden name="memberReport.id"></s:hidden>
						 <s:hidden name="memberReport.ctime"></s:hidden>
						 <s:hidden name="memberReport.isdel"></s:hidden>
						 <s:hidden name="memberReport.mid"></s:hidden>
						 <s:hidden name="memberReport.type"></s:hidden>
						 <s:hidden name="memberReport.typename"></s:hidden>
						 <s:hidden name="memberReport.cid"></s:hidden>	
						 <s:hidden name="memberReport.classname"></s:hidden>
						 <s:hidden name="memberReport.rid"></s:hidden>		
						 <s:hidden name="memberReport.content"></s:hidden>		
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#0099CC">
							<tr>
								<td colspan="2" class="yh">查看信息&nbsp;</td>
							</tr>
						
							<tr>
								<td width="20%" class="yh">会员名称（ 举报 ）</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="memberReport.memberInfo.account"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">会员名称（ 被举报 ）</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:property value="memberReport.rmemberInfo.account"/>		
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">举报时间</td>
								<td width="80%" height="30">&nbsp;&nbsp;		
								<s:date name="memberReport.ctime" format="yyyy-MM-dd" />
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">举报内容</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:if test="memberReport.type==2">（日志）</s:if><s:elseif test="memberReport.type==3">（文集）</s:elseif><s:elseif test="memberReport.type==4">（照片）</s:elseif><s:elseif test="memberReport.type==5">（视频）</s:elseif><s:property value="memberReport.content" escape="false" />&nbsp;&nbsp;<a target="_blank" href="<s:property value="memberReport.url"/>">查看原文</a>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">举报对象名称</td>
								<td width="80%" height="135">&nbsp;&nbsp;
								<s:property value="memberReport.classname"/>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">状态</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<s:select id="state" list="#{1:'立即锁定'}" name="memberReport.state"></s:select>
								</td>
							</tr>
							
							<tr>
								<td width="20%" class="yh">锁定开始日期</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<input type="hidden" name="memberReport.starttime" id="stime" value="" />
								<input id="starttime" type="text" class="tin" style="width:100px;" readonly="readonly" name="starttime" value="<s:date name="memberReport.starttime" format="yyyy-MM-dd"/>" />		
								<select id="shh" name="shh">
								     <option value="00">00</option>
								     <option value="01">01</option>
									 <option value="02">02</option>
									 <option value="03">03</option>
									 <option value="04">04</option>
									 <option value="05">05</option>
									 <option value="06">06</option>
									 <option value="07">07</option>
									 <option value="08">08</option>
									 <option value="09">09</option>
									 <option value="10">10</option>
									 <option value="11">11</option>
									 <option value="12">12</option>
									 <option value="13">13</option>
									 <option value="14">14</option>
									 <option value="15">15</option>
									 <option value="16">16</option>
									 <option value="17">17</option>
									 <option value="18">18</option>
									 <option value="19">19</option>
									 <option value="20">20</option>
									 <option value="21">21</option>
									 <option value="22">22</option>
									 <option value="23">23</option>
								</select>
								<select id="smm" name="smm">
								     <option value="00">00</option>
								     <option value="01">01</option>
									 <option value="02">02</option>
									 <option value="03">03</option>
									 <option value="04">04</option>
									 <option value="05">05</option>
									 <option value="06">06</option>
									 <option value="07">07</option>
									 <option value="08">08</option>
									 <option value="09">09</option>
									 <option value="10">10</option>
									 <option value="11">11</option>
									 <option value="12">12</option>
									 <option value="13">13</option>
									 <option value="14">14</option>
									 <option value="15">15</option>
									 <option value="16">16</option>
									 <option value="17">17</option>
									 <option value="18">18</option>
									 <option value="19">19</option>
									 <option value="20">20</option>
									 <option value="21">21</option>
									 <option value="22">22</option>
									 <option value="23">23</option>
									 <option value="24">24</option>
									 <option value="25">25</option>
									 <option value="26">26</option>
									 <option value="27">27</option>
									 <option value="28">28</option>
									 <option value="29">29</option>
									 <option value="30">30</option>
									 <option value="31">31</option>
									 <option value="32">32</option>
									 <option value="33">33</option>
									 <option value="34">34</option>
									 <option value="35">35</option>
									 <option value="36">36</option>
									 <option value="37">37</option>
									 <option value="38">38</option>
									 <option value="39">39</option>
									 <option value="40">40</option>
									 <option value="41">41</option>
									 <option value="42">42</option>
									 <option value="43">43</option>
									 <option value="44">44</option>
									 <option value="45">45</option>
									 <option value="46">46</option>
									 <option value="47">47</option>
									 <option value="48">48</option>
									 <option value="49">49</option>
									 <option value="50">50</option>
									 <option value="51">51</option>
									 <option value="52">52</option>
									 <option value="53">53</option>
									 <option value="54">54</option>
									 <option value="55">55</option>
									 <option value="56">56</option>
									 <option value="57">57</option>
									 <option value="58">58</option>
									 <option value="59">59</option>
								</select>								
								</td>
							</tr>
<script type="text/javascript">
var stime = '<s:date name="memberReport.starttime" format="yyyy-MM-dd HH:mm"/>';
if(stime != '' && stime.indexOf(" ")>0){
   var ss =  stime.split(" ")[1].split(":");
   if(ss.length==2){
      document.getElementById("shh").value = ss[0];
	  document.getElementById("smm").value = ss[1];
   }
}
</script>				
							<tr>
								<td width="20%" class="yh">锁定结束日期</td>
								<td width="80%" height="30">&nbsp;&nbsp;
								<input type="hidden" name="memberReport.endtime" id="etime" value="" />
								<input id="endtime" type="text" class="tin" style="width:100px;" readonly="readonly" name="endtime" value="<s:date name="memberReport.endtime" format="yyyy-MM-dd"/>" />
								<select id="ehh" name="ehh">
								     <option value="00">00</option>
								     <option value="01">01</option>
									 <option value="02">02</option>
									 <option value="03">03</option>
									 <option value="04">04</option>
									 <option value="05">05</option>
									 <option value="06">06</option>
									 <option value="07">07</option>
									 <option value="08">08</option>
									 <option value="09">09</option>
									 <option value="10">10</option>
									 <option value="11">11</option>
									 <option value="12">12</option>
									 <option value="13">13</option>
									 <option value="14">14</option>
									 <option value="15">15</option>
									 <option value="16">16</option>
									 <option value="17">17</option>
									 <option value="18">18</option>
									 <option value="19">19</option>
									 <option value="20">20</option>
									 <option value="21">21</option>
									 <option value="22">22</option>
									 <option value="23">23</option>
								</select>
								<select id="emm" name="emm">
								     <option value="00">00</option>
								     <option value="01">01</option>
									 <option value="02">02</option>
									 <option value="03">03</option>
									 <option value="04">04</option>
									 <option value="05">05</option>
									 <option value="06">06</option>
									 <option value="07">07</option>
									 <option value="08">08</option>
									 <option value="09">09</option>
									 <option value="10">10</option>
									 <option value="11">11</option>
									 <option value="12">12</option>
									 <option value="13">13</option>
									 <option value="14">14</option>
									 <option value="15">15</option>
									 <option value="16">16</option>
									 <option value="17">17</option>
									 <option value="18">18</option>
									 <option value="19">19</option>
									 <option value="20">20</option>
									 <option value="21">21</option>
									 <option value="22">22</option>
									 <option value="23">23</option>
									 <option value="24">24</option>
									 <option value="25">25</option>
									 <option value="26">26</option>
									 <option value="27">27</option>
									 <option value="28">28</option>
									 <option value="29">29</option>
									 <option value="30">30</option>
									 <option value="31">31</option>
									 <option value="32">32</option>
									 <option value="33">33</option>
									 <option value="34">34</option>
									 <option value="35">35</option>
									 <option value="36">36</option>
									 <option value="37">37</option>
									 <option value="38">38</option>
									 <option value="39">39</option>
									 <option value="40">40</option>
									 <option value="41">41</option>
									 <option value="42">42</option>
									 <option value="43">43</option>
									 <option value="44">44</option>
									 <option value="45">45</option>
									 <option value="46">46</option>
									 <option value="47">47</option>
									 <option value="48">48</option>
									 <option value="49">49</option>
									 <option value="50">50</option>
									 <option value="51">51</option>
									 <option value="52">52</option>
									 <option value="53">53</option>
									 <option value="54">54</option>
									 <option value="55">55</option>
									 <option value="56">56</option>
									 <option value="57">57</option>
									 <option value="58">58</option>
									 <option value="59">59</option>
								</select>		
								</td>
							</tr>
<script type="text/javascript">
var etime = '<s:date name="memberReport.endtime" format="yyyy-MM-dd HH:mm"/>';
if(etime != '' && etime.indexOf(" ")>0){
   var ss =  etime.split(" ")[1].split(":");
   if(ss.length==2){
      document.getElementById("ehh").value = ss[0];
	  document.getElementById("emm").value = ss[1];
   }
}
</script>
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
<%
	Object msg=request.getSession().getAttribute("msg");
	String str = (String) msg;
	if(msg!=null){
	    out.println("<script language=\"javascript\">alert('"+(String)msg+"');</script>");
		request.getSession().removeAttribute("msg");	
	}
%>