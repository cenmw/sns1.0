package com.cenmw.content.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.content.po.ContentInfo;

public class ContentInfoFrontDao extends BaseHibernateDao {

	public void saveContentInfo(ContentInfo contentInfo) {
		save(contentInfo);
	}

	public void deleteContentInfo(ContentInfo contentInfo) {
		delete(contentInfo);
	}

	public ContentInfo getContentInfoById(int id) {
		return (ContentInfo) findObjectById(ContentInfo.class, id);
	}

	public void updateContentInfo(ContentInfo contentInfo) {
		updateObject(contentInfo);
	}

}
