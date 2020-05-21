package com.cenmw.member.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.front.dao.MemberPhotoGroupFrontDao;
import com.cenmw.member.po.MemberPhotoGroup;
import com.cenmw.util.PageBean;

public class MemberPhotoGroupFrontService {
	private MemberPhotoGroupFrontDao memberPhotoGroupFrontDao;

	public PageBean findMemberPhotoGroupHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberPhotoGroupFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		boolean status = true;
		memberPhotoGroupFrontDao.saveMemberPhotoGroup(memberPhotoGroup);
		memberPhotoGroupFrontDao.updateMemberPhotoGroup(memberPhotoGroup);
		return status;
	}

	public List findMemberPhotoGroupInList(int mid) {
		String hql = "from MemberPhotoGroup where isdel=0 and mid=" + mid
				+ " order by id";
		return memberPhotoGroupFrontDao.getListForHql(hql, null);
	}

	public boolean updateMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		boolean status = true;
		memberPhotoGroupFrontDao.updateMemberPhotoGroup(memberPhotoGroup);
		return status;
	}

	public MemberPhotoGroup getMemberPhotoGroup(int id) {
		return memberPhotoGroupFrontDao.getMemberPhotoGroupById(id);
	}

	public void deleteMemberPhotoGroupById(int id) {
		MemberPhotoGroup pb = getMemberPhotoGroup(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberPhotoGroupFrontDao.saveMemberPhotoGroup(pb);
		}
	}

	public void deleteMemberPhotoGroupByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberPhotoGroupById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberPhotoGroupFrontDao getMemberPhotoGroupFrontDao() {
		return memberPhotoGroupFrontDao;
	}

	public void setMemberPhotoGroupFrontDao(MemberPhotoGroupFrontDao memberPhotoGroupFrontDao) {
		this.memberPhotoGroupFrontDao = memberPhotoGroupFrontDao;
	}


}
