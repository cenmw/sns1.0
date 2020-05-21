package com.cenmw.member.po;

import java.io.File;
import java.util.Date;

import com.cenmw.util.ImageUtil;
import com.cenmw.util.RootdirectoryPopUtil;
import com.cenmw.util.StringUtil;

/**
 * 会员相册
 */

public class MemberPhotoGroup implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid; // 会员id
	private String title; // 相册名称
	private String keyword; // 相册关键词
	private String description; // 相册描述
	private Integer sort; // 排序
	private Integer isdel; // 是否删除
	private Date ctime; // 创建时间
	private MemberInfo memberInfo;
	private String picpath;
	private Integer pcount;

	private String picpath_small; // 150x130
	private String picpath_center; // 250x190

	/** default constructor */
	public MemberPhotoGroup() {
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

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public Integer getPcount() {
		return pcount;
	}

	public void setPcount(Integer pcount) {
		this.pcount = pcount;
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

}