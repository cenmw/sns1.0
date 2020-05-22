<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta property="qc:admins" content="05163075076476721216375636716450"/>
    <meta name="Description"
          content="龙爸爸成长在线 是一个真实的社交网络，联络你和你周围的朋友。 加入龙爸爸成长在线你可以:联络朋友，了解他们的最新动态；和朋友分享相片、音乐和电影；找到老同学，结识新朋友；用照片和日志记录生活,展示自我。"/>
    <meta name="Keywords" content="longbaba,龙爸爸成长在线,幼儿园,小学,中学,大学,同学,同事,白领,个人主页,相册,群组,社区,交友,聊天,音乐,视频,龙爸爸,成长在线,龙爸爸成长在线"/>
    <title>龙爸爸成长在线</title>
    <link rel="stylesheet" type="text/css" href="/member/css/base.css"/>
    <script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
    <script language="javascript">
        function check() {
            var accountobj = document.getElementById("ac");
            if (accountobj.value == '') {
                document.getElementById("ac").value = "";
                document.getElementById("ac").focus();
                return false;
            }
            var passwordobj = document.getElementById("password");
            if (passwordobj.value == '') {
                document.getElementById("password").focus();
                return false;
            }
            return true;
        }

        $(function () {
            $("#ac").keyup(function () {
                if (document.getElementById("password").value != '') {
                }
            });
        });

        $(function () {
            $("#password").keyup(function () {
                if (document.getElementById("password").value != '') {
                }
            });
        });
    </script>
</head>
<body>
<!--top-->
<div class="login-top">
    <div class="layout-control">
        <a class="login-logo fl" href="/"></a>
        <div class="fr login-r">您没有龙爸爸帐号，<a class="blue" href="/reg">注册</a></div>
    </div>
</div>
<!--top end-->
<!--content-->
<form action="login" id="loginForm" namespace="/" method="post" onsubmit="return check()">
    <div class="layout-control">
        <div class="login-enter-l fl">
            <div class="login-content">
                <div class="login-input"><input type="text" maxlength="30" name="account" id="ac"
                                                placeholder="姓名/手机号/邮箱"/></div>
                <div class="login-input"><input type="password" maxlength="20" name="password" id="password"
                                                placeholder="请输入密码"/></div>
                <div class="jizhu">
                    <span class="jizhu-l fl"><input type="checkbox" checked="checked"/>下次自动登录</span>
                    <a class="jizhu-r fr blue" href="/member/findpwd.jsp">忘记密码?</a>
                </div>
                <div class="login-login"><input id="login" type="submit" class="login-input-btn" value=""/></div>
                <div class="login-login"><input type="button" onclick="location.href='/reg'" class="login-input-btn1"
                                                value=""/></div>
                <!-- <div class="login-login"><input type="button" onclick="location.href='/creg'" class="login-input-btn1_1"  value="" /></div> -->
                <div class="other-login">
                    <h2>使用其它帐号登录：</h2>
                    <ul>
                        <li><a href="/api/qq/index"><img src="/member/images/login1.png" height="22" width="82"
                                                         border="0"/></a></li>
                        <li><a href="/api/sina/index"><img src="/member/images/login3.png" height="22" width="89"
                                                           border="0"/></a></li>
                    </ul>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
        <!-- 图片播放 -->
        <script type="text/javascript" src="/member/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="/member/js/slides.js"></script>
        <style type="text/css">
            /*幻灯片*/
            #focus {
                width: 880px;
                height: 460px;
                overflow: hidden;
                position: relative;
                margin: 0px auto;
            }

            #focus ul {
                height: 270px;
                position: absolute;
            }

            #focus ul li {
                float: left;
                width: 880px;
                height: 460px;
                overflow: hidden;
                position: relative;
                background: #000;
            }

            #focus ul li div {
                position: absolute;
                overflow: hidden;
            }

            #focus .btnBg {
                position: absolute;
                width: 880px;
                left: 0;
                bottom: 0;
                background: #000;
            }

            #focus .btn {
                position: absolute;
                width: 880px;
                height: 25px;
                padding: 5px 10px;
                right: 0;
                bottom: 0;
                text-align: right;
                right: 100px;
            }

            #focus .btn span {
                display: inline-block;
                _display: inline;
                _zoom: 1;
                width: 25px;
                height: 10px;
                _font-size: 0;
                margin-left: 5px;
                cursor: pointer;
                background: #fff;
            }

            #focus .btn span.on {
                background: #fff;
            }

            #focus .preNext {
                width: 45px;
                height: 100px;
                position: absolute;
                top: 90px;
                cursor: pointer
            }

            #focus .pre {
                left: 0;
            }

            #focus .next {
                right: 0;
                background-position: right top;
            }

            #focus ul li .slideother {
                position: absolute;
                left: 15px;
                top: 280px;
                width: 890px;
                height: 80px;
                display: inline-block;
                background: url('images/bg_down.gif') no-repeat 0 0;
            }

            #focus ul li .slideother .h12 {
                font-size: 24px;
                padding-left: 10px;
                padding-bottom: 5px;
                position: absolute;
                top: 0px;
                left: 55px;
            }

            #focus ul li .slideother .h12 a {
                color: #FFF;
                font-size: 24px;
                font-weight: bold;
                padding-top: 10px;
                text-decoration: none;
            }

            #focus ul li .slideother .h12 a:hover {
                text-decoration: underline;
            }

            #focus ul li .slideother p {
                padding-left: 10px;
                position: absolute;
                top: 38px;
                left: 55px;
            }

            #focus ul li .slideother p a {
                color: #8B8B8B;
                text-decoration: none;
            }

            #focus ul li .slideother a:hover {
                color: #FFF;
            }

            #focus ul li .slideother p a:hover {
                text-decoration: none;
            }
        </style>
        <div class="login-enter-r fr">
            <div id="focus">
                <ul>
                    <s:iterator value="indexbiglist" id="beanlist" status="beanlist1">
                        <li>
                            <h2>
                                <s:if test="content == ''">
                                <img width="880px" src="<s:property value="picpath"/>" alt="<s:property value="title"/>"/></h2>
                            </s:if>
                            <s:elseif test="content != ''">
                                <a target="_blank" href="">
                                    <img width="880px" src="<s:property value="picpath"/>" alt="<s:property value="title"/>"/></h2>
                                </a>
                            </s:elseif>
                        </li>
                    </s:iterator>
                </ul>
            </div>
        </div>
        <div class="clear"></div>
    </div>

    <style>
        .new_index {
            background-color: #fff;
            height: 760px;
            margin-bottom: 10px;
        }

        .new_index1 {
            margin-right: 19px;
            width: 312px;
            margin-bottom: 20px;
            border: 1px solid #e3e3e3;
        }

        .new_index2 {
            width: 312px;
            margin-bottom: 20px;
            border: 1px solid #e3e3e3;
        }

        .online-list {
            padding-left: 5px;
            padding-right: 5px;
        }
    </style>

</form>
<!--content end-->
<jsp:include page="/member/inc/foot.jsp"></jsp:include>
</body>
</html>
<%
    Object msg = request.getSession().getAttribute("loginMemberInfoMsg");
    if (msg != null) {
        out.println("<script language=\"javascript\">$('.alert').html('" + (String) msg + "').addClass('alert-danger').show().delay(1500).fadeOut();</script>");
        request.getSession().removeAttribute("loginMemberInfoMsg");
        Object loginAccount = request.getSession().getAttribute("loginAccount");
        if (loginAccount != null) {
            out.println("<script language=\"javascript\">document.getElementById(\"ac\").value = '" + loginAccount + "';</script>");
            request.getSession().removeAttribute("loginAccount");
        }
    }
%>
