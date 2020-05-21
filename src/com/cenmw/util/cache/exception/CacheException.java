package com.cenmw.util.cache.exception;

/**
 * 
 * @author LiangJiChao
 * 
 * @ctime 2012-7-26 上午11:28:03
 */
public class CacheException extends RuntimeException {
	public CacheException(String s) {
		super(s);
	}

	public CacheException(String s, Throwable e) {
		super(s, e);
	}

	public CacheException(Throwable e) {
		super(e);
	}
}
