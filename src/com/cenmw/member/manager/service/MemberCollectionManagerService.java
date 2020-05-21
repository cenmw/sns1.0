package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberCollectionManagerDao;
import com.cenmw.member.po.MemberCollection;
import com.cenmw.util.PageBean;

public class MemberCollectionManagerService {
	private MemberCollectionManagerDao memberCollectionManagerDao;

	public PageBean findMemberCollectionHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberCollectionManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberCollection(MemberCollection memberCollection) {
		boolean status = true;
		memberCollectionManagerDao.saveMemberCollection(memberCollection);
		memberCollectionManagerDao.updateMemberCollection(memberCollection);
		return status;
	}

	public List findMemberCollectionInList(int mid) {
		String hql = "from MemberCollection where isdel=0 and mid=" + mid
				+ " order by id";
		return memberCollectionManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberCollection(MemberCollection memberCollection) {
		boolean status = true;
		memberCollectionManagerDao.updateMemberCollection(memberCollection);
		return status;
	}

	public MemberCollection getMemberCollection(int id) {
		return memberCollectionManagerDao.getMemberCollectionById(id);
	}

	public void deleteMemberCollectionById(int id) {
		MemberCollection pb = getMemberCollection(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberCollectionManagerDao.saveMemberCollection(pb);
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

	public MemberCollectionManagerDao getMemberCollectionManagerDao() {
		return memberCollectionManagerDao;
	}

	public void setMemberCollectionManagerDao(MemberCollectionManagerDao memberCollectionManagerDao) {
		this.memberCollectionManagerDao = memberCollectionManagerDao;
	}


}
