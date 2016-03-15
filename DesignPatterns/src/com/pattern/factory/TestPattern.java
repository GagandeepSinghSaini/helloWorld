package com.pattern.factory;

public class TestPattern {

	public static void main(String[] args) {
		Animal animal = AnimalFactory.getAnimal("loin");
		animal.speak();
		animal = AnimalFactory.getAnimal("duck");
		animal.speak();
		animal = AnimalFactory.getAnimal("dog");
		animal.speak();
	}
}
