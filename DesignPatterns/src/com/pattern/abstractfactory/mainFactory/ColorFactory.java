package com.pattern.abstractfactory.mainFactory;

import com.pattern.abstractfactory.Color.Black;
import com.pattern.abstractfactory.Color.Color;
import com.pattern.abstractfactory.Color.Red;
import com.pattern.abstractfactory.Shape.Shape;

public class ColorFactory implements AbstractFactory{

	@Override
	public Shape getShape(String shapeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor(String getColor) {
		if(("Red").equalsIgnoreCase(getColor)) {
			return new Red();
		}else if(("Black").equalsIgnoreCase(getColor)) {
			return new Black();
		}
		return null;
	}

}
