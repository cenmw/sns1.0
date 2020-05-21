package com.cenmw.comment.front.service;

import java.util.List;

import com.cenmw.comment.front.dao.CommentFaceFrontDao;
import com.cenmw.comment.po.CommentFace;

public class CommentFaceFrontService {
	private CommentFaceFrontDao commentFaceFrontDao;

	public List<CommentFace> getCommentFaceDefaultList() {
		return commentFaceFrontDao.getCommentFaceDefaultList();
	}

	public CommentFaceFrontDao getCommentFaceFrontDao() {
		return commentFaceFrontDao;
	}

	public void setCommentFaceFrontDao(CommentFaceFrontDao commentFaceFrontDao) {
		this.commentFaceFrontDao = commentFaceFrontDao;
	}

}
