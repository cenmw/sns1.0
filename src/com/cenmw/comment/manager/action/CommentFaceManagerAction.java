package com.cenmw.comment.manager.action;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.manager.service.CommentFaceGroupManagerService;
import com.cenmw.comment.manager.service.CommentFaceManagerService;
import com.cenmw.comment.po.CommentFace;
import com.cenmw.comment.po.CommentFaceGroup;

public class CommentFaceManagerAction extends BaseAction {
	private CommentFaceManagerService commentFaceManagerService;
	private CommentFaceGroupManagerService commentFaceGroupManagerService;

	private Integer gid;

	private CommentFaceGroup commentFaceGroup;

	private Integer id;
	private String facecode;
	private String facepic;
	private Integer sort;
	private CommentFace commentFace;

	/**
	 * 删除表情
	 * 
	 * @return
	 */
	public String deleteCommentFace() {
		gid = commentFaceManagerService.getCommentFace(id).getFacegroupid();
		commentFaceManagerService.deleteCommentFace(id);
		return SUCCESS;
	}

	/**
	 * 表情信息页面
	 * 
	 * @return
	 */
	public String commentFacePage() {
		if (id != null && id > 0) {
			commentFace = commentFaceManagerService.getCommentFace(id);
		}
		return SUCCESS;
	}

	/**
	 * 添加或修改表情
	 * 
	 * @return
	 */
	public String saveCommentFace() {
		if (id != null && id > 0) {
			commentFace = commentFaceManagerService.updateCommentFace(id,
					facepic, sort);
			gid = commentFace.getFacegroupid();
		} else {
			commentFaceManagerService.insertCommentFace(facecode, facepic, gid);
		}
		return SUCCESS;
	}

	/**
	 * 表情列表
	 * 
	 * @return
	 */
	public String commentFaceList() {
		commentFaceGroup = commentFaceGroupManagerService
				.getCommentFaceGroup(gid);
		pageSize = 20;
		pageBean = commentFaceManagerService.getCommentFace4SlicePage(gid,
				currentPage, pageSize);
		pageBean.setAction("/manager/comment/commentFaceList");
		pageBean.setGopageTemplate(request, "/manager/gopage/gopage.html");
		return SUCCESS;
	}

	public CommentFaceManagerService getCommentFaceManagerService() {
		return commentFaceManagerService;
	}

	public void setCommentFaceManagerService(
			CommentFaceManagerService commentFaceManagerService) {
		this.commentFaceManagerService = commentFaceManagerService;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public CommentFaceGroup getCommentFaceGroup() {
		return commentFaceGroup;
	}

	public void setCommentFaceGroup(CommentFaceGroup commentFaceGroup) {
		this.commentFaceGroup = commentFaceGroup;
	}

	public CommentFaceGroupManagerService getCommentFaceGroupManagerService() {
		return commentFaceGroupManagerService;
	}

	public void setCommentFaceGroupManagerService(
			CommentFaceGroupManagerService commentFaceGroupManagerService) {
		this.commentFaceGroupManagerService = commentFaceGroupManagerService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFacecode() {
		return facecode;
	}

	public void setFacecode(String facecode) {
		this.facecode = facecode;
	}

	public String getFacepic() {
		return facepic;
	}

	public void setFacepic(String facepic) {
		this.facepic = facepic;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public CommentFace getCommentFace() {
		return commentFace;
	}

	public void setCommentFace(CommentFace commentFace) {
		this.commentFace = commentFace;
	}

}
