package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberMessageManagerDao;
import com.cenmw.member.po.MemberMessage;
import com.cenmw.util.PageBean;

public class MemberMessageManagerService {
	private MemberMessageManagerDao memberMessageManagerDao;

	public PageBean findMemberMessageHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberMessageManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberMessage(MemberMessage memberMessage) {
		boolean status = true;
		memberMessageManagerDao.saveMemberMessage(memberMessage);
		memberMessageManagerDao.updateMemberMessage(memberMessage);
		return status;
	}

	public List findMemberMessageInList(int mid) {
		String hql = "from MemberMessage where isdel=0 and mid=" + mid
				+ " order by id";
		return memberMessageManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberMessage(MemberMessage memberMessage) {
		boolean status = true;
		memberMessageManagerDao.updateMemberMessage(memberMessage);
		return status;
	}

	public MemberMessage getMemberMessage(int id) {
		return memberMessageManagerDao.getMemberMessageById(id);
	}

	public void deleteMemberMessageById(int id) {
		MemberMessage pb = getMemberMessage(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberMessageManagerDao.saveMemberMessage(pb);
		}
	}

	public void deleteMemberMessageByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberMessageById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberMessageManagerDao getMemberMessageManagerDao() {
		return memberMessageManagerDao;
	}

	public void setMemberMessageManagerDao(MemberMessageManagerDao memberMessageManagerDao) {
		this.memberMessageManagerDao = memberMessageManagerDao;
	}


}
