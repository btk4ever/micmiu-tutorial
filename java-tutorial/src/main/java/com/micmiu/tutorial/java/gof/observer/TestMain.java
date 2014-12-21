package com.micmiu.tutorial.java.gof.observer;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 7/23/2014
 * Time: 16:49
 */
public class TestMain {

	public static void main(String[] args) {
		//创建被观察者
		TemperatureWatched obs = new TemperatureWatched();
		//创建观察者1
		TemperatureWatcher t1 = new TemperatureWatcher("T1");
		obs.addObserver(t1);
		obs.monitor();

	}
}