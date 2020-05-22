<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="memberInfo.account" />-习惯养成-龙爸爸成长在线</title>
<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
function checkcontentinfo(){
    $("#frm").submit();
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
   		<h2 class="second-title"><a class="blue" href="/"><<返回上一页</a>习惯养成</h2>
<form id="frm" method="post" action="xgycinfo5?dpage=4&id=<s:property value="memberDiaryXGYC.id" escape="false" />">
<table class="listGrid" width="640" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
      <td colspan="2" align="center" style="font-size: 14px; color: #FF0000;height: 60px;">读书与运动</td>
  </tr>

    <tr>
        <td class="th" align="center">项目</td>
        <td class="th" align="center">得分</td>
    </tr>

    <tr>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">必读</td>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">
            <select name="dsydxg1" id="dsydxg1">
                <option>10</option><option>9</option><option>8</option><option>7</option><option>6</option>
                <option>5</option><option>4</option><option>3</option><option>2</option><option>1</option>
            </select>
        </td>
    </tr>

    <tr>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">其他图书</td>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">
            <select name="dsydxg2" id="dsydxg2">
                <option>10</option><option>9</option><option>8</option><option>7</option><option>6</option>
                <option>5</option><option>4</option><option>3</option><option>2</option><option>1</option>
            </select>
        </td>
    </tr>

    <tr>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">跳绳</td>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">
            <select name="dsydxg3" id="dsydxg3">
                <option>10</option><option>9</option><option>8</option><option>7</option><option>6</option>
                <option>5</option><option>4</option><option>3</option><option>2</option><option>1</option>
            </select>
        </td>
    </tr>

    <tr>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">仰卧起坐</td>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">
            <select name="dsydxg4" id="dsydxg4">
                <option>10</option><option>9</option><option>8</option><option>7</option><option>6</option>
                <option>5</option><option>4</option><option>3</option><option>2</option><option>1</option>
            </select>
        </td>
    </tr>

    <tr>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">体前屈</td>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">
            <select name="dsydxg5" id="dsydxg5">
                <option>10</option><option>9</option><option>8</option><option>7</option><option>6</option>
                <option>5</option><option>4</option><option>3</option><option>2</option><option>1</option>
            </select>
        </td>
    </tr>

    <tr>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">其他</td>
        <td style="line-height:25px; padding-top:10px;" height="35" width="50%" align="center" valign="top">
            <select name="dsydxg6" id="dsydxg6">
                <option>10</option><option>9</option><option>8</option><option>7</option><option>6</option>
                <option>5</option><option>4</option><option>3</option><option>2</option><option>1</option>
            </select>
        </td>
    </tr>
  
  <tr>
    <td colspan="2" align="center" style="font-size: 14px;height: 50px;">
        <a class="blue" style="font-weight:bold;" href="javascript:void();" onclick="checkcontentinfo();"><input class="inputButton" type="button" value="下一步"/></a>
    </td>
  </tr>
</table>
</form>		
   </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
