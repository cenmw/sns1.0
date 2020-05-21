package com.cenmw.member.front.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.cenmw.base.BaseAction;
import com.cenmw.integral.front.service.IntegralConfigFrontService;
import com.cenmw.integral.front.service.IntegralInfoFrontService;
import com.cenmw.integral.po.IntegralConfig;
import com.cenmw.integral.po.IntegralInfo;
import com.cenmw.mail.MailSender;
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.center.service.MemberLogCenterService;
import com.cenmw.member.center.service.MemberStoreCenterService;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.po.MemberFriend;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberLog;
import com.cenmw.member.po.MemberStore;
import com.cenmw.util.*;

import javax.servlet.http.Cookie;

public class MemberInfoRegLoginAction extends BaseAction {
    private MemberInfoFrontService memberInfoFrontService;
    private MemberLogCenterService memberLogCenterService;
    private IntegralInfoFrontService integralInfoFrontService;
    private IntegralConfigFrontService integralConfigFrontService;
    private MemberFriendCenterService memberFriendCenterService;
    private MemberInfo memberInfo;
    private Integer id;
    private String account;
    private String email;
    private String password;
    private String checkcode;
    private String regMemberInfoMsg;
    private String mobile;// 手机
    private String sign; // 重置密码 随机码
    private String newpwd; // 重置新密码
    private String emailurl;
    private Integer price = 99; // 注册会员充值金额  普通会员：99 注册会员：360
    private MemberStoreCenterService memberStoreCenterService;
    private MemberStore memberStore;

    /**
     * 重置密码
     *
     * @return
     */
    public String uppwd() {
        memberInfo = memberInfoFrontService.getMemberInfoByEmail(email);
        if (memberInfo == null || !StringUtil.notNullStr(sign)) {
            return INPUT;
        }
        if (!StringUtil.notNullStr(memberInfo.getSign()) || !memberInfo.getSign().equals(sign)) {
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * 提交重置密码
     *
     * @return
     */
    public String saveuppwd() {
        memberInfo = memberInfoFrontService.getMemberInfoByEmail(email);
        if (memberInfo == null || !StringUtil.notNullStr(sign)) {
            return INPUT;
        }
        if (!memberInfo.getSign().equals(sign)) {
            return INPUT;
        }
        MD5 md5 = new MD5();
        String encryptedPassword = md5.getMD5ofStr(newpwd);
        memberInfo.setPassword(encryptedPassword);
        memberInfo.setSign("");
        // 添加 登陆记录表
        MemberLog memberLog = new MemberLog(memberInfo.getId(), 1, 0, "");
        memberLogCenterService.saveMemberLog(memberLog);
        memberInfo.setLastlogintime(new Date());
        memberInfoFrontService.updateMemberInfo(memberInfo);
        // 登陆获得积分
        IntegralConfig ic = integralConfigFrontService
                .findIntegralConfigInList(2);
        int curdatesum = integralInfoFrontService.getIntegralByDate(memberInfo
                .getId());
        if (ic != null && curdatesum < 5) {
            IntegralInfo integralInfo = new IntegralInfo(memberInfo.getId(),
                    memberInfo.getAccount(), 2, 0, ic.getScore());
            integralInfoFrontService.saveIntegralInfo(integralInfo);
        }
        // 得到当前最新积分
        memberInfo = memberInfoFrontService.getMemberInfoById(memberInfo
                .getId());
        session.put(ConstantUtils.MEMBERINFO, memberInfo);

        return SUCCESS;
    }

    /**
     * 提交重置密码成功
     *
     * @return
     */
    public String uppwdsuc() {
        return SUCCESS;
    }

    /**
     * 提交找回密码
     *
     * @return
     */
    public String initpwd() {
        String findwordmsg = "";
        if (session.get("rand") == null) {
            findwordmsg = "请输入正确的验证码！";
            session.put("findwordmsg", findwordmsg);
            session.put("findwordEmail", email);
            return INPUT;
        } else {
            String rand = session.get("rand").toString();
            if (!rand.equalsIgnoreCase(checkcode)) {
                findwordmsg = "请输入正确的验证码！";
                session.put("findwordmsg", findwordmsg);
                session.put("findwordEmail", email);
                return INPUT;
            }
        }
        memberInfo = memberInfoFrontService.getMemberInfoByEmail(email);
        if (memberInfo == null) {
            findwordmsg = "该邮箱不存在！";
            session.put("findwordmsg", findwordmsg);
            session.put("findwordEmail", email);
            return INPUT;
        }

        // 获取随机密码 sign
        List numList = StringUtil.getRanddomNosame(10, 6);
        String sign = "";
        for (Object obj : numList) {
            sign += obj.toString();
        }

        // 获取将要发送的邮箱地址
        String em = memberInfo.getEmail();
        if (email != null && email.length() > 0) {
            em = email;
        }
        String[] toAddress = {em};
        String link = "http://www.longbaba.com.cn/member/uppwd?id="
                + memberInfo.getId().intValue() + "&email=" + email + "&sign="
                + sign;
        // 确认邮箱发送的内容
        String content = "<strong>亲爱的会员，您好！</strong><p style=\"margin-top:10px;\">以下是您的密码信息,请查收:</p><p style=\"margin-top:10px;\"><a style='color:#007ED9;' target=\"_blank\" href="
                + link
                + ">请点击此链接重置您的密码</a></p><p style=\"margin-top:10px;\">如果该链接无效， 请直接复制以下的链接：</p><p style=\"margin-top:10px;\">"
                + link
                + "</p><p style=\"margin-top:10px;\">希望您能继续关注我们。您的支持是我们前进的最大动力。<a style='color:#007ED9;' target=\"_blank\" href='http://www.longbaba.com.cn/'>龙爸爸成长在线</a>";
        // 发送邮件
        MailSender.sendMail("[龙爸爸成长在线] 请查收您的密码", content, toAddress);
        memberInfo.setSign(sign);
        memberInfoFrontService.updateMemberInfo(memberInfo);

        return SUCCESS;
    }

    /**
     * 初始完密码信息，发送邮件成功
     *
     * @return
     */
    public String initpwdsuc() {
        if (StringUtil.notNullStr(email) && email.indexOf("@") > 0) {
            emailurl = email.split("@")[1];
        }
        return SUCCESS;
    }

    /**
     * 注册用户
     *
     * @return
     */
    public String reg() {
        if (memberInfo == null) {
            memberInfo = new MemberInfo();
            memberInfo.setSex(new Integer(0));
            memberInfo.setEmail("");
            memberInfo.setAccount("");
        }
        return SUCCESS;
    }

    /**
     * 注册用户
     *
     * @return
     */
    public String regsave() {
        MemberInfo checkMemberInfo = memberInfoFrontService.getMemberInfoByEmail(memberInfo.getEmail());
        if (checkMemberInfo != null) {
            regMemberInfoMsg = "该邮箱已被注册，请更换邮箱！";
            session.put("regMemberInfoMsg", regMemberInfoMsg);
            return INPUT;
        }
        checkMemberInfo = memberInfoFrontService.getMemberInfoByAccount(memberInfo.getAccount());
        if (checkMemberInfo != null) {
            regMemberInfoMsg = "该姓名已被注册，请更换姓名！";
            session.put("regMemberInfoMsg", regMemberInfoMsg);
            return INPUT;
        }
        // 去掉空格
        memberInfo.setAccount(memberInfo.getAccount().trim());
        memberInfo.setAvatar("");

        if (session.get("rand") == null) {
            regMemberInfoMsg = "请输入正确的验证码！";
            session.put("regMemberInfoMsg", regMemberInfoMsg);
            return INPUT;
        } else {
            String rand = session.get("rand").toString();
            if (!rand.equalsIgnoreCase(checkcode)) {
                regMemberInfoMsg = "请输入正确的验证码！";
                session.put("regMemberInfoMsg", regMemberInfoMsg);
                return INPUT;
            }
        }
        // 设置默认头像
        memberInfo.setAvatar("/member/images/common/avatar.gif");
        memberInfo = memberInfoFrontService.saveMemberInfo(memberInfo);
        session.put(ConstantUtils.MEMBERINFO, memberInfo);
        // 添加 登陆记录表
        MemberLog memberLog = new MemberLog(memberInfo.getId(), 1, 0, "");
        memberLogCenterService.saveMemberLog(memberLog);
        memberInfo.setLastlogintime(new Date());
        memberInfoFrontService.updateMemberInfo(memberInfo);
        // 注册获得积分
        IntegralConfig ic = integralConfigFrontService
                .findIntegralConfigInList(1);
        if (ic != null) {
            IntegralInfo integralInfo = new IntegralInfo(memberInfo.getId(),
                    memberInfo.getAccount(), 1, 0, ic.getScore());
            integralInfoFrontService.saveIntegralInfo(integralInfo);
        }
        // 默认添加两个机构 id 24,25
        MemberFriend memberFriend = new MemberFriend();
        memberFriend.setMid(memberInfo.getId());
        memberFriend.setType(1);
        memberFriend.setCid(new Integer(0));
        memberFriend.setFid(24);
        memberFriend.setContent("系统加关注");
        memberFriend.setIsagree(new Integer(1));
        memberFriend.setIsdel(new Integer(0));
        memberFriend.setCtime(new Date());
        memberFriendCenterService.saveMemberFriend(memberFriend);
        // 25
        MemberFriend memberFriend2 = new MemberFriend();
        memberFriend2.setMid(memberInfo.getId());
        memberFriend2.setType(1);
        memberFriend2.setCid(new Integer(0));
        memberFriend2.setFid(25);
        memberFriend2.setContent("系统加关注");
        memberFriend2.setIsagree(new Integer(1));
        memberFriend2.setIsdel(new Integer(0));
        memberFriend2.setCtime(new Date());
        memberFriendCenterService.saveMemberFriend(memberFriend2);
        // 默认添加一个普通好友 23
        MemberFriend memberFriend3 = new MemberFriend();
        memberFriend3.setMid(memberInfo.getId());
        memberFriend3.setType(0);
        memberFriend3.setCid(new Integer(0));
        memberFriend3.setFid(23);
        memberFriend3.setContent("系统加好友");
        memberFriend3.setIsagree(new Integer(1));
        memberFriend3.setIsdel(new Integer(0));
        memberFriend3.setCtime(new Date());
        memberFriendCenterService.saveMemberFriend(memberFriend3);
        Integer type = memberInfo.getType();
        if (null != type && type > 1) {
            return "store";
        }
        return SUCCESS;
    }

    /**
     * 注册企业用户
     *
     * @return
     */
    public String creg() {
        if (memberInfo == null) {
            memberInfo = new MemberInfo();
            memberInfo.setSex(new Integer(0));
            memberInfo.setEmail("");
            memberInfo.setAccount("");
            memberInfo.setCpicpath("/member/images/common/no_photo.png");
        }
        return SUCCESS;
    }

    /**
     * 注册企业用户成功
     *
     * @return
     */
    public String cregsuc() {
        return SUCCESS;
    }

    /**
     * 注册企业用户在线充值
     *
     * @return
     */
    public String store() {
        memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
        if (memberInfo.getType().intValue() == 3) {
            price = 360;
        }
        return SUCCESS;
    }

    /**
     * 注册用户
     *
     * @return
     */
    public String cregsave() {
        // 去掉空格
        memberInfo.setAccount(memberInfo.getAccount().trim());
        memberInfo.setAvatar("");
        if (session.get("rand") == null) {
            regMemberInfoMsg = "请输入正确的验证码！";
            return INPUT;
        } else {
            String rand = session.get("rand").toString();
            if (!rand.equalsIgnoreCase(checkcode)) {
                regMemberInfoMsg = "请输入正确的验证码！";
                return INPUT;
            }
        }
        // 设置默认头像
        memberInfo.setAvatar("/member/images/common/avatar.gif");
        memberInfo = memberInfoFrontService.saveCMemberInfo(memberInfo);
        // 添加 登陆记录表
        MemberLog memberLog = new MemberLog(memberInfo.getId(), 1, 0, "");
        memberLogCenterService.saveMemberLog(memberLog);
        memberInfo.setLastlogintime(new Date());
        memberInfoFrontService.updateMemberInfo(memberInfo);
        // 注册获得积分
        IntegralConfig ic = integralConfigFrontService
                .findIntegralConfigInList(1);
        if (ic != null) {
            IntegralInfo integralInfo = new IntegralInfo(memberInfo.getId(),
                    memberInfo.getAccount(), 1, 0, ic.getScore());
            integralInfoFrontService.saveIntegralInfo(integralInfo);
        }
        email = memberInfo.getEmail();
        return SUCCESS;
    }

    /**
     * 用户登陆
     *
     * @return
     */
    public String login() {
        memberInfo = memberInfoFrontService.loginMember(account, password);
        if (memberInfo == null) {
            session.put("loginMemberInfoMsg", "账号或密码错误！");
            session.put("loginAccount", account);
            return INPUT;
        }
        if (memberInfo.getStatus() != 1) {
            session.put("loginMemberInfoMsg", "此账号还在审核中，请稍后再尝试！");
            session.put("loginAccount", account);
            return INPUT;
        }
        // 添加 登陆记录表
        MemberLog memberLog = new MemberLog(memberInfo.getId(), 1, 0, "");
        memberLogCenterService.saveMemberLog(memberLog);
        memberInfo.setLastlogintime(new Date());
        memberInfoFrontService.updateMemberInfo(memberInfo);
        // 登陆获得积分
        IntegralConfig ic = integralConfigFrontService
                .findIntegralConfigInList(2);
        int curdatesum = integralInfoFrontService.getIntegralByDate(memberInfo
                .getId());
        if (ic != null && curdatesum < 5) {
            IntegralInfo integralInfo = new IntegralInfo(memberInfo.getId(),
                    memberInfo.getAccount(), 2, 0, ic.getScore());
            integralInfoFrontService.saveIntegralInfo(integralInfo);
        }
        // 得到当前最新积分
        memberInfo = memberInfoFrontService.getMemberInfoById(memberInfo.getId());
        session.put(ConstantUtils.MEMBERINFO, memberInfo);
        //2017-09-17 cenmiaowang 用户名密码存放到cookie中
        CookieUtils.addCookie(request, response, ConstantUtils.TOKEN, EncryptUtil.urlParamEncrypt(memberInfo.getId().toString()), "/");
        return SUCCESS;
    }

    /**
     * 验证账号是否被注册
     *
     * @return
     */
    public String checkAccount() {
        int result = 0;// result 0 无相同用户 1有相同的用户
        if (memberInfoFrontService.getMemberInfoByAccount(account) != null) {
            result = 1;
        }
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html;charset=UTF-8");

        try {
            PrintWriter out = response.getWriter();
            out.print(result);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证注册邮箱是否被注册
     *
     * @return
     */
    public String checkEmail() {
        int result = 0;// result 0 没有被注册 1被注册
        if (memberInfoFrontService.getMemberInfoByEmail(email) != null) {
            result = 1;
        }

        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html;charset=UTF-8");

        try {
            PrintWriter out = response.getWriter();
            out.print(result);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 会员充值交易记录修改功能
     *
     * @return
     */
    public String storeinfo() {
        memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
        if (id > 0) {
            memberStore = memberStoreCenterService.getMemberStore(id);
        }
        // 初始化信息
        if (memberStore == null) {
            memberStore = new MemberStore();
            memberStore.setPrice(0.0);
        }
        return SUCCESS;
    }

    public MemberInfoFrontService getMemberInfoFrontService() {
        return memberInfoFrontService;
    }

    public void setMemberInfoFrontService(
            MemberInfoFrontService memberInfoFrontService) {
        this.memberInfoFrontService = memberInfoFrontService;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegMemberInfoMsg() {
        return regMemberInfoMsg;
    }

    public void setRegMemberInfoMsg(String regMemberInfoMsg) {
        this.regMemberInfoMsg = regMemberInfoMsg;
    }

    public MemberLogCenterService getMemberLogCenterService() {
        return memberLogCenterService;
    }

    public void setMemberLogCenterService(
            MemberLogCenterService memberLogCenterService) {
        this.memberLogCenterService = memberLogCenterService;
    }

    public IntegralInfoFrontService getIntegralInfoFrontService() {
        return integralInfoFrontService;
    }

    public void setIntegralInfoFrontService(
            IntegralInfoFrontService integralInfoFrontService) {
        this.integralInfoFrontService = integralInfoFrontService;
    }

    public IntegralConfigFrontService getIntegralConfigFrontService() {
        return integralConfigFrontService;
    }

    public void setIntegralConfigFrontService(
            IntegralConfigFrontService integralConfigFrontService) {
        this.integralConfigFrontService = integralConfigFrontService;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getEmailurl() {
        return emailurl;
    }

    public void setEmailurl(String emailurl) {
        this.emailurl = emailurl;
    }

    public MemberFriendCenterService getMemberFriendCenterService() {
        return memberFriendCenterService;
    }

    public void setMemberFriendCenterService(
            MemberFriendCenterService memberFriendCenterService) {
        this.memberFriendCenterService = memberFriendCenterService;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public MemberStoreCenterService getMemberStoreCenterService() {
        return memberStoreCenterService;
    }

    public void setMemberStoreCenterService(MemberStoreCenterService memberStoreCenterService) {
        this.memberStoreCenterService = memberStoreCenterService;
    }

    public MemberStore getMemberStore() {
        return memberStore;
    }

    public void setMemberStore(MemberStore memberStore) {
        this.memberStore = memberStore;
    }
}
