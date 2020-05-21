package com.cenmw.comment.manager.service;

import java.util.List;

import com.cenmw.comment.manager.dao.CommentFaceGroupManagerDao;
import com.cenmw.comment.manager.dao.CommentFaceManagerDao;
import com.cenmw.comment.po.CommentFaceGroup;

public class CommentFaceGroupManagerService {
	private CommentFaceGroupManagerDao commentFaceGroupManagerDao;
	private CommentFaceManagerDao commentFaceManagerDao;

	public int deleteCommentFaceGroup(int id) {
		commentFaceManagerDao.deleteCommentFaceByFaceGroupId(id);
		return commentFaceGroupManagerDao.deleteCommentFaceGroup(id);
	}

	public CommentFaceGroup getCommentFaceGroup(int id) {
		return commentFaceGroupManagerDao.getCommentFaceGroup(id);
	}

	public CommentFaceGroup insertCommentFaceGroup(String groupname) {
		return commentFaceGroupManagerDao.insertCommentFaceGroup(groupname);
	}

	public CommentFaceGroup updateCommentFaceGroup(int id, String groupname) {
		return commentFaceGroupManagerDao.updateCommentFaceGroup(id, groupname);
	}

	public List<CommentFaceGroup> getCommentFaceGroupList() {
		return commentFaceGroupManagerDao.getCommentFaceGroupList();
	}

	public CommentFaceGroupManagerDao getCommentFaceGroupManagerDao() {
		return commentFaceGroupManagerDao;
	}

	public void setCommentFaceGroupManagerDao(
			CommentFaceGroupManagerDao commentFaceGroupManagerDao) {
		this.commentFaceGroupManagerDao = commentFaceGroupManagerDao;
	}

	public CommentFaceManagerDao getCommentFaceManagerDao() {
		return commentFaceManagerDao;
	}

	public void setCommentFaceManagerDao(
			CommentFaceManagerDao commentFaceManagerDao) {
		this.commentFaceManagerDao = commentFaceManagerDao;
	}

}
