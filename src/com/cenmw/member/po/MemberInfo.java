package com.cenmw.member.po;

// default package

import java.io.File;
import java.util.Date;

import com.cenmw.util.ImageUtil;
import com.cenmw.util.RootdirectoryPopUtil;
import com.cenmw.util.StringUtil;

/**
 * MemberInfo entity. @author MyEclipse Persistence Tools
 */

public class MemberInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String code; // 会员编码
	private Integer type;// 会员类型： 1.机构 0.消费者 2.普通会员 3.注册会员
	private String avatar; // 头像 或者 机构 logo
	private String account; // 用户名
	private String password; // 密码
	private String email;// 邮箱
	private String mobile;// 手机
	private String introduction;// 介绍
	private String professional; // 职业 或者 机构执照
	private Integer sex;
	private Date birthday; // 出生日期
	private Integer status; // 审核状态 0:默认未审核 1:已审核 2:服务商户 -1:列为黑名单
	private Integer isdel; // 是否删除 1：删除
	private Date ctime; // 注册时间
	private String qq;
	private String address; // 现住址详细地址
	private String a_province; // 现住址详细地址所在省
	private String a_city; // 现住址详细地址所在市
	private String a_county; // 现住址详细地址所在县
	private String zcode; // 现住址邮编
	private String h_province; // 家乡地址所在省
	private String h_city; // 家乡地址所在市
	private String h_county; // 家乡地址所在县
	private String telphone;// 电话
	private String cname; // 法人名称
	private String cpicpath; // 公司营业执照
	private Date lastlogintime; // 最后登录时间
	private String sign; // 找回密码需要的随机码
	private String qq_uid;    //qq登陆对应的uid
	private String sina_uid;  //sina登陆对应的uid

	private String avatar_small; // 48x48
	private String avatar_big; // 200x200
	private String avatar_center; // 120x120
	private Integer sumscore; // 总积分

	private MemberStatus memberStatus; // 好友最新动态
	private Double sumprice;   //当前会员的总金额

	/** default constructor */
	public MemberInfo() {
	}

	/** minimal constructor */
	public MemberInfo(Integer type, String account, String password, Date ctime) {
		this.type = type;
		this.account = account;
		this.password = password;
		this.ctime = ctime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		if (StringUtil.notNullStr(avatar)) {
			return avatar;
		} else {
			return "/member/images/common/avatar.gif";
		}
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar_small() {
		if (StringUtil.notNullStr(avatar) && avatar.indexOf("avatar.gif") < 0) {
			avatar_small = StringUtil.rename(avatar, "_small");
			String filePath = RootdirectoryPopUtil
					.getPicLocalPath(avatar_small);
			String srcFilePath = RootdirectoryPopUtil.getPicLocalPath(avatar);
			File file = new File(filePath);
			File srcFile = new File(srcFilePath);
			if (srcFile.exists() && !file.exists()) {
				try {
					ImageUtil.zoomCenterImage(srcFile, filePath, 48, 48);
				} catch (Exception e) {
					//e.printStackTrace();
					avatar_small = avatar;
				}
			}
			if(!file.exists()){
				avatar_small = avatar;
			}
			return avatar_small;
		} else {
			return "/member/images/common/avatar_small.gif";
		}
	}

	public String getAvatar_big() {
		if (StringUtil.notNullStr(avatar) && avatar.indexOf("avatar.gif") < 0) {
			avatar_big = StringUtil.rename(avatar, "_big");
			String filePath = RootdirectoryPopUtil.getPicLocalPath(avatar_big);
			String srcFilePath = RootdirectoryPopUtil.getPicLocalPath(avatar);
			File file = new File(filePath);
			File srcFile = new File(srcFilePath);
			if (srcFile.exists() && !file.exists()) {
				try {
					ImageUtil.zoomCenterImage(srcFile, filePath, 48, 48);
				} catch (Exception e) {
					e.printStackTrace();
					avatar_big = avatar;
				}
			}
			if(!file.exists()){
				avatar_big = avatar;
			}
			return avatar_big;
		} else {
			return "/member/images/common/avatar_big.gif";
		}

	}

	public String getAvatar_center() {
		if (StringUtil.notNullStr(avatar) && avatar.indexOf("avatar.gif") < 0) {
			avatar_center = StringUtil.rename(avatar, "_center");
			String filePath = RootdirectoryPopUtil
					.getPicLocalPath(avatar_center);
			String srcFilePath = RootdirectoryPopUtil.getPicLocalPath(avatar);
			File file = new File(filePath);
			File srcFile = new File(srcFilePath);
			if (srcFile.exists() && !file.exists()) {
				try {
					ImageUtil.zoomCenterImage(srcFile, filePath, 48, 48);
				} catch (Exception e) {
					e.printStackTrace();
					avatar_center = avatar;
				}
			}
			if(!file.exists()){
				avatar_center = avatar;
			}
			return avatar_center;
		} else {
			return "/member/images/common/avatar.gif";
		}
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getA_province() {
		return a_province;
	}

	public void setA_province(String a_province) {
		this.a_province = a_province;
	}

	public String getA_city() {
		return a_city;
	}

	public void setA_city(String a_city) {
		this.a_city = a_city;
	}

	public String getA_county() {
		return a_county;
	}

	public void setA_county(String a_county) {
		this.a_county = a_county;
	}

	public String getZcode() {
		return zcode;
	}

	public void setZcode(String zcode) {
		this.zcode = zcode;
	}

	public String getH_province() {
		return h_province;
	}

	public void setH_province(String h_province) {
		this.h_province = h_province;
	}

	public String getH_city() {
		return h_city;
	}

	public void setH_city(String h_city) {
		this.h_city = h_city;
	}

	public String getH_county() {
		return h_county;
	}

	public void setH_county(String h_county) {
		this.h_county = h_county;
	}

	public void setAvatar_small(String avatar_small) {
		this.avatar_small = avatar_small;
	}

	public void setAvatar_big(String avatar_big) {
		this.avatar_big = avatar_big;
	}

	public void setAvatar_center(String avatar_center) {
		this.avatar_center = avatar_center;
	}

	public Integer getSumscore() {
		return sumscore;
	}

	public void setSumscore(Integer sumscore) {
		this.sumscore = sumscore;
	}

	public MemberStatus getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(MemberStatus memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getCpicpath() {
		return cpicpath;
	}

	public void setCpicpath(String cpicpath) {
		this.cpicpath = cpicpath;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getQq_uid() {
		return qq_uid;
	}

	public void setQq_uid(String qqUid) {
		qq_uid = qqUid;
	}

	public String getSina_uid() {
		return sina_uid;
	}

	public void setSina_uid(String sinaUid) {
		sina_uid = sinaUid;
	}

	public Double getSumprice() {
		return sumprice;
	}

	public void setSumprice(Double sumprice) {
		this.sumprice = sumprice;
	}

}