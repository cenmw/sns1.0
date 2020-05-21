package com.cenmw.comment.manager.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.manager.service.CommentIpManagerService;
import com.cenmw.comment.po.CommentIp;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;


public class CommentIpManagerAction extends BaseAction {
	/**
	 * 评论信息 模块
	 */
	private CommentIpManagerService commentIpManagerService; 
	private CommentIp commentIp;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchip = "";

	/**
	 * 评论信息删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteCOMMENTIP })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				commentIpManagerService.deleteCommentIpById(new Integer(idarrs[i].trim()));
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
	@EmployeePermission(perm = { EmployeePermissionType.ADDCOMMENTIP })
	public String save() {
		String msg = "修改成功！";
		if (commentIp.getId() == null) {
			msg = "添加成功！";
			commentIp.setCtime(new Date());
			commentIpManagerService.saveCommentIp(commentIp);
		}
		commentIpManagerService.updateCommentIp(commentIp);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 评论信息查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCOMMENTIP })
	public String info() {
		if (id > 0) {
			commentIp = commentIpManagerService.getCommentIp(id);
		}
		// 初始化信息
		if (commentIp == null) {
			commentIp = new CommentIp();
		} 
		return SUCCESS;
	}

	/**
	 * 评论列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCOMMENTIP })
	public String list() {
		String hql = " from CommentIp ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchip != null && searchip.length()>0) {
			hql += " and ip '%" + searchip + "%'";
			HqlBean hqlBean = new HqlBean(searchip, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchip", hqlBean);
			parameter += "&searchip=" + searchip;
		}
		pageSize = 20;
		// 判断排序条件
		pageBean = commentIpManagerService.findCommentIpHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/commentip/list");
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
		return pagelist;
	}
	
	public CommentIpManagerService getCommentIpManagerService() {
		return commentIpManagerService;
	}

	public void setCommentIpManagerService(
			CommentIpManagerService commentIpManagerService) {
		this.commentIpManagerService = commentIpManagerService;
	}

	public CommentIp getCommentIp() {
		return commentIp;
	}

	public void setCommentIp(CommentIp commentIp) {
		this.commentIp = commentIp;
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

	public String getSearchip() {
		return searchip;
	}

	public void setSearchip(String searchip) {
		this.searchip = searchip;
	}

}
