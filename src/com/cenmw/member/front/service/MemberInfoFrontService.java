package com.cenmw.member.front.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cenmw.integral.front.dao.IntegralInfoFrontDao;
import com.cenmw.member.front.dao.MemberInfoFrontDao;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.*;

public class MemberInfoFrontService {
	private MemberInfoFrontDao memberInfoFrontDao;
	private IntegralInfoFrontDao integralInfoFrontDao;

	/**
	 * 获取最新注册的会员 要求:必需通过邮箱验证,已填写手机号和详细地址
	 * 
	 * @return
	 */
	public List<MemberInfo> getNewRegMemberList(int top) {
		return memberInfoFrontDao.getNewRegMemberList(top);
	}

	public MemberInfo getMemberInfoByQqUID(String uid) {
		return memberInfoFrontDao.getMemberInfoByQqUID(uid);
	}

	public MemberInfo getMemberInfoBySinaUID(String uid) {
		return memberInfoFrontDao.getMemberInfoBySinaUID(uid);
	}

	public PageBean getMemberListForZoneByTid(String tid, int currentPage,
			int pageSize) {
		return memberInfoFrontDao.getMemberListForZoneByTid(tid, currentPage,
				pageSize);
	}

	public MemberInfo getMemberInfoBySession(Map<String, Object> session) {
		return (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
	}

	public MemberInfo getMemberInfo(HttpServletRequest request,
			Map<String, Object> session) {
		MemberInfo memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (memberInfo == null) {
			String account = CookieUtils.getCookieValue(request, "M_account");
			String password = CookieUtils.getCookieValue(request, "M_password");
			memberInfo = loginUndecryMember(account, password);
			if (memberInfo != null) {
				session.put(ConstantUtils.MEMBERINFO, memberInfo);
			} else {
				session.remove("session_backurl");
			}
		}
		return memberInfo;
	}

	public MemberInfo getMemberInfoByAe(String account, String email) {
		String hql = "from MemberInfo where account='" + account
				+ "' and email='" + email + "'";
		List list = memberInfoFrontDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			return (MemberInfo) list.get(0);
		}
		return null;
	}

	public MemberInfo getMemberInfoByNickName(String nickname) {
		Map map = new LinkedHashMap<String, HqlBean>();
		map.put("nickname", new HqlBean(nickname, "=", "and", "String"));
		List list = memberInfoFrontDao.getMemberInfoList(map);
		if (list != null && !list.isEmpty()) {
			return (MemberInfo) list.get(0);
		}
		return null;
	}

	public MemberInfo getMemberInfoByMobile(String mobile, int id) {
		String hql = "from MemberInfo where isdel=0 and id <>" + id
				+ " and mobile=" + mobile;
		List list = memberInfoFrontDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			return (MemberInfo) list.get(0);
		}
		return null;
	}

	public MemberInfo getMemberInfoByAccount(String account) {
		Map<String, HqlBean> map = new HashMap();
		map.put("account", new HqlBean(account, "=", "and", "String"));
		List list = memberInfoFrontDao.getMemberInfoList(map);
		if (list != null && !list.isEmpty()) {
			return (MemberInfo) list.get(0);
		}
		return null;
	}

	public MemberInfo getMemberInfoByEmail(String email) {
		Map<String, HqlBean> map = new HashMap();
		map.put("email", new HqlBean(email, "=", "and", "String"));
		List list = memberInfoFrontDao.getMemberInfoList(map);
		if (list != null && !list.isEmpty()) {
			return (MemberInfo) list.get(0);
		}
		return null;
	}

	/**
	 * 注册用户
	 * 
	 * @param memberInfo
	 */
	public MemberInfo saveMemberInfo(MemberInfo memberInfo) {
		MD5 md5 = new MD5();
		String p = md5.getMD5ofStr(memberInfo.getPassword());
		memberInfo.setPassword(p);
		memberInfo.setCtime(new Date());
		memberInfo.setStatus(1);  // 默认已审核
		memberInfo.setIsdel(0);
		if (memberInfo.getAvatar() == null) {
			memberInfo.setAvatar("");
		}
		memberInfoFrontDao.saveMemberInfo(memberInfo);
		// 得到当前会员的积分情况
		memberInfo.setSumscore(integralInfoFrontDao.getIntegralByMid(memberInfo
				.getId()));
		return memberInfo;
	}
	
	/**
	 * 注册企业用户
	 * 
	 * @param memberInfo
	 */
	public MemberInfo saveCMemberInfo(MemberInfo memberInfo) {
		Map<String, HqlBean> map = new LinkedHashMap<String, HqlBean>();
		map.put("email", new HqlBean(memberInfo.getEmail(), "=", "and",
				"String"));
		List list = memberInfoFrontDao.getMemberInfoList(map);
		if (list != null && !list.isEmpty()) {
			return null;
		}
		MD5 md5 = new MD5();
		String p = md5.getMD5ofStr(memberInfo.getPassword());
		memberInfo.setPassword(p);
		memberInfo.setCtime(new Date());
		memberInfo.setStatus(0);
		memberInfo.setIsdel(0);
		if (memberInfo.getAvatar() == null) {
			memberInfo.setAvatar("");
		}
		memberInfoFrontDao.saveMemberInfo(memberInfo);
		// 得到当前会员的积分情况
		memberInfo.setSumscore(integralInfoFrontDao.getIntegralByMid(memberInfo
				.getId()));
		return memberInfo;
	}

	public void saveMemberInfo(String user_name, String password, String email) {
		System.out.println("saveusers");
		String sql = "INSERT INTO [member_info] ([account],[password],[email],[type],[level],[status],[sex],[integral],[ctime],[task],[pay],[collevel])  VALUES ('"
				+ user_name
				+ "','"
				+ password
				+ "','"
				+ email
				+ "',0,'0',0,'0',0,GETDATE(),0,0,0)";
		memberInfoFrontDao.updateUsers(sql);
	}

	public MemberInfo updateMemberInfo(MemberInfo memberInfo,
			Map<String, Object> session) {
		memberInfoFrontDao.updateMemberInfo(memberInfo);
		session.put(ConstantUtils.MEMBERINFO, memberInfo);
		return memberInfo;
	}

	public MemberInfo updateMemberInfo(MemberInfo memberInfo) {
		memberInfoFrontDao.updateMemberInfo(memberInfo);
		return memberInfo;
	}

	/**
	 * 验证用户登陆
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public MemberInfo loginMember(String account, String password) {
		Map<String, HqlBean> map = new HashMap();
		if (StringUtil.notNull(account).length() > 0
				&& StringUtil.notNull(password).length() > 0) {
			if (StringUtil.checkEmail(account)) {
				map.put("email", new HqlBean(account, "=", "and", "String"));
			} else if (StringUtil.checkMobile(account)) {
				map.put("mobile", new HqlBean(account, "=", "and", "String"));
			} else {
				map.put("account", new HqlBean(account, "=", "and", "String"));
			}
			List list = memberInfoFrontDao.getMemberInfoList(map);
			if (list != null && !list.isEmpty()) {
				MemberInfo m = (MemberInfo) list.get(0);
				MD5 md5 = new MD5();
				String p = md5.getMD5ofStr(password);
				if (p.equalsIgnoreCase(m.getPassword()) && m.getStatus() >= 0) {
					// 得到当前会员的积分情况
					m.setSumscore(integralInfoFrontDao.getIntegralByMid(m
							.getId()));
					return m;
				}
			}
		}
		return null;
	}

	/**
	 * 验证用户登陆
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public MemberInfo zdloginMember(String account, String password) {
		Map<String, HqlBean> map = new HashMap();
		if (StringUtil.notNull(account).length() > 0
				&& StringUtil.notNull(password).length() > 0) {

			if (StringUtil.checkEmail(account)) {
				map.put("email", new HqlBean(account, "=", "and", "String"));
			} else {
				map.put("account", new HqlBean(account, "=", "and", "String"));
			}
			List list = memberInfoFrontDao.getMemberInfoList(map);
			if (list != null && !list.isEmpty()) {
				MemberInfo m = (MemberInfo) list.get(0);
				String p = password;
				if (p.equalsIgnoreCase(m.getPassword()) && m.getStatus() >= 0) {
					return m;
				}
			}
		}
		return null;
	}

	public MemberInfo loginUndecryMember(String account, String password) {
		if (StringUtil.notNull(account).length() > 0
				&& StringUtil.notNull(password).length() > 0) {
			Map<String, HqlBean> map = new HashMap();
			password = EncryptUtil.urlParamDecrypt(password);
			MD5 md5 = new MD5();
			password = md5.getMD5ofStr(password);
			if (StringUtil.checkEmail(account)) {
				map.put("email", new HqlBean(account, "=", "and", "String"));
			} else {
				map.put("account", new HqlBean(account, "=", "and", "String"));
			}
			map.put("password", new HqlBean(password, "=", "and", "String"));
			List list = memberInfoFrontDao.getMemberInfoList(map);
			if (list != null && !list.isEmpty()) {
				MemberInfo m = (MemberInfo) list.get(0);
				return m;
			}
		}
		return null;
	}

	public List getMemberInfoByIntegral(int top) {
		String hql = "from MemberInfo where type=0 and status>=0 order by integral desc";
		return memberInfoFrontDao.findList(hql, 0, top);
	}

	// 根据会员id取得订单商品的id
	public Integer[] getOrderInfoProductIdsByMember(MemberInfo memberInfo) {
		Integer[] ids = null;
		List<Integer> idList = new ArrayList<Integer>();
		String hql = "SELECT DISTINCT o.pid FROM OrderGoods  o ,OrderInfo i WHERE (i.mid = 21) and  o.id = i.id";
		idList = memberInfoFrontDao.findList(hql);
		if (idList != null && !idList.isEmpty()) {
			ids = idList.toArray(new Integer[idList.size()]);
		}
		return ids;
	}

	public List findMemberInfoList(String mids) {
		List list = null;
		if (mids != null && mids.length() > 0) {
			list = new ArrayList();
			String[] midarrs = mids.split(",");
			for (int i = 0; i < midarrs.length; i++) {
				int mid = new Integer(midarrs[i].replaceAll("-", ""));
				MemberInfo minfo = getMemberInfoById(mid);
				list.add(minfo);
			}
		}
		return list;
	}

	public MemberInfo getMemberInfoById(int id) {
		MemberInfo memberInfo = null;
		memberInfo = memberInfoFrontDao.getMemberInfoById(id);
		if (memberInfo != null) {
			memberInfo.setSumscore(integralInfoFrontDao.getIntegralByMid(id));
		}
		return memberInfo;
	}

	public MemberInfoFrontDao getMemberInfoFrontDao() {
		return memberInfoFrontDao;
	}

	public void setMemberInfoFrontDao(MemberInfoFrontDao memberInfoFrontDao) {
		this.memberInfoFrontDao = memberInfoFrontDao;
	}

	public IntegralInfoFrontDao getIntegralInfoFrontDao() {
		return integralInfoFrontDao;
	}

	public void setIntegralInfoFrontDao(
			IntegralInfoFrontDao integralInfoFrontDao) {
		this.integralInfoFrontDao = integralInfoFrontDao;
	}
	
	/**
	 * 验证用户登陆
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public MemberInfo getLoginMember(String account) {
		Map<String, HqlBean> map = new HashMap();
		if (StringUtil.notNull(account).length() > 0) {
			if (StringUtil.checkEmail(account)) {
				map.put("email", new HqlBean(account, "=", "and", "String"));
			} else if (StringUtil.checkMobile(account)) {
				map.put("mobile", new HqlBean(account, "=", "and", "String"));
			} else {
				map.put("account", new HqlBean(account, "=", "and", "String"));
			}
			List list = memberInfoFrontDao.getMemberInfoList(map);
			if (list != null && !list.isEmpty()) {
				return (MemberInfo) list.get(0);
			}
		}
		return null;
	}

}
