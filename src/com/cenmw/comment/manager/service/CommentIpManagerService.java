package com.cenmw.comment.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.comment.manager.dao.CommentIpManagerDao;
import com.cenmw.comment.po.CommentIp;
import com.cenmw.util.PageBean;

public class CommentIpManagerService {
	private CommentIpManagerDao commentIpManagerDao;

	public PageBean findCommentIpHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return commentIpManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveCommentIp(CommentIp commentIp) {
		boolean status = true;
		commentIpManagerDao.saveCommentIp(commentIp);
		commentIpManagerDao.updateCommentIp(commentIp);
		return status;
	}

	public List findCommentIpInList(int type) {
		String hql = "from CommentIp where isdel=0 and type=" + type
				+ " order by id";
		return commentIpManagerDao.getListForHql(hql, null);
	}

	public int getCommentByMid(int mid) {
		String sql = "select sum(score) from comment_info where isdel=0 and mid=" + mid;
		List list = commentIpManagerDao.getListSql(sql);
		return new Integer((Integer)list.get(0)).intValue();
	}

	public boolean updateCommentIp(CommentIp commentIp) {
		boolean status = true;
		commentIpManagerDao.updateCommentIp(commentIp);
		return status;
	}

	public CommentIp getCommentIp(int id) {
		return commentIpManagerDao.getCommentIpById(id);
	}

	public void deleteCommentIpById(int id) {
		CommentIp pb = getCommentIp(id);
		if (pb != null) {
			commentIpManagerDao.delete(pb);
		}
	}

	public void deleteCommentIpByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteCommentIpById(new Integer(idsArr[i]));
			}
		}
	}

	public CommentIpManagerDao getCommentIpManagerDao() {
		return commentIpManagerDao;
	}

	public void setCommentIpManagerDao(
			CommentIpManagerDao commentIpManagerDao) {
		this.commentIpManagerDao = commentIpManagerDao;
	}

}
