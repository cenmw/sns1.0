package com.cenmw.consult.front.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.cenmw.consult.front.dao.ConsultReplyInfoFrontDao;
import com.cenmw.consult.po.ConsultReplyInfo;
import com.cenmw.member.front.dao.MemberInfoFrontDao;
import com.cenmw.util.PageBean;

public class ConsultReplyInfoFrontService {
	private ConsultReplyInfoFrontDao consultReplyInfoFrontDao;
	private MemberInfoFrontDao memberInfoFrontDao;

	public PageBean findConsultReplyInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return consultReplyInfoFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveConsultReplyInfo(ConsultReplyInfo consultReplyInfo) {
		boolean status = true;
		consultReplyInfoFrontDao.saveConsultReplyInfo(consultReplyInfo);
		consultReplyInfoFrontDao.updateConsultReplyInfo(consultReplyInfo);
		return status;
	}

	public List findConsultReplyInfoInList(int cid) {
		List newlist = new ArrayList();
		String hql = "from ConsultReplyInfo where isdel=0 and cid=" + cid
				+ " order by isagree desc,sort asc";
		List list = consultReplyInfoFrontDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				ConsultReplyInfo cri = (ConsultReplyInfo) list.get(i);
				cri.setMemberInfo(memberInfoFrontDao.getMemberInfoById(cri
						.getMid()));
				newlist.add(cri);
			}
		}
		return newlist;
	}

	public int findConsultReplyInfoCount(int cid) {
		String hql = "from ConsultReplyInfo where isdel=0 and cid=" + cid;
		return consultReplyInfoFrontDao.findAllRow(hql);
	}

	public int findConsultReplyInfoIsgreeCount(int cid) {
		String hql = "from ConsultReplyInfo where isdel=0 and isagree=1 and cid="
				+ cid;
		return consultReplyInfoFrontDao.findAllRow(hql);
	}

	public boolean updateConsultReplyInfo(ConsultReplyInfo consultReplyInfo) {
		boolean status = true;
		consultReplyInfoFrontDao.updateConsultReplyInfo(consultReplyInfo);
		return status;
	}

	public ConsultReplyInfo getConsultReplyInfo(int id) {
		return consultReplyInfoFrontDao.getConsultReplyInfoById(id);
	}

	public void deleteConsultReplyInfoById(int id) {
		ConsultReplyInfo pb = getConsultReplyInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			consultReplyInfoFrontDao.saveConsultReplyInfo(pb);
		}
	}

	public void deleteConsultReplyInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteConsultReplyInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public ConsultReplyInfoFrontDao getConsultReplyInfoFrontDao() {
		return consultReplyInfoFrontDao;
	}

	public void setConsultReplyInfoFrontDao(
			ConsultReplyInfoFrontDao consultReplyInfoFrontDao) {
		this.consultReplyInfoFrontDao = consultReplyInfoFrontDao;
	}

	public MemberInfoFrontDao getMemberInfoFrontDao() {
		return memberInfoFrontDao;
	}

	public void setMemberInfoFrontDao(MemberInfoFrontDao memberInfoFrontDao) {
		this.memberInfoFrontDao = memberInfoFrontDao;
	}

}
