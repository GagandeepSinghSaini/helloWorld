package com.pattern.prototypeDesign;

public class Circle implements Shape {

	private int radius;
	private String name;
	
	public Circle(int radius, String name) {
		this.radius = radius;
		this.name = name;
		System.out.println("NAME: "+name+" --> "+"radius: "+radius);
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public void draw() {
		System.out.println("CIRCLE is to be drawn");
	}
	@Override
	public Shape clone() {
		try {
			return (Shape) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
