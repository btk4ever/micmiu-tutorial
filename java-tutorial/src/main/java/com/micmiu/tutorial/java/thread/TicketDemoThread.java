package com.micmiu.tutorial.java.thread;

public class TicketDemoThread extends Thread {

	private String name;

	private Ticket ticket;

	public TicketDemoThread(String name, Ticket ticket) {
		this.name = name;
		this.ticket = ticket;
	}

	@Override
	public void run() {
		while (ticket.buy()) {
			System.out.println(">>>> Thread [ " + name + " ] sale");
			try {
				Thread.sleep(1000 * Math.round(5 * Math.random()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(">>>> Thread [ " + name + " ] END.");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ticket ticket = new Ticket(10);
		new TicketDemoThread("T1", ticket).start();
		new TicketDemoThread("T2", ticket).start();
		new TicketDemoThread("T3", ticket).start();

	}

}
