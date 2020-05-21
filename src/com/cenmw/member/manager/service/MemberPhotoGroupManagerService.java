package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberPhotoGroupManagerDao;
import com.cenmw.member.po.MemberPhotoGroup;
import com.cenmw.util.PageBean;

public class MemberPhotoGroupManagerService {
	private MemberPhotoGroupManagerDao memberPhotoGroupManagerDao;

	public PageBean findMemberPhotoGroupHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberPhotoGroupManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		boolean status = true;
		memberPhotoGroupManagerDao.saveMemberPhotoGroup(memberPhotoGroup);
		memberPhotoGroupManagerDao.updateMemberPhotoGroup(memberPhotoGroup);
		return status;
	}

	public List findMemberPhotoGroupInList(int mid) {
		String hql = "from MemberPhotoGroup where isdel=0 and mid=" + mid
				+ " order by id";
		return memberPhotoGroupManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		boolean status = true;
		memberPhotoGroupManagerDao.updateMemberPhotoGroup(memberPhotoGroup);
		return status;
	}

	public MemberPhotoGroup getMemberPhotoGroup(int id) {
		return memberPhotoGroupManagerDao.getMemberPhotoGroupById(id);
	}

	public void deleteMemberPhotoGroupById(int id) {
		MemberPhotoGroup pb = getMemberPhotoGroup(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberPhotoGroupManagerDao.saveMemberPhotoGroup(pb);
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

	public MemberPhotoGroupManagerDao getMemberPhotoGroupManagerDao() {
		return memberPhotoGroupManagerDao;
	}

	public void setMemberPhotoGroupManagerDao(MemberPhotoGroupManagerDao memberPhotoGroupManagerDao) {
		this.memberPhotoGroupManagerDao = memberPhotoGroupManagerDao;
	}


}
