package com.cenmw.member.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;
import com.cenmw.vedio.manager.service.VedioInfoManagerService;
import com.cenmw.vedio.po.VedioInfo;


public class MemberVedioManagerAction extends BaseAction {
	/**
	 * 会员视频 模块
	 */
	private VedioInfoManagerService vedioInfoManagerService; 
	private MemberInfoManagerService memberInfoManagerService; 
	private VedioInfo vedioInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员视频删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERVEDIO })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				vedioInfo = vedioInfoManagerService.getVedioInfo(new Integer(idarrs[i].trim()));
				vedioInfo.setIsdel(new Integer(1));
				vedioInfoManagerService.updateVedioInfo(vedioInfo);
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
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERVEDIO })
	public String save() {
		String msg = "修改成功！";
		if (vedioInfo.getId() == null) {
			msg = "添加成功！";
			vedioInfo.setIsdel(new Integer(0));
			vedioInfo.setCtime(new Date());
			vedioInfoManagerService.saveVedioInfo(vedioInfo);
		}
		vedioInfoManagerService.updateVedioInfo(vedioInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员视频查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERVEDIO })
	public String info() {
		if (id > 0) {
			vedioInfo = vedioInfoManagerService.getVedioInfo(id);
			vedioInfo.setMemberInfo(memberInfoManagerService.getMemberInfo(vedioInfo.getMid()));
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
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERVEDIO })
	public String list() {
		String hql = " from VedioInfo where type<2 and isdel=0";
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
		pageSize = 20;
		// 判断排序条件
		pageBean = vedioInfoManagerService.findVedioInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/membervedio/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
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
				VedioInfo mblog = (VedioInfo) pagelist.get(i);
				mblog.setMemberInfo(memberInfoManagerService.getMemberInfo(mblog.getMid()));
				newlist.add(mblog);
			}
		}
		return newlist;
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

	public MemberInfoManagerService getMemberInfoManagerService() {
		return memberInfoManagerService;
	}

	public void setMemberInfoManagerService(
			MemberInfoManagerService memberInfoManagerService) {
		this.memberInfoManagerService = memberInfoManagerService;
	}

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

}
