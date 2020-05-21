package com.cenmw.comment.manager.action;

import java.util.List;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.manager.service.CommentFaceGroupManagerService;
import com.cenmw.comment.po.CommentFaceGroup;

public class CommentFaceGroupManagerAction extends BaseAction {
	private CommentFaceGroupManagerService commentFaceGroupManagerService;
	private List<CommentFaceGroup> commentFaceGroupList;

	private Integer id;
	private String groupname;
	private CommentFaceGroup commentFaceGroup;

	public String deleteCommentFaceGroup() {
		commentFaceGroupManagerService.deleteCommentFaceGroup(id);
		return SUCCESS;
	}

	/**
	 * 保存表情组信息
	 * 
	 * @return
	 */
	public String saveCommentFaceGroup() {
		if (id != null && id > 0) {
			commentFaceGroupManagerService
					.updateCommentFaceGroup(id, groupname);
		} else {
			commentFaceGroupManagerService.insertCommentFaceGroup(groupname);
		}
		return SUCCESS;
	}

	/**
	 * 添加或修改表情组页面
	 * 
	 * @return
	 */
	public String commentFaceGroupPage() {
		if (id != null && id > 0) {
			commentFaceGroup = commentFaceGroupManagerService
					.getCommentFaceGroup(id);
		}
		return SUCCESS;
	}

	/**
	 * 表情组列表
	 * 
	 * @return
	 */
	public String faceGroupList() {
		commentFaceGroupList = commentFaceGroupManagerService
				.getCommentFaceGroupList();
		return SUCCESS;
	}

	public CommentFaceGroupManagerService getCommentFaceGroupManagerService() {
		return commentFaceGroupManagerService;
	}

	public void setCommentFaceGroupManagerService(
			CommentFaceGroupManagerService commentFaceGroupManagerService) {
		this.commentFaceGroupManagerService = commentFaceGroupManagerService;
	}

	public List<CommentFaceGroup> getCommentFaceGroupList() {
		return commentFaceGroupList;
	}

	public void setCommentFaceGroupList(
			List<CommentFaceGroup> commentFaceGroupList) {
		this.commentFaceGroupList = commentFaceGroupList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public CommentFaceGroup getCommentFaceGroup() {
		return commentFaceGroup;
	}

	public void setCommentFaceGroup(CommentFaceGroup commentFaceGroup) {
		this.commentFaceGroup = commentFaceGroup;
	}

}
