package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberBlogCenterDao;
import com.cenmw.member.po.MemberBlog;
import com.cenmw.util.PageBean;

public class MemberBlogCenterService {
	private MemberBlogCenterDao memberBlogCenterDao;

	public PageBean findMemberBlogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberBlogCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberBlog(MemberBlog memberBlog) {
		boolean status = true;
		memberBlogCenterDao.saveMemberBlog(memberBlog);
		memberBlogCenterDao.updateMemberBlog(memberBlog);
		return status;
	}

	public List findMemberBlogInList(int mid) {
		String hql = "from MemberBlog where isdel=0 and mid=" + mid
				+ " order by id";
		return memberBlogCenterDao.getListForHql(hql, null);
	}

	public int getMemberBlogInListNumber(int cid) {
		String hql = "from MemberBlog where isdel=0 and rcid=" + cid;
		return memberBlogCenterDao.findAllRow(hql);
	}
	
	public List findHotZTMemberBlogList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberBlog where isdel=0 order by rcnumber desc,id asc";
		return memberBlogCenterDao.getListForHql(hql, null, top);
	}

	public List findHotVIEWMemberBlogList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from MemberBlog where isdel=0 order by viewnumber desc,id asc";
		return memberBlogCenterDao.getListForHql(hql, null, top);
	}
	
	public boolean updateMemberBlog(MemberBlog memberBlog) {
		boolean status = true;
		memberBlogCenterDao.updateMemberBlog(memberBlog);
		return status;
	}

	public MemberBlog getMemberBlog(int id) {
		return memberBlogCenterDao.getMemberBlogById(id);
	}

	public MemberBlog getMemberBlog(int mid, int cid) {
		MemberBlog mp = null;
		String hql = "from MemberBlog where isdel=0 and mid=" + mid
				+ " and rcid=" + cid + " order by id";
		List list = memberBlogCenterDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (MemberBlog) list.get(0);
		}
		return mp;
	}
	
	public void deleteMemberBlogById(int id) {
		MemberBlog pb = getMemberBlog(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberBlogCenterDao.saveMemberBlog(pb);
		}
	}

	public void deleteMemberBlogByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberBlogById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberBlogCenterDao getMemberBlogCenterDao() {
		return memberBlogCenterDao;
	}

	public void setMemberBlogCenterDao(MemberBlogCenterDao memberBlogCenterDao) {
		this.memberBlogCenterDao = memberBlogCenterDao;
	}


}
