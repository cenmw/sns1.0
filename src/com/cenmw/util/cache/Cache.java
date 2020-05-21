package com.cenmw.util.cache;

import java.util.List;

import com.cenmw.util.cache.exception.CacheException;

/**
 * 
 * @author LiangJiChao
 * 
 * @ctime 2012-7-26 上午11:28:10
 */
public interface Cache {
	/**
	 * 从缓存中获取一个元素
	 * 
	 * @method get
	 * @param key
	 * @return 返回缓存对象或null
	 * @throws CacheException
	 * @ReturnType Object
	 */
	public Object get(Object key) throws CacheException;

	/**
	 * 添加一个元素到缓存中
	 * 
	 * @method put
	 * @param key
	 * @param value
	 * @throws CacheException
	 * @ReturnType void
	 */
	public void put(Object key, Object value) throws CacheException;

	/**
	 * 添加一个元素到缓存中
	 * 
	 * @method update
	 * @param key
	 * @param value
	 * @throws CacheException
	 * @ReturnType void
	 */
	public void update(Object key, Object value) throws CacheException;

	@SuppressWarnings("rawtypes")
	public List keys() throws CacheException;

	/**
	 * 从缓存中移除一个元素
	 * 
	 * @method remove
	 * @param key
	 * @throws CacheException
	 * @ReturnType void
	 */
	public void remove(Object key) throws CacheException;

	/**
	 * 清除缓存 缓存可用
	 * 
	 * @method clear
	 * @throws CacheException
	 * @ReturnType void
	 */
	public void clear() throws CacheException;

	/**
	 * 清理 缓存不可用
	 * 
	 * @method destroy
	 * @throws CacheException
	 * @ReturnType void
	 */
	public void destroy() throws CacheException;
}
