package com.cenmw.member.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.front.dao.MemberMoodFrontDao;
import com.cenmw.member.po.MemberMood;
import com.cenmw.util.PageBean;

public class MemberMoodFrontService {
	private MemberMoodFrontDao memberMoodFrontDao;

	public PageBean findMemberMoodHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberMoodFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberMood(MemberMood memberMood) {
		boolean status = true;
		memberMoodFrontDao.saveMemberMood(memberMood);
		memberMoodFrontDao.updateMemberMood(memberMood);
		return status;
	}

	public List findMemberMoodInList(int mid) {
		String hql = "from MemberMood where isdel=0 and mid=" + mid
				+ " order by id";
		return memberMoodFrontDao.getListForHql(hql, null);
	}

	public boolean updateMemberMood(MemberMood memberMood) {
		boolean status = true;
		memberMoodFrontDao.updateMemberMood(memberMood);
		return status;
	}

	public MemberMood getMemberMood(int id) {
		return memberMoodFrontDao.getMemberMoodById(id);
	}

	public void deleteMemberMoodById(int id) {
		MemberMood pb = getMemberMood(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberMoodFrontDao.saveMemberMood(pb);
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

	public MemberMoodFrontDao getMemberMoodFrontDao() {
		return memberMoodFrontDao;
	}

	public void setMemberMoodFrontDao(MemberMoodFrontDao memberMoodFrontDao) {
		this.memberMoodFrontDao = memberMoodFrontDao;
	}


}
