package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberDiaryCenterDao;
import com.cenmw.member.po.MemberDiary;
import com.cenmw.util.PageBean;

public class MemberDiaryCenterService {
	private MemberDiaryCenterDao memberDiaryCenterDao;

	public PageBean findMemberDiaryHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberDiaryCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberDiary(MemberDiary memberDiary) {
		boolean status = true;
		memberDiaryCenterDao.saveMemberDiary(memberDiary);
		memberDiaryCenterDao.updateMemberDiary(memberDiary);
		return status;
	}

	public List findMemberDiaryInList(int mid) {
		String hql = "from MemberDiary where isdel=0 and mid=" + mid
				+ " order by id";
		return memberDiaryCenterDao.getListForHql(hql, null);
	}

	public int getMemberDiaryInListNumber(int cid) {
		String hql = "from MemberDiary where isdel=0 and rcid=" + cid;
		return memberDiaryCenterDao.findAllRow(hql);
	}
	
	public List findHotZTMemberDiaryList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberDiary where isdel=0 order by rcnumber desc,id asc";
		return memberDiaryCenterDao.getListForHql(hql, null, top);
	}

	public List findHotVIEWMemberDiaryList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberDiary where isdel=0 order by viewnumber desc,id asc";
		return memberDiaryCenterDao.getListForHql(hql, null, top);
	}
	
	public boolean updateMemberDiary(MemberDiary memberDiary) {
		boolean status = true;
		memberDiaryCenterDao.updateMemberDiary(memberDiary);
		return status;
	}

	public MemberDiary getMemberDiary(int id) {
		return memberDiaryCenterDao.getMemberDiaryById(id);
	}

	public MemberDiary getMemberDiary(int mid, int cid) {
		MemberDiary mp = null;
		String hql = "from MemberDiary where isdel=0 and mid=" + mid
				+ " and rcid=" + cid + " order by id";
		List list = memberDiaryCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberDiary) list.get(0);
		}
		return mp;
	}
	
	public void deleteMemberDiaryById(int id) {
		MemberDiary pb = getMemberDiary(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberDiaryCenterDao.saveMemberDiary(pb);
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

	public MemberDiaryCenterDao getMemberDiaryCenterDao() {
		return memberDiaryCenterDao;
	}

	public void setMemberDiaryCenterDao(MemberDiaryCenterDao memberDiaryCenterDao) {
		this.memberDiaryCenterDao = memberDiaryCenterDao;
	}


}
