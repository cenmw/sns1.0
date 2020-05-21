package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberPraiseCenterDao;
import com.cenmw.member.po.MemberPraise;
import com.cenmw.util.PageBean;

public class MemberPraiseCenterService {
	private MemberPraiseCenterDao memberPraiseCenterDao;

	public PageBean findMemberPraiseHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberPraiseCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberPraise(MemberPraise memberPraise) {
		boolean status = true;
		memberPraiseCenterDao.saveMemberPraise(memberPraise);
		memberPraiseCenterDao.updateMemberPraise(memberPraise);
		return status;
	}

	public List findMemberPraiseInList(int mid) {
		String hql = "from MemberPraise where isdel=0 and mid=" + mid
				+ " order by id";
		return memberPraiseCenterDao.getListForHql(hql, null);
	}

	public List findMemberPraiseInList(int type, int cid) {
		String hql = "from MemberPraise where isdel=0 and type=" + type
				+ " and cid=" + cid + " order by id";
		return memberPraiseCenterDao.getListForHql(hql, null);
	}

	public MemberPraise findMemberPraise(int mid, int type, int cid) {
		MemberPraise mp = null;
		String hql = "from MemberPraise where mid=" + mid + " and type=" + type
				+ " and cid=" + cid + " order by id";
		List list = memberPraiseCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberPraise) list.get(0);
		}
		return mp;
	}

	public MemberPraise findMemberPraiseAll(int mid, int type, int cid) {
		MemberPraise mp = null;
		String hql = "from MemberPraise where isdel=0 and mid=" + mid + " and type=" + type
				+ " and cid=" + cid + " order by id";
		List list = memberPraiseCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberPraise) list.get(0);
		}
		return mp;
	}
	
	public int getMemberPraiseInListNumber(int type, int cid) {
		String hql = "from MemberPraise where isdel=0 and type=" + type
				+ " and cid=" + cid;
		return memberPraiseCenterDao.findAllRow(hql);
	}

	public boolean updateMemberPraise(MemberPraise memberPraise) {
		boolean status = true;
		memberPraiseCenterDao.updateMemberPraise(memberPraise);
		return status;
	}

	public MemberPraise getMemberPraise(int id) {
		return memberPraiseCenterDao.getMemberPraiseById(id);
	}

	public void deleteMemberPraiseById(int id) {
		MemberPraise pb = getMemberPraise(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberPraiseCenterDao.saveMemberPraise(pb);
		}
	}

	public void deleteMemberPraiseByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberPraiseById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberPraiseCenterDao getMemberPraiseCenterDao() {
		return memberPraiseCenterDao;
	}

	public void setMemberPraiseCenterDao(
			MemberPraiseCenterDao memberPraiseCenterDao) {
		this.memberPraiseCenterDao = memberPraiseCenterDao;
	}

}
