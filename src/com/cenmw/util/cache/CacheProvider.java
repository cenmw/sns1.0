package com.cenmw.util.cache;

import com.cenmw.util.cache.exception.CacheException;

public interface CacheProvider {
	/**
	 * 构建缓存
	 * 
	 * @method buildCache
	 * @param regionName
	 *            缓存区域名称
	 * @return
	 * @throws CacheException
	 * @ReturnType Cache
	 */
	public Cache buildCache(String regionName) throws CacheException;

	/**
	 * 回调来执行任何必要的初始化底层的缓存实现 用于SessionFactory的建设
	 * 
	 * @method start
	 * @throws CacheException
	 * @ReturnType void
	 */
	public void start() throws CacheException;

	/**
	 * 回调来执行任何必要的清理底层的缓存实现 用于 SessionFactory.close().
	 * 
	 * @method stop
	 * @ReturnType void
	 */
	public void stop();
}
