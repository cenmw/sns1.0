package com.cenmw.member.center.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.store.po.StoreConfig;

public class StoreConfigCenterDao extends BaseHibernateDao {

	public void saveStoreConfig(StoreConfig storeConfig) {
		save(storeConfig);
	}

	public void deleteStoreConfig(StoreConfig storeConfig) {
		delete(storeConfig);
	}

	public StoreConfig getStoreConfigById(int id) {
		return (StoreConfig) findObjectById(StoreConfig.class, id);
	}

	public void updateStoreConfig(StoreConfig storeConfig) {
		updateObject(storeConfig);
	}

}
