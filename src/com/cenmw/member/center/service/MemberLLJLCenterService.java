package com.cenmw.member.center.service;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberLLJLCenterDao;
import com.cenmw.member.po.MemberLLJL;
import com.cenmw.util.PageBean;

public class MemberLLJLCenterService {
	private MemberLLJLCenterDao memberLLJLCenterDao;

	public PageBean findMemberLLJLHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberLLJLCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberLLJL(MemberLLJL memberLLJL) {
		boolean status = true;
		memberLLJLCenterDao.saveMemberLLJL(memberLLJL);
		memberLLJLCenterDao.updateMemberLLJL(memberLLJL);
		return status;
	}
	
	public void saveMemberLLJL(int mid,String title,String url) {
		MemberLLJL memberLLJL = findMemberLLJLList(url);
		if(memberLLJL == null){
			memberLLJL = new MemberLLJL();
		}
		memberLLJL.setMid(mid);
		memberLLJL.setTitle(title);
		memberLLJL.setUrl(url);
		memberLLJL.setIsdel(new Integer(0));
		memberLLJL.setCtime(new Date());
		memberLLJLCenterDao.saveMemberLLJL(memberLLJL);
	}

	public List findMemberLLJLInList(int mid) {
		String hql = "from MemberLLJL where isdel=0 and mid=" + mid
				+ " order by id";
		return memberLLJLCenterDao.getListForHql(hql, null);
	}

	public MemberLLJL findMemberLLJLList(String url) {
		MemberLLJL memberLLJL = null;
		String hql = "from MemberLLJL where isdel=0 and url='"+url+"' order by id asc";
		List<MemberLLJL> list = memberLLJLCenterDao.getListForHql(hql, null, 1);
		if(list!=null && !list.isEmpty()){
			memberLLJL = list.get(0);
		}
		return memberLLJL;
	}

	public List findHotVIEWMemberLLJLList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberLLJL where isdel=0 order by id asc";
		return memberLLJLCenterDao.getListForHql(hql, null, top);
	}
	
	public boolean updateMemberLLJL(MemberLLJL memberLLJL) {
		boolean status = true;
		memberLLJLCenterDao.updateMemberLLJL(memberLLJL);
		return status;
	}

	public MemberLLJL getMemberLLJL(int id) {
		return memberLLJLCenterDao.getMemberLLJLById(id);
	}

	public void deleteMemberLLJLById(int id) {
		MemberLLJL pb = getMemberLLJL(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberLLJLCenterDao.saveMemberLLJL(pb);
		}
	}

	public void deleteMemberLLJLByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberLLJLById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberLLJLCenterDao getMemberLLJLCenterDao() {
		return memberLLJLCenterDao;
	}

	public void setMemberLLJLCenterDao(MemberLLJLCenterDao memberLLJLCenterDao) {
		this.memberLLJLCenterDao = memberLLJLCenterDao;
	}


}
