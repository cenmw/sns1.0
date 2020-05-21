package com.cenmw.comment.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.comment.po.CommentInfo;

public class CommentInfoFrontDao extends BaseHibernateDao {

	public void saveCommentInfo(CommentInfo commentInfo) {
		save(commentInfo);
	}

	public void deleteCommentInfo(CommentInfo commentInfo) {
		delete(commentInfo);
	}

	public CommentInfo getCommentInfoById(int id) {
		return (CommentInfo) findObjectById(CommentInfo.class, id);
	}

	public void updateCommentInfo(CommentInfo commentInfo) {
		updateObject(commentInfo);
	}

}
