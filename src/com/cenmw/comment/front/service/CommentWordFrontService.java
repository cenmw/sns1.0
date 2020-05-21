package com.cenmw.comment.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.comment.front.dao.CommentWordFrontDao;
import com.cenmw.comment.po.CommentWord;
import com.cenmw.util.PageBean;

public class CommentWordFrontService {
	private CommentWordFrontDao commentWordFrontDao;

	public PageBean findCommentWordHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return commentWordFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveCommentWord(CommentWord commentWord) {
		boolean status = true;
		commentWordFrontDao.saveCommentWord(commentWord);
		commentWordFrontDao.updateCommentWord(commentWord);
		return status;
	}

	public List findCommentWordInList(int type) {
		String hql = "from CommentWord where isdel=0 and type=" + type
				+ " order by id";
		return commentWordFrontDao.getListForHql(hql, null);
	}

	public int getCommentByMid(int mid) {
		String sql = "select sum(score) from comment_word where isdel=0 and mid=" + mid;
		List list = commentWordFrontDao.getListSql(sql);
		return new Integer((Integer)list.get(0)).intValue();
	}

	public boolean updateCommentWord(CommentWord commentWord) {
		boolean status = true;
		commentWordFrontDao.updateCommentWord(commentWord);
		return status;
	}

	public CommentWord getCommentWord(int id) {
		return commentWordFrontDao.getCommentWordById(id);
	}

	public void deleteCommentWordById(int id) {
		CommentWord pb = getCommentWord(id);
		if (pb != null) {
			commentWordFrontDao.deleteCommentWord(pb);
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

	public CommentWordFrontDao getCommentWordFrontDao() {
		return commentWordFrontDao;
	}

	public void setCommentWordFrontDao(
			CommentWordFrontDao commentWordFrontDao) {
		this.commentWordFrontDao = commentWordFrontDao;
	}

}
