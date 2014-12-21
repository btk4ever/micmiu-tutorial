package com.micmiu.tutorial.java.gof.singleton;

/**
 * 使用枚举实现单例：如果枚举中只有一个元素，那么就可以使用单例设计模式
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-8-30 下午3:49:36
 * @version 1.0
 */
public enum SingletonForEnum {

	/**
	 * 枚举类型 INSTANCE 就是一个单例，引用方式为 EnumSingleton.INSTANCE
	 */
	INSTANCE;

	// 后面是单例中的属性及方法,如
	void doSomething() {

	}

	private SingletonForEnum() {

	}

	public static void main(String[] args) {
		// 引用单例并使用其方法
		SingletonForEnum.INSTANCE.doSomething();
	}

}
