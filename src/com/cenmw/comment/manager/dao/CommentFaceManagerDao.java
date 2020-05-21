package com.cenmw.comment.manager.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.comment.po.CommentFace;
import com.cenmw.util.HqlBean;
import com.cenmw.util.PageBean;

public class CommentFaceManagerDao extends BaseHibernateDao {
	public CommentFace getCommentFace(int id) {
		return (CommentFace) findObjectById(CommentFace.class, id);
	}

	/**
	 * 根据组id获取表情列表
	 * 
	 * @param gid
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean getCommentFace4SlicePage(int gid, int currentPage,
			int pageSize) {
		String hql = "from CommentFace where facegroupid=:gid";
		String orderstr = "sort desc,id asc";
		Map<String, HqlBean> map = new HashMap<String, HqlBean>();
		map.put("gid", new HqlBean(gid, "Integer"));
		return findListHqlForPage(hql, orderstr, map, currentPage, pageSize);
	}

	/**
	 * 添加表情
	 * 
	 * @param facecode
	 * @param facepic
	 * @param gid
	 * @return
	 */
	public CommentFace insertCommentFace(String facecode, String facepic,
			int gid) {
		List<CommentFace> list = getCommentFaceList4FaceCode(null, facecode, 1);
		if (list != null && !list.isEmpty()) {
			return null;
		}
		CommentFace c = new CommentFace();
		c.setFacecode(facecode);
		c.setFacepic(facepic);
		c.setFacegroupid(gid);
		c.setCtime(new Date());
		c.setSort(0);
		save(c);
		return c;
	}

	/**
	 * 更新表情
	 * 
	 * @param id
	 * @param facepic
	 * @param sort
	 * @return
	 */
	public CommentFace updateCommentFace(int id, String facepic, int sort) {
		CommentFace c = getCommentFace(id);
		c.setFacepic(facepic);
		c.setSort(sort);
		save(c);
		return c;
	}

	/**
	 * 删除表情
	 * 
	 * @param id
	 * @return
	 */
	public int deleteCommentFace(int id) {
		CommentFace c = getCommentFace(id);
		if (c == null)
			return 0;
		delete(c);
		return id;
	}

	/**
	 * 根据组id获取表情列表
	 * 
	 * @param gid
	 * @return
	 */
	public List<CommentFace> getCommentFaceList(int gid) {
		String hql = "from CommentFace where facegroupid=:gid";
		Map<String, HqlBean> map = new HashMap<String, HqlBean>();
		map.put("gid", new HqlBean(gid, "Integer"));
		return getListForHql(hql, map);
	}

	/**
	 * 根据表情代码获取表情列表
	 * 
	 * @param unid
	 * @param facecode
	 * @return
	 */
	public List<CommentFace> getCommentFaceList4FaceCode(Integer unid,
			String facecode, int top) {
		String hql = "from CommentFace where facecode=:facecode";
		Map<String, HqlBean> map = new HashMap<String, HqlBean>();
		map.put("facecode", new HqlBean(facecode, "String"));
		if (unid != null && unid > 0) {
			hql += " and id!=" + unid;
		}
		return getListForHql(hql, map, top);
	}

	/**
	 * 根据组id删除表情列表
	 * 
	 * @param gid
	 */
	public void deleteCommentFaceByFaceGroupId(int gid) {
		String hql = "DELETE FROM CommentFace WHERE facegroupid=" + gid;
		executeHql(hql);
	}
}
