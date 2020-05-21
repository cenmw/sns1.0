package com.cenmw.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.admin.po.User;
import com.cenmw.base.BaseHibernateDao;
import com.cenmw.util.HqlBean;
import com.cenmw.util.MD5;

public class UserDao extends BaseHibernateDao {
	public User getUser(String username, String password) {
		User user = null;
		Map map = new HashMap();
		map.put("username", new HqlBean(username, "=", "and", "String"));
		List list;
		try {
			list = this.findList("User", map);
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					User u = (User) list.get(i);
					MD5 md5 = new MD5();
					String p = md5.getMD5ofStr(password);
					if (p.equals(u.getPassword())) {
						user = u;
					}
				}
			}
		} catch (Exception e) {
		}
		return user;
	}
	
	public static void main(String[] str){
		String pw = "bptcg2015";
		MD5 md5 = new MD5();
		System.out.println("========="+md5.getMD5ofStr(pw));
	}
}
