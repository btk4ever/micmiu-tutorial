package com.micmiu.tutorial.java.gof.singleton;

/**
 * 基于内部类的单例模式 Lazy 线程安全 <br>
 * 优点： <br>
 * 1、线程安全 2、lazy <br>
 * 缺点： <br>
 * 1、除反射机制下无效
 */
public class SingletonFor6 {

	/**
	 * 内部类，用于实现lzay机制
	 */
	private static class SingletonHolder {
		/** 单例变量 */
		private static SingletonFor6 instance = new SingletonFor6();
	}

	/**
	 * 私有化的构造方法，保证外部的类不能通过构造器来实例化。
	 */
	private SingletonFor6() {

	}

	/**
	 * 获取单例对象实例
	 * 
	 * @return 单例对象
	 */
	public static SingletonFor6 getInstance() {
		return SingletonHolder.instance;
	}

}