package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberPhotoGroupCenterDao;
import com.cenmw.member.po.MemberPhotoGroup;
import com.cenmw.util.PageBean;

public class MemberPhotoGroupCenterService {
	private MemberPhotoGroupCenterDao memberPhotoGroupCenterDao;

	public PageBean findMemberPhotoGroupHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberPhotoGroupCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		boolean status = true;
		memberPhotoGroupCenterDao.saveMemberPhotoGroup(memberPhotoGroup);
		memberPhotoGroupCenterDao.updateMemberPhotoGroup(memberPhotoGroup);
		return status;
	}

	public List findMemberPhotoGroupInList(int mid) {
		String hql = "from MemberPhotoGroup where isdel=0 and mid=" + mid
				+ " order by id";
		return memberPhotoGroupCenterDao.getListForHql(hql, null);
	}

	public boolean updateMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		boolean status = true;
		memberPhotoGroupCenterDao.updateMemberPhotoGroup(memberPhotoGroup);
		return status;
	}

	public MemberPhotoGroup getMemberPhotoGroup(int id) {
		return memberPhotoGroupCenterDao.getMemberPhotoGroupById(id);
	}

	public void deleteMemberPhotoGroupById(int id) {
		MemberPhotoGroup pb = getMemberPhotoGroup(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberPhotoGroupCenterDao.saveMemberPhotoGroup(pb);
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

	public MemberPhotoGroupCenterDao getMemberPhotoGroupCenterDao() {
		return memberPhotoGroupCenterDao;
	}

	public void setMemberPhotoGroupCenterDao(MemberPhotoGroupCenterDao memberPhotoGroupCenterDao) {
		this.memberPhotoGroupCenterDao = memberPhotoGroupCenterDao;
	}


}
