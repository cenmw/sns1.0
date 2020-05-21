package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberPhotoManagerDao;
import com.cenmw.member.po.MemberPhoto;
import com.cenmw.util.PageBean;

public class MemberPhotoManagerService {
	private MemberPhotoManagerDao memberPhotoManagerDao;

	public PageBean findMemberPhotoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberPhotoManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberPhoto(MemberPhoto memberPhoto) {
		boolean status = true;
		memberPhotoManagerDao.saveMemberPhoto(memberPhoto);
		memberPhotoManagerDao.updateMemberPhoto(memberPhoto);
		return status;
	}

	public List findMemberPhotoInList(int mid) {
		String hql = "from MemberPhoto where isdel=0 and mid=" + mid
				+ " order by id";
		return memberPhotoManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberPhoto(MemberPhoto memberPhoto) {
		boolean status = true;
		memberPhotoManagerDao.updateMemberPhoto(memberPhoto);
		return status;
	}

	public MemberPhoto getMemberPhoto(int id) {
		return memberPhotoManagerDao.getMemberPhotoById(id);
	}

	public void deleteMemberPhotoById(int id) {
		MemberPhoto pb = getMemberPhoto(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberPhotoManagerDao.saveMemberPhoto(pb);
		}
	}

	public void deleteMemberPhotoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberPhotoById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberPhotoManagerDao getMemberPhotoManagerDao() {
		return memberPhotoManagerDao;
	}

	public void setMemberPhotoManagerDao(MemberPhotoManagerDao memberPhotoManagerDao) {
		this.memberPhotoManagerDao = memberPhotoManagerDao;
	}


}
