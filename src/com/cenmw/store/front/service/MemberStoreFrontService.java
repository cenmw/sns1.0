package com.cenmw.store.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.store.front.dao.MemberStoreFrontDao;
import com.cenmw.member.po.MemberStore;
import com.cenmw.util.PageBean;

public class MemberStoreFrontService {
	private MemberStoreFrontDao memberStoreFrontDao;

	public PageBean findMemberStoreHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberStoreFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberStore(MemberStore memberStore) {
		boolean status = true;
		memberStoreFrontDao.saveMemberStore(memberStore);
		memberStoreFrontDao.updateMemberStore(memberStore);
		return status;
	}

	public List findMemberStoreInList(int mid) {
		String hql = "from MemberStore where isdel=0 and mid=" + mid
				+ " order by id";
		return memberStoreFrontDao.getListForHql(hql, null);
	}

	public double getMemberStoreSumPrice(int mid) {
		String hql = "SELECT SUM(price) FROM MemberStore where isdel=0 and state=1 and mid="
				+ mid;
		double sumprice = 0.0f;
		List list = memberStoreFrontDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			Object object = list.get(0) == null ? 0.0 : list.get(0);
			sumprice = (Double) object;
		}
		return sumprice;
	}

	public boolean updateMemberStore(MemberStore memberStore) {
		boolean status = true;
		memberStoreFrontDao.updateMemberStore(memberStore);
		return status;
	}

	public MemberStore getMemberStore(int id) {
		return memberStoreFrontDao.getMemberStoreById(id);
	}

	public MemberStore getMemberStore(String out_trade_no) {
		MemberStore memberStore = null;
		String hql = "from MemberStore where isdel=0 and code='" + out_trade_no
				+ "' ";
		List list = memberStoreFrontDao.getListForHql(hql, null, 1);
		if (list != null && list.size() > 0) {
			memberStore = (MemberStore) list.get(0);
		}
		return memberStore;
	}
	
	public void deleteMemberStoreById(int id) {
		MemberStore pb = getMemberStore(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberStoreFrontDao.saveMemberStore(pb);
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

	public MemberStoreFrontDao getMemberStoreFrontDao() {
		return memberStoreFrontDao;
	}

	public void setMemberStoreFrontDao(MemberStoreFrontDao memberStoreFrontDao) {
		this.memberStoreFrontDao = memberStoreFrontDao;
	}

}
