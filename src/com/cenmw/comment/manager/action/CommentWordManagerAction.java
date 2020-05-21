package com.cenmw.comment.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.manager.service.CommentWordManagerService;
import com.cenmw.comment.po.CommentWord;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class CommentWordManagerAction extends BaseAction {
	/**
	 * 评论信息 模块
	 */
	private CommentWordManagerService commentWordManagerService; 
	private CommentWord commentWord;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchword = "";

	/**
	 * 评论信息删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteCOMMENTWORD })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for(int i=0;i<idarrs.length;i++){
				commentWordManagerService.deleteCommentWordById(new Integer(idarrs[i].trim()));
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
	@EmployeePermission(perm = { EmployeePermissionType.ADDCOMMENTWORD })
	public String save() {
		String msg = "修改成功！";
		if (commentWord.getId() == null) {
			msg = "添加成功！";
			commentWord.setCtime(new Date());
			commentWordManagerService.saveCommentWord(commentWord);
		}
		commentWordManagerService.updateCommentWord(commentWord);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 评论信息查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCOMMENTWORD })
	public String info() {
		if (id > 0) {
			commentWord = commentWordManagerService.getCommentWord(id);
		}
		// 初始化信息
		if (commentWord == null) {
			commentWord = new CommentWord();
		} 
		return SUCCESS;
	}

	/**
	 * 评论列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWCOMMENTWORD })
	public String list() {
		String hql = " from CommentWord ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchword != null && searchword.length()>0) {
			hql += " and words like '%" + searchword + "%'";
			HqlBean hqlBean = new HqlBean(searchword, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchword", hqlBean);
			try {
				parameter += "&searchword=" + StringUtil.URLEncode(searchword);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		pageSize = 20;
		// 判断排序条件
		pageBean = commentWordManagerService.findCommentWordHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/manager/commentword/list");
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
	
	public CommentWordManagerService getCommentWordManagerService() {
		return commentWordManagerService;
	}

	public void setCommentWordManagerService(
			CommentWordManagerService commentWordManagerService) {
		this.commentWordManagerService = commentWordManagerService;
	}

	public CommentWord getCommentWord() {
		return commentWord;
	}

	public void setCommentWord(CommentWord commentWord) {
		this.commentWord = commentWord;
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

	public String getSearchword() {
		return searchword;
	}

	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}


}
