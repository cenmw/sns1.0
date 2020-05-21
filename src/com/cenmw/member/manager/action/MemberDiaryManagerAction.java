package com.cenmw.member.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.manager.service.MemberDiaryManagerService;
import com.cenmw.member.po.MemberDiary;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class MemberDiaryManagerAction extends BaseAction {
	/**
	 * 会员日记 模块
	 */
	private MemberDiaryManagerService memberDiaryManagerService; 
	private MemberInfoManagerService memberInfoManagerService; 
	private MemberDiary memberDiary;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员日记删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERBLOG })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				memberDiary = memberDiaryManagerService.getMemberDiary(new Integer(idarrs[i].trim()));
				memberDiary.setIsdel(new Integer(1));
				memberDiaryManagerService.updateMemberDiary(memberDiary);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员日记添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERBLOG })
	public String save() {
		String msg = "修改成功！";
		if (memberDiary.getId() == null) {
			msg = "添加成功！";
			memberDiary.setIsdel(new Integer(0));
			memberDiary.setCtime(new Date());
			memberDiaryManagerService.saveMemberDiary(memberDiary);
		}
		memberDiaryManagerService.updateMemberDiary(memberDiary);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员日记查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERBLOG })
	public String info() {
		if (id > 0) {
			memberDiary = memberDiaryManagerService.getMemberDiary(id);
			memberDiary.setMemberInfo(memberInfoManagerService.getMemberInfo(memberDiary.getMid()));
		}
		// 初始化信息
		if (memberDiary == null) {
			memberDiary = new MemberDiary();
			memberDiary.setIsdel(new Integer(0));
		} 
		return SUCCESS;
	}

	/**
	 * 会员日记列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERBLOG })
	public String list() {
		String hql = " from MemberDiary where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchtitle != null && searchtitle.length()>0) {
			hql += " and ptime = '" + searchtitle + "'";
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
		pageBean = memberDiaryManagerService.findMemberDiaryHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/memberdiary/list");
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
				MemberDiary mblog = (MemberDiary) pagelist.get(i);
				mblog.setMemberInfo(memberInfoManagerService.getMemberInfo(mblog.getMid()));
				newlist.add(mblog);
			}
		}
		return newlist;
	}
	public MemberDiaryManagerService getMemberDiaryManagerService() {
		return memberDiaryManagerService;
	}

	public void setMemberDiaryManagerService(
			MemberDiaryManagerService memberDiaryManagerService) {
		this.memberDiaryManagerService = memberDiaryManagerService;
	}

	public MemberDiary getMemberDiary() {
		return memberDiary;
	}

	public void setMemberDiary(MemberDiary memberDiary) {
		this.memberDiary = memberDiary;
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
