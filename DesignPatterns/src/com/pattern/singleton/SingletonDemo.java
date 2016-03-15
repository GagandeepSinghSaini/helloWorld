package com.pattern.singleton;

public class SingletonDemo {

	public static void main(String[] args) {
		SingletonClass singleton = SingletonClass.getInstance();
		singleton.printObj();
		singleton.printObj();
		singleton.printObj();
	}
}
