package com.micmiu.tutorial.java.gof.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 7/23/2014
 * Time: 16:48
 */
public class TemperatureWatcher implements Observer {

	private String name=null;

	public TemperatureWatcher(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Observable = "+o+", Object = "+ arg);
		if(o instanceof TemperatureWatched){
			double temp = ((TemperatureWatched)o).getTemperatrue();
			System.out.println("curr temperature = "+temp);
			if(temp>30){
				System.out.println("warn high");
			}else{
				System.out.println("info common");
			}
		}
	}
}
