package com.cenmw.util.cache.ehcache;

import java.util.List;

import net.sf.ehcache.Element;

import com.cenmw.util.cache.Cache;
import com.cenmw.util.cache.exception.CacheException;

public class EhCache implements Cache {

	private net.sf.ehcache.Cache cache;

	public EhCache(net.sf.ehcache.Cache cache) {
		this.cache = cache;
	}

	/**
	 * 根据给的key匹配得到一个值
	 */
	public Object get(Object key) throws CacheException {
		try {
			if (key == null) {
				return null;
			} else {
				Element element = cache.get(key);
				if (element != null) {
					return element.getObjectValue();
				}
			}
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
		return null;
	}

	/**
	 * 将对象放入缓存中
	 */
	public void put(Object key, Object value) throws CacheException {
		try {
			Element element = new Element(key, value);
			cache.put(element);
		} catch (IllegalArgumentException e) {
			throw new CacheException(e);
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	/**
	 * 将对象放入缓存中
	 */
	public void update(Object key, Object value) throws CacheException {
		put(key, value);
	}

	public List keys() throws CacheException {
		return this.cache.getKeys();
	}

	/**
	 * 根据key匹配从缓存中移除
	 */
	public void remove(Object key) throws CacheException {
		try {
			cache.remove(key);
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	/**
	 * 清除缓存中所有元素，但保留缓存为可用状态
	 */
	public void clear() throws CacheException {
		try {
			cache.removeAll();
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	/**
	 * 移除缓存为不可用状态
	 */
	public void destroy() throws CacheException {
		try {
			cache.getCacheManager().removeCache(cache.getName());
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}
}
