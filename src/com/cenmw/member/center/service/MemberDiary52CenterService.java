package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberDiary52CenterDao;
import com.cenmw.member.po.MemberDiary52;
import com.cenmw.util.PageBean;

public class MemberDiary52CenterService {
	private MemberDiary52CenterDao memberDiary52CenterDao;

	public PageBean findMemberDiary52HQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberDiary52CenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberDiary52(MemberDiary52 memberDiary52) {
		boolean status = true;
		memberDiary52CenterDao.saveMemberDiary52(memberDiary52);
		memberDiary52CenterDao.updateMemberDiary52(memberDiary52);
		return status;
	}

	public List findMemberDiary52InList(int mid) {
		String hql = "from MemberDiary52 where isdel=0 and mid=" + mid
				+ " order by id";
		return memberDiary52CenterDao.getListForHql(hql, null);
	}

	public int getMemberDiary52InListNumber(int cid) {
		String hql = "from MemberDiary52 where isdel=0 and rcid=" + cid;
		return memberDiary52CenterDao.findAllRow(hql);
	}
	
	public List findHotZTMemberDiary52List(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberDiary52 where isdel=0 order by rcnumber desc,id asc";
		return memberDiary52CenterDao.getListForHql(hql, null, top);
	}

	public List findHotVIEWMemberDiary52List(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberDiary52 where isdel=0 order by viewnumber desc,id asc";
		return memberDiary52CenterDao.getListForHql(hql, null, top);
	}
	
	public boolean updateMemberDiary52(MemberDiary52 memberDiary52) {
		boolean status = true;
		memberDiary52CenterDao.updateMemberDiary52(memberDiary52);
		return status;
	}

	public MemberDiary52 getMemberDiary52(int id) {
		return memberDiary52CenterDao.getMemberDiary52ById(id);
	}

	public MemberDiary52 getMemberDiary52(int mid, int cid) {
		MemberDiary52 mp = null;
		String hql = "from MemberDiary52 where isdel=0 and mid=" + mid
				+ " and rcid=" + cid + " order by id";
		List list = memberDiary52CenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberDiary52) list.get(0);
		}
		return mp;
	}
	
	public MemberDiary52 getFirstMemberDiary52(int mid, int type) {
		MemberDiary52 mp = null;
		String hql = "from MemberDiary52 where isdel=0 and mid=" + mid + " and type=" + type + " and gq is null or gq<>1 order by id";
		List list = memberDiary52CenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberDiary52) list.get(0);
		}
		return mp;
	}
	
	public MemberDiary52 getNewMemberDiary52(int mid, int type) {
		MemberDiary52 mp = null;
		String hql = "from MemberDiary52 where isdel=0 and mid=" + mid + " and type=" + type + " and gq is null or gq<>1 order by id desc";
		List list = memberDiary52CenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberDiary52) list.get(0);
		}
		return mp;
	}
	
	public void deleteMemberDiary52ById(int id) {
		MemberDiary52 pb = getMemberDiary52(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberDiary52CenterDao.saveMemberDiary52(pb);
		}
	}

	public void deleteMemberDiary52ByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberDiary52ById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberDiary52CenterDao getMemberDiary52CenterDao() {
		return memberDiary52CenterDao;
	}

	public void setMemberDiary52CenterDao(MemberDiary52CenterDao memberDiary52CenterDao) {
		this.memberDiary52CenterDao = memberDiary52CenterDao;
	}


}
