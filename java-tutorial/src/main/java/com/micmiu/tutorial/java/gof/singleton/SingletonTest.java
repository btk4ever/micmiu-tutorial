package com.micmiu.tutorial.java.gof.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonTest {

	/**
	 * 
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws java.lang.reflect.InvocationTargetException
	 */

	@SuppressWarnings("rawtypes")
	public static Object getReflectInstance(String className)
			throws ClassNotFoundException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Class c1 = Class.forName(className);
		Constructor[] cons = c1.getDeclaredConstructors();
		Constructor cc1 = cons[0];
		cc1.setAccessible(true);
		return cc1.newInstance(null);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
