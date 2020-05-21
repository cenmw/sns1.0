package com.cenmw.comment.manager.service;

import com.cenmw.comment.manager.dao.CommentFaceManagerDao;
import com.cenmw.comment.po.CommentFace;
import com.cenmw.util.PageBean;

public class CommentFaceManagerService {
	private CommentFaceManagerDao commentFaceManagerDao;

	public CommentFace getCommentFace(int id) {
		return commentFaceManagerDao.getCommentFace(id);
	}

	public PageBean getCommentFace4SlicePage(int gid, int currentPage,
			int pageSize) {
		return commentFaceManagerDao.getCommentFace4SlicePage(gid, currentPage,
				pageSize);
	}

	public CommentFace insertCommentFace(String facecode, String facepic,
			int gid) {
		return commentFaceManagerDao.insertCommentFace(facecode, facepic, gid);
	}

	public CommentFace updateCommentFace(int id, String facepic, int sort) {
		return commentFaceManagerDao.updateCommentFace(id, facepic, sort);
	}

	public int deleteCommentFace(int id) {
		return commentFaceManagerDao.deleteCommentFace(id);
	}

	public CommentFaceManagerDao getCommentFaceManagerDao() {
		return commentFaceManagerDao;
	}

	public void setCommentFaceManagerDao(
			CommentFaceManagerDao commentFaceManagerDao) {
		this.commentFaceManagerDao = commentFaceManagerDao;
	}

}
