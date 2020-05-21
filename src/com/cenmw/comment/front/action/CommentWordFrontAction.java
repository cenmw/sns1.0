package com.cenmw.comment.front.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.front.service.CommentWordFrontService;
import com.cenmw.comment.po.CommentWord;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;


public class CommentWordFrontAction extends BaseAction {
	/**
	 * 评论信息 模块
	 */
	private CommentWordFrontService commentWordFrontService; 
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
				commentWordFrontService.deleteCommentWordById(new Integer(idarrs[i].trim()));
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
			commentWordFrontService.saveCommentWord(commentWord);
		}
		commentWordFrontService.updateCommentWord(commentWord);
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
			commentWord = commentWordFrontService.getCommentWord(id);
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

		// 判断排序条件
		pageBean = commentWordFrontService.findCommentWordHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/commentword/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/gopage/gopage.html"));
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
	
	public CommentWordFrontService getCommentWordFrontService() {
		return commentWordFrontService;
	}

	public void setCommentWordFrontService(
			CommentWordFrontService commentWordFrontService) {
		this.commentWordFrontService = commentWordFrontService;
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
