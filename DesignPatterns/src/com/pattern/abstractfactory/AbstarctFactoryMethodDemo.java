package com.pattern.abstractfactory;

import com.pattern.abstractfactory.Color.Black;
import com.pattern.abstractfactory.Color.Red;
import com.pattern.abstractfactory.Shape.Shape;
import com.pattern.abstractfactory.mainFactory.AbstractFactory;
import com.pattern.abstractfactory.mainFactory.FactoryProducer;

public class AbstarctFactoryMethodDemo {
	public static void main(String[] args) {
		AbstractFactory factory = FactoryProducer.getFactory("ShapeFactory");
		Shape shape1 = factory.getShape("circle");
		shape1.draw();
		shape1 = factory.getShape("square");
		shape1.draw();
		factory= FactoryProducer.getFactory("ColorFactory");
		Red lal = (Red) factory.getColor("red");
		Black kala= (Black) factory.getColor("black");
		lal.fill();
		kala.fill();
		
		
	}
	
}
