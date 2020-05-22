<%@ page language="java" import="java.io.*,java.util.*"
	pageEncoding="UTF-8"%>

<%

　　String path = request.getContextPath();

　　%>

<%-- <%

　　out.print(request.getParameter("echostr"));

　　%> --%>

<%

　　// 接收XML数据

　　BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));

　　String line = null;

　　StringBuilder sb = new StringBuilder();

　　while((line = br.readLine())!=null){

　　sb.append(line);

　　}

　　// 取出发送用户

　　String xmlS = sb.toString();

　　System.out.println(xmlS);

　　if(xmlS !=null && !xmlS.equals("")){

　　int fromuser_s = xmlS.indexOf("<FromUserName$amp;>amp;$lt;![CDATA[");

　　int fromuser_e = xmlS.indexOf("]]$amp;>amp;$lt;/FromUserName$amp;>quot;$);

　　String fromuser = xmlS.substring(fromuser_s + 23, fromuser_e);

　　System.out.println("fromuser:"+fromuser);

　　// 取出目标用户

　　int touser_s = xmlS.indexOf("<ToUserName$amp;>amp;$lt;![CDATA[");

　　int touser_e = xmlS.indexOf("]]$amp;>amp;$lt;/ToUserName$amp;>quot;$);

　　String touser = xmlS.substring(touser_s + 21, touser_e);

　　System.out.println("touser:"+touser);

　　// 取出发送内容

　　int content_s = xmlS.indexOf("<Content$amp;>amp;$lt;![CDATA[");

　　int content_e = xmlS.indexOf("]]$amp;>amp;$lt;/Content$amp;>quot;$);

　　String content = xmlS.substring(content_s + 18, content_e);

　　System.out.println("content:"+content);

　　if(content.equals("Hello2BizUser")){//注意：Hello2BizUser是默认关注时发送的内容

　　out.print("<xml$amp;>quot;$);

　　out.print(" <ToUserName$amp;>amp;$lt;![CDATA["+fromuser+"]]$amp;>amp;$lt;/ToUserName$amp;>quot;$);

　　out.print(" <FromUserName$amp;>amp;$lt;![CDATA["+touser+"]]$amp;>amp;$lt;/FromUserName$amp;>quot;$);

　　out.print(" <CreateTime$amp;>quot;$+new Date().getTime()+"</CreateTime$amp;>quot;$);

　　out.print(" <MsgType$amp;>amp;$lt;![CDATA[text]]$amp;>amp;$lt;/MsgType$amp;>quot;$);

　　out.print(" <Content$amp;>amp;$lt;![CDATA[ 额.....没能成功识别，元芳淡定的说，“有错别字，换个字试试！]]$amp;>amp;$lt;/Content$amp;>quot;$);

　　out.print(" <FuncFlag>0</FuncFlag$amp;>quot;$);

　　out.print("</xml$amp;>quot;$);

　　}

　　%>