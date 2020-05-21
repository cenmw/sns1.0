package com.cenmw.member.front.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.front.service.MemberPhotoFrontService;
import com.cenmw.member.po.MemberPhoto;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class MemberPhotoFrontAction extends BaseAction {
	/**
	 * 会员相片 模块
	 */
	private MemberPhotoFrontService memberPhotoFrontService; 
	private MemberInfoFrontService memberInfoFrontService; 
	private MemberPhoto memberPhoto;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员相片删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				memberPhoto = memberPhotoFrontService.getMemberPhoto(new Integer(idarrs[i].trim()));
				memberPhoto.setIsdel(new Integer(1));
				memberPhotoFrontService.updateMemberPhoto(memberPhoto);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员相片添加功能
	 * 
	 * @return
	 */
	public String save() {
		String msg = "修改成功！";
		if (memberPhoto.getId() == null) {
			msg = "添加成功！";
			memberPhoto.setIsdel(new Integer(0));
			memberPhoto.setCtime(new Date());
			memberPhotoFrontService.saveMemberPhoto(memberPhoto);
		}
		memberPhotoFrontService.updateMemberPhoto(memberPhoto);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员相片查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberPhoto = memberPhotoFrontService.getMemberPhoto(id);
			memberPhoto.setMemberInfo(memberInfoFrontService.getMemberInfoById(memberPhoto.getMid()));
		}
		// 初始化信息
		if (memberPhoto == null) {
			memberPhoto = new MemberPhoto();
			memberPhoto.setIsdel(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 会员相片列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from MemberPhoto where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchtitle != null && searchtitle.length()>0) {
			hql += " and title like '%" + searchtitle + "%'";
			HqlBean hqlBean = new HqlBean(searchtitle, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchtitle", hqlBean);
			try {
				parameter += "&searchtitle=" + StringUtil.URLEncode(searchtitle);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		// 判断排序条件
		pageBean = memberPhotoFrontService.findMemberPhotoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/front/memberphoto/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/front/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewlist(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				MemberPhoto mblog = (MemberPhoto) pagelist.get(i);
				mblog.setMemberInfo(memberInfoFrontService.getMemberInfoById(mblog.getMid()));
				newlist.add(mblog);
			}
		}
		return newlist;
	}
	public MemberPhotoFrontService getMemberPhotoFrontService() {
		return memberPhotoFrontService;
	}

	public void setMemberPhotoFrontService(
			MemberPhotoFrontService memberPhotoFrontService) {
		this.memberPhotoFrontService = memberPhotoFrontService;
	}

	public MemberPhoto getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(MemberPhoto memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public MemberInfoFrontService getMemberInfoByIdFrontService() {
		return memberInfoFrontService;
	}

	public void setMemberInfoFrontService(
			MemberInfoFrontService memberInfoFrontService) {
		this.memberInfoFrontService = memberInfoFrontService;
	}

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

}
