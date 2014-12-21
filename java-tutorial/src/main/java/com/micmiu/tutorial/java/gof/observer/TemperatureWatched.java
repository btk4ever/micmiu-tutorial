package com.micmiu.tutorial.java.gof.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 7/23/2014
 * Time: 16:47
 */
public class TemperatureWatched extends Observable {

	private double temperatrue;

	public void monitor() {
		temperatrue = 25+Math.random()*10;
		setChanged();
		notifyObservers();
	}

	public double getTemperatrue() {
		return temperatrue;
	}

	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
	}

	@Override
	public synchronized void deleteObserver(Observer o) {
		super.deleteObserver(o);
	}

	@Override
	public void notifyObservers(Object arg) {
		super.notifyObservers(arg);
	}
}
