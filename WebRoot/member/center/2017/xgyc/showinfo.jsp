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
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/common/newkindeditor/lang/zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/common/newkindeditor/themes/default/default.css"></link>
<link href="/common/js/popup/jquery.popup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/common/js/popup/jquery.popup.js"></script>
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
   		<h2 class="second-title"><a class="blue" href="<s:if test="backUrl == null">/</s:if><s:else><s:property value="backUrl"/></s:else>"><<返回上一页</a>查看习惯养成</h2>
  
       <div class="vedio-list">
        	<div class="pl-title">
            	<h1><s:if test="memberDiaryXGYC.rcid>0">[转]</s:if><s:property value="memberDiaryXGYC.ptime" escape="false" /></h1>
                <p class="title-infor"><span><s:date name="memberDiaryXGYC.ctime" format="yyyy-MM-dd"/></span>
					<span>作者：<s:property value="memberDiaryXGYC.memberInfo.account" escape="false" /></span>
					<span>点击量：<s:property value="memberDiaryXGYC.viewnumber" escape="false" /></span></p>
            </div>
            <div class="pl-content">
			<table class="listGrid" width="710" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="th" colspan="2">品德习惯</td>
					<td class="th" colspan="2">生活习惯</td>
					<td class="th" colspan="2">学习习惯</td>
					<td class="th" colspan="2">读书与运动</td>
				</tr>
				<tr>
					<td>易物明求</td>
					<td><s:property value="memberDiaryXGYC.pdxg1" escape="false" /></td>
					<td>早睡早起</td>
					<td><s:property value="memberDiaryXGYC.shxg1" escape="false" /></td>
					<td>案洁包整</td>
					<td><s:property value="memberDiaryXGYC.xxxg1" escape="false" /></td>
					<td>必读</td>
					<td><s:property value="memberDiaryXGYC.dsydxg1" escape="false" /></td>
				</tr>
				<tr>
					<td>借物必还</td>
					<td><s:property value="memberDiaryXGYC.pdxg2" escape="false" /></td>
					<td>洗脸刷牙</td>
					<td><s:property value="memberDiaryXGYC.shxg2" escape="false" /></td>
					<td>字正心正</td>
					<td><s:property value="memberDiaryXGYC.xxxg2" escape="false" /></td>
					<td>其他图书</td>
					<td><s:property value="memberDiaryXGYC.dsydxg2" escape="false" /></td>
				</tr>
				<tr>
					<td>敬长呼人</td>
					<td><s:property value="memberDiaryXGYC.pdxg3" escape="false" /></td>
					<td>衣着得体</td>
					<td><s:property value="memberDiaryXGYC.shxg3" escape="false" /></td>
					<td>阅毕归位</td>
					<td><s:property value="memberDiaryXGYC.xxxg3" escape="false" /></td>
					<td>跳绳</td>
					<td><s:property value="memberDiaryXGYC.dsydxg3" escape="false" /></td>
				</tr>
				<tr>
					<td>用餐长先</td>
					<td><s:property value="memberDiaryXGYC.pdxg4" escape="false" /></td>
					<td>少吃零食</td>
					<td><s:property value="memberDiaryXGYC.shxg4" escape="false" /></td>
					<td>专心读书</td>
					<td><s:property value="memberDiaryXGYC.xxxg4" escape="false" /></td>
					<td>仰卧起坐</td>
					<td><s:property value="memberDiaryXGYC.dsydxg4" escape="false" /></td>
				</tr>
				<tr>
					<td>出言信先（诚实）</td>
					<td><s:property value="memberDiaryXGYC.pdxg5" escape="false" /></td>
					<td>屋净墙清</td>
					<td><s:property value="memberDiaryXGYC.shxg5" escape="false" /></td>
					<td></td>
					<td></td>
					<td>体前屈</td>
					<td><s:property value="memberDiaryXGYC.dsydxg5" escape="false" /></td>
				</tr>
				<tr>
					<td>言少词优（文明）</td>
					<td><s:property value="memberDiaryXGYC.pdxg6" escape="false" /></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>其他运动</td>
					<td><s:property value="memberDiaryXGYC.dsydxg6" escape="false" /></td>
				</tr>
				<tr>
					<td>改过勿掩（认错）</td>
					<td><s:property value="memberDiaryXGYC.pdxg7" escape="false" /></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			<table class="note-tabs1" width="710" border="0" cellspacing="0" cellpadding="0">

				<tr>
					<td colspan="2" height="50" align="left"><font class="font14">
						今天家里每个人做得最好，进步最大地方是什么？</font>
					</td>
				</tr>

				<tr style="padding-bottom:10px;">
				<td colspan="2" style="line-height:25px;">
					<p>孩子：<s:property value="memberDiaryXGYC.hzbx" escape="false" /></p>
					<p>父亲：<s:property value="memberDiaryXGYC.fqbx" escape="false" /></p>
					<p>母亲：<s:property value="memberDiaryXGYC.mqbx" escape="false" /></p>
				</td>
			  </tr>
			  
			  <tr>
				<td colspan="2" height="50" align="left"><font class="font14">今天一天我们一家人的感受是什么呢？</font></td>
			  </tr>

				<tr style="padding-bottom:10px;">
					<td  colspan="2"style="line-height:25px;">
						<p><s:property value="memberDiaryXGYC.wdgs" escape="false" /></p>
					</td>
				</tr>

				<s:if test="memberDiaryXGYC.memberInfo.id == memberInfo.id">
				<tr>
					<td  colspan="2">
						<a class="blue" style="font-weight:bold;" href="myzyinfo">
							<input class="inputButton" type="button" value="@龙爸爸"/>
						</a>
						<a class="blue" style="font-weight:bold;" href="mysdinfo">
							<input class="inputButton" type="button" value="我要疏导"/>
						</a>
						<a class="blue" style="font-weight:bold;" href="wrssinfo">
							<input class="inputButton" type="button" value="吾日省身"/>
						</a>
					</td>
				</tr>
				</s:if>

			</table>			

			</div>
            <div class="fr pl-zan"><jsp:include page="/member/center/2017/xgyc/inc/fxinfo.jsp"></jsp:include></div>
            <div class="clear"></div>
            
			<!--分享到-->
			<div style="width:710px; height:30px; margin:15px auto;">
			<!-- Baidu Button BEGIN -->
			<div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
			<span style="color:#000000;" class="bds_more">分享到：</span>
			<a class="bds_qzone"></a>
			<a class="bds_tsina"></a>
			<a class="bds_tqq"></a>
			<a class="bds_renren"></a>
			<a class="bds_t163"></a>
			<a class="shareCount"></a>
			</div>
			<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6883689" ></script>
			<script type="text/javascript" id="bdshell_js"></script>
			<script type="text/javascript">
			document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
			</script>
			<!-- Baidu Button END -->
			</div>
			<!--分享到 end-->
			
			<s:action name="inccomment" namespace="/membercenter" executeResult="true">
				<s:param name="type" value="10"></s:param>
				<s:param name="cid" value="memberDiaryXGYC.id"></s:param>
				<s:param name="backUrl" value="backUrl"></s:param>
			</s:action>
	
        </div>	
  </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>