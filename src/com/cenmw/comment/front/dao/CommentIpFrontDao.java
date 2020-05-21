package com.cenmw.comment.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.comment.po.CommentIp;

public class CommentIpFrontDao extends BaseHibernateDao {

	public void saveCommentIp(CommentIp commentIp) {
		save(commentIp);
	}

	public void deleteCommentIp(CommentIp commentIp) {
		delete(commentIp);
	}

	public CommentIp getCommentIpById(int id) {
		return (CommentIp) findObjectById(CommentIp.class, id);
	}

	public void updateCommentIp(CommentIp commentIp) {
		updateObject(commentIp);
	}

}
