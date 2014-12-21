package com.micmiu.tutorial.java.thread;

public class TicketDemoRunnable implements Runnable {

	private Integer ticket = 10;

	public void run() {
		while (ticket > 0) {
			try {
				Thread.sleep(1000 * Math.round(5 * Math.random()));
				synchronized (ticket) {
					if (ticket <= 0) {
						break;
					}
					System.out.println("Ticket sale = " + ticket);
					ticket--;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TicketDemoRunnable ticket = new TicketDemoRunnable();
		new Thread(ticket).start();
		new Thread(ticket).start();
		new Thread(ticket).start();

	}

}
