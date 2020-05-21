package com.cenmw.util.cache.ehcache;

import java.util.Hashtable;

import net.sf.ehcache.CacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cenmw.util.cache.Cache;
import com.cenmw.util.cache.CacheProvider;
import com.cenmw.util.cache.exception.CacheException;

public class EhCacheProvider implements CacheProvider {

	private static final Log log = LogFactory.getLog(EhCacheProvider.class);

	private net.sf.ehcache.CacheManager manager;

	private Hashtable<String, EhCache> _CacheManager;

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
	public Cache buildCache(String name) throws CacheException {
		if (_CacheManager == null) {
			start();
		}
		EhCache ehcache = _CacheManager.get(name);
		if (ehcache != null) {
			return ehcache;
		} else {
			try {
				net.sf.ehcache.Cache cache = manager.getCache(name);
				if (cache == null) {
					log.warn("Could not find configuration [" + name
							+ "]; using defaults.");
					manager.addCache(name);
					cache = manager.getCache(name);
					log.debug("started EHCache region: " + name);
				}
				synchronized (_CacheManager) {
					ehcache = new EhCache(cache);
					_CacheManager.put(name, ehcache);
					return ehcache;
				}
			} catch (net.sf.ehcache.CacheException e) {
				throw new CacheException(e);
			}
		}
	}

	/**
	 * 回调来执行任何必要的初始化底层的缓存实现 用于SessionFactory的建设
	 * 
	 * @method start
	 * @throws CacheException
	 * @ReturnType void
	 */
	public void start() throws CacheException {
		if (manager != null) {
			log.warn("EHCACHE::Attempt to restart an already started EhCacheProvider. Use sessionFactory.close() "
					+ " between repeated calls to buildSessionFactory. Using previously created EhCacheProvider."
					+ " If this behaviour is required, consider using net.sf.ehcache.hibernate.SingletonEhCacheProvider.");
			return;
		}
		manager = CacheManager.getInstance();
		_CacheManager = new Hashtable<String, EhCache>();
	}

	/**
	 * 回调来执行任何必要的清理底层的缓存实现 用于 SessionFactory.close().
	 * 
	 * @method stop
	 * @ReturnType void
	 */
	public void stop() {
		if (manager != null) {
			manager.shutdown();
			manager = null;
		}
	}
}
