package com.cenmw.comment.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.comment.front.dao.CommentInfoFrontDao;
import com.cenmw.comment.po.CommentInfo;
import com.cenmw.util.PageBean;

public class CommentInfoFrontService {
	private CommentInfoFrontDao commentInfoFrontDao;

	public PageBean findCommentInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return commentInfoFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveCommentInfo(CommentInfo commentInfo) {
		boolean status = true;
		commentInfoFrontDao.saveCommentInfo(commentInfo);
		commentInfoFrontDao.updateCommentInfo(commentInfo);
		return status;
	}

	public List findCommentInfoInList(int type) {
		String hql = "from CommentInfo where isdel=0 and type=" + type
				+ " order by id";
		return commentInfoFrontDao.getListForHql(hql, null);
	}

	public void updateCommentInfoInList(int type, int cid) {
		String sql = "update comment_info set isopen=1 where isdel=0 and type="
				+ type + " and cid=" + cid;
		commentInfoFrontDao.executeSql(sql);
	}

	public Integer getCommentInfoCount(int type, int mid) {
		String sql = "SELECT COUNT(t1.cid) FROM comment_info t1 INNER JOIN member_blog t2 ON t1.cid = t2.id WHERE (t1.type=2 and t1.cid=t2.id and t1.isopen=0 and t1.isdel=0 and t2.isdel=0 and t2.type=1 and t2.mid="
				+ mid + ")";
		if (type == 2) {
			sql = "SELECT COUNT(t1.cid) FROM comment_info t1 INNER JOIN member_blog t2 ON t1.cid = t2.id WHERE (t1.type = 2 and t1.cid=t2.id and t1.isopen=0 and t1.isdel=0 and t2.isdel=0 and t2.type=1 and t2.mid="
					+ mid + ")";
		}
		Integer count = 0;
		List list = commentInfoFrontDao.getListSql(sql);
		if (list != null && !list.isEmpty()) {
			count = (Integer) list.get(0);
		}
		return count;
	}

	public int getCommentByMid(int mid) {
		String sql = "select sum(score) from comment_info where isdel=0 and mid="
				+ mid;
		List list = commentInfoFrontDao.getListSql(sql);
		return new Integer((Integer) list.get(0)).intValue();
	}

	public int getCommentNumber(int cid, int type) {
		String hql = "from CommentInfo where isdel=0 and cid=" + cid
				+ " and type=" + type;
		return commentInfoFrontDao.findAllRow(hql);
	}

	public boolean updateCommentInfo(CommentInfo commentInfo) {
		boolean status = true;
		commentInfoFrontDao.updateCommentInfo(commentInfo);
		return status;
	}

	public CommentInfo getCommentInfo(int id) {
		return commentInfoFrontDao.getCommentInfoById(id);
	}

	public void deleteCommentInfoById(int id) {
		CommentInfo pb = getCommentInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			commentInfoFrontDao.saveCommentInfo(pb);
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

	public CommentInfoFrontDao getCommentInfoFrontDao() {
		return commentInfoFrontDao;
	}

	public void setCommentInfoFrontDao(CommentInfoFrontDao commentInfoFrontDao) {
		this.commentInfoFrontDao = commentInfoFrontDao;
	}

}
