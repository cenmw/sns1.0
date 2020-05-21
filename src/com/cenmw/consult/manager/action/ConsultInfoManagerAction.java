package com.cenmw.consult.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.consult.manager.service.ConsultClassManagerService;
import com.cenmw.consult.manager.service.ConsultInfoManagerService;
import com.cenmw.consult.manager.service.ConsultReplyInfoManagerService;
import com.cenmw.consult.po.ConsultClass;
import com.cenmw.consult.po.ConsultInfo;
import com.cenmw.consult.po.ConsultReplyInfo;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class ConsultInfoManagerAction extends BaseAction {
	/**
	 * 咨询信息 模块
	 */
	private ConsultInfoManagerService consultInfoManagerService;
	private ConsultClassManagerService consultClassManagerService;
	private ConsultReplyInfoManagerService consultReplyInfoManagerService;
	private ConsultInfo consultInfo;
	private ConsultClass consultClass;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchcid = 0;
	private int searchtype = -1;
	private List consultclasslist; // 管理员自定义咨询分类
	private List consultreplylist; // 咨询回复信息
	private int replyid;

	/**
	 * 同意咨询回复添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDCONSULTREPLY })
	public String savereply() {
		if (id > 0) {
			consultInfo = consultInfoManagerService.getConsultInfo(id);
		}
		if (replyid > 0) {
			ConsultReplyInfo consultReplyInfo = consultReplyInfoManagerService
					.getConsultReplyInfo(replyid);
			if (consultReplyInfo != null) {
				consultReplyInfo.setIsagree(new Integer(1));
				consultReplyInfoManagerService
						.updateConsultReplyInfo(consultReplyInfo);
				// 并给当前会员添加积分操作
				// 预留。。。
			}
		}
		return SUCCESS;
	}

	/**
	 * 咨询信息删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteCONSULT })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				consultInfo = consultInfoManagerService
						.getConsultInfo(new Integer(idarrs[i].trim()));
				consultInfo.setIsdel(new Integer(1));
				consultInfoManagerService.updateConsultInfo(consultInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 咨询信息添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDCONSULT })
	public String save() {
		String msg = "修改成功！";
		if (consultInfo.getId() == null) {
			msg = "添加成功！";
			consultInfo.setIsdel(new Integer(0));
			consultInfo.setCtime(new Date());
			consultInfoManagerService.saveConsultInfo(consultInfo);
			consultInfo.setSort(consultInfo.getId());
		}
		int cid = consultInfo.getCid().intValue();
		if (cid > 0) {
			ConsultClass consultClass = consultClassManagerService
					.getConsultClass(cid);
			if (consultClass != null) {
				consultInfo.setClassname(consultClass.getTitle());
			}
		}
		consultInfoManagerService.updateConsultInfo(consultInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 咨询信息查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCONSULT })
	public String info() {
		consultclasslist = consultClassManagerService.findConsultClassInList(2);
		if (id > 0) {
			consultInfo = consultInfoManagerService.getConsultInfo(id);
			consultreplylist = consultReplyInfoManagerService
					.findConsultReplyInfoInList(id);
			consultInfo.setReplyisgreecount(consultReplyInfoManagerService
					.findConsultReplyInfoIsgreeCount(consultInfo.getId()));
			consultClass = consultClassManagerService
					.getConsultClass(consultInfo.getCid());
		}
		// 初始化信息
		if (consultInfo == null) {
			consultInfo = new ConsultInfo();
			consultInfo.setIsdel(new Integer(0));
			consultInfo.setCid(new Integer(0));
			consultInfo.setTitle("");
			consultInfo.setDescription("");
			consultInfo.setContent("");
			consultInfo.setState(new Integer(1));
			consultInfo.setSort(new Integer(0));
			consultInfo.setMid(new Integer(0));
			consultInfo.setType(new Integer(2));
			consultInfo.setScore(new Integer(5));
		}
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCONSULT })
	public String list() {
		consultclasslist = consultClassManagerService.findConsultClassInList(2);
		String hql = " from ConsultInfo where isdel=0 and state=1 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , id desc ";

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
		pageBean = consultInfoManagerService.findConsultInfoHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/manager/consult/list");
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
				ConsultInfo cinfo = (ConsultInfo) pagelist.get(i);
				cinfo.setReplycount(consultReplyInfoManagerService
						.findConsultReplyInfoCount(cinfo.getId()));
				cinfo.setReplyisgreecount(consultReplyInfoManagerService
						.findConsultReplyInfoIsgreeCount(cinfo.getId()));
				newlist.add(cinfo);
			}
		}
		return newlist;
	}

	public ConsultInfoManagerService getConsultInfoManagerService() {
		return consultInfoManagerService;
	}

	public void setConsultInfoManagerService(
			ConsultInfoManagerService consultInfoManagerService) {
		this.consultInfoManagerService = consultInfoManagerService;
	}

	public ConsultInfo getConsultInfo() {
		return consultInfo;
	}

	public void setConsultInfo(ConsultInfo consultInfo) {
		this.consultInfo = consultInfo;
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

	public ConsultClassManagerService getConsultClassManagerService() {
		return consultClassManagerService;
	}

	public void setConsultClassManagerService(
			ConsultClassManagerService consultClassManagerService) {
		this.consultClassManagerService = consultClassManagerService;
	}

	public List getConsultclasslist() {
		return consultclasslist;
	}

	public void setConsultclasslist(List consultclasslist) {
		this.consultclasslist = consultclasslist;
	}

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}

	public ConsultReplyInfoManagerService getConsultReplyInfoManagerService() {
		return consultReplyInfoManagerService;
	}

	public void setConsultReplyInfoManagerService(
			ConsultReplyInfoManagerService consultReplyInfoManagerService) {
		this.consultReplyInfoManagerService = consultReplyInfoManagerService;
	}

	public List getConsultreplylist() {
		return consultreplylist;
	}

	public void setConsultreplylist(List consultreplylist) {
		this.consultreplylist = consultreplylist;
	}

	public int getReplyid() {
		return replyid;
	}

	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}

	public ConsultClass getConsultClass() {
		return consultClass;
	}

	public void setConsultClass(ConsultClass consultClass) {
		this.consultClass = consultClass;
	}

}
