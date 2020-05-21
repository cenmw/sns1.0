package com.cenmw.member.center.service;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberStatusCenterDao;
import com.cenmw.member.po.MemberStatus;
import com.cenmw.util.PageBean;

public class MemberStatusCenterService {
	private MemberStatusCenterDao memberStatusCenterDao;

	public PageBean findMemberStatusHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberStatusCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberStatus(MemberStatus memberStatus) {
		boolean status = true;
		memberStatusCenterDao.saveMemberStatus(memberStatus);
		return status;
	}

	public boolean saveMemberStatus(int mid, int type, int cid, String classname,int qx) {
		boolean status = true;
		MemberStatus memberStatus = new MemberStatus();
		memberStatus.setMid(mid);
		memberStatus.setType(type);
		if (type == 1) {
			memberStatus.setTypename("会员说说");
		} else if (type == 2) {
			memberStatus.setTypename("会员疏导");
		} else if (type == 3) {
			memberStatus.setTypename("会员文集");
		} else if (type == 4) {
			memberStatus.setTypename("会员相册");
		} else if (type == 5) {
			memberStatus.setTypename("会员视频");
		} else if (type == 7) {
			memberStatus.setTypename("会员52周");
		} else if (type == 8) {
			memberStatus.setTypename("会员咨询");
		} else if (type == 9) {
			memberStatus.setTypename("问题区");
		} else if (type == 10) {
			memberStatus.setTypename("习惯养成");
		}
		memberStatus.setCid(cid);
		memberStatus.setClassname(classname);
		memberStatus.setCtime(new Date());
		memberStatus.setPtime(new Date());
		memberStatus.setIsdel(new Integer(0));
		memberStatus.setQx(qx);
		memberStatusCenterDao.saveMemberStatus(memberStatus);
		return status;
	}

	public boolean updateMemberStatus(int mid, int type, int cid) {
		boolean status = true;
		MemberStatus ms = getMemberStatusByCid(type, cid);
		if (ms != null) {
			ms.setPtime(new Date());
			memberStatusCenterDao.updateMemberStatus(ms);
		}
		return status;
	}

	public List findMemberStatusInList(int mid) {
		String hql = "from MemberStatus where isdel=0 and mid=" + mid
				+ " order by id";
		return memberStatusCenterDao.getListForHql(hql, null);
	}

	public List findFMemberStatusInList(String fids) {
		String hql = "from MemberStatus where isdel=0 and mid in (" + fids
				+ ")" + " order by ptime desc";
		return memberStatusCenterDao.getListForHql(hql, null);
	}

	public List findFCMemberStatusInList(String fids, int top,int qx) {
		String hql = "from MemberStatus where isdel=0 and qx="+qx+" and mid in (" + fids
				+ ")" + " order by ptime desc";
		return memberStatusCenterDao.getListForHql(hql, null, top);
	}

	public List findMemberStatusHQLList(String fids, int cpage, int pageSize,int qx) {
		String hql = "from MemberStatus where isdel=0 and (type<8 or type=10) and qx="+qx+" and mid in (" + fids
				+ ")";
		String orderstr = " ptime desc";
		PageBean pb = memberStatusCenterDao.findListHqlForPage(hql, orderstr,
				null, cpage, pageSize);
		return pb.getPageList();
	}
	
	public List findMemberStatusHQLList(String fids, int cpage, int pageSize,int qx,int type) {
		String hql = "from MemberStatus where isdel=0 and type="+type+" and mid in (" + fids
				+ ")";
		if(qx !=-1){
			 hql +=" and qx="+qx;
		}
		String orderstr = " ptime desc";
		PageBean pb = memberStatusCenterDao.findListHqlForPage(hql, orderstr,
				null, cpage, pageSize);
		return pb.getPageList();
	}

	public void deleteMemberStatusByCid(int type, int cid, int mid) {
		MemberStatus ms = null;
		String hql = "from MemberStatus where isdel=0 and type=" + type
				+ " and cid=" + cid + " and mid=" + mid + " order by id";
		List list = memberStatusCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			ms = (MemberStatus) list.get(0);
			ms.setIsdel(1);
			memberStatusCenterDao.updateMemberStatus(ms);
		}
	}
	
	public void deleteMemberStatusByCid(int type, int cid) {
		MemberStatus ms = null;
		String hql = "from MemberStatus where isdel=0 and type=" + type
				+ " and cid=" + cid + " order by id";
		List list = memberStatusCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			ms = (MemberStatus) list.get(0);
			ms.setIsdel(1);
			memberStatusCenterDao.updateMemberStatus(ms);
		}
	}

	public MemberStatus getMemberStatusByMid(int mid) {
		MemberStatus ms = null;
		String hql = "from MemberStatus where isdel=0 and mid=" + mid
				+ " order by id desc";
		List list = memberStatusCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			ms = (MemberStatus) list.get(0);
		}
		return ms;
	}

	public MemberStatus getMemberStatusByCid(int type, int cid) {
		MemberStatus ms = null;
		String hql = "from MemberStatus where isdel=0 and type=" + type
				+ " and cid=" + cid + " order by id desc";
		List list = memberStatusCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			ms = (MemberStatus) list.get(0);
		}
		return ms;
	}

	public void deleteMemberStatus(int mid, int type, int cid) {
		String hql = "from MemberStatus where isdel=0 and mid=" + mid
				+ " and type=" + type + " and cid=" + cid;
		List list = memberStatusCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			MemberStatus memberStatus = (MemberStatus) list.get(0);
			memberStatus.setIsdel(new Integer(1));
			memberStatusCenterDao.updateMemberStatus(memberStatus);
		}
	}

	public boolean updateMemberStatus(MemberStatus memberStatus) {
		boolean status = true;
		memberStatusCenterDao.updateMemberStatus(memberStatus);
		return status;
	}

	public MemberStatus getMemberStatus(int id) {
		return memberStatusCenterDao.getMemberStatusById(id);
	}

	public void deleteMemberStatusById(int id) {
		MemberStatus pb = getMemberStatus(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberStatusCenterDao.saveMemberStatus(pb);
		}
	}

	public void deleteMemberStatusByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberStatusById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberStatusCenterDao getMemberStatusCenterDao() {
		return memberStatusCenterDao;
	}

	public void setMemberStatusCenterDao(
			MemberStatusCenterDao memberStatusCenterDao) {
		this.memberStatusCenterDao = memberStatusCenterDao;
	}

}
