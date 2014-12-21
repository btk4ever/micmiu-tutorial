package com.micmiu.tutorial.java.gof.singleton;

/**
 * 解决无序写的问题 lazy 单例类 <br>
 */
public class SingletonFor4 {

	/** 单例实例变量 */
	private static SingletonFor4 instance = null;

	/**
	 * 私有化的构造方法，保证外部的类不能通过构造器来实例化。
	 */
	private SingletonFor4() {

	}

	/**
	 * 1、线程A进入getInstance()方法。<br>
	 * 2、因为instance是空的 ，所以线程A进入位置//1的第一个synchronized块。 　　<br>
	 * 3、线程A执行位置//2的代码，把instance赋值给本地变量temp。instance为空，所以temp也为空。 　　<br>
	 * 4、因为temp为空，所以线程A进入位置//3的第二个synchronized块。 　　<br>
	 * 5、线程A执行位置//4的代码，把temp设置成非空，但还没有调用构造方法！（“无序写”问题） 　　<br>
	 * 6、线程A阻塞，线程B进入getInstance()方法。 　　<br>
	 * 7、因为instance为空，所以线程B试图进入第一个synchronized块。但由于线程A已经在里面了。所以无法进入。线程B阻塞。 　　<br>
	 * 8、线程A激活，继续执行位置//4的代码。调用构造方法。生成实例。 　　<br>
	 * 9、将temp的实例引用赋值给instance。退出两个synchronized块。返回实例。 　　<br>
	 * 10、线程B激活，进入第一个synchronized块。 　　<br>
	 * 11、线程B执行位置//2的代码，把instance实例赋值给temp本地变量。 　　<br>
	 * 12、线程B判断本地变量temp不为空，所以跳过if块。返回instance实例。
	 * 
	 * @return
	 */
	public static SingletonFor4 getInstance() {
		if (instance == null) {
			synchronized (SingletonFor4.class) { // 1
				SingletonFor4 temp = instance; // 2
				if (temp == null) {
					synchronized (SingletonFor4.class) { // 3
						temp = new SingletonFor4(); // 4
					}
					instance = temp; // 5
				}
			}
		}
		return instance;
	}

}