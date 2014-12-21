package com.micmiu.tutorial.java.thread;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @create Mar 26, 2014 9:17:05 AM
 * @version 1.0
 */
public class SimpleDemoRunnable implements Runnable {
	private String name;

	public SimpleDemoRunnable(String name) {
		this.name = name;
	}

	public void run() {
		System.out.println("Runnable [ " + name + " ] run ...");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new SimpleDemoRunnable("R1")).start();

	}

}
