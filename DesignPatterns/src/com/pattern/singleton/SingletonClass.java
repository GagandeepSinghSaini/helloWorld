package com.pattern.singleton;

public class SingletonClass {

	private static SingletonClass singleton = null;
	
	private SingletonClass() {
		
	}
	
	public static SingletonClass getInstance() {
		if(singleton == null) {
			System.out.println("________ NEW OBJECT CREATED________");
			singleton = new SingletonClass();
			System.out.println("______________________________________");
		}
		return singleton;
	}
	
	public void printObj() {
		System.out.println("Singleton object: "+singleton);
	}
}
