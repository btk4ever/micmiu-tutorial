package com.micmiu.tutorial.java.gc;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 8/12/2014
 * Time: 10:35
 */
public class TestGC {

	public static void main (String[] args){
		new TestGC();
		System.gc();
		System.runFinalization();
	}

}
