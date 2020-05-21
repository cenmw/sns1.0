package com.cenmw.member.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.front.dao.MemberPhotoFrontDao;
import com.cenmw.member.po.MemberPhoto;
import com.cenmw.util.PageBean;

public class MemberPhotoFrontService {
	private MemberPhotoFrontDao memberPhotoFrontDao;

	public PageBean findMemberPhotoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberPhotoFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberPhoto(MemberPhoto memberPhoto) {
		boolean status = true;
		memberPhotoFrontDao.saveMemberPhoto(memberPhoto);
		memberPhotoFrontDao.updateMemberPhoto(memberPhoto);
		return status;
	}

	public List findMemberPhotoInList(int mid) {
		String hql = "from MemberPhoto where isdel=0 and mid=" + mid
				+ " order by id";
		return memberPhotoFrontDao.getListForHql(hql, null);
	}

	public boolean updateMemberPhoto(MemberPhoto memberPhoto) {
		boolean status = true;
		memberPhotoFrontDao.updateMemberPhoto(memberPhoto);
		return status;
	}

	public MemberPhoto getMemberPhoto(int id) {
		return memberPhotoFrontDao.getMemberPhotoById(id);
	}

	public void deleteMemberPhotoById(int id) {
		MemberPhoto pb = getMemberPhoto(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberPhotoFrontDao.saveMemberPhoto(pb);
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

	public MemberPhotoFrontDao getMemberPhotoFrontDao() {
		return memberPhotoFrontDao;
	}

	public void setMemberPhotoFrontDao(MemberPhotoFrontDao memberPhotoFrontDao) {
		this.memberPhotoFrontDao = memberPhotoFrontDao;
	}


}
