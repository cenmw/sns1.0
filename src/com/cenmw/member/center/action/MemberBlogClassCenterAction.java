package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberBlogClassCenterService;
import com.cenmw.member.po.MemberBlogClass;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberBlogClassCenterAction extends BaseAction {
	/**
	 * 会员日志分类 模块
	 */
	private MemberBlogClassCenterService memberBlogClassCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberBlogClass memberBlogClass;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private String backUrl;
	private List classlist;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员日志分类删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberBlogClass = memberBlogClassCenterService
						.getMemberBlogClass(new Integer(idarrs[i].trim()));
				memberBlogClass.setIsdel(new Integer(1));
				memberBlogClassCenterService
						.updateMemberBlogClass(memberBlogClass);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员日志分类添加功能
	 * 
	 * @return
	 */
	public String save() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "修改成功！";
		if (memberBlogClass.getId() == null) {
			msg = "添加成功！";
			memberBlogClass.setMid(memberInfo.getId());
			memberBlogClass.setIsdel(new Integer(0));
			memberBlogClass.setCtime(new Date());
			memberBlogClass.setSort(new Integer(0));
			memberBlogClass.setType(new Integer(1));
			memberBlogClassCenterService.saveMemberBlogClass(memberBlogClass);
		}
		memberBlogClassCenterService.updateMemberBlogClass(memberBlogClass);
		session.put("classmsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员日志分类查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			memberBlogClass = memberBlogClassCenterService
					.getMemberBlogClass(id);
			memberBlogClass.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberBlogClass.getMid()));
			String msg = (String) session.get("classmsg");
			if (msg != null && msg.equals("添加成功")) {
				classlist = memberBlogClassCenterService
						.findMemberBlogClassInList(memberInfo.getId(),1);
			}
		}
		// 初始化信息
		if (memberBlogClass == null) {
			memberBlogClass = new MemberBlogClass();
			memberBlogClass.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberBlogClass where type=1 and isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

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

		// 判断排序条件
		pageBean = memberBlogClassCenterService.findMemberBlogClassHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/center/memberblogclass/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
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
				MemberBlogClass mblogclass = (MemberBlogClass) pagelist.get(i);
				mblogclass.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mblogclass.getMid()));
				newlist.add(mblogclass);
			}
		}
		return newlist;
	}

	public MemberBlogClassCenterService getMemberBlogClassCenterService() {
		return memberBlogClassCenterService;
	}

	public void setMemberBlogClassCenterService(
			MemberBlogClassCenterService memberBlogClassCenterService) {
		this.memberBlogClassCenterService = memberBlogClassCenterService;
	}

	public MemberBlogClass getMemberBlogClass() {
		return memberBlogClass;
	}

	public void setMemberBlogClass(MemberBlogClass memberBlogClass) {
		this.memberBlogClass = memberBlogClass;
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

	public MemberInfoCenterService getMemberInfoCenterService() {
		return memberInfoCenterService;
	}

	public void setMemberInfoCenterService(
			MemberInfoCenterService memberInfoCenterService) {
		this.memberInfoCenterService = memberInfoCenterService;
	}

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public List getClasslist() {
		return classlist;
	}

	public void setClasslist(List classlist) {
		this.classlist = classlist;
	}

}
