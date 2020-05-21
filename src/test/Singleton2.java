package test;

public class Singleton2 {

	public static Singleton2 instance = null;

	//优点 只在第一次调用方法的时候，生成对象。 确定 属性都公开了，不够安全。
	public static Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}
}
