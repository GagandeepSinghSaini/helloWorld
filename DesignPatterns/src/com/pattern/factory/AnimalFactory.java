package com.pattern.factory;

public class AnimalFactory {
	private static Animal animal = null;
	
	public static Animal getAnimal(String animalType) {
		if(("dog").equals(animalType)) {
			animal = new Dog();
		} else if(("duck").equals(animalType)) {
			animal = new Duck();
		} else if(("loin").equals(animalType)) {
			animal = new Loin();
		}
		return animal;
	}
}
