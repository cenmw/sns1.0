<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="/comment/tieba/editor.css"/>
<script type="text/javascript" src="/comment/tieba/editor.js"></script>
<script type="text/javascript" src="/comment/tieba/tiebatitle.js"></script>
<script type="text/javascript" src="/member/js/common.js"></script>
<script type="text/javascript">
    $(function () {
        $("#commentEditor").keyup(function () {
            var topic_len = zjlen($.trim($("#commentEditor").html()));
            if (topic_len > 280) {
                $("#mcontentclenid").html("标题不能超过140个汉字长度");
            } else if (topic_len == 0) {
                $("#mcontentclenid").html("");
            } else {
                $("#mcontentclenid").html("");
            }
            $("#mcontentlenid").html(parseInt(topic_len / 2) + "/" + 140);
        });
        $("#commentEditor").focus();
    });
</script>
<div class="pl-content1">
    <div class="pl-cn1">
        <p class="pl-limg"><img src="<s:property value="memberInfo.avatar_center" />" height="80" width="80"
                                border="0"/></p>
        <div class="pl-rcn">
            <div class="pl-rcn-tp">
                <div class="pl-rcn-text" id="commentEditor"></div>
                <div class="pl-fabu"><span class="pl-icon" id="commentFace"
                                           style="cursor: pointer; position: relative;"><img
                        src="/common/kindeditor/plugins/emoticons/0.gif" height="24" width="24" border="0"/></span><span
                        class="pl-icon" id="mcontentlenid">0/140</span><span class="pl-icon" id="mcontentclenid"></span>
                </div>
            </div>
            <div class="pl-fabu-btn"><input type="button" value="" onclick="pcomment()"/></div>

        </div>
    </div>
    <script type="text/javascript">
        function pcomment() {
            var postUrl = '/membercenter/commentsave';
            var no = $.trim($("#commentEditor").html());
            if (no == "") {
                $('.alert').html('请输入评论内容！').addClass('alert-danger').show().delay(1500).fadeOut();
                $("#commentEditor").focus();
                return;
            } else if (zjlen($.trim($("#commentEditor").html())) > 280) {
                $('.alert').html('标题不能超过140个汉字长度！').addClass('alert-danger').show().delay(1500).fadeOut();
                $("#commentEditor").focus();
                return;
            }
            var postData = 'no=' + encodeURIComponent(no) + '&type=<s:property value="type"/>&cid=<s:property value="cid"/>';
            $.ajax({
                type: 'post',
                url: postUrl,
                data: postData,
                success: function (state) {
                    if (state == 0) {
                        To_loginWindow(4);
                    } else if (state == 1) {
                        $('.alert').html('评论成功！').addClass('alert-success').show().delay(1500).fadeOut();
                        window.location.reload();
                    }
                }
            });
        }
    </script>
    <!--评论内容-->
    <div class="pl-list-cn">
        <s:iterator value="pageBean.pageList" id="beanlist" status="beanlist1">
            <!--1-->
            <div class="pl-list">
                <a class="pl-img fl" href="javascript:void(0)"><img src="<s:property value="memberInfo.avatar_small" />"
                                                                    height="31" width="31" border="0"></a>
                <div class="pl-word fl">
                    <div class="user-publish">
                        <a class="blue" href="javascript:void(0)"><s:property value="memberInfo.account"/> :</a>
                        <s:property value="#beanlist.content" escape="false"/>
                    </div>
                    <div class="user-talk">
                        <div class="gray"><s:date name="#beanlist.ctime" format="yyyy-MM-dd HH:mm"/></div>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </s:iterator>
    </div>
    <!--评论内容 end-->
    <!--分页-->
    <s:property value="pageBean.gopagehtml" escape="false"/>
    <!--分页 end-->
</div>