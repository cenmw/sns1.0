package com.cenmw.comment.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.comment.po.CommentWord;

public class CommentWordManagerDao extends BaseHibernateDao {

	public void saveCommentWord(CommentWord commentWord) {
		save(commentWord);
	}

	public void deleteCommentWord(CommentWord commentWord) {
		delete(commentWord);
	}

	public CommentWord getCommentWordById(int id) {
		return (CommentWord) findObjectById(CommentWord.class, id);
	}

	public void updateCommentWord(CommentWord commentWord) {
		updateObject(commentWord);
	}

}
