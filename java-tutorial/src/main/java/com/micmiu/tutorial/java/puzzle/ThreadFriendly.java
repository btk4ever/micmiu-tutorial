package com.micmiu.tutorial.java.puzzle;

/**
 * 猜猜打印结果是什么？
 * <p/>
 * 其实这个细说起来还是很复杂的，需要理解ThreadLocal的原理和弱引用的相关知识。
 * 因为内部类对象存在一个指向外部类（包含内部类的类）ThreadFriendly对象的引用，而ThreadFriendly对象又存在到ThreadLocal对象的引用，
 * 导致Thread中ThreadLocal.ThreadLocalMap inheritableThreadLocals 中的key一直是强引用，无法释放，最终导致内存溢出。
 * 解决方法：
 * static class Value{
 * final int i;
 * Value(int i){
 * this.i = i;
 * }
 * }
 * 当然，也可以static ThreadLocal<Value> threadLocalPart = new ThreadLocal<Value>();
 */
public class ThreadFriendly {
	ThreadLocal<Value> threadLocalPart = new ThreadLocal<Value>();

	class Value {
		final int i;

		Value(int i) {
			this.i = i;
		}
	}

	ThreadFriendly setThreadVal(int i) {
		threadLocalPart.set(new Value(i));
		return this;
	}

	int getThreadVal() {
		return threadLocalPart.get().i;
	}

	public static void main(String[] args) {
		int sum = 0;
		for (int i = -500000; i <= 500000; i++) {
			sum += new ThreadFriendly().setThreadVal(i).getThreadVal();
		}
		System.out.println(sum);
	}
}