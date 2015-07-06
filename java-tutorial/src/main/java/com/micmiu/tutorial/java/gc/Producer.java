package com.micmiu.tutorial.java.gc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Java垃圾回收调优实战
 * http://www.javacodegeeks.com/2015/06/gc-tuning-in-practice.html
 * 开启 GC 日志并使用参数 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps
 * 不同配置运行结果：
 * 堆	          GC算法	            有效工作		长暂停
 * -Xmx12g	-XX:+UseConcMarkSweepGC	89.8%		560 ms
 * -Xmx12g	-XX:+UseParallelGC		91.5%		1,104 ms
 * -Xmx8g	-XX:+UseConcMarkSweepGC	66.3%		1,610 ms
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 7/6/2015
 * Time: 14:39
 */
public class Producer implements Runnable {

	private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

	private Deque<byte[]> deque;
	private int objectSize;
	private int queueSize;

	public Producer(int objectSize, int ttl) {
		this.deque = new ArrayDeque<byte[]>();
		this.objectSize = objectSize;
		this.queueSize = ttl * 1000;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			deque.add(new byte[objectSize]);
			if (deque.size() > queueSize) {
				deque.poll();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		executorService.scheduleAtFixedRate(new Producer(200 * 1024 * 1024 / 1000, 5), 0, 100, TimeUnit.MILLISECONDS);
		executorService.scheduleAtFixedRate(new Producer(50 * 1024 * 1024 / 1000, 120), 0, 100, TimeUnit.MILLISECONDS);
		TimeUnit.MINUTES.sleep(10);
		executorService.shutdownNow();
	}

}
