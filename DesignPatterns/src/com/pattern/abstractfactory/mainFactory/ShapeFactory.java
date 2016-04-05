package com.pattern.abstractfactory.mainFactory;

import com.pattern.abstractfactory.Color.Color;
import com.pattern.abstractfactory.Shape.Circle;
import com.pattern.abstractfactory.Shape.Shape;
import com.pattern.abstractfactory.Shape.Square;

public class ShapeFactory implements AbstractFactory{

	@Override
	public Shape getShape(String shapeType) {
		if(("Circle").equalsIgnoreCase(shapeType))
		{
			return new Circle();
		}else if(("Square").equalsIgnoreCase(shapeType)) {
			return new Square();
		} 
		return null;
	}

	@Override
	public Color getColor(String getColor) {
		// TODO Auto-generated method stub
		return null;
	}

}
