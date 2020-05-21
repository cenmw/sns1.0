package com.cenmw.member.manager.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberInfoManagerDao;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.MD5;
import com.cenmw.util.PageBean;

public class MemberInfoManagerService {
	private MemberInfoManagerDao memberInfoManagerDao;

	public PageBean findMemberInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberInfoManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberInfo(MemberInfo memberInfo) {
		boolean status = true;
		memberInfoManagerDao.saveMemberInfo(memberInfo);
		return status;
	}

	public List findMemberInfoInList(int type) {
		String hql = "from MemberInfo where isdel=0 and type=" + type
				+ " order by id";
		return memberInfoManagerDao.getListForHql(hql, null);
	}

	public MemberInfo getMemberInfoByEmail(String email,int mid) {
		String hql = "from MemberInfo where isdel=0 and id !=" + mid
				+ " and email ='"+email+"' order by id";
		List list = memberInfoManagerDao.getListForHql(hql, null,1);
		if (list != null && !list.isEmpty()) {
			return (MemberInfo) list.get(0);
		}
		return null;
	}
	
	public MemberInfo getMemberInfoByMobile(String mobile,int mid) {
		String hql = "from MemberInfo where isdel=0 and id !=" + mid
				+ " and mobile ='"+mobile+"' order by id";
		List list = memberInfoManagerDao.getListForHql(hql, null,1);
		if (list != null && !list.isEmpty()) {
			return (MemberInfo) list.get(0);
		}
		return null;
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
		memberInfoManagerDao.updateMemberInfo(memberInfo);
		return status;
	}

	public MemberInfo getMemberInfo(int id) {
		return memberInfoManagerDao.getMemberInfoById(id);
	}

	public void deleteMemberInfoById(int id) {
		MemberInfo pb = getMemberInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberInfoManagerDao.saveMemberInfo(pb);
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

	public MemberInfoManagerDao getMemberInfoManagerDao() {
		return memberInfoManagerDao;
	}

	public void setMemberInfoManagerDao(
			MemberInfoManagerDao memberInfoManagerDao) {
		this.memberInfoManagerDao = memberInfoManagerDao;
	}

}
