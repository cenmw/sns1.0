package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberMoodManagerDao;
import com.cenmw.member.po.MemberMood;
import com.cenmw.util.PageBean;

public class MemberMoodManagerService {
	private MemberMoodManagerDao memberMoodManagerDao;

	public PageBean findMemberMoodHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberMoodManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberMood(MemberMood memberMood) {
		boolean status = true;
		memberMoodManagerDao.saveMemberMood(memberMood);
		memberMoodManagerDao.updateMemberMood(memberMood);
		return status;
	}

	public List findMemberMoodInList(int mid) {
		String hql = "from MemberMood where isdel=0 and mid=" + mid
				+ " order by id";
		return memberMoodManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberMood(MemberMood memberMood) {
		boolean status = true;
		memberMoodManagerDao.updateMemberMood(memberMood);
		return status;
	}

	public MemberMood getMemberMood(int id) {
		return memberMoodManagerDao.getMemberMoodById(id);
	}

	public void deleteMemberMoodById(int id) {
		MemberMood pb = getMemberMood(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberMoodManagerDao.saveMemberMood(pb);
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

	public MemberMoodManagerDao getMemberMoodManagerDao() {
		return memberMoodManagerDao;
	}

	public void setMemberMoodManagerDao(MemberMoodManagerDao memberMoodManagerDao) {
		this.memberMoodManagerDao = memberMoodManagerDao;
	}


}
