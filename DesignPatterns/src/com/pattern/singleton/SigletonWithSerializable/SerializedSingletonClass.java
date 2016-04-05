package com.pattern.singleton.SigletonWithSerializable;

import java.io.Serializable;

public class SerializedSingletonClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SerializedSingletonClass() {
		// do nothing
	}
	private static class InnerSingleton {
		private static SerializedSingletonClass INSTANCE = new SerializedSingletonClass();
	}
	
	public static SerializedSingletonClass getInstance() {
		return InnerSingleton.INSTANCE;
	}
	
	public Object readResolve () {
		return getInstance();
	}
	
}
