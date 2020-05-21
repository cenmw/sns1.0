package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberReportManagerDao;
import com.cenmw.member.po.MemberReport;
import com.cenmw.util.PageBean;

public class MemberReportManagerService {
	private MemberReportManagerDao memberReportManagerDao;

	public PageBean findMemberReportHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberReportManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberReport(MemberReport memberReport) {
		boolean status = true;
		memberReportManagerDao.saveMemberReport(memberReport);
		memberReportManagerDao.updateMemberReport(memberReport);
		return status;
	}

	public List findMemberReportInList(int mid) {
		String hql = "from MemberReport where isdel=0 and mid=" + mid
				+ " order by id";
		return memberReportManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberReport(MemberReport memberReport) {
		boolean status = true;
		memberReportManagerDao.updateMemberReport(memberReport);
		return status;
	}

	public MemberReport getMemberReport(int id) {
		return memberReportManagerDao.getMemberReportById(id);
	}

	public void deleteMemberReportById(int id) {
		MemberReport pb = getMemberReport(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberReportManagerDao.saveMemberReport(pb);
		}
	}

	public void deleteMemberReportByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberReportById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberReportManagerDao getMemberReportManagerDao() {
		return memberReportManagerDao;
	}

	public void setMemberReportManagerDao(MemberReportManagerDao memberReportManagerDao) {
		this.memberReportManagerDao = memberReportManagerDao;
	}


}
