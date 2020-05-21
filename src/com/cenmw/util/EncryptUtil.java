package com.cenmw.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptUtil {
	private static final String EncryptPassword = "100cenmw";// 注意:不能修改

	/**
	 * 加密
	 * 
	 * @param param
	 * @return
	 */
	public static String urlParamEncrypt(String param) {
		StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
		s.setPassword(EncryptPassword);
		s.initialize();
		return s.encrypt(param);
	}

	/**
	 * 解密
	 * 
	 * @param encrydtStr
	 * @return
	 */
	public static String urlParamDecrypt(String encrydtStr) {
		StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
		s.setPassword(EncryptPassword);
		s.initialize();
		return s.decrypt(encrydtStr);
	}

	/**
	 * 加密参数
	 * 
	 * @param param
	 * @param encryptPassword
	 *            加密密码 注:解密时加密密码必需相同
	 * @return
	 */
	public static String urlParamEncrypt(String param, String encryptPassword) {
		StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
		s.setPassword(encryptPassword);
		s.initialize();
		return s.encrypt(param);
	}

	/**
	 * 解密参数
	 * 
	 * @param encrydtStr
	 * @param encryptPassword
	 *            加密密码
	 * @return
	 */
	public static String urlParamDecrypt(String encrydtStr,
			String encryptPassword) {
		StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
		s.setPassword(encryptPassword);
		s.initialize();
		return s.decrypt(encrydtStr);
	}

	public static void main(String args[]) {
		String param = "123123";
		System.out.println(urlParamEncrypt(param));
		System.out
				.println(urlParamDecrypt(urlParamEncrypt(param)));
	}
}
