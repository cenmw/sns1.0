package com.cenmw.vedio.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.vedio.manager.service.VedioClassManagerService;
import com.cenmw.vedio.manager.service.VedioInfoManagerService;
import com.cenmw.vedio.po.VedioClass;
import com.cenmw.vedio.po.VedioInfo;
import com.cenmw.integral.front.service.IntegralInfoFrontService;
import com.cenmw.integral.po.IntegralInfo;
import com.cenmw.member.center.service.MemberStatusCenterService;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class VedioInfoManagerAction extends BaseAction {
	/**
	 * 视频信息 模块
	 */
	private VedioInfoManagerService vedioInfoManagerService;
	private VedioClassManagerService vedioClassManagerService;
	private MemberStatusCenterService memberStatusCenterService;
	private IntegralInfoFrontService integralInfoFrontService;
	private VedioInfo vedioInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchcid = 0;
	private int searchtype = -1;
	private List vedioclasslist; // 管理员自定义视频分类

	/**
	 * 视频信息删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteVEDIO })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				vedioInfo = vedioInfoManagerService.getVedioInfo(new Integer(
						idarrs[i].trim()));
				vedioInfo.setIsdel(new Integer(1));
				vedioInfoManagerService.updateVedioInfo(vedioInfo);
				// 删除动态信息
				memberStatusCenterService.deleteMemberStatusByCid(5,
						vedioInfo.getId());
				// 删除获得的积分
				IntegralInfo integralInfo = integralInfoFrontService
						.deleteIntegralInfoByCid(5, vedioInfo.getId(),
								new Integer(
										idarrs[i].trim()));
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 视频信息添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDVEDIO })
	public String save() {
		String msg = "修改成功！";
		if (vedioInfo.getId() == null) {
			msg = "添加成功！";
			vedioInfo.setIsdel(new Integer(0));
			vedioInfo.setCtime(new Date());
			vedioInfoManagerService.saveVedioInfo(vedioInfo);
			vedioInfo.setSort(vedioInfo.getId());
		}
		int cid = vedioInfo.getCid().intValue();
		if (cid > 0) {
			VedioClass vedioClass = vedioClassManagerService.getVedioClass(cid);
			if (vedioClass != null) {
				vedioInfo.setClassname(vedioClass.getTitle());
			}
		}
		vedioInfoManagerService.updateVedioInfo(vedioInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 视频信息查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWVEDIO })
	public String info() {
		vedioclasslist = vedioClassManagerService.findVedioClassInList(2);
		if (id > 0) {
			vedioInfo = vedioInfoManagerService.getVedioInfo(id);
		}
		// 初始化信息
		if (vedioInfo == null) {
			vedioInfo = new VedioInfo();
			vedioInfo.setIsdel(new Integer(0));
			vedioInfo.setMid(new Integer(0));
			vedioInfo.setCid(new Integer(0));
			vedioInfo.setTitle("");
			vedioInfo.setDescription("");
			vedioInfo.setContent("");
			vedioInfo.setPicpath("");
			vedioInfo.setAuthor("");
			vedioInfo.setSource("");
			vedioInfo.setState(new Integer(1));
			vedioInfo.setPtime(new Date());
			vedioInfo.setSort(new Integer(0));
			vedioInfo.setMid(new Integer(0));
			vedioInfo.setType(new Integer(2));
		}
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWVEDIO })
	public String list() {
		vedioclasslist = vedioClassManagerService.findVedioClassInList(2);
		String hql = " from VedioInfo where isdel=0 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " state asc , sort desc , ptime desc , id desc ";

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

		if (searchtype > -1) {
			hql += " and type =" + searchtype;
			HqlBean hqlBean = new HqlBean(searchtype, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchtype", hqlBean);
			parameter += "&searchtype=" + searchtype;
		}

		if (searchcid > 0) {
			hql += " and cid =" + searchcid;
			HqlBean hqlBean = new HqlBean(searchcid, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchcid", hqlBean);
			parameter += "&searchcid=" + searchcid;
		}
		pageSize = 20;
		// 判断排序条件
		pageBean = vedioInfoManagerService.findVedioInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/vedio/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public VedioInfoManagerService getVedioInfoManagerService() {
		return vedioInfoManagerService;
	}

	public void setVedioInfoManagerService(
			VedioInfoManagerService vedioInfoManagerService) {
		this.vedioInfoManagerService = vedioInfoManagerService;
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

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

	public int getSearchcid() {
		return searchcid;
	}

	public void setSearchcid(int searchcid) {
		this.searchcid = searchcid;
	}

	public VedioClassManagerService getVedioClassManagerService() {
		return vedioClassManagerService;
	}

	public void setVedioClassManagerService(
			VedioClassManagerService vedioClassManagerService) {
		this.vedioClassManagerService = vedioClassManagerService;
	}

	public List getVedioclasslist() {
		return vedioclasslist;
	}

	public void setVedioclasslist(List vedioclasslist) {
		this.vedioclasslist = vedioclasslist;
	}

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}

	public MemberStatusCenterService getMemberStatusCenterService() {
		return memberStatusCenterService;
	}

	public void setMemberStatusCenterService(
			MemberStatusCenterService memberStatusCenterService) {
		this.memberStatusCenterService = memberStatusCenterService;
	}

	public IntegralInfoFrontService getIntegralInfoFrontService() {
		return integralInfoFrontService;
	}

	public void setIntegralInfoFrontService(
			IntegralInfoFrontService integralInfoFrontService) {
		this.integralInfoFrontService = integralInfoFrontService;
	}

}
