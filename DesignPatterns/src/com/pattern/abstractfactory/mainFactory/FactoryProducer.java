package com.pattern.abstractfactory.mainFactory;

public class FactoryProducer {
	public static AbstractFactory getFactory(String factoryType) {
		if(("ShapeFactory").equalsIgnoreCase(factoryType)) {
			return new ShapeFactory();
		}else if(("ColorFactory").equalsIgnoreCase(factoryType)) {
			return new ColorFactory();
		}
		return null;
	}
}
