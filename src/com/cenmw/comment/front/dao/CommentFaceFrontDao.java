package com.cenmw.comment.front.dao;

import java.util.List;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.comment.po.CommentFace;

public class CommentFaceFrontDao extends BaseHibernateDao {
	
	/**
	 * 获取默认的表情列表
	 * 
	 * @return
	 */
	public List<CommentFace> getCommentFaceDefaultList() {
		String hql = "from CommentFace where facegroupid=1";
		return getListForHql(hql, null);
	}
}
