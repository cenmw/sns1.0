package com.cenmw.member.center.service;

import com.cenmw.member.center.dao.MemberDiaryXGYCCenterDao;
import com.cenmw.member.po.MemberDiaryXGYC;
import com.cenmw.util.PageBean;

import java.util.List;
import java.util.Map;

public class MemberDiaryXGYCCenterService {
	private MemberDiaryXGYCCenterDao memberDiaryXGYCCenterDao;

	public PageBean findMemberDiaryXGYCHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberDiaryXGYCCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberDiaryXGYC(MemberDiaryXGYC memberDiaryXGYC) {
		boolean status = true;
		memberDiaryXGYCCenterDao.saveMemberDiaryXGYC(memberDiaryXGYC);
		memberDiaryXGYCCenterDao.updateMemberDiaryXGYC(memberDiaryXGYC);
		return status;
	}

	public List findMemberDiaryXGYCInList(int mid) {
		String hql = "from MemberDiaryXGYC where isdel=0 and mid=" + mid
				+ " order by id";
		return memberDiaryXGYCCenterDao.getListForHql(hql, null);
	}

	public int getMemberDiaryXGYCInListNumber(int cid) {
		String hql = "from MemberDiaryXGYC where isdel=0 and rcid=" + cid;
		return memberDiaryXGYCCenterDao.findAllRow(hql);
	}
	
	public List findHotZTMemberDiaryXGYCList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberDiaryXGYC where isdel=0 order by rcnumber desc,id asc";
		return memberDiaryXGYCCenterDao.getListForHql(hql, null, top);
	}

	public List findHotVIEWMemberDiaryXGYCList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberDiaryXGYC where isdel=0 order by viewnumber desc,id asc";
		return memberDiaryXGYCCenterDao.getListForHql(hql, null, top);
	}
	
	public boolean updateMemberDiaryXGYC(MemberDiaryXGYC memberDiaryXGYC) {
		boolean status = true;
		memberDiaryXGYCCenterDao.updateMemberDiaryXGYC(memberDiaryXGYC);
		return status;
	}

	public MemberDiaryXGYC getMemberDiaryXGYC(int id) {
		return memberDiaryXGYCCenterDao.getMemberDiaryXGYCById(id);
	}

	public MemberDiaryXGYC getMemberDiaryXGYC(int mid, int cid) {
		MemberDiaryXGYC mp = null;
		String hql = "from MemberDiaryXGYC where isdel=0 and mid=" + mid
				+ " and rcid=" + cid + " order by id";
		List list = memberDiaryXGYCCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberDiaryXGYC) list.get(0);
		}
		return mp;
	}
	
	public void deleteMemberDiaryXGYCById(int id) {
		MemberDiaryXGYC pb = getMemberDiaryXGYC(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberDiaryXGYCCenterDao.saveMemberDiaryXGYC(pb);
		}
	}

	public void deleteMemberDiaryXGYCByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberDiaryXGYCById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberDiaryXGYCCenterDao getMemberDiaryXGYCCenterDao() {
		return memberDiaryXGYCCenterDao;
	}

	public void setMemberDiaryXGYCCenterDao(MemberDiaryXGYCCenterDao memberDiaryXGYCCenterDao) {
		this.memberDiaryXGYCCenterDao = memberDiaryXGYCCenterDao;
	}

	public MemberDiaryXGYC getNewMemberDiaryXGYC(int mid) {
		MemberDiaryXGYC mp = null;
		String hql = "from MemberDiaryXGYC where isdel=0 and mid=" + mid
				+ " order by id desc";
		List list = memberDiaryXGYCCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberDiaryXGYC) list.get(0);
		}
		return mp;
	}

	public MemberDiaryXGYC getNewMemberDiaryXGYCByPtime(String ptime) {
		MemberDiaryXGYC mp = null;
		String hql = "from MemberDiaryXGYC where isdel=0 and ptime='" + ptime
				+ "' order by id desc";
		List list = memberDiaryXGYCCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberDiaryXGYC) list.get(0);
		}
		return mp;
	}
}
