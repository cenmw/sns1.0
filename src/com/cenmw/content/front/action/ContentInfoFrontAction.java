package com.cenmw.content.front.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.content.front.service.ContentInfoFrontService;
import com.cenmw.content.po.ContentInfo;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class ContentInfoFrontAction extends BaseAction {
	/**
	 * 基础信息 模块
	 */
	private ContentInfoFrontService contentInfoFrontService;
	private ContentInfo contentInfo;
	private MemberInfo memberInfo;
	private int id;
	// 搜索条件
	private String searchtitle = "";
	private int searchtype = 0; // 1:关于我们 2:联系我们 3:招聘信息 4:友情接 5:客服帮助 6:隐私说明

	/**
	 * 基础信息查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			contentInfo = contentInfoFrontService.getContentInfo(id);
		} else {
			if (searchtype > 0) {
				List list = contentInfoFrontService.findContentInfoInList(
						searchtype, 1);
				if (list != null && !list.isEmpty()) {
					contentInfo = (ContentInfo) list.get(0);
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 关于我们查看功能
	 * 
	 * @return
	 */
	public String aboutus() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		searchtype = 1;
		List list = contentInfoFrontService
				.findContentInfoInList(searchtype, 1);
		if (list != null && !list.isEmpty()) {
			contentInfo = (ContentInfo) list.get(0);
			contentInfo.setTypename("关于我们");
		} else {
			contentInfo = new ContentInfo();
			contentInfo.setType(searchtype);
			contentInfo.setTypename("关于我们");
		}
		return SUCCESS;
	}

	/**
	 * 联系我们查看功能
	 * 
	 * @return
	 */
	public String contactus() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		searchtype = 2;
		List list = contentInfoFrontService
				.findContentInfoInList(searchtype, 1);
		if (list != null && !list.isEmpty()) {
			contentInfo = (ContentInfo) list.get(0);
			contentInfo.setTypename("联系我们");
		} else {
			contentInfo = new ContentInfo();
			contentInfo.setType(searchtype);
			contentInfo.setTypename("联系我们");
		}
		return SUCCESS;
	}

	/**
	 * 招聘信息查看功能
	 * 
	 * @return
	 */
	public String recruitment() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		searchtype = 3;
		List list = contentInfoFrontService
				.findContentInfoInList(searchtype, 1);
		if (list != null && !list.isEmpty()) {
			contentInfo = (ContentInfo) list.get(0);
			contentInfo.setTypename("招聘信息");
		} else {
			contentInfo = new ContentInfo();
			contentInfo.setType(searchtype);
			contentInfo.setTypename("招聘信息");
		}
		return SUCCESS;
	}

	/**
	 * 友情链接查看功能
	 * 
	 * @return
	 */
	public String link() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		searchtype = 4;
		List list = contentInfoFrontService
				.findContentInfoInList(searchtype, 1);
		if (list != null && !list.isEmpty()) {
			contentInfo = (ContentInfo) list.get(0);
			contentInfo.setTypename("友情链接");
		} else {
			contentInfo = new ContentInfo();
			contentInfo.setType(searchtype);
			contentInfo.setTypename("友情链接");
		}
		return SUCCESS;
	}

	/**
	 * 客服帮助查看功能
	 * 
	 * @return
	 */
	public String help() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		searchtype = 5;
		List list = contentInfoFrontService
				.findContentInfoInList(searchtype, 1);
		if (list != null && !list.isEmpty()) {
			contentInfo = (ContentInfo) list.get(0);
			contentInfo.setTypename("客服帮助");
		} else {
			contentInfo = new ContentInfo();
			contentInfo.setType(searchtype);
			contentInfo.setTypename("客服帮助");
		}
		return SUCCESS;
	}

	/**
	 * 隐私说明查看功能
	 * 
	 * @return
	 */
	public String privacy() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		searchtype = 6;
		List list = contentInfoFrontService
				.findContentInfoInList(searchtype, 1);
		if (list != null && !list.isEmpty()) {
			contentInfo = (ContentInfo) list.get(0);
			contentInfo.setTypename("隐私说明");
		} else {
			contentInfo = new ContentInfo();
			contentInfo.setType(searchtype);
			contentInfo.setTypename("隐私说明");
		}
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from ContentInfo where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , ptime desc , id desc ";

		if (searchtitle != null && searchtitle.length() > 0) {
			hql += " and title like '%" + searchtitle + "%'";
			HqlBean hqlBean = new HqlBean(searchtitle, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchtitle", hqlBean);
			try {
				parameter += "&searchtitle="
						+ StringUtil.URLEncode(searchtitle);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (searchtype > 0) {
			hql += " and type =" + searchtype;
			HqlBean hqlBean = new HqlBean(searchtype, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchtype", hqlBean);
			parameter += "&searchtype=" + searchtype;
		}

		// 判断排序条件
		pageBean = contentInfoFrontService.findContentInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/front/content/list");
		pageBean.setGopageTemplate(request
				.getRealPath("/front/gopage/gopage.html"));
		return SUCCESS;
	}

	public ContentInfoFrontService getContentInfoFrontService() {
		return contentInfoFrontService;
	}

	public void setContentInfoFrontService(
			ContentInfoFrontService contentInfoFrontService) {
		this.contentInfoFrontService = contentInfoFrontService;
	}

	public ContentInfo getContentInfo() {
		return contentInfo;
	}

	public void setContentInfo(ContentInfo contentInfo) {
		this.contentInfo = contentInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

}
