package com.cenmw.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.cenmw.util.DateUtil;
import com.cenmw.util.HqlBean;
import com.cenmw.util.PageBean;
import com.cenmw.util.ProcBean;
import com.cenmw.util.ProcPageBean;
import com.cenmw.util.StringUtil;
import com.cenmw.util.WebUtil;

public class BaseHibernateDao extends HibernateDaoSupport {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Session simpleSession;

	public void save(Object obj) {
		Assert.notNull(obj, "obj不能为空");
		this.getHibernateTemplate().saveOrUpdate(obj);
		logger.debug("save obj:", obj);
	}

	public void delete(Object obj) {
		Assert.notNull(obj, "obj不能为空");
		this.getHibernateTemplate().delete(obj);
		logger.debug("delete obj:", obj);
	}

	public Object findObjectById(Class oclass, int id) {
		Assert.notNull(oclass, "oclass不能为空");
		Assert.notNull(id, "id不能为空");
		logger.debug("findObjectById oclass||id:", oclass + "\n" + id);
		return this.getHibernateTemplate().get(oclass, id);

	}

	/**
	 * 合并session中 的同一个对象。
	 */
	public Object updateObject(Object obj) {
		return this.getHibernateTemplate().merge(obj);
	}

	/**
	 * 查找列表
	 * 
	 * @param hql
	 * @param offset
	 *            查找起始位置
	 * @param count
	 *            查找 个数
	 * @return
	 */
	public List findList(final String hql, final int offset, final int count) {
		List list = null;
		simpleSession = getSession();
		try {
			Query query = simpleSession.createQuery(hql);
			printHqlLog(hql);
			query.setFirstResult(offset);
			query.setMaxResults(count);
			list = query.list();
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	/**
	 * 根据sql语句获取列表
	 * 
	 * @param sql
	 * @return
	 */
	public List getListSql(final String sql) {
		List list = null;
		simpleSession = getSession();
		try {
			Query query = simpleSession.createSQLQuery(sql);
			logger.info("SQL >>> " + sql);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	/**
	 * 根据sql语句和实体类封装数据列表
	 * 
	 * @param sql
	 * @param c
	 * @return
	 */
	public List getListSql(final String sql, final Class c) {
		List list = null;
		simpleSession = getSession();
		try {
			Query query = simpleSession.createSQLQuery(sql).addEntity(c);
			printHqlLog(sql);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	public List getListSql(final String sql, final Class c, final int top) {
		List list = null;
		simpleSession = getSession();
		try {
			Query query = simpleSession.createSQLQuery(sql).addEntity(c);
			printHqlLog(sql);
			query.setFirstResult(0);
			query.setMaxResults(top);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	public void executeHql(final String hql) {
		simpleSession = getSession();
		try {
			Query query = simpleSession.createQuery(hql);
			printHqlLog(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
	}

	public void executeSql(final String sql) {
		simpleSession = getSession();
		try {
			Query query = simpleSession.createSQLQuery(sql);
			printHqlLog(sql);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
	}

	/**
	 * 根据hql语句获取个数 eg:select count(*) from tablename
	 * 
	 * @param hql
	 * @return
	 */
	public int getNumByHql(String hql) {
		return getNumByHql(hql, null);
	}

	public int getNumByHql(final String hql, final Map map) {
		Assert.hasText(hql, "hql不能为空");
		int allRow = 0;
		List list = null;
		simpleSession = getSession();
		try {
			Query query = simpleSession.createQuery(hql);
			printHqlLog(hql);
			if (map != null) {
				hqlHanndel(map, query);
			}
			list = query.list();
			if (list != null && !list.isEmpty()) {
				allRow = Integer.valueOf(list.get(0).toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return allRow;
	}

	/**
	 * 根据hql语句获取查询列表
	 * 
	 * @param hql
	 * @param map
	 * @return
	 */
	public <T> List<T> getListForHql(final String hql, final Map map) {
		List list = null;
		simpleSession = getSession();
		try {
			Query query = simpleSession.createQuery(hql);
			printHqlLog(hql);
			if (map != null) {
				hqlHanndel(map, query);
			}
			list = query.list();
			if (map != null) {
				map.clear();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	/**
	 * 给hql语句附值
	 * 
	 * @param map
	 * @param query
	 */
	private void hqlHanndel(final Map map, Query query) {
		for (Object key : map.keySet()) {
			String keyName = (String) key;
			HqlBean pb = (HqlBean) map.get(keyName);
			Object keyValue = pb.getValue();
			if (pb.getIshql() == 1) {

			} else {
				if (pb.getType().equalsIgnoreCase("String")) {
					String value = "";
					if (pb.getOp().equalsIgnoreCase("like")) {
						value = "%" + (String) keyValue + "%";
						query.setString(keyName, value);
					} else if (pb.getOp().equalsIgnoreCase("in")) {
						query.setParameterList(keyName, (List) keyValue);
					} else {
						query.setString(keyName, (String) keyValue);
					}
				} else if (pb.getType().equalsIgnoreCase("Integer")) {
					query.setInteger(keyName,
							Integer.valueOf(keyValue.toString()));
				} else if (pb.getType().equalsIgnoreCase("Date")) {
					query.setDate(keyName, (Date) keyValue);
				} else if (pb.getType().equalsIgnoreCase("Datetime")) {
					query.setTimestamp(keyName, (Date) keyValue);
				} else if (pb.getType().equalsIgnoreCase("double")) {
					query.setDouble(keyName,
							Double.valueOf(keyValue.toString()));
				} else if (pb.getType().equalsIgnoreCase("like")) {
					String value = "";
					value = "%" + (String) keyValue + "%";
					query.setString(keyName, value);
				} else if (pb.getType().equalsIgnoreCase("in")) {
					query.setParameterList(keyName, (List) keyValue);
				}
			}
		}
	}

	public <T> List<T> getListForHql(final String hql, final Map map,
			final int top) {
		int n_top = top;
		if(n_top>100 || n_top<0){
			n_top = 100;
		}
		List list = null;
		simpleSession = getSession();
		try {
			Query query = simpleSession.createQuery(hql);
			printHqlLog(hql);
			if (map != null) {
				hqlHanndel(map, query);
			}
			query.setFirstResult(0);
			query.setMaxResults(n_top);
			list = query.list();
			if (map != null) {
				map.clear();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	public PageBean findListHqlForPage(final String hql, final String orderstr,
			final Map map, final int currentPage, final int pageSize) {
		PageBean pageBean = new PageBean();
		List list = null;
		simpleSession = getSession();
		try {
			String nhql = hql + " order by " + orderstr;
			printHqlLog(nhql);
			Query query = simpleSession.createQuery(nhql);
			if (map != null) {
				hqlHanndel(map, query);
			}
			int offset = (currentPage - 1) * pageSize;
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
			list = query.list();
			if (map != null) {
				String parameters = "";
				parameters = paramtersHandle(map, parameters);
				System.out.println("Param>>" + parameters);
				pageBean.setParameters(parameters);
			}
			pageBeanHandle(hql, map, currentPage, pageSize, pageBean, list);
			if (map != null) {
				map.clear();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return pageBean;
	}

	private void pageBeanHandle(final String hql, final Map map,
			final int cpage, final int pageSize, PageBean pageBean, List list) {
		pageBean.setPageList(list);
		int offset = (cpage - 1) * pageSize;
		int rows = getNumByHql("select count(*) " + hql, map);
		int pageCount = (rows + pageSize - 1) / pageSize;
		pageBean.setPageCount(pageCount);
		pageBean.setRows(rows);
		pageBean.setCurrentPage(cpage);
		pageBean.setFirstRow(offset + 1);
		pageBean.setLastRow(offset + pageSize);
		pageBean.setPageSize(pageSize);
	}

	private String paramtersHandle(final Map map, String parameters) {
		if (map != null) {
			for (Object key : map.keySet()) {
				String keyName = (String) key;
				HqlBean pb = (HqlBean) map.get(keyName);
				if (pb.getIsparam() != 1) {
					if (pb.getType().trim().equalsIgnoreCase("String")) {
						String keyValue = "";
						if (!pb.getOp().equalsIgnoreCase("in")) {
							keyValue = (String) pb.getValue();
							if (pb.getParamVal() != null) {
								keyValue = pb.getParamVal();
							}
							try {
								keyValue = URLEncoder.encode(keyValue, "utf-8");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							if (StringUtil.notNull(pb.getParam()).length() > 0) {
								parameters += pb.getParam() + "=" + keyValue
										+ "&";
							} else {
								parameters += keyName + "=" + keyValue + "&";
							}
						}
					} else if (pb.getType().trim().equalsIgnoreCase("Integer")) {
						String keyValue = String.valueOf(pb.getValue());
						if (StringUtil.notNull(pb.getParam()).length() > 0) {
							parameters += pb.getParam() + "=" + keyValue + "&";
						} else {
							parameters += keyName + "=" + keyValue + "&";
						}

					} else if (pb.getType().trim().equalsIgnoreCase("Date")) {
						Date date = (Date) pb.getValue();
						String keyValue = String.valueOf(DateUtil
								.getFormatDate(date, "yyyy-MM-dd HH:mm:ss"));
						if (StringUtil.notNull(pb.getParam()).length() > 0) {
							parameters += pb.getParam() + "=" + keyValue + "&";
						} else {
							parameters += keyName + "=" + keyValue + "&";
						}
					} else if (pb.getType().equalsIgnoreCase("double")) {
						String keyValue = String.valueOf(pb.getValue());
						if (StringUtil.notNull(pb.getParam()).length() > 0) {
							parameters += pb.getParam() + "=" + keyValue + "&";
						} else {
							parameters += keyName + "=" + keyValue + "&";
						}
					}
				}
			}
		}
		if (parameters.length() > 1) {
			parameters = parameters.substring(0, parameters.length() - 1);
		}
		return parameters;
	}

	/**
	 * 分布条件搜索
	 * 
	 * @param hql
	 *            eg:from tablename where 1=1
	 * @param map
	 *            参数集合
	 * @param isOr
	 *            and 或 or
	 * @param orderName
	 *            排序字段名
	 * @param sort
	 *            eg:desc , asc
	 * @param cpage
	 * @param pageSize
	 * @return
	 */
	@Deprecated
	public PageBean findListForPage(String hql, Map map, boolean isOr,
			String orderName, boolean isDesc, int cpage, int pageSize) {
		PageBean pageBean = new PageBean();
		String sort = "";
		if (isDesc) {
			sort = "desc";
		}
		StringBuffer newHql = new StringBuffer();
		newHql.append(hql);
		int offset = (cpage - 1) * pageSize;
		if (map != null) {
			// 得到参数，翻页参数，以及返回操作参数
			pageBean.setParameters(WebUtil.getParameters(map));
			// 查询数据库操作
			newHql = WebUtil.getHqlParameter(newHql, map, isOr);
		}
		int rows = findAllRow(newHql.toString());
		int pageCount = (rows + pageSize - 1) / pageSize;
		pageBean.setPageCount(pageCount);
		pageBean.setRows(rows);
		pageBean.setCurrentPage(cpage);
		pageBean.setFirstRow(offset + 1);
		pageBean.setLastRow(offset + pageSize);
		pageBean.setPageSize(pageSize);

		newHql.append("order by " + orderName + " " + sort);
		List pageList = findList(newHql.toString(), offset, pageSize);
		pageBean.setPageList(pageList);
		return pageBean;
	}

	@Deprecated
	public List findList(final String tablename, final Map map) {
		List list = null;
		simpleSession = getSession();
		try {
			String hql = createHQL(tablename, map);
			Query query = simpleSession.createQuery(hql);
			if (map != null) {
				hqlHanndel(map, query);
			}
			list = query.list();
			if (map != null) {
				map.clear();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	@Deprecated
	public List findList(final String tablename, final Map map,
			final String ordername, final String sort) {
		List list = null;
		simpleSession = getSession();
		try {
			String hql = createHQL(tablename, map);
			hql += " order by " + ordername + " " + sort;
			Query query = simpleSession.createQuery(hql);
			if (map != null) {
				hqlHanndel(map, query);
			}
			list = query.list();
			if (map != null) {
				map.clear();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	@Deprecated
	public List findList(final String tablename, final Map map,
			final String[] ordername, final String[] sort) {
		List list = null;
		simpleSession = getSession();
		try {
			String hql = createHQL(tablename, map);
			for (int i = 0; i < ordername.length; i++) {
				if (i == 0) {
					hql += " order by " + ordername[i] + " " + sort[i];
				} else {
					hql += " and " + ordername[i] + " " + sort[i];
				}
			}
			// hql += " order by " + ordername + " " + sort;
			Query query = simpleSession.createQuery(hql);
			if (map != null) {
				hqlHanndel(map, query);
			}
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	/**
	 * 返回总记录数
	 * 
	 * @param hql
	 *            from tablename
	 * @return
	 */
	@Deprecated
	public int findAllRow(String hql) {
		Assert.hasText(hql, "hql不能为空");
		String newHql = "select count(id) " + hql;
		int allRow = 0;
		simpleSession = getSession();
		try {
			Query query = simpleSession.createQuery(newHql);
			List list = query.list();			
			allRow = Integer.valueOf(list.get(0).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return allRow;
	}

	/**
	 * 
	 * @param tablename
	 * @param map
	 * @param split
	 * @return
	 */
	@Deprecated
	private String createHQL(String tablename, Map map) {
		String hql = "from " + tablename + " where 1=1 ";
		if (map != null) {
			for (Object key : map.keySet()) {
				String keyName = (String) key;
				HqlBean pb = (HqlBean) map.get(keyName);
				if (StringUtil.notNull(pb.getReplacekey()).length() > 0) {
					keyName = pb.getReplacekey();
				}
				if (pb.getIshql() != 1) {
					if (pb.getOp().trim().equalsIgnoreCase("in")) {
						if (StringUtil.notNull(pb.getReplacekey()).length() > 0) {
							keyName = pb.getReplacekey();
						}
						hql += pb.getSplit().trim() + " " + keyName + " "
								+ pb.getOp() + " (:" + (String) key + ") ";
					} else {

						hql += pb.getSplit().trim() + " " + keyName + " "
								+ pb.getOp() + " :" + (String) key + " ";
					}
				}
			}
		}
		return hql.toString();
	}

	@Deprecated
	public PageBean findListForPage(final String tablename, final Map map,
			final String ordername, final String sort, final int cpage,
			final int pageSize) {
		PageBean pageBean = new PageBean();
		List list = null;
		simpleSession = getSession();
		try {
			String hql = createHQL(tablename, map);
			hql += " order by " + ordername + " " + sort;
			Query query = simpleSession.createQuery(hql);
			if (map != null) {
				hqlHanndel(map, query);
			}
			int offset = (cpage - 1) * pageSize;
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
			list = query.list();
			String parameters = "";
			parameters = parametersHandle(map, parameters);
			pageBeanHandle(tablename, map, sort, cpage, pageSize, pageBean,
					list, parameters);
			if (map != null) {
				map.clear();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return pageBean;
	}

	@Deprecated
	private void pageBeanHandle(final String tablename, final Map map,
			final String sort, final int cpage, final int pageSize,
			PageBean pageBean, List list, String parameters) {
		pageBean.setParameters(parameters);
		pageBean.setSort(sort);
		pageBean.setPageList(list);
		int offset = (cpage - 1) * pageSize;
		int rows = findAllRow(tablename, map);
		int pageCount = (rows + pageSize - 1) / pageSize;
		pageBean.setPageCount(pageCount);
		pageBean.setRows(rows);
		pageBean.setCurrentPage(cpage);
		pageBean.setFirstRow(offset + 1);
		pageBean.setLastRow(offset + pageSize);
		pageBean.setPageSize(pageSize);
	}

	@Deprecated
	private String parametersHandle(final Map map, String parameters) {
		if (map != null) {
			if (map != null) {
				for (Object key : map.keySet()) {
					String keyName = (String) key;
					HqlBean pb = (HqlBean) map.get(keyName);
					if (pb.getIsparam() != 1) {
						if (pb.getType().trim().equalsIgnoreCase("String")) {
							String keyValue = "";
							if (!pb.getOp().equalsIgnoreCase("in")) {
								keyValue = (String) pb.getValue();
								if (StringUtil.notNull(pb.getParam()).length() > 0) {
									parameters += pb.getParam() + "="
											+ keyValue + "&";
								} else {
									parameters += keyName + "=" + keyValue
											+ "&";
								}
							}
						} else if (pb.getType().trim()
								.equalsIgnoreCase("Integer")) {
							String keyValue = String.valueOf(pb.getValue());
							if (StringUtil.notNull(pb.getParam()).length() > 0) {
								parameters += pb.getParam() + "=" + keyValue
										+ "&";
							} else {
								parameters += keyName + "=" + keyValue + "&";
							}

						} else if (pb.getType().trim().equalsIgnoreCase("Date")) {
							Date date = (Date) pb.getValue();
							String keyValue = String
									.valueOf(DateUtil.getFormatDate(date,
											"yyyy-MM-dd HH:mm:ss"));
							if (StringUtil.notNull(pb.getParam()).length() > 0) {
								parameters += pb.getParam() + "=" + keyValue
										+ "&";
							} else {
								parameters += keyName + "=" + keyValue + "&";
							}
						} else if (pb.getType().equalsIgnoreCase("double")) {
							String keyValue = String.valueOf(pb.getValue());
							if (StringUtil.notNull(pb.getParam()).length() > 0) {
								parameters += pb.getParam() + "=" + keyValue
										+ "&";
							} else {
								parameters += keyName + "=" + keyValue + "&";
							}
						}
					}
				}
			}
			if (parameters.length() > 1) {
				parameters = parameters.substring(0, parameters.length() - 1);
			}
			System.out.println("parameters:" + parameters);
		}
		return parameters;
	}

	@Deprecated
	public PageBean findListForPage(final String tablename, final Map map,
			final String[] ordername, final String[] sort, final int cpage,
			final int pageSize) {
		PageBean pageBean = new PageBean();
		List list = null;
		simpleSession = getSession();

		try {
			String hql = createHQL(tablename, map);
			for (int i = 0; i < ordername.length; i++) {
				if (i == 0) {
					hql += " order by " + ordername[i] + " " + sort[i];
				} else {
					hql += " and " + ordername[i] + " " + sort[i];
				}
			}

			Query query = simpleSession.createQuery(hql);
			if (map != null) {
				hqlHanndel(map, query);
			}
			int offset = (cpage - 1) * pageSize;
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
			list = query.list();

			String parameters = "";
			parameters = parametersHandle(map, parameters);
			pageBean.setParameters(parameters);
			pageBean.setPageList(list);

			int rows = findAllRow(tablename, map);
			int pageCount = (rows + pageSize - 1) / pageSize;
			pageBean.setPageCount(pageCount);
			pageBean.setRows(rows);
			pageBean.setCurrentPage(cpage);
			pageBean.setFirstRow(offset + 1);
			pageBean.setLastRow(offset + pageSize);
			pageBean.setPageSize(pageSize);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return pageBean;
	}

	@Deprecated
	public List findList(String hql) {
		Assert.hasText(hql, "hql不能为空");
		logger.debug("findList hql:", hql);
		simpleSession = getSession();
		List list = null;
		try {
			Query query = simpleSession.createQuery(hql);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	/**
	 * 推荐不用
	 * 
	 * @param table
	 * @param map
	 * @return
	 */
	@Deprecated
	public int findAllRow(final String table, final Map map) {
		List list = null;
		simpleSession = getSession();
		int allRow = 0;
		try {
			String hql = "select count(id) from " + table + " where 1=1 ";
			if (map != null) {
				for (Object key : map.keySet()) {
					String keyName = (String) key;
					HqlBean pb = (HqlBean) map.get(keyName);
					if (StringUtil.notNull(pb.getReplacekey()).length() > 0) {
						keyName = pb.getReplacekey();
					}
					if (pb.getIshql() != 1) {
						if (pb.getOp().trim().equalsIgnoreCase("in")) {
							if (StringUtil.notNull(pb.getReplacekey()).length() > 0) {
								keyName = pb.getReplacekey();
							}
							hql += pb.getSplit().trim() + " " + keyName + " "
									+ pb.getOp() + " (:" + (String) key + ") ";
						} else {

							hql += pb.getSplit().trim() + " " + keyName + " "
									+ pb.getOp() + " :" + (String) key + " ";
						}
					}
				}
			}
			Query query = simpleSession.createQuery(hql);
			if (map != null) {
				for (Object key : map.keySet()) {
					String keyName = (String) key;
					HqlBean pb = (HqlBean) map.get(keyName);
					Object keyValue = pb.getValue();

					if (pb.getIshql() != 1) {

						if (pb.getType().equalsIgnoreCase("String")) {
							String value = "";
							if (pb.getOp().equalsIgnoreCase("like")) {
								value = "%" + (String) keyValue + "%";
								query.setString(keyName, value);
							} else if (pb.getOp().equalsIgnoreCase("in")) {
								query.setParameterList(keyName, (List) keyValue);
							} else {
								query.setString(keyName, (String) keyValue);
							}
						} else if (pb.getType().equalsIgnoreCase("Integer")) {
							query.setInteger(keyName,
									Integer.valueOf(keyValue.toString()));
						} else if (pb.getType().equalsIgnoreCase("Date")) {
							query.setDate(keyName, (Date) keyValue);
						} else if (pb.getType().equalsIgnoreCase("double")) {
							query.setDouble(keyName,
									Double.valueOf(keyValue.toString()));
						}
					}
				}
			}
			list = query.list();

			if (list != null && !list.isEmpty()) {
				allRow = Integer.valueOf(list.get(0).toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return allRow;
	}

	@Deprecated
	public List findList(final String table, final Map map, final int offset,
			final int pageSize) {
		List list = null;
		simpleSession = getSession();

		try {
			Query query = simpleSession.createQuery(createHQL(table, map));
			if (map != null) {
				for (Object key : map.keySet()) {
					String keyName = (String) key;
					HqlBean pb = (HqlBean) map.get(keyName);
					Object keyValue = pb.getValue();
					if (pb.getIshql() != 1) {

						if (pb.getType().equalsIgnoreCase("String")) {
							String value = "";
							if (pb.getOp().equalsIgnoreCase("like")) {
								value = "%" + (String) keyValue + "%";
								query.setString(keyName, value);
							} else if (pb.getOp().equalsIgnoreCase("in")) {
								query.setParameterList(keyName, (List) keyValue);
							} else {
								query.setString(keyName, (String) keyValue);
							}
						} else if (pb.getType().equalsIgnoreCase("Integer")) {
							query.setInteger(keyName,
									Integer.valueOf(keyValue.toString()));
						} else if (pb.getType().equalsIgnoreCase("Date")) {
							query.setDate(keyName, (Date) keyValue);
						} else if (pb.getType().equalsIgnoreCase("double")) {
							query.setDouble(keyName,
									Double.valueOf(keyValue.toString()));
						}
					}
				}
			}
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
			list = query.list();
			if (map != null) {
				map.clear();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return list;
	}

	@Deprecated
	public ProcPageBean findProcForPage(final String nameQuery,
			final String tablename, final String hbmbean, final String idname,
			final List clist, final String orderstr, final int idorder,
			final int pageSize, final int currentPage, final boolean iscount) {
		List list = new ArrayList();
		simpleSession = getSession();
		ProcPageBean pageBean = new ProcPageBean();
		try {
			Query query = simpleSession.getNamedQuery(nameQuery);
			query.setString(0, tablename);
			query.setString(1, "*");
			query.setInteger(2, 0);
			query.setInteger(3, pageSize);
			query.setInteger(4, currentPage);

			String where = "";
			if (clist != null && !clist.isEmpty()) {

				for (int i = 0; i < clist.size(); i++) {
					ProcBean p = (ProcBean) clist.get(i);
					if (p.isSql()) {
						if (i > 0) {
							where += " " + p.getAndOr() + " ";
						}
						where += " " + p.getSqlKey() + " " + p.getOperate()
								+ " ";
						if (p.getOperate().equalsIgnoreCase("like")) {
							where += " '%" + p.getValue().toString() + "%' ";
						} else if (p.getOperate().equalsIgnoreCase("in")) {
							where += " (" + p.getValue().toString() + ") ";
						} else {
							if (p.getValueType().equalsIgnoreCase("String")) {
								where += " '" + p.getValue().toString() + "' ";
							} else if (p.getValueType().equalsIgnoreCase(
									"Integer")) {
								where += " "
										+ Integer.valueOf(p.getValue()
												.toString()) + " ";
							} else if (p.getValueType()
									.equalsIgnoreCase("Date")) {
								where += " '"
										+ DateUtil.getFormatDate(
												(Date) p.getValue(),
												"yyyy-MM-dd HH:mm:ss") + "' ";
							}
						}
					}
				}
			}
			query.setString(5, where);
			query.setString(6, idname);
			query.setString(7, orderstr);
			query.setInteger(8, idorder);
			query.setString(9, "");
			list = query.list();

			pageBean.setPageList(list);
			String parameters = "";
			if (clist != null && !clist.isEmpty()) {

				for (int i = 0; i < clist.size(); i++) {
					ProcBean p = (ProcBean) clist.get(i);
					if (p.isParam()) {
						if (i > 0) {
							parameters += "&";
						}
						parameters += p.getParamKey() + "=";
						if (p.getValueType().equalsIgnoreCase("String")) {
							parameters += p.getValue().toString();
						} else if (p.getValueType().equalsIgnoreCase("Integer")) {
							parameters += Integer.valueOf(p.getValue()
									.toString());
						} else if (p.getValueType().equalsIgnoreCase("Date")) {
							parameters += DateUtil.getFormatDate(
									(Date) p.getValue(), "yyyy-MM-dd HH:mm:ss");
						}
					}
				}
			}
			pageBean.setParameters(parameters);
			System.out.println("parameters:" + parameters);
			int offset = (currentPage - 1) * pageSize;
			pageBean.setCurrentPage(currentPage);
			pageBean.setFirstRow(offset + 1);
			pageBean.setLastRow(offset + pageSize);
			pageBean.setPageSize(pageSize);
			pageBean.setOrderstr(orderstr);
			pageBean.setIdorder(idorder);
			if (iscount) {
				int rows = findAllRow(hbmbean, clist);
				int pageCount = (rows + pageSize - 1) / pageSize;
				pageBean.setPageCount(pageCount);
				pageBean.setRows(rows);
			}
			if (clist != null) {
				clist.clear();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return pageBean;
	}

	@Deprecated
	public ProcPageBean findProcForPage(final String nameQuery,
			final String tablename, final String hbmbean, final String idname,
			final List clist, final String orderstr, final int idorder,
			final int pageSize, final int currentPage) {
		return findProcForPage(nameQuery, tablename, hbmbean, idname, clist,
				orderstr, idorder, pageSize, currentPage, true);
	}

	@Deprecated
	public int findAllRow(final String table, final List clist) {
		List list = null;
		simpleSession = getSession();
		int allRow = 0;
		try {
			String hql = "select count(*) from " + table + " ";
			if (clist != null && !clist.isEmpty()) {
				hql += "where ";
				for (int i = 0; i < clist.size(); i++) {
					ProcBean p = (ProcBean) clist.get(i);

					if (p.isSql()) {
						if (i > 0) {
							hql += " " + p.getAndOr() + " ";
						}
						if (p.getOperate().equalsIgnoreCase("in")) {
							hql += "" + p.getSqlKey() + " " + p.getOperate()
									+ " (" + p.getValue().toString() + ")";
						} else {

							if (p.getValueType().equalsIgnoreCase("Integer")) {
								hql += "" + p.getSqlKey() + " "
										+ p.getOperate() + " "
										+ p.getValue().toString();
							} else if (p.getValueType().equalsIgnoreCase(
									"String")) {
								if (p.getOperate().equalsIgnoreCase("like")) {
									hql += "" + p.getSqlKey() + " "
											+ p.getOperate() + " '%"
											+ p.getValue().toString() + "%' ";
								} else {
									hql += "" + p.getSqlKey() + " "
											+ p.getOperate() + " '"
											+ p.getValue().toString() + "' ";

								}
							} else if (p.getValueType()
									.equalsIgnoreCase("Date")) {
								Date date = (Date) p.getValue();
								String str = DateUtil.getFormatDate(date,
										"yyyy-MM-dd HH:mm:ss");
								hql += "" + p.getSqlKey() + " "
										+ p.getOperate() + " '" + str + "'";
							}
						}
					}
				}
			}
			Query query = simpleSession.createQuery(hql);
			list = query.list();

			if (list != null && !list.isEmpty()) {
				allRow = Integer.valueOf(list.get(0).toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			releaseSimpleSession();
		}
		return allRow;
	}

	public void releaseSimpleSession() {
		releaseSession(simpleSession);
	}

	private void printHqlLog(String hql) {
		logger.info("HQL >>> " + hql);
	}

}
