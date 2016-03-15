package com.pattern.abstractfactory.mainFactory;

import com.pattern.abstractfactory.Color.Color;
import com.pattern.abstractfactory.Shape.Shape;

public interface AbstractFactory {
	public Shape getShape(String shapeType);
	public Color getColor(String getColor);
}
