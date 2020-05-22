String.prototype.len = function () {
    return this.replace(/[^\x00-\xff]/g, "**").length;
}
function checkRegMember() {

    var t = true;
    var suc = '<span class="succeed"></span>';
    var failure = '<span class="failure"></span>';
    //$("#accountem").html('4-20位字母或数字，中文。注册成功后不可修改。'+suc).attr("class","ash");
    $("#passwordem").html('最少6个字符，不超过15位，请勿使用个人信息！' + suc).attr("class", "ash");
    $("#m_emailem").html('邮箱地址可用于登录您的账户和找回密码。').attr("class", "ash");
    $("#codeem").html('');
    if (!checkEmailAjax()) {
        $("#m_email").focus();
        t = false;
        return;
    }
    if (!checkAccountAjax()) {
        $("#m_account").focus();
        t = false;
        return;
    }
    var pwd = $.trim($("#password").val());
    if (pwd == "") {
        $("#password").focus();
        t = false;
        return;
    } else {
        if (pwd.length < 6 || pwd.length > 15) {
            $("#password").focus();
            t = false;
            return;
        }
    }
    var code = $.trim($("#code").val());
    if (code == "") {
        $("#code").focus();
        t = false;
        return;
    }

    if (t) {
        document.getElementById("regLogin").disabled = true;
        $("#regForm").submit();
    }
}

function checkCRegMember() {

    var t = true;
    var suc = '<span class="succeed"></span>';
    var failure = '<span class="failure"></span>';
    //$("#accountem").html('4-20位字母或数字，中文。注册成功后不可修改。'+suc).attr("class","ash");
    $("#passwordem").html('最少6个字符，不超过15位，请勿使用个人信息！' + suc).attr("class", "ash");
    $("#repasswordem").html('　' + suc).attr("class", "ash");
    $("#m_emailem").html('邮箱地址可用于登录您的账户和找回密码！' + suc).attr("class", "ash");
    var account = $.trim($("#m_account").val());
    if (account == "") {
        $("#accountem").html('机构名称不能为空！' + failure).attr("class", "ash_red");
        t = false;
    }
    var cname = $.trim($("#m_cname").val());
    if (cname == "") {
        $("#cnameem").html('法人名称不能为空！' + failure).attr("class", "ash_red");
        t = false;
    }
    var telphone = $.trim($("#m_telphone").val());
    if (telphone == "") {
        $("#telphoneem").html('机构电话不能为空！' + failure).attr("class", "ash_red");
        t = false;
    }
    var cpicpath = $.trim($("#cpicpath").val());
    if (cpicpath == "" || cpicpath == "/member/images/common/no_photo.png") {
        $("#cpicpathem").html('营业执照需要上传！' + failure).attr("class", "ash_red");
        t = false;
    } else {
        $("#cpicpathem").html('');
    }

    var pwd = $.trim($("#password").val());
    if (pwd == "") {
        $("#passwordem").html('密码不能为空！' + failure).attr("class", "ash_red");
        t = false;
    } else {
        if (pwd.length < 6 || pwd.length > 15) {
            $("#passwordem").html('密码最少6个字符，不超过15位！' + failure).attr("class", "ash_red");
            t = false;
        }
    }

    var repwd = $.trim($("#repassword").val());
    if (repwd == "") {
        $("#repasswordem").html('确认密码不能为空！' + failure).attr("class", "ash_red");
    }
    var email = $.trim($("#m_email").val());
    if (!checkEmail(email)) {
        $("#m_emailem").html('邮箱格式不正确！' + failure).attr("class", "ash_red");
        t = false;
    }

    var code = $.trim($("#code").val());
    if (code == "") {
        $("#codeem").html('验证码不能为空！').css("color", "red");
        t = false;
    }

    if (t) {
        $("#regForm").submit();
    }
}

//验证手机
function checkMobile(val) {
    var reg = /^[1][3,5,8][0-9]{9}$/;
    return reg.test(val);
}
//验证电话
function checkPhone(val) {
    var reg = /^(((\()?\d{2,4}(\))?[-(\s)*]){0,2})?(\d{7,8})$/;
    return reg.test(val);
}
//验证账号
function checkAccount(account) {
    //var reg = /^([A-Za-z0-9]){4,20}$/;
    var reg = /^(\w|[\u4E00-\u9FA5])*$/;
    return reg.test(account);
}
//验证邮箱
function checkEmail(email) {
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    return reg.test(email);
}
function checkPost(val) {   //邮政编码判断
    var reg = /^[0-9]{6}$/;
    return reg.test(val);
}

function updateCheckcode() {
    $("#checkcode").attr("src", "/common/checkcode.jsp?t=" + Math.random());
}


function changeInfo(obj) {
    var val = obj.value;

    if (val == 1) {
        $("#nicknamediv").hide();
        $("#nicknamediv1").hide();
        $("#companydiv").show();


    } else {
        $("#nicknamediv").show();
        $("#nicknamediv1").show();
        $("#companydiv").hide();


    }
}
function checkAccountAjax() {
    var suc = '<span class="succeed"></span>';
    var failure = '<span class="failure"></span>';
    var t = true;
    var account = $.trim($("#m_account").val());
    if (account == "") {
        $("#accountem").html('姓名不能为空！' + failure).attr("class", "ash_red");
        t = false;
        return t;
    } else {
        if (!checkAccount(account)) {
            $("#accountem").html('请输入4-20位字母或数字或2-10个中文！' + failure).attr("class", "ash_red");
            t = false;
            return t;
        } else {
            if (account.len() < 4 || account.len() > 20) {
                $("#accountem").html('请输入4-20位字母或数字或2-10个中文！' + failure).attr("class", "ash_red");
                t = false;
                return t;
            }
        }
    }
    if (t) {
        $("#accountem").html('验证中...');

        $.ajax({
            type: "POST",
            url: "/front/member/checkAccount",
            data: "account=" + account + "&v=" + new Date().getTime(),
            success: function (data) {
                if (data == 1) {
                    $("#accountem").html('该姓名已存在！').attr("class", "ash_red");
                    t = false;
                } else {
                    $("#accountem").html('').attr("class", "ash");
                    t = true;
                }
            }
        });
    }
    return t;
}
function checkEmailAjax() {
    var suc = '<span class="succeed"></span>';
    var failure = '<span class="failure"></span>';
    var t = true;
    var email = $.trim($("#m_email").val());
    if (email == "") {
        $("#m_emailem").html('邮箱不能为空！' + failure).attr("class", "ash_red");
        t = false;
        return t;
    } else {
        if (!checkEmail(email)) {
            $("#m_emailem").html('邮箱格式不正确！' + failure).attr("class", "ash_red");
            t = false;
            return t;
        }
    }
    if (t) {
        $("#m_emailem").html('验证中...');
        $.ajax({
            type: "POST",
            url: "/front/member/checkEmail",
            data: "email=" + email + "&v=" + new Date().getTime(),
            success: function (data) {
                if (data == 1) {
                    $("#m_emailem").html('该邮箱已被注册！' + failure).attr("class", "ash_red");
                    t = false;
                } else {
                    $("#m_emailem").html('邮箱地址可用于登录您的账户和找回密码。' + suc).attr("class", "ash");
                    t = true;
                }
            }
        });
    }
    return t;
}
function checkNickNameAjax() {
    var t = true;
    var nickname = $.trim($("#nickname").val());
    if (nickname == "") {
        $("#nicknameem").html('<img src="/member/images/zs_bg_01.jpg" /><font color="red"> 昵称不能为空</font>');
        t = false;
    }

    if (containSpecial(nickname)) {
        $("#nicknameem").html('<img src="/member/images/zs_bg_01.jpg" /><font color="red"> 昵称包含有特殊字符</font>');
        t = false;
    }
    var nlen = strLength(nickname);
    if (nlen < 4 || nlen > 20) {
        $("#nicknameem").html('<img src="/member/images/zs_bg_01.jpg" /><font color="red"> 昵称长度大于等于4小于等于20位</font>');
        $("#nicknameem").attr("class", "red");
        t = false;
    }
    if (t) {
        $("#nicknameem").html('<img src="/member/images/dengdai.gif" /><font color="#888"> 验证中...</font>');
        $.ajax({
            type: "GET",
            url: "/front/member/checkNickName",
            data: "nickname=" + nickname + "&v=" + new Date().getTime(),
            success: function (data) {
                if (data == 1) {
                    $("#nicknameem").html('<img src="/member/images/zs_bg_01.jpg" /><font color="red"> 昵称重复</font>');
                } else {
                    $("#nicknameem").html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="/member/images/accept.png" />');
                    $("#nicknameem").attr("class", "hui");
                }
            }
        });
    }
}
/**
 * 验证是否包含特殊字符
 * @param {Object} s
 */
function containSpecial(s) {
    var reg = /^[0-9a-zA-Z_\u4e00-\u9fa5\-]+$/;
    return !reg.test(s);
}
//得到字符串的字符长度（一个汉字占两个字符长）
function strLength(s) {
    return s.replace(/[^\x00-\xff]/g, 'xx').length;
}

function checkPwd() {
    var suc = '<span class="succeed"></span>';
    var failure = '<span class="failure"></span>';
    var pwd = $.trim($("#password").val());
    if (pwd == "") {
        $("#passwordem").html('密码不能为空' + failure).attr("class", "ash_red");
        return;
    } else {
        if (pwd.length < 6 || pwd.length > 15) {
            $("#passwordem").html('密码最少6个字符，不超过15位' + failure).attr("class", "ash_red");
            return;
        }
    }

    $("#passwordem").html('最少6个字符，不超过15位，请勿使用个人信息。' + suc).attr("class", "ash");
}
function checkRepwd() {
    var suc = '<span class="succeed"></span>';
    var failure = '<span class="failure"></span>';
    var pwd = $.trim($("#password").val());
    var repwd = $.trim($("#repassword").val());

    if (pwd == "") {
        $("#passwordem").html('密码不能为空' + failure).attr("class", "ash_red");
        return;
    }
    if (repwd != pwd) {
        $("#repasswordem").html('确认密码不一致').attr("class", "ash_red");
        return;
    }
    $("#repasswordem").html('　' + suc).attr("class", "ash");
}
/*验证联系人*/
function checklxr() {
    var truename = $.trim($("#truename").val());
    $("#truenameem").attr("class", "red");

    if (truename == "") {
        $("#truenameem").html('<img src="/member/images/zs_bg_01.jpg" /><font color="red"> 联系人不能为空</font>');
        return;
    }
    $("#truenameem").html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="/member/images/accept.png" />');
    $("#truenameem").attr("class", "");
}
function checkcompanyname() {

    var companyname = $.trim($("#companyname").val());
    $("#companynameem").attr("class", "red");

    if (companyname == "") {
        $("#companynameem").html('<img src="/member/images/zs_bg_01.jpg" /><font color="red"> 公司名称不能为空</font>');
        return;
    }
    $("#companynameem").html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="/member/images/accept.png" />');
    $("#companynameem").attr("class", "");
}
function checkcompanyphone() {
    var telephone = $.trim($("#telephone").val());
    $("#telephoneem").attr("class", "red");
    if (telephone == "") {
        $("#telephoneem").html('<img src="/member/images/zs_bg_01.jpg" /><font color="red"> 固定电话不能为空</font>');
        return;
    } else {
        if (!checkPhone(telephone)) {
            $("#telephoneem").html(
                '<img src="/member/images/zs_bg_01.jpg" /><font color="red"> 请输入正确的中国固定电话格式:如 0511-4405222 或 021-87888822</font>');
            return;
        }
    }
    $("#telephoneem").html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="/member/images/accept.png" />');
    $("#telephoneem").attr("class", "");
}
function checkcompanymobile() {
    var mobile = $.trim($("#mobile").val());
    $("#mobileem").attr("class", "red");
    if (mobile == "") {
        $("#mobileem").html('<img src="/member/images/zs_bg_01.jpg" /><font color="red"> 手机号不能为空</font>');
        return;
    } else {
        if (!checkMobile(mobile)) {
            $("#mobileem").html('<img src="/member/images/zs_bg_01.jpg" /><font color="red"> 请输入正确的手机</font>');
            return;
        }
    }
    $("#mobileem").html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="/member/images/accept.png" />');
    $("#mobileem").attr("class", "");
}

function checkMobileAjax() {
    var t = true;
    var mobile = $.trim($("#mobile").val());
    if (mobile != "") {
        if (!checkMobile(mobile)) {
            $("#mobileem").html('请输入正确的手机号格式！').attr("class", "ash_red");
            ;
            t = false;
        }
        if (t) {
            $("#mobileem").html('验证中...');
            $.ajax({
                type: "GET",
                url: "/membercenter/checkMobileAjax",
                data: "mobile=" + mobile + "&v=" + new Date().getTime(),
                success: function (data) {
                    if (data == 1) {
                        $("#mobileem").html('该手机号已经绑定其他用户，请更换手机号。').attr("class", "ash_red");
                    } else {
                        $("#mobileem").html('可以使用。').attr("class", "ash_blue");
                    }
                }
            });
        }
    }
}
	