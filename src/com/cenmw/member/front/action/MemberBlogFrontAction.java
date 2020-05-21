package com.cenmw.member.front.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.front.service.MemberBlogFrontService;
import com.cenmw.member.po.MemberBlog;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class MemberBlogFrontAction extends BaseAction {
	/**
	 * 会员日志 模块
	 */
	private MemberBlogFrontService memberBlogFrontService; 
	private MemberInfoFrontService memberInfoFrontService; 
	private MemberBlog memberBlog;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员日志删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				memberBlog = memberBlogFrontService.getMemberBlog(new Integer(idarrs[i].trim()));
				memberBlog.setIsdel(new Integer(1));
				memberBlogFrontService.updateMemberBlog(memberBlog);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员日志添加功能
	 * 
	 * @return
	 */
	public String save() {
		String msg = "修改成功！";
		if (memberBlog.getId() == null) {
			msg = "添加成功！";
			memberBlog.setIsdel(new Integer(0));
			memberBlog.setCtime(new Date());
			memberBlogFrontService.saveMemberBlog(memberBlog);
		}
		memberBlogFrontService.updateMemberBlog(memberBlog);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员日志查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberBlog = memberBlogFrontService.getMemberBlog(id);
			memberBlog.setMemberInfo(memberInfoFrontService.getMemberInfoById(memberBlog.getMid()));
		}
		// 初始化信息
		if (memberBlog == null) {
			memberBlog = new MemberBlog();
			memberBlog.setIsdel(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from MemberBlog where type=1 and isdel=0";
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
		pageBean = memberBlogFrontService.findMemberBlogHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/front/memberblog/list");
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
				MemberBlog mblog = (MemberBlog) pagelist.get(i);
				mblog.setMemberInfo(memberInfoFrontService.getMemberInfoById(mblog.getMid()));
				newlist.add(mblog);
			}
		}
		return newlist;
	}
	public MemberBlogFrontService getMemberBlogFrontService() {
		return memberBlogFrontService;
	}

	public void setMemberBlogFrontService(
			MemberBlogFrontService memberBlogFrontService) {
		this.memberBlogFrontService = memberBlogFrontService;
	}

	public MemberBlog getMemberBlog() {
		return memberBlog;
	}

	public void setMemberBlog(MemberBlog memberBlog) {
		this.memberBlog = memberBlog;
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
