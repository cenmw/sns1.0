<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
				<meta http-equiv="expires" content="0">
					<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
						<meta http-equiv="description" content="This is my page">
							<title>龙爸爸成长在线后台 管理系统</title>
							<link href="/manager/css/backstage.css" rel="stylesheet"
								type="text/css">
								<script src="/common/js/jquery-1.4.js" language="javascript">
</script>
								<script src="/manager/js/sys.js" language="javascript">
</script>
								<script src="/common/loading/loading-min.js"
									language="javascript">
</script>
								<script src="/common/loading/jquery.bgiframe.min.js"
									language="javascript">
</script>
								<link href="/common/loading/loading.css" rel="stylesheet"
									type="text/css">

									<script type="text/javascript">
$(function() {
	$(".cllist").click(function() {
		var check = $(this).attr("checked");
		var aid = $(this).attr("rel");
		if (check) {
			$(".sllist" + aid).attr("checked", "true");
		} else {
			$(".sllist" + aid).removeAttr("checked");
		}
	});
});
function saveactiontogroup(actionid) {
	var groupid = '<s:property value="groupid"/>';
	var link = '/manager/sys/saveaction';
	var loading = new ol.loading( {
		id : "loadbody"
	});//初始化对象
	loading.show();//显示遮罩
	$.ajax( {
		type : 'POST',
		url : link,
		data : 'id=' + actionid + '&groupid=' + groupid,
		success : function(msg) {
			alert('添加成功');
			loading.hide();//隐藏遮罩
		self.parent.tb_remove();
		self.parent.reflush();
	}
	});
}
function saveactionlist() {
	var actionids = "";
	$(".sllist").each(function() {
		if ($(this).attr("checked")) {
			actionids += "," + $(this).val();
		}
	});
	if (actionids.length > 0) {
		actionids = actionids.substring(1);
		var groupid = '<s:property value="groupid"/>';
		var link = '/manager/sys/saveactionlist';
		var loading = new ol.loading( {
			id : "loadbody"
		});//初始化对象
		loading.show();//显示遮罩
		$.ajax( {
			type : 'POST',
			url : link,
			data : 'acts=' + actionids + '&groupid=' + groupid,
			success : function(msg) {
				alert('添加成功');
				loading.hide();//隐藏遮罩
			self.parent.tb_remove();
			self.parent.reflush();
		}
		});
	} else {
		alert('请选择要添加的权限');
	}
}
</script>
	</head>

	<body>

		<div class="right">
			<div class="sub">
				<a href="javascript:saveactionlist();">添加</a>
			</div>
			<div class="right_con">
				<table id="loadbody" width="100%" border="1" cellpadding="0"
					cellspacing="0" bordercolor="#0099CC">

					<s:iterator value="cloList" id="cl">
						<tr>
							<td class="yh00">
								<input type="checkbox" class="cllist"
									rel="<s:property value="actioncolumnid"/>" />
							</td>
							<td colspan="2" class="yh00" style="text-align: left;">
								&nbsp;
								<s:property value="actioncolumnname" />
							</td>
						</tr>
						<s:iterator value="spList" id="sl">
							<s:if test="#cl.actioncolumnid==#sl.actioncolumnid">
								<tr>
									<td class="yh00">
										<input type="checkbox" value="<s:property value="actionid"/>"
											class="sllist<s:property value="#sl.actioncolumnid"/> sllist" />
									</td>
									<td class="yh00" style="text-align: left; padding-left: 20px;">
										<a
											href="javascript:saveactiontogroup('<s:property value="actionid"/>')"><s:property
												value="actionname" /> </a>
									</td>
									<td class="yh00" style="text-align: left;">
										<a
											href="javascript:saveactiontogroup('<s:property value="actionid"/>')"><s:property
												value="action" /> </a>
									</td>
								</tr>
							</s:if>
						</s:iterator>
					</s:iterator>
				</table>
			</div>
		</div>
	</body>
</html>
