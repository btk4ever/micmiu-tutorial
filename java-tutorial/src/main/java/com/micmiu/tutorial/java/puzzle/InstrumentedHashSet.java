package com.micmiu.tutorial.java.puzzle;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * 猜猜打印结果是什么？
 *
 * @param <E>
 */
public class InstrumentedHashSet<E> extends HashSet<E> {
	private int addCount = 0;

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	/**
	 * 应该是6，不要写3个
	 * super.addAll()也是一个一个add()方法加进去的，所以add()方法也会再执行三次，一共是6次
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		InstrumentedHashSet<String> s = new InstrumentedHashSet<String>();
		s.addAll(Arrays.asList("Accordion", "Banjo", "Kazoo"));
		System.out.println(s.addCount);
	}
}
