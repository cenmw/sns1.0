package com.cenmw.consult.manager.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.cenmw.consult.manager.dao.ConsultReplyInfoManagerDao;
import com.cenmw.consult.po.ConsultReplyInfo;
import com.cenmw.member.manager.dao.MemberInfoManagerDao;
import com.cenmw.util.PageBean;

public class ConsultReplyInfoManagerService {
	private ConsultReplyInfoManagerDao consultReplyInfoManagerDao;
	private MemberInfoManagerDao memberInfoManagerDao;

	public PageBean findConsultReplyInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return consultReplyInfoManagerDao.findListHqlForPage(hql, orderstr,
				map, cpage, pageSize);
	}

	public boolean saveConsultReplyInfo(ConsultReplyInfo consultReplyInfo) {
		boolean status = true;
		consultReplyInfoManagerDao.saveConsultReplyInfo(consultReplyInfo);
		consultReplyInfoManagerDao.updateConsultReplyInfo(consultReplyInfo);
		return status;
	}

	public List findConsultReplyInfoInList(int cid) {
		List newlist = new ArrayList();
		String hql = "from ConsultReplyInfo where isdel=0 and cid=" + cid
				+ " order by isagree desc,sort asc";
		List list = consultReplyInfoManagerDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				ConsultReplyInfo cri = (ConsultReplyInfo) list.get(i);
				cri.setMemberInfo(memberInfoManagerDao.getMemberInfoById(cri
						.getMid()));
				newlist.add(cri);
			}
		}
		return newlist;
	}

	public int findConsultReplyInfoCount(int cid) {
		String hql = "from ConsultReplyInfo where isdel=0 and cid=" + cid;
		return consultReplyInfoManagerDao.findAllRow(hql);
	}

	public int findConsultReplyInfoIsgreeCount(int cid) {
		String hql = "from ConsultReplyInfo where isdel=0 and isagree=1 and cid="
				+ cid;
		return consultReplyInfoManagerDao.findAllRow(hql);
	}

	public boolean updateConsultReplyInfo(ConsultReplyInfo consultReplyInfo) {
		boolean status = true;
		consultReplyInfoManagerDao.updateConsultReplyInfo(consultReplyInfo);
		return status;
	}

	public ConsultReplyInfo getConsultReplyInfo(int id) {
		return consultReplyInfoManagerDao.getConsultReplyInfoById(id);
	}

	public void deleteConsultReplyInfoById(int id) {
		ConsultReplyInfo pb = getConsultReplyInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			consultReplyInfoManagerDao.saveConsultReplyInfo(pb);
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

	public ConsultReplyInfoManagerDao getConsultReplyInfoManagerDao() {
		return consultReplyInfoManagerDao;
	}

	public void setConsultReplyInfoManagerDao(
			ConsultReplyInfoManagerDao consultReplyInfoManagerDao) {
		this.consultReplyInfoManagerDao = consultReplyInfoManagerDao;
	}

	public MemberInfoManagerDao getMemberInfoManagerDao() {
		return memberInfoManagerDao;
	}

	public void setMemberInfoManagerDao(
			MemberInfoManagerDao memberInfoManagerDao) {
		this.memberInfoManagerDao = memberInfoManagerDao;
	}

}
