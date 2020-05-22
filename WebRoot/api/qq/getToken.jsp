<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.cenmw.util.StringUtil"%>
<%
	String code=request.getParameter("code");
	String openid=request.getParameter("openid");
	String callback=request.getParameter("callback");
	String uri="/api/qq/checkLogin?code="+code+"&openid="+openid;
	if(StringUtil.notNullStr(callback)){
		uri+="&callback="+callback;
	}
%>
<script type="text/javascript">
var gourl = '<%=uri%>';
location.replace(gourl);
</script>
