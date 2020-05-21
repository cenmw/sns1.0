package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberDiary52ContentCenterDao;
import com.cenmw.member.po.MemberDiary52Content;
import com.cenmw.util.PageBean;

public class MemberDiary52ContentCenterService {
	private MemberDiary52ContentCenterDao memberDiary52ContentCenterDao;

	public PageBean findMemberDiary52ContentHQLList(final String hql, 
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberDiary52ContentCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberDiary52Content(MemberDiary52Content memberDiary52Content) {
		boolean status = true;
		memberDiary52ContentCenterDao.saveMemberDiary52Content(memberDiary52Content);
		memberDiary52ContentCenterDao.updateMemberDiary52Content(memberDiary52Content);
		return status;
	}

	public List<MemberDiary52Content> findMemberDiary52ContentInList(int day,int zposition) {
		String hql = "from MemberDiary52Content where isdel=0 and zbegin<=" + day + " and zend>=" + day
				+ " and zposition="+zposition+" order by sort asc, id asc";
		return memberDiary52ContentCenterDao.getListForHql(hql, null);
	}

	public List findHotZTMemberDiary52ContentList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberDiary52Content where isdel=0 order by sort asc,id asc";
		return memberDiary52ContentCenterDao.getListForHql(hql, null, top);
	}

	public List findHotVIEWMemberDiary52ContentList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberDiary52Content where isdel=0 order by viewnumber desc,id asc";
		return memberDiary52ContentCenterDao.getListForHql(hql, null, top);
	}
	
	public boolean updateMemberDiary52Content(MemberDiary52Content memberDiary52Content) {
		boolean status = true;
		memberDiary52ContentCenterDao.updateMemberDiary52Content(memberDiary52Content);
		return status;
	}

	public MemberDiary52Content getMemberDiary52Content(int id) {
		return memberDiary52ContentCenterDao.getMemberDiary52ContentById(id);
	}

	public MemberDiary52Content getMemberDiary52Content(int mid, int cid) {
		MemberDiary52Content mp = null;
		String hql = "from MemberDiary52Content where isdel=0 and mid=" + mid
				+ " and rcid=" + cid + " order by id";
		List list = memberDiary52ContentCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberDiary52Content) list.get(0);
		}
		return mp;
	}
	
	public MemberDiary52Content getFirstMemberDiary52Content(int mid, int type) {
		MemberDiary52Content mp = null;
		String hql = "from MemberDiary52Content where isdel=0 and mid=" + mid + " and type=" + type + " order by id";
		List list = memberDiary52ContentCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberDiary52Content) list.get(0);
		}
		return mp;
	}
	
	public void deleteMemberDiary52ContentById(int id) {
		MemberDiary52Content pb = getMemberDiary52Content(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberDiary52ContentCenterDao.saveMemberDiary52Content(pb);
		}
	}

	public void deleteMemberDiary52ContentByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberDiary52ContentById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberDiary52ContentCenterDao getMemberDiary52ContentCenterDao() {
		return memberDiary52ContentCenterDao;
	}

	public void setMemberDiary52ContentCenterDao(MemberDiary52ContentCenterDao memberDiary52ContentCenterDao) {
		this.memberDiary52ContentCenterDao = memberDiary52ContentCenterDao;
	}


}
