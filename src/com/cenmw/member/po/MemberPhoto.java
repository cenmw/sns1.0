package com.cenmw.member.po;

import java.io.File;
import java.util.Date;

import com.cenmw.util.ImageUtil;
import com.cenmw.util.RootdirectoryPopUtil;
import com.cenmw.util.StringUtil;

/**
 * 会员相片
 */

public class MemberPhoto implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private Integer cid; // 相册id
	private String classname; // 相册名称
	private String title; // 相片名称
	private String keyword; // 相片关键词
	private String description; // 相片描述
	private String picpath; // 相片路径
	private Integer isindex; // 设置封页显示 1
	private Integer sort; // 相片排序
	private Integer isdel; // 是否删除
	private Date ctime; // 创建时间
	private Integer viewnumber; // 查看次数
	private Integer qx; // 权限 0:所有人可见，1：仅好友可见，2：仅自己可见

	private int praisenumber; // 赞个数
	private MemberPraise memberPraise;
	private int commentnumber; // 评论次数
	private MemberInfo memberInfo;
	private String url; // url

	private String picpath_small; // 155x130
	private String picpath_center; // 250x190

	/** default constructor */
	public MemberPhoto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public Integer getIsindex() {
		return isindex;
	}

	public void setIsindex(Integer isindex) {
		this.isindex = isindex;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public Integer getViewnumber() {
		return viewnumber;
	}

	public void setViewnumber(Integer viewnumber) {
		this.viewnumber = viewnumber;
	}

	public int getPraisenumber() {
		return praisenumber;
	}

	public void setPraisenumber(int praisenumber) {
		this.praisenumber = praisenumber;
	}

	public MemberPraise getMemberPraise() {
		return memberPraise;
	}

	public void setMemberPraise(MemberPraise memberPraise) {
		this.memberPraise = memberPraise;
	}

	public int getCommentnumber() {
		return commentnumber;
	}

	public void setCommentnumber(int commentnumber) {
		this.commentnumber = commentnumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUrl() {
		url = "/membercenter/showphotoinfo?id=" + id;
		return url;
	}

	public String getPicpath_small() {
		if (StringUtil.notNullStr(picpath)) {
			picpath_small = StringUtil.rename(picpath, "_small");
			String filePath = RootdirectoryPopUtil
					.getPicLocalPath(picpath_small);
			String srcFilePath = RootdirectoryPopUtil.getPicLocalPath(picpath);
			File file = new File(filePath);
			File srcFile = new File(srcFilePath);
			if (srcFile.exists() && !file.exists()) {
				try {
					ImageUtil.zoomCenterImage(srcFile, filePath, 155, 130);
				} catch (Exception e) {
					// e.printStackTrace();
					picpath_small = picpath;
				}
			}
			if (!file.exists()) {
				picpath_small = picpath;
			}
			return picpath_small;
		}
		return "/member/images/common/no_photo.png";
	}

	public String getPicpath_center() {
		if (StringUtil.notNullStr(picpath)) {
			picpath_center = StringUtil.rename(picpath, "_center");
			String filePath = RootdirectoryPopUtil
					.getPicLocalPath(picpath_center);
			String srcFilePath = RootdirectoryPopUtil.getPicLocalPath(picpath);
			File file = new File(filePath);
			File srcFile = new File(srcFilePath);
			if (srcFile.exists() && !file.exists()) {
				try {
					ImageUtil.zoomCenterImage(srcFile, filePath, 250, 190);
				} catch (Exception e) {
					// e.printStackTrace();
					picpath_center = picpath;
				}
			}
			if (!file.exists()) {
				picpath_center = picpath;
			}
			return picpath_center;
		}
		return "/member/images/common/no_photo.png";
	}

	public void setPicpath_small(String picpath_small) {
		this.picpath_small = picpath_small;
	}

	public void setPicpath_center(String picpath_center) {
		this.picpath_center = picpath_center;
	}

	public Integer getQx() {
		return qx;
	}

	public void setQx(Integer qx) {
		this.qx = qx;
	}

	public String getQxname() {
		String qxname = "任何人可见";
		if (qx == 0) {
			qxname = "任何人可见";
		} else if (qx == 1) {
			qxname = "仅好友可见";
		} else if (qx == 2) {
			qxname = "仅自己可见";
		}
		return qxname;
	}

}