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
import com.cenmw.vedio.front.service.VedioInfoFrontService;
import com.cenmw.vedio.po.VedioClass;
import com.cenmw.vedio.po.VedioInfo;

public class MemberVedioAllCenterAction extends BaseAction {
	/**
	 * 会员视频 模块
	 */
	private VedioInfoFrontService vedioInfoFrontService;
	private MemberInfoCenterService memberInfoCenterService;
	private VedioClassFrontService vedioClassFrontService;
	private VedioInfo vedioInfo;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchcid = 0;
	private List classlist; // 管理员自定义视频分类

	/**
	 * 会员视频删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				vedioInfo = vedioInfoFrontService.getVedioInfo(new Integer(
						idarrs[i].trim()));
				vedioInfo.setIsdel(new Integer(1));
				vedioInfoFrontService.updateVedioInfo(vedioInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员视频添加功能
	 * 
	 * @return
	 */
	public String save() {
		String msg = "修改成功！";
		if (vedioInfo.getId() == null) {
			msg = "添加成功！";
			vedioInfo.setIsdel(new Integer(0));
			vedioInfo.setCtime(new Date());
			vedioInfoFrontService.saveVedioInfo(vedioInfo);
		}
		vedioInfoFrontService.updateVedioInfo(vedioInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员视频查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			vedioInfo = vedioInfoFrontService.getVedioInfo(id);
			vedioInfo.setMemberInfo(memberInfoCenterService
					.getMemberInfo(vedioInfo.getMid()));
		}
		// 初始化信息
		if (vedioInfo == null) {
			vedioInfo = new VedioInfo();
			vedioInfo.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员视频列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		classlist = vedioClassFrontService.findVedioClassInList(2);
		String hql = " from VedioInfo where type>=1 and isdel=0 and state=1 ";
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

		if (searchcid > 0) {
			hql += " and cid =" + searchcid;
			HqlBean hqlBean = new HqlBean(searchcid, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchcid", hqlBean);
			parameter += "&searchcid=" + searchcid;
		}
		pageSize = 12;
		// 判断排序条件
		pageBean = vedioInfoFrontService.findVedioInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/vedioalllist");
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
				VedioInfo mi = (VedioInfo) pagelist.get(i);
				VedioClass vc = vedioClassFrontService.getVedioClass(
						mi.getCid());
				if(vc != null){
					mi.setClassname(vc.getTitle());
				}
				mi.setMemberInfo(memberInfoCenterService.getMemberInfo(mi
						.getMid()));
				newlist.add(mi);
			}
		}
		return newlist;
	}

	public VedioInfoFrontService getVedioInfoFrontService() {
		return vedioInfoFrontService;
	}

	public void setVedioInfoFrontService(
			VedioInfoFrontService vedioInfoFrontService) {
		this.vedioInfoFrontService = vedioInfoFrontService;
	}

	public VedioInfo getVedioInfo() {
		return vedioInfo;
	}

	public void setVedioInfo(VedioInfo vedioInfo) {
		this.vedioInfo = vedioInfo;
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

	public VedioClassFrontService getVedioClassFrontService() {
		return vedioClassFrontService;
	}

	public void setVedioClassFrontService(
			VedioClassFrontService vedioClassFrontService) {
		this.vedioClassFrontService = vedioClassFrontService;
	}

	public int getSearchcid() {
		return searchcid;
	}

	public void setSearchcid(int searchcid) {
		this.searchcid = searchcid;
	}

	public List getClasslist() {
		return classlist;
	}

	public void setClasslist(List classlist) {
		this.classlist = classlist;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

}
