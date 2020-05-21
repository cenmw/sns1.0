package com.cenmw.store.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.store.manager.dao.StoreConfigManagerDao;
import com.cenmw.store.po.StoreConfig;
import com.cenmw.util.PageBean;

public class StoreConfigManagerService {
	private StoreConfigManagerDao storeConfigManagerDao;

	public PageBean findStoreConfigHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return storeConfigManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveStoreConfig(StoreConfig storeConfig) {
		boolean status = true;
		storeConfigManagerDao.saveStoreConfig(storeConfig);
		storeConfigManagerDao.updateStoreConfig(storeConfig);
		return status;
	}

	public StoreConfig findStoreConfigInList(int type) {
		StoreConfig ic = null;
		String hql = "from StoreConfig where type=" + type;
		List list = storeConfigManagerDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			ic = (StoreConfig) list.get(0);
		}
		return ic;
	}

	public boolean updateStoreConfig(StoreConfig storeConfig) {
		boolean status = true;
		storeConfigManagerDao.updateStoreConfig(storeConfig);
		return status;
	}

	public StoreConfig getStoreConfig(int id) {
		return storeConfigManagerDao.getStoreConfigById(id);
	}

	public StoreConfigManagerDao getStoreConfigManagerDao() {
		return storeConfigManagerDao;
	}

	public void setStoreConfigManagerDao(
			StoreConfigManagerDao storeConfigManagerDao) {
		this.storeConfigManagerDao = storeConfigManagerDao;
	}

}
