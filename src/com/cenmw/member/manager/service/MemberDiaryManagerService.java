package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberDiaryManagerDao;
import com.cenmw.member.po.MemberDiary;
import com.cenmw.util.PageBean;

public class MemberDiaryManagerService {
	private MemberDiaryManagerDao memberDiaryManagerDao;

	public PageBean findMemberDiaryHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberDiaryManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberDiary(MemberDiary memberDiary) {
		boolean status = true;
		memberDiaryManagerDao.saveMemberDiary(memberDiary);
		memberDiaryManagerDao.updateMemberDiary(memberDiary);
		return status;
	}

	public List findMemberDiaryInList(int mid) {
		String hql = "from MemberDiary where isdel=0 and mid=" + mid
				+ " order by id";
		return memberDiaryManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberDiary(MemberDiary memberDiary) {
		boolean status = true;
		memberDiaryManagerDao.updateMemberDiary(memberDiary);
		return status;
	}

	public MemberDiary getMemberDiary(int id) {
		return memberDiaryManagerDao.getMemberDiaryById(id);
	}

	public void deleteMemberDiaryById(int id) {
		MemberDiary pb = getMemberDiary(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberDiaryManagerDao.saveMemberDiary(pb);
		}
	}

	public void deleteMemberDiaryByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberDiaryById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberDiaryManagerDao getMemberDiaryManagerDao() {
		return memberDiaryManagerDao;
	}

	public void setMemberDiaryManagerDao(MemberDiaryManagerDao memberDiaryManagerDao) {
		this.memberDiaryManagerDao = memberDiaryManagerDao;
	}


}
