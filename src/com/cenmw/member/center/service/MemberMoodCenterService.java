package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberMoodCenterDao;
import com.cenmw.member.po.MemberMood;
import com.cenmw.util.PageBean;

public class MemberMoodCenterService {
	private MemberMoodCenterDao memberMoodCenterDao;

	public PageBean findMemberMoodHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberMoodCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberMood(MemberMood memberMood) {
		boolean status = true;
		memberMoodCenterDao.saveMemberMood(memberMood);
		memberMoodCenterDao.updateMemberMood(memberMood);
		return status;
	}

	public List findMemberMoodInList(int mid) {
		String hql = "from MemberMood where isdel=0 and mid=" + mid
				+ " order by id";
		return memberMoodCenterDao.getListForHql(hql, null);
	}

	public List findHotZTMemberMoodList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberMood where isdel=0 and type=1 order by viewnumber desc,id asc";
		return memberMoodCenterDao.getListForHql(hql, null, top);
	}

	public MemberMood getMemberMood(int mid, int cid) {
		MemberMood mp = null;
		String hql = "from MemberMood where isdel=0 and mid=" + mid
				+ " and cid=" + cid + " order by id";
		List list = memberMoodCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberMood) list.get(0);
		}
		return mp;
	}

	public int getMemberMoodInListNumber(int cid) {
		String hql = "from MemberMood where isdel=0 and rcid=" + cid;
		return memberMoodCenterDao.findAllRow(hql);
	}

	public boolean updateMemberMood(MemberMood memberMood) {
		boolean status = true;
		memberMoodCenterDao.updateMemberMood(memberMood);
		return status;
	}

	public MemberMood getMemberMood(int id) {
		return memberMoodCenterDao.getMemberMoodById(id);
	}

	public void deleteMemberMoodById(int id) {
		MemberMood pb = getMemberMood(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberMoodCenterDao.saveMemberMood(pb);
		}
	}

	public void deleteMemberMoodByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberMoodById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberMoodCenterDao getMemberMoodCenterDao() {
		return memberMoodCenterDao;
	}

	public void setMemberMoodCenterDao(MemberMoodCenterDao memberMoodCenterDao) {
		this.memberMoodCenterDao = memberMoodCenterDao;
	}

}
