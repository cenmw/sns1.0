package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.StoreConfigCenterDao;
import com.cenmw.store.po.StoreConfig;
import com.cenmw.util.PageBean;

public class StoreConfigCenterService {
	private StoreConfigCenterDao storeConfigCenterDao;

	public PageBean findStoreConfigHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return storeConfigCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveStoreConfig(StoreConfig storeConfig) {
		boolean status = true;
		storeConfigCenterDao.saveStoreConfig(storeConfig);
		storeConfigCenterDao.updateStoreConfig(storeConfig);
		return status;
	}

	public StoreConfig findStoreConfigInList(int type) {
		StoreConfig storeConfig = null;
		String hql = "from StoreConfig where type=" + type
				+ " order by id";
		List<StoreConfig> list = storeConfigCenterDao.getListForHql(hql, null);
		if(list != null && !list.isEmpty()){
			storeConfig = list.get(0);
		}
		return storeConfig;
	}

	public boolean updateStoreConfig(StoreConfig storeConfig) {
		boolean status = true;
		storeConfigCenterDao.updateStoreConfig(storeConfig);
		return status;
	}

	public StoreConfig getStoreConfig(int id) {
		return storeConfigCenterDao.getStoreConfigById(id);
	}

	public StoreConfigCenterDao getStoreConfigCenterDao() {
		return storeConfigCenterDao;
	}

	public void setStoreConfigCenterDao(StoreConfigCenterDao storeConfigCenterDao) {
		this.storeConfigCenterDao = storeConfigCenterDao;
	}


}
