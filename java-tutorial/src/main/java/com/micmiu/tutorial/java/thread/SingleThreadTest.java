package com.micmiu.tutorial.java.thread;

public class SingleThreadTest extends Thread {

	private String name;

	private NextValHandler handler;

	public SingleThreadTest(String name, NextValHandler handler) {
		this.name = name;
		this.handler = handler;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep((long) Math.random() * 5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(">> [" + name + "] next val = "
					+ handler.getNextVal());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new SingleThreadTest("T1", NextValHandler.getInstance()).start();
		new SingleThreadTest("T2", NextValHandler.getInstance()).start();
		new SingleThreadTest("T3", NextValHandler.getInstance()).start();
		new SingleThreadTest("T4", NextValHandler.getInstance()).start();
		new SingleThreadTest("T5", NextValHandler.getInstance()).start();
	}

}
