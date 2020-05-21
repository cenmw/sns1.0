package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;
import com.cenmw.vedio.front.service.VedioClassFrontService;
import com.cenmw.vedio.po.VedioClass;

public class MemberVedioClassCenterAction extends BaseAction {
	/**
	 * 会员视频分组 模块
	 */
	private VedioClassFrontService vedioClassFrontService;
	private MemberInfoCenterService memberInfoCenterService;
	private VedioClass vedioClass;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private List classlist;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员视频分组删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				vedioClass = vedioClassFrontService.getVedioClass(new Integer(
						idarrs[i].trim()));
				vedioClass.setIsdel(new Integer(1));
				vedioClassFrontService.updateVedioClass(vedioClass);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员视频分组添加功能
	 * 
	 * @return
	 */
	public String save() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "修改成功！";
		if (vedioClass.getId() == null) {
			msg = "添加成功！";
			vedioClass.setMid(memberInfo.getId());
			vedioClass.setIsdel(new Integer(0));
			vedioClass.setCtime(new Date());
			vedioClass.setType(new Integer(1));
			vedioClass.setSort(new Integer(0));
			vedioClassFrontService.saveVedioClass(vedioClass);
		}
		vedioClassFrontService.updateVedioClass(vedioClass);
		session.put("classmsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员视频分组查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			vedioClass = vedioClassFrontService.getVedioClass(id);
			vedioClass.setMemberInfo(memberInfoCenterService
					.getMemberInfo(vedioClass.getMid()));
			String msg = (String) session.get("classmsg");
			if (msg != null && msg.equals("添加成功")) {
				classlist = vedioClassFrontService.findVedioClassInList(
						memberInfo.getId(), 1);
				session.remove("classmsg");
			}
		}
		// 初始化信息
		if (vedioClass == null) {
			vedioClass = new VedioClass();
			vedioClass.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员视频分组列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from VedioClass where type<2 and isdel=0 and mid="
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
		pageBean = vedioClassFrontService.findVedioClassHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/vedioclasslist");
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
				VedioClass mblog = (VedioClass) pagelist.get(i);
				mblog.setMemberInfo(memberInfoCenterService.getMemberInfo(mblog
						.getMid()));
				newlist.add(mblog);
			}
		}
		return newlist;
	}

	public VedioClassFrontService getVedioClassFrontService() {
		return vedioClassFrontService;
	}

	public void setVedioClassFrontService(
			VedioClassFrontService vedioClassFrontService) {
		this.vedioClassFrontService = vedioClassFrontService;
	}

	public VedioClass getVedioClass() {
		return vedioClass;
	}

	public void setVedioClass(VedioClass vedioClass) {
		this.vedioClass = vedioClass;
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
