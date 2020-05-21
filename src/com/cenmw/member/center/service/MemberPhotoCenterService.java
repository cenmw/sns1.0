package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberPhotoCenterDao;
import com.cenmw.member.po.MemberPhoto;
import com.cenmw.util.PageBean;

public class MemberPhotoCenterService {
	private MemberPhotoCenterDao memberPhotoCenterDao;

	public PageBean findMemberPhotoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberPhotoCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberPhoto(MemberPhoto memberPhoto) {
		boolean status = true;
		memberPhotoCenterDao.saveMemberPhoto(memberPhoto);
		memberPhotoCenterDao.updateMemberPhoto(memberPhoto);
		return status;
	}

	public List findMemberPhotoInList(int mid) {
		String hql = "from MemberPhoto where isdel=0 and mid=" + mid
				+ " order by id";
		return memberPhotoCenterDao.getListForHql(hql, null);
	}

	public int findMemberPhotoCount(int cid) {
		String hql = "from MemberPhoto where isdel=0 and cid=" + cid;
		return memberPhotoCenterDao.findAllRow(hql);
	}

	public String findMemberPhoto(int cid) {
		String picpath = "/member/images/common/no_photo.png";
		String hql = "from MemberPhoto where isdel=0 and cid=" + cid
				+ " order by isindex desc";
		List list = memberPhotoCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			MemberPhoto memberPhoto = (MemberPhoto) list.get(0);
			picpath = memberPhoto.getPicpath();
		}
		return picpath;
	}

	public void updateMemberPhotos(int cid) {
		String hql = "update MemberPhoto set isindex=0 where isdel=0 and cid="
				+ cid;
		memberPhotoCenterDao.executeHql(hql);
	}

	public MemberPhoto findLastMemberPhoto(int cid, int id) {
		MemberPhoto memberPhoto = null;
		String hql = "from MemberPhoto where isdel=0 and cid=" + cid
				+ " and id >" + id + " order by id";
		List list = memberPhotoCenterDao.getListForHql(hql, null, 1);
		if (list != null && list.size() > 0) {
			memberPhoto = (MemberPhoto) list.get(0);
		}
		return memberPhoto;
	}

	public MemberPhoto findNextMemberPhoto(int cid, int id) {
		MemberPhoto memberPhoto = null;
		String hql = "from MemberPhoto where isdel=0 and cid=" + cid
				+ " and id <" + id + " order by id desc";
		List list = memberPhotoCenterDao.getListForHql(hql, null, 1);
		if (list != null && list.size() > 0) {
			memberPhoto = (MemberPhoto) list.get(0);
		}
		return memberPhoto;
	}

	public boolean updateMemberPhoto(MemberPhoto memberPhoto) {
		boolean status = true;
		memberPhotoCenterDao.updateMemberPhoto(memberPhoto);
		return status;
	}

	public MemberPhoto getMemberPhoto(int id) {
		return memberPhotoCenterDao.getMemberPhotoById(id);
	}

	public void deleteMemberPhotoById(int id) {
		MemberPhoto pb = getMemberPhoto(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberPhotoCenterDao.saveMemberPhoto(pb);
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

	public MemberPhotoCenterDao getMemberPhotoCenterDao() {
		return memberPhotoCenterDao;
	}

	public void setMemberPhotoCenterDao(
			MemberPhotoCenterDao memberPhotoCenterDao) {
		this.memberPhotoCenterDao = memberPhotoCenterDao;
	}

}
