package com.cenmw.member.center.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.cenmw.integral.front.dao.IntegralInfoFrontDao;
import com.cenmw.member.center.dao.MemberInfoCenterDao;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.PageBean;

public class MemberInfoCenterService {
	private MemberInfoCenterDao memberInfoCenterDao;
	private IntegralInfoFrontDao integralInfoFrontDao;

	public PageBean findMemberInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberInfoCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberInfo(MemberInfo memberInfo) {
		boolean status = true;
		memberInfoCenterDao.saveMemberInfo(memberInfo);
		memberInfoCenterDao.updateMemberInfo(memberInfo);
		return status;
	}

	public List findMemberInfoInList(int type) {
		String hql = "from MemberInfo where isdel=0 and type=" + type
				+ " order by id";
		return memberInfoCenterDao.getListForHql(hql, null);
	}

	public int getMemberInfoByMobile(String mobile, int id) {
		String hql = "from MemberInfo where isdel=0 and id <>" + id
				+ " and mobile='" + mobile + "'";
		return memberInfoCenterDao.findAllRow(hql);
	}

	public int getMemberInfoByAccount(String account, int id) {
		String hql = "from MemberInfo where isdel=0 and id <>" + id
				+ " and account='" + account + "'";
		return memberInfoCenterDao.findAllRow(hql);
	}

	public List findMayMemberInfoInList(MemberInfo memberInfo, String mids,
			int top) {
		String hql = "from MemberInfo where isdel=0 and type=0 and id not in("
				+ mids + ") and a_province='" + memberInfo.getA_province()
				+ "' and a_city='" + memberInfo.getA_city()
				+ "' and a_county='" + memberInfo.getA_county()
				+ "' order by id";
		List list = memberInfoCenterDao.getListForHql(hql, null, top);
		if (list == null || list.isEmpty() || list.size() == 0) {
			String new_hql = "from MemberInfo where isdel=0 and type=0 and id not in("
					+ mids + ") order by id";
			list = memberInfoCenterDao.getListForHql(new_hql, null, top);
		} else if (list.size() < top) {
			for (int i = 0; i < list.size(); i++) {
				MemberInfo mi = (MemberInfo) list.get(i);
				mids += ","+mi.getId().intValue();
			}
			int newtop = top - list.size();
			String new_hql = "from MemberInfo where isdel=0 and type=0 and id not in("
					+ mids + ") order by id";
			List newlist = memberInfoCenterDao.getListForHql(new_hql, null,
					newtop);
			if (newlist != null && !newlist.isEmpty()) {
				for (int i = 0; i < newlist.size(); i++) {
					MemberInfo mf = (MemberInfo) newlist.get(i);
					list.add(mf);
				}
			}
		}
		return list;
	}

	public List findOnlineMemberInfoList(int type, String mids, int top) {
		String hql = "from MemberInfo where isdel=0 and type=" + type
				+ " and id not in(" + mids + ") order by lastlogintime desc";
		return memberInfoCenterDao.getListForHql(hql, null, top);
	}
	
	public List findOnlineFMemberInfoList(int type, String mids, int top) {
		String hql = "from MemberInfo where isdel=0 and type=" + type
				+ " and id in(" + mids + ") order by lastlogintime desc";
		return memberInfoCenterDao.getListForHql(hql, null, top);
	}

	public String findMemberInfos(List list, int mid) {
		String mids = "";
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				MemberInfo mf = (MemberInfo) list.get(i);
				int fid = mf.getId().intValue();
				if (mids.length() == 0) {
					mids += fid;
				} else {
					mids += "," + mids;
				}
			}
		}
		if (mids.length() == 0) {
			mids += mid;
		} else {
			mids += "," + mid;
		}
		return mids;
	}

	public List findMemberInfoList(String mids) {
		List list = null;
		if (mids != null && mids.length() > 0) {
			list = new ArrayList();
			String[] midarrs = mids.split(",");
			for (int i = 0; i < midarrs.length; i++) {
				int mid = new Integer(midarrs[i].replaceAll("-", ""));
				MemberInfo minfo = getMemberInfo(mid);
				list.add(minfo);
			}
		}
		return list;
	}

	public boolean updateMemberInfo(MemberInfo memberInfo) {
		boolean status = true;
		memberInfoCenterDao.updateMemberInfo(memberInfo);
		return status;
	}

	public MemberInfo getMemberInfo(int id) {
		MemberInfo memberInfo = null;
		memberInfo = memberInfoCenterDao.getMemberInfoById(id);
		if (memberInfo != null) {
			memberInfo.setSumscore(integralInfoFrontDao.getIntegralByMid(id));
		}
		return memberInfo;
	}

	public void deleteMemberInfoById(int id) {
		MemberInfo pb = getMemberInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberInfoCenterDao.saveMemberInfo(pb);
		}
	}

	public void deleteMemberInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberInfoCenterDao getMemberInfoCenterDao() {
		return memberInfoCenterDao;
	}

	public void setMemberInfoCenterDao(MemberInfoCenterDao memberInfoCenterDao) {
		this.memberInfoCenterDao = memberInfoCenterDao;
	}

	public IntegralInfoFrontDao getIntegralInfoFrontDao() {
		return integralInfoFrontDao;
	}

	public void setIntegralInfoFrontDao(
			IntegralInfoFrontDao integralInfoFrontDao) {
		this.integralInfoFrontDao = integralInfoFrontDao;
	}

}
