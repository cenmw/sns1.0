package com.cenmw.member.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberReport;

public class MemberReportManagerDao extends BaseHibernateDao {

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
