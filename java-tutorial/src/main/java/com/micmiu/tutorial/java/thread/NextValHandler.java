package com.micmiu.tutorial.java.thread;

public class NextValHandler {

	private Integer val = 10;

	private static class NextValHandlerHolder {
		private static final NextValHandler instance = new NextValHandler();
	}

	public static NextValHandler getInstance() {
		return NextValHandlerHolder.instance;
	}

	private NextValHandler() {
	}

	public Integer getNextVal() {
		try {
			Thread.sleep((long) Math.random() * 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ++val;
	}
}
