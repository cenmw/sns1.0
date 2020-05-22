<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>上传头像</title>
    <script src="/common/js/jquery-1.4.js" type="text/javascript"></script>
    <script type="text/javascript" src="/member/js/ajaxupload.js"></script>
    <link href="/member/css/ajaxupload.css" rel="stylesheet" type="text/css">

</head>

<body>
<s:form cssClass="upform" action="uploadAvatar" namespace="/front/member" enctype="multipart/form-data" method="POST"
        theme="simple" onsubmit="return startProgress()">
    <div id="uploadbox" style="text-align: center; margin-top: 10px;height: 35px;">
        <input type="hidden" name="memberid" value="<s:property value="#parameters.memberid"/>"></input>
        <input type="hidden" name="status" value="<s:property value="#parameters.status"/>"></input>
        <s:hidden name="customType" value="imageBaseDir"></s:hidden>
        <span id="errormsg" style="display:none"><s:fielderror name="struts.messages.error.content.type.not.allowed"
                                                               theme="simple"></s:fielderror></span>
        <script>
            var errormsg = $.trim($("#errormsg").text());
            if (errormsg != "") {
                $('.alert').html('(支持：bmp,jpg,png)').addClass('alert-warning').show().delay(1500).fadeOut();
            }
        </script>
        <input type="hidden" name="customDir" value="member,avatar"></input>

        <input type="file" id="uploadfile" name="excel"></input>

        <input type="submit" name="submitbutton" id="submitbutton"
               value="上传" class="sbtn"></input>
    </div>

    <div id="progressBar" style="display: none;">
        <div id="theMeter">
            <div id="progressBarText">准备上传</div>
            <div id="progressBarBox">
                <div id="progressBarBoxContent"></div>
            </div>
        </div>
    </div>
</s:form>
</body>
</html>
