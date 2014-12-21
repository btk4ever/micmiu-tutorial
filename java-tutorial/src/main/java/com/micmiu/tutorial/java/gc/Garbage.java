package com.micmiu.tutorial.java.gc;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 8/12/2014
 * Time: 13:50
 */
public class Garbage {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Usage: /n" + "java Garbage before/n or:/n" + "java Garbage after");
			return;
		}
		while (!Chair.f) {
			new Chair();
			new String("To take up space");
		}
		System.out.println("After all Chairs have been created:/n" + "total created = " + Chair.created + ", total finalized = " + Chair.finalized);
		if (args[0].equals("before")) {
			System.out.println("gc():");
			System.gc();
			System.out.println("runFinalization():");
			System.runFinalization();
		}
		System.out.println("bye!");
		if (args[0].equals("after"))
			System.runFinalizersOnExit(true);
	}


}
