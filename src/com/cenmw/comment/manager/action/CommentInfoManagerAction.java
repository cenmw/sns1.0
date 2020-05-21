package com.cenmw.comment.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.manager.service.CommentInfoManagerService;
import com.cenmw.comment.po.CommentInfo;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class CommentInfoManagerAction extends BaseAction {
	/**
	 * 评论信息 模块
	 */
	private CommentInfoManagerService commentInfoManagerService;
	private MemberInfoManagerService memberInfoManagerService;
	private CommentInfo commentInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchaccount = "";
	private int searchtype = -1;

	/**
	 * 评论信息删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteCOMMENT })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				commentInfo = commentInfoManagerService
						.getCommentInfo(new Integer(idarrs[i].trim()));
				commentInfo.setIsdel(new Integer(1));
				commentInfoManagerService.updateCommentInfo(commentInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 评论信息添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDCOMMENT })
	public String save() {
		String msg = "修改成功！";
		if (commentInfo.getId() == null) {
			msg = "添加成功！";
			commentInfo.setIsdel(new Integer(0));
			commentInfo.setCtime(new Date());
			commentInfoManagerService.saveCommentInfo(commentInfo);
		}
		int newtype = commentInfo.getType() == null ? 1 : commentInfo.getType()
				.intValue();
		switch (newtype) {
		case 0:
			commentInfo.setTypename("回复评论");
			break;
		case 1:
			commentInfo.setTypename("说说评论");
			break;
		case 2:
			commentInfo.setTypename("日志评论");
			break;
		case 3:
			commentInfo.setTypename("文章评论");
			break;
		case 4:
			commentInfo.setTypename("相片评论");
			break;
		case 5:
			commentInfo.setTypename("视频评论");
			break;
		}
		commentInfoManagerService.updateCommentInfo(commentInfo);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 评论信息查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCOMMENT })
	public String info() {
		if (id > 0) {
			commentInfo = commentInfoManagerService.getCommentInfo(id);
			commentInfo.setMemberInfo(memberInfoManagerService
					.getMemberInfo(commentInfo.getMid()));
		}
		// 初始化信息
		if (commentInfo == null) {
			commentInfo = new CommentInfo();
			commentInfo.setIsdel(new Integer(0));
			commentInfo.setType(new Integer(1));
		}
		return SUCCESS;
	}

	/**
	 * 评论列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCOMMENT })
	public String list() {
		String hql = " from CommentInfo where isdel=0";
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
		pageBean = commentInfoManagerService.findCommentInfoHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/manager/comment/list");
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
				CommentInfo cinfo = (CommentInfo) pagelist.get(i);
				cinfo.setMemberInfo(memberInfoManagerService
						.getMemberInfo(cinfo.getMid()));
				newlist.add(cinfo);
			}
		}
		return newlist;
	}

	public CommentInfoManagerService getCommentInfoManagerService() {
		return commentInfoManagerService;
	}

	public void setCommentInfoManagerService(
			CommentInfoManagerService commentInfoManagerService) {
		this.commentInfoManagerService = commentInfoManagerService;
	}

	public CommentInfo getCommentInfo() {
		return commentInfo;
	}

	public void setCommentInfo(CommentInfo commentInfo) {
		this.commentInfo = commentInfo;
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

	public MemberInfoManagerService getMemberInfoManagerService() {
		return memberInfoManagerService;
	}

	public void setMemberInfoManagerService(
			MemberInfoManagerService memberInfoManagerService) {
		this.memberInfoManagerService = memberInfoManagerService;
	}

}
