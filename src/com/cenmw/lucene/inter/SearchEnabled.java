package com.cenmw.lucene.inter;

import java.util.HashMap;
import java.util.List;

/**
 * 搜索bean类接口 主要用于把对象指定字段值 封装成功lucene索引对象
 * 
 * @author LiangJiChao
 * 
 * @ctime 2012-8-18 下午02:10:49
 */
public interface SearchEnabled {
	/**
	 * 获取搜索对象唯一标识
	 * 
	 * @return
	 */
	public Integer getId();

	/**
	 * 返回搜索对象需要存储的字段名
	 * 
	 * @return
	 */
	public String[] GetStoreFields();

	/**
	 * 返回搜索对象用于索引的字段
	 * 
	 * @return
	 */
	public String[] GetIndexFields();

	/**
	 * 返回对象的扩展信息
	 * 
	 * @return
	 */
	public HashMap<String, String> GetExtendValues();

	/**
	 * 返回对象的扩展索引信息
	 * 
	 * @return
	 */
	public HashMap<String, String> GetExtendIndexValues();

	/**
	 * 列出id值大于指定值得所有对象
	 * 
	 * @param id
	 * @param count
	 * @return
	 */
	public List<? extends SearchEnabled> ListAfter(Integer id, int count);

	/**
	 * 权重值 默认1 用户搜索排序
	 * 
	 * @return
	 */
	public float GetBoost();

	/**
	 * 返回用于搜索 值字符串集合
	 * 
	 * @return
	 */
	public String getSearchFieldValue();

}
