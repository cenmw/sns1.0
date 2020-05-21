package com.cenmw.member.front.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.front.service.MemberStatusFrontService;
import com.cenmw.member.po.MemberStatus;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class MemberStatusFrontAction extends BaseAction {
	/**
	 * 会员动态 模块
	 */
	private MemberStatusFrontService memberStatusFrontService; 
	private MemberInfoFrontService memberInfoFrontService; 
	private MemberStatus memberStatus;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员动态删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				memberStatus = memberStatusFrontService.getMemberStatus(new Integer(idarrs[i].trim()));
				memberStatus.setIsdel(new Integer(1));
				memberStatusFrontService.updateMemberStatus(memberStatus);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员动态添加功能
	 * 
	 * @return
	 */
	public String save() {
		String msg = "修改成功！";
		if (memberStatus.getId() == null) {
			msg = "添加成功！";
			memberStatus.setIsdel(new Integer(0));
			memberStatus.setCtime(new Date());
			memberStatusFrontService.saveMemberStatus(memberStatus);
		}
		memberStatusFrontService.updateMemberStatus(memberStatus);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员动态查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberStatus = memberStatusFrontService.getMemberStatus(id);
			memberStatus.setMemberInfo(memberInfoFrontService.getMemberInfoById(memberStatus.getMid()));
		}
		// 初始化信息
		if (memberStatus == null) {
			memberStatus = new MemberStatus();
			memberStatus.setIsdel(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from MemberStatus where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchtitle != null && searchtitle.length()>0) {
			hql += " and classname like '%" + searchtitle + "%'";
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
		pageBean = memberStatusFrontService.findMemberStatusHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/front/memberstatus/list");
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
				MemberStatus mstatus = (MemberStatus) pagelist.get(i);
				mstatus.setMemberInfo(memberInfoFrontService.getMemberInfoById(mstatus.getMid()));
				newlist.add(mstatus);
			}
		}
		return newlist;
	}
	public MemberStatusFrontService getMemberStatusFrontService() {
		return memberStatusFrontService;
	}

	public void setMemberStatusFrontService(
			MemberStatusFrontService memberStatusFrontService) {
		this.memberStatusFrontService = memberStatusFrontService;
	}

	public MemberStatus getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(MemberStatus memberStatus) {
		this.memberStatus = memberStatus;
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
