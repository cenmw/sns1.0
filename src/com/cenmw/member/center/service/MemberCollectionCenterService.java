package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberCollectionCenterDao;
import com.cenmw.member.po.MemberCollection;
import com.cenmw.member.po.MemberReport;
import com.cenmw.util.PageBean;

public class MemberCollectionCenterService {
	private MemberCollectionCenterDao memberCollectionCenterDao;

	public PageBean findMemberCollectionHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberCollectionCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberCollection(MemberCollection memberCollection) {
		boolean status = true;
		memberCollectionCenterDao.saveMemberCollection(memberCollection);
		memberCollectionCenterDao.updateMemberCollection(memberCollection);
		return status;
	}

	public List findMemberCollectionInList(int mid) {
		String hql = "from MemberCollection where isdel=0 and mid=" + mid
				+ " order by id";
		return memberCollectionCenterDao.getListForHql(hql, null);
	}

	public boolean updateMemberCollection(MemberCollection memberCollection) {
		boolean status = true;
		memberCollectionCenterDao.updateMemberCollection(memberCollection);
		return status;
	}

	public MemberCollection getMemberCollection(int id) {
		return memberCollectionCenterDao.getMemberCollectionById(id);
	}

	public MemberCollection getMemberCollection(int mid, int cid, int type) {
		MemberCollection mp = null;
		String hql = "from MemberCollection where isdel=0 and mid=" + mid
				+ " and cid=" + cid + " and type=" + type + " order by id";
		List list = memberCollectionCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberCollection) list.get(0);
		}
		return mp;
	}
	
	public void deleteMemberCollectionById(int id) {
		MemberCollection pb = getMemberCollection(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberCollectionCenterDao.saveMemberCollection(pb);
		}
	}

	public void deleteMemberCollectionByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberCollectionById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberCollectionCenterDao getMemberCollectionCenterDao() {
		return memberCollectionCenterDao;
	}

	public void setMemberCollectionCenterDao(MemberCollectionCenterDao memberCollectionCenterDao) {
		this.memberCollectionCenterDao = memberCollectionCenterDao;
	}


}
