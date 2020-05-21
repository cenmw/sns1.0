package com.cenmw.comment.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.comment.manager.dao.CommentInfoManagerDao;
import com.cenmw.comment.po.CommentInfo;
import com.cenmw.util.PageBean;

public class CommentInfoManagerService {
	private CommentInfoManagerDao commentInfoManagerDao;

	public PageBean findCommentInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return commentInfoManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveCommentInfo(CommentInfo commentInfo) {
		boolean status = true;
		commentInfoManagerDao.saveCommentInfo(commentInfo);
		commentInfoManagerDao.updateCommentInfo(commentInfo);
		return status;
	}

	public List findCommentInfoInList(int type) {
		String hql = "from CommentInfo where isdel=0 and type=" + type
				+ " order by id";
		return commentInfoManagerDao.getListForHql(hql, null);
	}

	public int getCommentByMid(int mid) {
		String sql = "select sum(score) from comment_info where isdel=0 and mid=" + mid;
		List list = commentInfoManagerDao.getListSql(sql);
		return new Integer((Integer)list.get(0)).intValue();
	}

	public boolean updateCommentInfo(CommentInfo commentInfo) {
		boolean status = true;
		commentInfoManagerDao.updateCommentInfo(commentInfo);
		return status;
	}

	public CommentInfo getCommentInfo(int id) {
		return commentInfoManagerDao.getCommentInfoById(id);
	}

	public void deleteCommentInfoById(int id) {
		CommentInfo pb = getCommentInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			commentInfoManagerDao.saveCommentInfo(pb);
		}
	}

	public void deleteCommentInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteCommentInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public CommentInfoManagerDao getCommentInfoManagerDao() {
		return commentInfoManagerDao;
	}

	public void setCommentInfoManagerDao(
			CommentInfoManagerDao commentInfoManagerDao) {
		this.commentInfoManagerDao = commentInfoManagerDao;
	}

}
