<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script src="/common/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="kindeditor-min.js"></script>
<script charset="utf-8" src="lang/zh_CN.js"></script>
<script>
			$(function() {
				var editor = KindEditor.create('textarea[name="content"]');
			});
		</script>
</head>

<body>
<h3>使用jQuery</h3>
		<form>
			<textarea name="content" style="width:800px;height:200px;"></textarea>

		</form>
</body>
</html>
