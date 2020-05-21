package com.cenmw.member.center.service;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberReportCenterDao;
import com.cenmw.member.po.MemberReport;
import com.cenmw.util.DateUtil;
import com.cenmw.util.PageBean;

public class MemberReportCenterService {
	private MemberReportCenterDao memberReportCenterDao;

	public PageBean findMemberReportHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberReportCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberReport(MemberReport memberReport) {
		boolean status = true;
		memberReportCenterDao.saveMemberReport(memberReport);
		memberReportCenterDao.updateMemberReport(memberReport);
		return status;
	}

	public List findMemberReportInList(int mid) {
		String hql = "from MemberReport where isdel=0 and mid=" + mid
				+ " order by id";
		return memberReportCenterDao.getListForHql(hql, null);
	}

	public boolean getIsMemberReport(int mid) {
		boolean status = false;
		String hql = "from MemberReport where isdel=0 and starttime<'"
				+ DateUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss")
				+ "' and endtime>'"
				+ DateUtil.getFormatDate(new Date(),
						"yyyy-MM-dd HH:mm:ss") + "' and rid=" + mid;
		List list = memberReportCenterDao.getListForHql(hql, null, 1);
		if (list != null && !list.isEmpty()) {
			status = true;
		}
		return status;
	}

	public MemberReport getMemberReport(int mid, int cid, int rid) {
		MemberReport mp = null;
		String hql = "from MemberReport where isdel=0 and mid=" + mid
				+ " and cid=" + cid + " and rid=" + rid + " order by id";
		List list = memberReportCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberReport) list.get(0);
		}
		return mp;
	}

	public void updateMemberReportAll(int mid) {
		String sql = "update member_report set state=2,sftime=endtime where isdel=0 and state=1 and endtime<'"
				+ DateUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss")
				+ "' and rid=" + mid;
		memberReportCenterDao.executeSql(sql);
	}

	public boolean updateMemberReport(MemberReport memberReport) {
		boolean status = true;
		memberReportCenterDao.updateMemberReport(memberReport);
		return status;
	}

	public MemberReport getMemberReport(int id) {
		return memberReportCenterDao.getMemberReportById(id);
	}

	public void deleteMemberReportById(int id) {
		MemberReport pb = getMemberReport(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberReportCenterDao.saveMemberReport(pb);
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

	public MemberReportCenterDao getMemberReportCenterDao() {
		return memberReportCenterDao;
	}

	public void setMemberReportCenterDao(
			MemberReportCenterDao memberReportCenterDao) {
		this.memberReportCenterDao = memberReportCenterDao;
	}

}
