package com.cenmw.comment.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.comment.manager.dao.CommentWordManagerDao;
import com.cenmw.comment.po.CommentWord;
import com.cenmw.util.PageBean;

public class CommentWordManagerService {
	private CommentWordManagerDao commentWordManagerDao;

	public PageBean findCommentWordHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return commentWordManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveCommentWord(CommentWord commentWord) {
		boolean status = true;
		commentWordManagerDao.saveCommentWord(commentWord);
		commentWordManagerDao.updateCommentWord(commentWord);
		return status;
	}

	public List findCommentWordInList(int type) {
		String hql = "from CommentWord where isdel=0 and type=" + type
				+ " order by id";
		return commentWordManagerDao.getListForHql(hql, null);
	}

	public int getCommentByMid(int mid) {
		String sql = "select sum(score) from comment_word where isdel=0 and mid=" + mid;
		List list = commentWordManagerDao.getListSql(sql);
		return new Integer((Integer)list.get(0)).intValue();
	}

	public boolean updateCommentWord(CommentWord commentWord) {
		boolean status = true;
		commentWordManagerDao.updateCommentWord(commentWord);
		return status;
	}

	public CommentWord getCommentWord(int id) {
		return commentWordManagerDao.getCommentWordById(id);
	}

	public void deleteCommentWordById(int id) {
		CommentWord pb = getCommentWord(id);
		if (pb != null) {
			commentWordManagerDao.deleteCommentWord(pb);
		}
	}

	public void deleteCommentWordByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteCommentWordById(new Integer(idsArr[i]));
			}
		}
	}

	public CommentWordManagerDao getCommentWordManagerDao() {
		return commentWordManagerDao;
	}

	public void setCommentWordManagerDao(
			CommentWordManagerDao commentWordManagerDao) {
		this.commentWordManagerDao = commentWordManagerDao;
	}

}
