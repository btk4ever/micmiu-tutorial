package com.micmiu.tutorial.java.gof.singleton;

/**
 * 基础的单例模式，Lazy模式，非线程安全 <br>
 * 优点：lazy，初次使用时实例化单例，避免资源浪费 <br>
 * 缺点：1、lazy，如果实例初始化非常耗时，初始使用时，可能造成性能问题<br>
 * 2、非线程安全。多线程下可能会有多个实例被初始化。
 */
public class SingletonFor1 {

	/** 单例实例变量 */
	private static SingletonFor1 instance = null;

	/**
	 * 私有化的构造方法，保证外部的类不能通过构造器来实例化。
	 */
	private SingletonFor1() {

	}

	/**
	 * 1、当线程A进入到（#1）时，检查instance是否为空，此时是空的。 　　<br>
	 * 2、此时，线程B也进入到（#1）。切换到线程B执行。同样检查instance为空，于是往下执行（#2），创建了一个实例。接着返回了。<br>
	 * 3、在切换回线程A，由于之前检查到instance为空。所以也会执行（#2）创建实例。返回。 　　<br>
	 * 4、至此，已经有两个实例被创建了，这不是我们所希望的。
	 * 
	 * @return 单例对象
	 */
	public static SingletonFor1 getInstance() {
		if (instance == null) { // 1
			instance = new SingletonFor1(); // 2
		}
		return instance;
	}

}
