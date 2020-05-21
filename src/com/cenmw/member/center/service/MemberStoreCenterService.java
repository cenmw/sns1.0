package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberStoreCenterDao;
import com.cenmw.member.po.MemberStore;
import com.cenmw.util.PageBean;

public class MemberStoreCenterService {
	private MemberStoreCenterDao memberStoreCenterDao;

	public PageBean findMemberStoreHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberStoreCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberStore(MemberStore memberStore) {
		boolean status = true;
		memberStoreCenterDao.saveMemberStore(memberStore);
		memberStoreCenterDao.updateMemberStore(memberStore);
		return status;
	}

	public List findMemberStoreInList(int mid) {
		String hql = "from MemberStore where isdel=0 and mid=" + mid
				+ " order by id";
		return memberStoreCenterDao.getListForHql(hql, null);
	}

	public double getMemberStoreSumPrice(int mid) {
		String hql = "SELECT SUM(price) FROM MemberStore where isdel=0 and state=1 and mid=" + mid;
		double sumprice = 0.0f;
		List list = memberStoreCenterDao.getListForHql(hql,null);
		if(list != null && !list.isEmpty()){
			Object object = list.get(0)==null?0.0:list.get(0);
			sumprice = (Double)object ;
		}
		return sumprice;
	}
	
	public boolean updateMemberStore(MemberStore memberStore) {
		boolean status = true;
		memberStoreCenterDao.updateMemberStore(memberStore);
		return status;
	}

	public MemberStore getMemberStore(int id) {
		return memberStoreCenterDao.getMemberStoreById(id);
	}

	public void deleteMemberStoreById(int id) {
		MemberStore pb = getMemberStore(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberStoreCenterDao.saveMemberStore(pb);
		}
	}

	public void deleteMemberStoreByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberStoreById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberStoreCenterDao getMemberStoreCenterDao() {
		return memberStoreCenterDao;
	}

	public void setMemberStoreCenterDao(MemberStoreCenterDao memberStoreCenterDao) {
		this.memberStoreCenterDao = memberStoreCenterDao;
	}


}
