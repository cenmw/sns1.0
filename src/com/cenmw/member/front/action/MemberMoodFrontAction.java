package com.cenmw.member.front.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.front.service.MemberMoodFrontService;
import com.cenmw.member.po.MemberMood;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class MemberMoodFrontAction extends BaseAction {
	/**
	 * 会员心情 模块
	 */
	private MemberMoodFrontService memberMoodFrontService; 
	private MemberInfoFrontService memberInfoFrontService; 
	private MemberMood memberMood;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchcontent = "";

	/**
	 * 会员心情删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				memberMood = memberMoodFrontService.getMemberMood(new Integer(idarrs[i].trim()));
				memberMood.setIsdel(new Integer(1));
				memberMoodFrontService.updateMemberMood(memberMood);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员心情添加功能
	 * 
	 * @return
	 */
	public String save() {
		String msg = "修改成功！";
		if (memberMood.getId() == null) {
			msg = "添加成功！";
			memberMood.setIsdel(new Integer(0));
			memberMood.setCtime(new Date());
			memberMoodFrontService.saveMemberMood(memberMood);
		}
		memberMoodFrontService.updateMemberMood(memberMood);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员心情查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberMood = memberMoodFrontService.getMemberMood(id);
			memberMood.setMemberInfo(memberInfoFrontService.getMemberInfoById(memberMood.getMid()));
		}
		// 初始化信息
		if (memberMood == null) {
			memberMood = new MemberMood();
			memberMood.setIsdel(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from MemberMood where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchcontent != null && searchcontent.length()>0) {
			hql += " and content like '%" + searchcontent + "%'";
			HqlBean hqlBean = new HqlBean(searchcontent, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchcontent", hqlBean);
			try {
				parameter += "&searchcontent=" + StringUtil.URLEncode(searchcontent);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		// 判断排序条件
		pageBean = memberMoodFrontService.findMemberMoodHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/front/membermood/list");
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
				MemberMood mmood = (MemberMood) pagelist.get(i);
				mmood.setMemberInfo(memberInfoFrontService.getMemberInfoById(mmood.getMid()));
				newlist.add(mmood);
			}
		}
		return newlist;
	}
	public MemberMoodFrontService getMemberMoodFrontService() {
		return memberMoodFrontService;
	}

	public void setMemberMoodFrontService(
			MemberMoodFrontService memberMoodFrontService) {
		this.memberMoodFrontService = memberMoodFrontService;
	}

	public MemberMood getMemberMood() {
		return memberMood;
	}

	public void setMemberMood(MemberMood memberMood) {
		this.memberMood = memberMood;
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

	public String getSearchcontent() {
		return searchcontent;
	}

	public void setSearchcontent(String searchcontent) {
		this.searchcontent = searchcontent;
	}

}
