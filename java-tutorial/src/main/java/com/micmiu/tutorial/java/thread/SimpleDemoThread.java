package com.micmiu.tutorial.java.thread;

public class SimpleDemoThread extends Thread {

	private String name;

	public SimpleDemoThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("Thread [ " + name + " ] start ...");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new SimpleDemoThread("T1");
		new SimpleDemoThread("T2").start();

	}

}
