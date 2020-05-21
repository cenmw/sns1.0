package com.cenmw.content.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.content.manager.dao.ContentInfoManagerDao;
import com.cenmw.content.po.ContentInfo;
import com.cenmw.util.PageBean;

public class ContentInfoManagerService {
	private ContentInfoManagerDao contentInfoManagerDao;

	public PageBean findContentInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return contentInfoManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveContentInfo(ContentInfo contentInfo) {
		boolean status = true;
		contentInfoManagerDao.saveContentInfo(contentInfo);
		contentInfoManagerDao.updateContentInfo(contentInfo);
		return status;
	}

	public List findContentInfoInList(int type) {
		String hql = "from ContentInfo where isdel=0 and type=" + type
				+ " order by id";
		return contentInfoManagerDao.getListForHql(hql, null);
	}

	public boolean updateContentInfo(ContentInfo contentInfo) {
		boolean status = true;
		contentInfoManagerDao.updateContentInfo(contentInfo);
		return status;
	}

	public ContentInfo getContentInfo(int id) {
		return contentInfoManagerDao.getContentInfoById(id);
	}

	public void deleteContentInfoById(int id) {
		ContentInfo pb = getContentInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			contentInfoManagerDao.saveContentInfo(pb);
		}
	}

	public void deleteContentInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteContentInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public ContentInfoManagerDao getContentInfoManagerDao() {
		return contentInfoManagerDao;
	}

	public void setContentInfoManagerDao(ContentInfoManagerDao contentInfoManagerDao) {
		this.contentInfoManagerDao = contentInfoManagerDao;
	}


}
