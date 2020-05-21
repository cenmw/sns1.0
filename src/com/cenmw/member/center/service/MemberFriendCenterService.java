package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberFriendCenterDao;
import com.cenmw.member.po.MemberFriend;
import com.cenmw.util.PageBean;

public class MemberFriendCenterService {
	private MemberFriendCenterDao memberFriendCenterDao;

	public PageBean findMemberFriendHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberFriendCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberFriend(MemberFriend memberFriend) {
		boolean status = true;
		memberFriendCenterDao.saveMemberFriend(memberFriend);
		memberFriendCenterDao.updateMemberFriend(memberFriend);
		return status;
	}

	public List findMemberFriendInList(int mid) {
		String hql = "from MemberFriend where isdel=0 and isagree=1 and (mid="
				+ mid + " or fid=" + mid + " )" + " order by id";
		return memberFriendCenterDao.getListForHql(hql, null);
	}

	public MemberFriend findMemberFriendInList(int mid, int fid) {
		MemberFriend mf = null;
		String hql = "from MemberFriend where isdel=0 and isagree=1 and (mid="
				+ mid + " and fid=" + fid + " ) or (mid=" + fid + " and fid="
				+ mid + " )" + " order by id";
		List list = memberFriendCenterDao.getListForHql(hql, null, 1);
		if (list != null && !list.isEmpty() && list.size() > 0) {
			mf = (MemberFriend) list.get(0);
		}
		return mf;
	}

	public String findMemberFriends(int mid) {
		String hql = "from MemberFriend where isdel=0 and type=0 and isagree=1 and (mid="
				+ mid + " or fid=" + mid + " )" + " order by id";
		List list = memberFriendCenterDao.getListForHql(hql, null);
		String fids = "";
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				MemberFriend mf = (MemberFriend) list.get(i);
				int fid = mf.getMid().intValue();
				if (mf.getMid().intValue() == mid) {
					fid = mf.getFid().intValue();
				}
				if (fids.length() == 0) {
					fids += fid;
				} else {
					fids += "," + fid;
				}
			}
		}
		return fids;
	}

	public boolean findMemberFriends(int mid, int fid) {
		int m = 0;
		String hql0 = "from MemberFriend where isdel=0 and type=0 and isagree=1 and mid="
				+ mid + " and fid=" + fid + "  order by id";
		String hql1 = "from MemberFriend where isdel=0 and type=0 and isagree=1 and mid="
				+ fid + " and fid=" + mid + "  order by id";
		List list0 = memberFriendCenterDao.getListForHql(hql0, null, 1);
		List list1 = memberFriendCenterDao.getListForHql(hql1, null, 1);
		if (list0 != null && !list0.isEmpty()) {
			m++;
		}
		if (list1 != null && !list1.isEmpty()) {
			m++;
		}
		if (m > 0) {
			return true;
		} else {
			return false;
		}
	}

	public String findMemberFriendsSH(int mid) {
		String hql = "from MemberFriend where isdel=0 and type=0 and isagree<=1 and (mid="
				+ mid + " or fid=" + mid + " )" + " order by id";
		List list = memberFriendCenterDao.getListForHql(hql, null);
		String fids = "";
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				MemberFriend mf = (MemberFriend) list.get(i);
				int fid = mf.getMid().intValue();
				if (mf.getMid().intValue() == mid) {
					fid = mf.getFid().intValue();
				}
				if (fids.length() == 0) {
					fids += fid;
				} else {
					if (("," + fids).indexOf("," + fid + ",") < 0) {
						fids += "," + fid;
					}
				}
			}
		}
		if (fids.length() == 0) {
			fids += mid;
		} else {
			fids += "," + mid;
		}
		return fids;
	}

	public String findMemberAllCFriends(int mid, int type) {
		String hql = "from MemberFriend where isdel=0 and type=1 and isagree=1 and mid="
				+ mid + " order by id";
		if (type == 1) {
			hql = "from MemberFriend where isdel=0 and type=1 and isagree=1 and fid="
					+ mid + " order by id";
		}
		List list = memberFriendCenterDao.getListForHql(hql, null);
		String fids = "";
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				MemberFriend mf = (MemberFriend) list.get(i);
				int fid = mf.getMid().intValue();
				if (mf.getMid().intValue() == mid) {
					fid = mf.getFid().intValue();
				}
				if (fids.length() == 0) {
					fids += fid;
				} else {
					fids += "," + fid;
				}
			}
		}
		return fids;
	}

	// 申请好友个数
	public int findFriendMessageCount(int mid) {
		String hql = "from MemberFriend where isdel=0 and type=0 and isagree=0 and fid="
				+ mid;
		return memberFriendCenterDao.findAllRow(hql);
	}

	// 已经是好友个数 普通好友
	public int findMemberFriendsCount(int mid) {
		String hql = "from MemberFriend where isdel=0 and type=0 and isagree=1 and (mid="
				+ mid + " or fid=" + mid + " )";
		return memberFriendCenterDao.findAllRow(hql);
	}

	// 已经是好友个数 机构好友
	public int findMemberCFriendsCount(int mid) {
		String hql = "from MemberFriend where isdel=0 and type=1 and isagree=1 and (mid="
				+ mid + " or fid=" + mid + " )";
		return memberFriendCenterDao.findAllRow(hql);
	}

	public boolean updateMemberFriend(MemberFriend memberFriend) {
		boolean status = true;
		memberFriendCenterDao.updateMemberFriend(memberFriend);
		return status;
	}

	public MemberFriend getMemberFriend(int id) {
		return memberFriendCenterDao.getMemberFriendById(id);
	}

	public void deleteMemberFriendById(int id) {
		MemberFriend pb = getMemberFriend(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberFriendCenterDao.saveMemberFriend(pb);
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

	public MemberFriendCenterDao getMemberFriendCenterDao() {
		return memberFriendCenterDao;
	}

	public void setMemberFriendCenterDao(
			MemberFriendCenterDao memberFriendCenterDao) {
		this.memberFriendCenterDao = memberFriendCenterDao;
	}

}
