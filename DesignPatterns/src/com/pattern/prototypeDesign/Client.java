package com.pattern.prototypeDesign;

public class Client {
	public static void main(String[] args) {
		System.out.println("(************ <<Prototype Design Pattern >> ************)");
		Circle circle = (Circle) CacheObject.loadCache("circle");
		System.out.println("CIRCLE OBJECT: "+circle.getName()+" || "+circle.getRadius());
		circle.draw();
		Circle circle1 = (Circle)CacheObject.getCacheObject("circle");
		System.out.println("CIRCLE1 OBJECT BEFORE CHANGE:  "+circle1.getName()+" || "+circle1.getRadius());
		circle1.draw();
		circle1.setName("Manmeet");
		circle1.setRadius(20);
		System.out.println("CIRCLE OBJECT: "+circle.getName()+" || "+circle.getRadius());
		System.out.println("CIRCLE1 OBJECT AFTER CHANGE:  "+circle1.getName()+" || "+circle1.getRadius());
	}
}
