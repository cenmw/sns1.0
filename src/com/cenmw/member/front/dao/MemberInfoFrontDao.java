package com.cenmw.member.front.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.HqlBean;
import com.cenmw.util.PageBean;

public class MemberInfoFrontDao extends BaseHibernateDao {

	/**
	 * 获取最新注册的会员 要求:必需通过邮箱验证,已填写手机号和详细地址
	 *
	 * @return
	 */
	public List<MemberInfo> getNewRegMemberList(int top) {
		String hql = "from MemberInfo where emailcode='1' and type=0 and mobile!='' and address!='' order by id desc";
		return getListForHql(hql, null, top);
	}

	/**
	 * 根据QQ用户uid查找是否有已绑定的消费者会员
	 *
	 * @param uid
	 * @return
	 */
	public MemberInfo getMemberInfoByQqUID(String uid) {
		String hql = "from MemberInfo where qq_uid=:qq_uid";
		Map<String, HqlBean> map = new HashMap<String, HqlBean>();
		map.put("qq_uid", new HqlBean(uid, "String"));
		List list = getListForHql(hql, map, 1);
		if (list != null && !list.isEmpty()) {
			return (MemberInfo) list.get(0);
		}
		return null;
	}

	/**
	 * 根据新浪用户uid查找是否有已绑定的消费者会员
	 *
	 * @param uid
	 * @return
	 */
	public MemberInfo getMemberInfoBySinaUID(String uid) {
		String hql = "from MemberInfo where sina_uid=:sina_uid";
		Map<String, HqlBean> map = new HashMap<String, HqlBean>();
		map.put("sina_uid", new HqlBean(uid, "String"));
		List list = getListForHql(hql, map, 1);
		if (list != null && !list.isEmpty()) {
			return (MemberInfo) list.get(0);
		}
		return null;
	}

	public void updateUsers(String sql) {
		executeSql(sql);
	}

	public PageBean getMemberListForZoneByTid(String tid, int currentPage,
											  int pageSize) {
		String hql = "from MemberInfo where type=0 and status>=0";
		Map<String, HqlBean> map = new HashMap<String, HqlBean>();
		if (tid != null && tid.length() > 0) {
			hql += " and industry like :tid";
			HqlBean hb = new HqlBean("-" + tid + "-", "like", "and", "String");
			hb.setParamVal(tid);
			map.put("tid", hb);
		}
		return findListHqlForPage(hql, "ctime desc", map, currentPage, pageSize);
	}

	public void saveMemberInfo(MemberInfo memberInfo) {
		save(memberInfo);
	}

	public void updateMemberInfo(MemberInfo memberInfo) {
		updateObject(memberInfo);
	}

	public MemberInfo getMemberInfoById(int id) {
		return (MemberInfo) findObjectById(MemberInfo.class, id);
	}

	public List getMemberInfoList(Map map) {
		return findList("MemberInfo", map);
	}

	public List getMemberInfoList(Map map, String ordername, String sort) {
		return findList("MemberInfo", map, ordername, sort);
	}

	public PageBean getMemberInfoForPage(Map map, String ordername,
										 String sort, int currentPage, int pageSize) {
		return findListForPage("MemberInfo", map, ordername, sort, currentPage,
				pageSize);
	}
}
