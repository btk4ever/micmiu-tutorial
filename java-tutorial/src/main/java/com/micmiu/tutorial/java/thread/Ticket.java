package com.micmiu.tutorial.java.thread;

public class Ticket {

	private int count;

	public Ticket(int count) {
		this.count = count;
	}

	public synchronized boolean buy() {
		if (count <= 0) {
			return false;
		} else {
			System.out.println("Ticket sale = " + count);
			count--;
			return true;
		}
	}

}
