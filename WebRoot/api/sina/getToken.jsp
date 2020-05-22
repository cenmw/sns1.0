<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.cenmw.util.StringUtil"%>
<%@ page import="weibo4j.Oauth"%>
<%@ page import="weibo4j.http.AccessToken"%>
<%
	String accessToken = "";
	String uid = "";
	String code = request.getParameter("code");
	String callback = request.getParameter("callback");
	if (StringUtil.notNullStr(code)) {// 当登录成功后会向此地址发送code来获取token
		Oauth oauth = new Oauth();
		AccessToken at = oauth.getAccessTokenByCode(code);
		accessToken = at.getAccessToken();
		uid = at.getUid();
		String uri = "/api/sina/checkLogin?accessToken=" + accessToken
				+ "&uid=" + uid;
		if (StringUtil.notNullStr(callback)) {
			uri += "&callback=" + callback;
		}
%>
<script type="text/javascript">
var gourl = '<%=uri%>';
location.replace(gourl);
</script>
<%
	}
%>

