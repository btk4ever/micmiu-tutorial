package com.micmiu.tutorial.java.gof.singleton;

/**
 * 基于双检查锁机制的lazy 单例类 <br>
 * 优点：<br>
 * 1、lazy，初次使用时实例化单例，避免资源浪费<br>
 * 2、线程安全<br>
 * 缺点：<br>
 * 1、双检查锁只有在 jdk 1.5 及以上版本才能达到单例的效果 <br>
 * 2、每次调用getInstance()都要获得同步锁，性能消耗。<br>
 */
public class SingletonFor3 {

	/** 单例实例变量 */
	private static SingletonFor3 instance = null;

	/**
	 * 私有化的构造方法，保证外部的类不能通过构造器来实例化。
	 */
	private SingletonFor3() {

	}

	/**
	 * 双检查锁只有在 jdk 1.5 及以上版本才能达到单例的效果
	 * 
	 * java平台内存模型中有一个叫“无序写”（out-of-order writes）的机制。正是这个机制导致了双重检查加锁方法的失效。<br>
	 * 这个问题的关键在上面代码上的第5行：instance = new SingletonThree();<br>
	 * 这行其实做了两个事情：1、调用构造方法，创建了一个实例。2、把这个实例赋值给instance这个实例变量。<br>
	 * 可问题就是这两步jvm是不保证顺序的。也就是说。可能在调用构造方法之前，instance已经被设置为非空了。 下面我们看一下出问题的过程：<br>
	 * 1、线程A进入getInstance()方法。 　　<br>
	 * 2、因为此时instance为空，所以线程A进入synchronized块。 　　<br>
	 * 3、线程A执行 instance = new SingletonThree();
	 * 　　把实例变量instance设置成了非空。（注意，实在调用构造方法之前。） 　　<br>
	 * 4、线程A退出，线程B进入。 　　<br>
	 * 5、线程B检查instance是否为空，此时不为空（第三步的时候被线程A设置成了非空）。线程B返回instance的引用。（问题出现了，
	 * 　　这时instance的引用并不是SingletonThree的实例，因为没有调用构造方法。） 　　<br>
	 * 6、线程B退出，线程A进入。 　　<br>
	 * 7、线程A继续调用构造方法，完成instance的初始化，再返回。
	 */
	public static SingletonFor3 getInstance() {
		if (instance == null) { // 1
			synchronized (SingletonFor3.class) {
				if (instance == null) {
					instance = new SingletonFor3(); // 2
				}
			}
		}
		return instance;
	}

}