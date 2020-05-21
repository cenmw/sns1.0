package com.cenmw.member.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.front.dao.MemberMessageFrontDao;
import com.cenmw.member.po.MemberMessage;
import com.cenmw.util.PageBean;

public class MemberMessageFrontService {
	private MemberMessageFrontDao memberMessageFrontDao;

	public PageBean findMemberMessageHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberMessageFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberMessage(MemberMessage memberMessage) {
		boolean status = true;
		memberMessageFrontDao.saveMemberMessage(memberMessage);
		memberMessageFrontDao.updateMemberMessage(memberMessage);
		return status;
	}

	public List findMemberMessageInList(int mid) {
		String hql = "from MemberMessage where isdel=0 and mid=" + mid
				+ " order by id";
		return memberMessageFrontDao.getListForHql(hql, null);
	}

	public boolean updateMemberMessage(MemberMessage memberMessage) {
		boolean status = true;
		memberMessageFrontDao.updateMemberMessage(memberMessage);
		return status;
	}

	public MemberMessage getMemberMessage(int id) {
		return memberMessageFrontDao.getMemberMessageById(id);
	}

	public void deleteMemberMessageById(int id) {
		MemberMessage pb = getMemberMessage(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberMessageFrontDao.saveMemberMessage(pb);
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

	public MemberMessageFrontDao getMemberMessageFrontDao() {
		return memberMessageFrontDao;
	}

	public void setMemberMessageFrontDao(MemberMessageFrontDao memberMessageFrontDao) {
		this.memberMessageFrontDao = memberMessageFrontDao;
	}


}
