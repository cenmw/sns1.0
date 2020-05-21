package com.cenmw.content.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.content.front.dao.ContentInfoFrontDao;
import com.cenmw.content.po.ContentInfo;
import com.cenmw.util.PageBean;

public class ContentInfoFrontService {
	private ContentInfoFrontDao contentInfoFrontDao;

	public PageBean findContentInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return contentInfoFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveContentInfo(ContentInfo contentInfo) {
		boolean status = true;
		contentInfoFrontDao.saveContentInfo(contentInfo);
		contentInfoFrontDao.updateContentInfo(contentInfo);
		return status;
	}

	public List findContentInfoInList(int type, int top) {
		String hql = "from ContentInfo where isdel=0 and type=" + type
				+ " order by sort desc , ptime desc , id desc ";
		return contentInfoFrontDao.getListForHql(hql, null, top);
	}

	public boolean updateContentInfo(ContentInfo contentInfo) {
		boolean status = true;
		contentInfoFrontDao.updateContentInfo(contentInfo);
		return status;
	}

	public ContentInfo getContentInfo(int id) {
		return contentInfoFrontDao.getContentInfoById(id);
	}

	public void deleteContentInfoById(int id) {
		ContentInfo pb = getContentInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			contentInfoFrontDao.saveContentInfo(pb);
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

	public ContentInfoFrontDao getContentInfoFrontDao() {
		return contentInfoFrontDao;
	}

	public void setContentInfoFrontDao(ContentInfoFrontDao contentInfoFrontDao) {
		this.contentInfoFrontDao = contentInfoFrontDao;
	}

}
