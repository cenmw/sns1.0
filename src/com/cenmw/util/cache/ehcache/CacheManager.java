package com.cenmw.util.cache.ehcache;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cenmw.util.cache.Cache;
import com.cenmw.util.cache.CacheProvider;

public class CacheManager {
	private static final Log log = LogFactory.getLog(CacheManager.class);
	private static CacheProvider provider;
	static {
		init("com.cenmw.util.cache.ehcache.EhCacheProvider");
	}
	public static void begin(){}
	public static void end(){CacheManager.provider.stop();}

	public static void init(String prv_name) {
		try {
			CacheManager.provider = (CacheProvider) Class.forName(prv_name)
					.newInstance();
			CacheManager.provider.start();
			System.out.println("=====================初始化App缓存=====================");
		} catch (Exception e) {
			log.fatal("Unabled to initialize cache provider:" + prv_name
					+ ", using ehcache default.", e);
			CacheManager.provider = new EhCacheProvider();
		}
	}

	private final static Cache _GetCache(String cachename) {
		if (provider == null) {
			provider = new EhCacheProvider();
		}
		return provider.buildCache(cachename);
	}

	/**
	 * 获取缓存中的数据
	 * 
	 * @param name
	 * @param key
	 * @return
	 */
	public final static Object get(String name, Serializable key) {
		if (name != null && key != null)
			return _GetCache(name).get(key);
		return null;
	}

	/**
	 * 获取缓存中的数据
	 * 
	 * @method get
	 * @param <T>
	 * @param resultClass
	 * @param name
	 * @param key
	 * @return
	 * @ReturnType T
	 */
	public final static <T> T get(Class<T> resultClass, String name,
			Serializable key) {
		if (name != null && key != null)
			return (T) _GetCache(name).get(key);
		return null;
	}

	/**
	 * 写入缓存
	 * 
	 * @method set
	 * @param name
	 * @param key
	 * @param value
	 * @ReturnType void
	 */
	public final static void set(String name, Serializable key,
			Serializable value) {
		if (name != null && key != null && value != null)
			_GetCache(name).put(key, value);
	}

	/**
	 * 清除缓冲中的某个数据
	 * 
	 * @method evict
	 * @param name
	 * @param key
	 * @ReturnType void
	 */
	public final static void evict(String name, Serializable key) {
		if (name != null && key != null)
			_GetCache(name).remove(key);
	}

	public final static void clear(String name) {
		if (name != null) {
			_GetCache(name).clear();
		}
	}
}
