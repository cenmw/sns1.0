package com.cenmw.comment.manager.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.comment.po.CommentFaceGroup;
import com.cenmw.util.HqlBean;

public class CommentFaceGroupManagerDao extends BaseHibernateDao {

	/**
	 * 删除表情组
	 * 
	 * @param id
	 * @return
	 */
	public int deleteCommentFaceGroup(int id) {
		CommentFaceGroup c = getCommentFaceGroup(id);
		if (c == null) {
			return 0;
		}
		delete(c);
		return id;
	}

	public CommentFaceGroup getCommentFaceGroup(int id) {
		return (CommentFaceGroup) findObjectById(CommentFaceGroup.class, id);
	}

	/**
	 * 添加表情组
	 * 
	 * @param groupname
	 * @return
	 */
	public CommentFaceGroup insertCommentFaceGroup(String groupname) {
		CommentFaceGroup c = new CommentFaceGroup();
		List<CommentFaceGroup> list = getCommentFaceGroupListByUnidGroupname(
				null, groupname, 1);
		if (list == null || list.isEmpty()) {
			c.setGroupname(groupname);
			save(c);
			return c;
		}
		return null;
	}

	/**
	 * 修改表情组
	 * 
	 * @param groupname
	 * @return
	 */
	public CommentFaceGroup updateCommentFaceGroup(int id, String groupname) {
		CommentFaceGroup c = getCommentFaceGroup(id);
		List<CommentFaceGroup> list = getCommentFaceGroupListByUnidGroupname(
				id, groupname, 1);
		if (list == null || list.isEmpty()) {
			c.setGroupname(groupname);
			save(c);
			return c;
		}
		return null;
	}

	/**
	 * 表情组所有列表
	 * 
	 * @return
	 */
	public List<CommentFaceGroup> getCommentFaceGroupList() {
		String hql = "from CommentFaceGroup";
		return getListForHql(hql, null);
	}

	public List<CommentFaceGroup> getCommentFaceGroupListByUnidGroupname(
			Integer unid, String groupname, int top) {
		String hql = "from CommentFaceGroup where groupname = :groupname";
		Map<String, HqlBean> map = new HashMap<String, HqlBean>();
		map.put("groupname", new HqlBean(groupname, "String"));
		if (unid != null && unid > 0) {
			hql += " and id!=" + unid;
		}
		return getListForHql(hql, map, top);
	}
}
