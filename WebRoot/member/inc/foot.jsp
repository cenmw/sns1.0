<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
function addfavorite()
{
   if (document.all)
   {
      window.external.addFavorite('http://www.longbaba.com.cn','龙爸爸成长在线');
   }
   else if (window.sidebar)
   {
      window.sidebar.addPanel('龙爸爸成长在线', 'http://www.longbaba.com.cn', "");
   }
} 
</script>

<link rel="stylesheet" type="text/css" href="/member/css/base.css" />
<div class="footer layout-control">
<div class="foot1"><a href="/aboutus">关于我们</a>|<a href="/contactus">联系我们</a>|<a href="/recruitment">招聘信息</a>|<a href="/link">友情链接</a>|<a href="/help">客服帮助</a>|<a href="/privacy">隐私说明</a>|<a href="javascript:void(0)" onclick="addfavorite()">收藏龙爸爸成长在线</a>
</div>
<div class="foot1" style="margin-top:10px;"> 京公网安备11000002003463号 　<a target="_blank" href="http://www.miibeian.gov.cn/">京ICP备15055401号</a>
<!-- longbaba.com.cn Baidu tongji analytics --><script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F383a0e99ccbab9f0a31c528e30851c7e' type='text/javascript'%3E%3C/script%3E"));
</script>
</div>
<p class="foot2 mar-t10">Copyright © 2013- 2014  All Rights Reserved.龙爸爸成长在线 版权所有</p>
</div>
<style>
   .alert {
      display: none;
      position: fixed;
      top: 30%;
      left: 50%;
      min-width: 200px;
      margin-left: -100px;
      z-index: 99999;
      padding: 15px;
      border: 1px solid transparent;
      border-radius: 4px;
      font-size: larger;
   }

   .alert-success {
      color: #3c763d;
      background-color: #dff0d8;
      border-color: #d6e9c6;
   }

   .alert-info {
      color: #31708f;
      background-color: #d9edf7;
      border-color: #bce8f1;
   }

   .alert-warning {
      color: #8a6d3b;
      background-color: #fcf8e3;
      border-color: #faebcc;
   }

   .alert-danger {
      color: #a94442;
      background-color: #f2dede;
      border-color: #ebccd1;
   }
</style>
<div class="alert"></div>
