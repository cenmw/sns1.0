package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberFriendManagerDao;
import com.cenmw.member.po.MemberFriend;
import com.cenmw.util.PageBean;

public class MemberFriendManagerService {
	private MemberFriendManagerDao memberFriendManagerDao;

	public PageBean findMemberFriendHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberFriendManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberFriend(MemberFriend memberFriend) {
		boolean status = true;
		memberFriendManagerDao.saveMemberFriend(memberFriend);
		memberFriendManagerDao.updateMemberFriend(memberFriend);
		return status;
	}

	public List findMemberFriendInList(int mid) {
		String hql = "from MemberFriend where isdel=0 and mid=" + mid
				+ " order by id";
		return memberFriendManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberFriend(MemberFriend memberFriend) {
		boolean status = true;
		memberFriendManagerDao.updateMemberFriend(memberFriend);
		return status;
	}

	public MemberFriend getMemberFriend(int id) {
		return memberFriendManagerDao.getMemberFriendById(id);
	}

	public void deleteMemberFriendById(int id) {
		MemberFriend pb = getMemberFriend(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberFriendManagerDao.saveMemberFriend(pb);
		}
	}

	public void deleteMemberFriendByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberFriendById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberFriendManagerDao getMemberFriendManagerDao() {
		return memberFriendManagerDao;
	}

	public void setMemberFriendManagerDao(MemberFriendManagerDao memberFriendManagerDao) {
		this.memberFriendManagerDao = memberFriendManagerDao;
	}


}
