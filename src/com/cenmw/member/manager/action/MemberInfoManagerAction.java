package com.cenmw.member.manager.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.MD5;
import com.cenmw.util.StringUtil;

public class MemberInfoManagerAction extends BaseAction {
	/**
	 * 会员信息 模块
	 */
	private MemberInfoManagerService memberInfoManagerService;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private Integer mid;
	private String email;
	private String mobile;
	private String password;
	private String backUrl;
	// 搜索条件
	private String searchaccount = "";
	private int searchtype = -1;

	/**
	 * 初始化密码
	 * 
	 * @return
	 */
	public String initpwd() {
		int result = 0;// result 0 没有被注册 1被注册
		memberInfo = memberInfoManagerService.getMemberInfo(mid);
		if(memberInfo != null){
			MD5 md5 = new MD5();
			String p = md5.getMD5ofStr("123123");  // 后台创建用户密码默认都为 123123
			memberInfo.setPassword(p);
			memberInfoManagerService.updateMemberInfo(memberInfo);
			result = 1;
		}
		
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 验证手机号是否被注册
	 * 
	 * @return
	 */
	public String checkMobile() {
		int result = 0;// result 0 没有被注册 1被注册
		if (memberInfoManagerService.getMemberInfoByMobile(mobile, mid) != null) {
			result = 1;
		}

		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 验证注册邮箱是否被注册
	 * 
	 * @return
	 */
	public String checkEmail() {
		int result = 0;// result 0 没有被注册 1被注册
		if (memberInfoManagerService.getMemberInfoByEmail(email, mid) != null) {
			result = 1;
		}

		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");

		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 会员信息删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBER })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberInfo = memberInfoManagerService
						.getMemberInfo(new Integer(idarrs[i].trim()));
				memberInfo.setIsdel(new Integer(1));
				memberInfoManagerService.updateMemberInfo(memberInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员信息添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBER })
	public String save() {
		String msg = "修改成功！";
		if (memberInfoManagerService.getMemberInfoByEmail(email, memberInfo
				.getId() == null ? 0 : memberInfo.getId()) != null) {
			msg = "此邮箱已经被注册，请更换邮箱！";
		}
		if (memberInfoManagerService.getMemberInfoByMobile(mobile, memberInfo
				.getId() == null ? 0 : memberInfo.getId()) != null) {
			id = 0;
			msg = "此手机号已经被注册，请更换手机号！";
		}
		if (memberInfo.getId() == null) {
			msg = "添加成功！";
			memberInfo.setCtime(new Date());
			MD5 md5 = new MD5();
			String p = md5.getMD5ofStr("123123");  // 后台创建用户密码默认都为 123123
			memberInfo.setPassword(p);
			memberInfoManagerService.saveMemberInfo(memberInfo);
		}
		memberInfoManagerService.updateMemberInfo(memberInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员信息查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBER })
	public String info() {
		if (id > 0) {
			memberInfo = memberInfoManagerService.getMemberInfo(id);
		}
		// 初始化信息
		if (memberInfo == null) {
			memberInfo = new MemberInfo();
			memberInfo.setIsdel(new Integer(0));
			memberInfo.setType(new Integer(0));
			memberInfo.setSex(new Integer(0));
			memberInfo.setStatus(new Integer(1));
		}
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBER })
	public String list() {
		String hql = " from MemberInfo where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchaccount != null && searchaccount.length() > 0) {
			hql += " and account like '%" + searchaccount + "%'";
			HqlBean hqlBean = new HqlBean(searchaccount, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchaccount", hqlBean);
			try {
				parameter += "&searchaccount="
						+ StringUtil.URLEncode(searchaccount);
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
		pageSize = 20;
		// 判断排序条件
		pageBean = memberInfoManagerService.findMemberInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/member/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
		return SUCCESS;
	}

	public MemberInfoManagerService getMemberInfoManagerService() {
		return memberInfoManagerService;
	}

	public void setMemberInfoManagerService(
			MemberInfoManagerService memberInfoManagerService) {
		this.memberInfoManagerService = memberInfoManagerService;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
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

	public String getSearchaccount() {
		return searchaccount;
	}

	public void setSearchaccount(String searchaccount) {
		this.searchaccount = searchaccount;
	}

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
