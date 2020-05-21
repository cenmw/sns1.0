package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberMessageCenterDao;
import com.cenmw.member.po.MemberMessage;
import com.cenmw.util.PageBean;

public class MemberMessageCenterService {
	private MemberMessageCenterDao memberMessageCenterDao;

	public PageBean findMemberMessageHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberMessageCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberMessage(MemberMessage memberMessage) {
		boolean status = true;
		memberMessageCenterDao.saveMemberMessage(memberMessage);
		memberMessageCenterDao.updateMemberMessage(memberMessage);
		return status;
	}

	public List findMemberMessageInList(int mid) {
		String hql = "from MemberMessage where isdel=0 and mid=" + mid
				+ " order by id";
		return memberMessageCenterDao.getListForHql(hql, null);
	}

	public int findMemberMessageCount(int mid) {
		String hql = "from MemberMessage where isdel=0 and isopen=0 and reviceid="
				+ mid;
		return memberMessageCenterDao.findAllRow(hql);
	}

	public List findMemberMessagerList(int mid, int reviceid) {
		String hql = "from MemberMessage where isdel=0 and (mid=" + mid
				+ " and reviceid=" + reviceid + ") or (mid=" + reviceid
				+ " and reviceid=" + mid + ") order by id";
		return memberMessageCenterDao.getListForHql(hql, null);
	}

	public boolean updateMemberMessage(MemberMessage memberMessage) {
		boolean status = true;
		memberMessageCenterDao.updateMemberMessage(memberMessage);
		return status;
	}

	public MemberMessage getMemberMessage(int id) {
		return memberMessageCenterDao.getMemberMessageById(id);
	}

	public void deleteMemberMessageById(int id) {
		MemberMessage pb = getMemberMessage(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberMessageCenterDao.saveMemberMessage(pb);
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

	public MemberMessageCenterDao getMemberMessageCenterDao() {
		return memberMessageCenterDao;
	}

	public void setMemberMessageCenterDao(
			MemberMessageCenterDao memberMessageCenterDao) {
		this.memberMessageCenterDao = memberMessageCenterDao;
	}

	public void updateMemberMessageZhou(int mid, int reviceid, int type) {
		String sql = "update member_message set isdel=1 where isdel=0 and mid="
				+ mid + " and reviceid=" + reviceid + " and type=" + type;
		memberMessageCenterDao.executeSql(sql);
	}

}
