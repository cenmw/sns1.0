package com.cenmw.member.center.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberReport;

public class MemberReportCenterDao extends BaseHibernateDao {

	public void saveMemberReport(MemberReport memberReport) {
		save(memberReport);
	}

	public void deleteMemberReport(MemberReport memberReport) {
		delete(memberReport);
	}

	public MemberReport getMemberReportById(int id) {
		return (MemberReport) findObjectById(MemberReport.class, id);
	}

	public void updateMemberReport(MemberReport memberReport) {
		updateObject(memberReport);
	}

}
