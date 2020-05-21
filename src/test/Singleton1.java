package test;

public class Singleton1 {
	//在内部定义一个初始化实例的的属性，只供本地方法调用
	private static Singleton1 instance = new Singleton1();
	private Singleton1() {}
    //提供一个外部直接可以调用的方法。从而实现单例的目的
	//优点安全，确定，每次调用该方法，就会生成一个新的实例。
	public static Singleton1 getInstance() {
		return instance;
	}
}
