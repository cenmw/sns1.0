package com.cenmw.member.center.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.consult.front.service.ConsultClassFrontService;
import com.cenmw.consult.po.ConsultClass;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;

public class MemberConsultClassCenterAction extends BaseAction {
	/**
	 * 咨询分类 模块
	 */
	private ConsultClassFrontService consultClassFrontService;
	private ConsultClass consultClass;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private List classlist;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 咨询分类删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				consultClass = consultClassFrontService
						.getConsultClass(new Integer(idarrs[i].trim()));
				consultClass.setIsdel(new Integer(1));
				consultClassFrontService.updateConsultClass(consultClass);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 咨询分类添加功能
	 * 
	 * @return
	 */
	public String save() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "修改成功！";
		if (consultClass.getId() == null) {
			msg = "添加成功！";
			consultClass.setMid(memberInfo.getId());
			consultClass.setIsdel(new Integer(0));
			consultClass.setCtime(new Date());
			consultClass.setType(new Integer(0));
			consultClass.setSort(new Integer(0));
			consultClassFrontService.saveConsultClass(consultClass);
		}
		consultClassFrontService.updateConsultClass(consultClass);
		session.put("classmsg", msg);
		return SUCCESS;
	}

	/**
	 * 咨询分类查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			consultClass = consultClassFrontService.getConsultClass(id);
			String msg = (String) session.get("classmsg");
			if (msg != null && msg.equals("添加成功！")) {
				classlist = consultClassFrontService.findConsultClassInList(2);
			}
		}
		// 初始化信息
		if (consultClass == null) {
			consultClass = new ConsultClass();
			consultClass.setIsdel(new Integer(0));
			consultClass.setMid(memberInfo.getId());
			consultClass.setType(new Integer(0));
			consultClass.setSort(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 咨询分类列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from ConsultClass where isdel=0 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , id desc ";

		// 判断排序条件
		pageBean = consultClassFrontService.findConsultClassHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/consultclasslist");
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
		List newlist = pagelist;
		return newlist;
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

	public ConsultClassFrontService getConsultClassFrontService() {
		return consultClassFrontService;
	}

	public void setConsultClassFrontService(
			ConsultClassFrontService consultClassFrontService) {
		this.consultClassFrontService = consultClassFrontService;
	}

	public ConsultClass getConsultClass() {
		return consultClass;
	}

	public void setConsultClass(ConsultClass consultClass) {
		this.consultClass = consultClass;
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
