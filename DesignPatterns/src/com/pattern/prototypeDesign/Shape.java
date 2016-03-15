package com.pattern.prototypeDesign;

public interface Shape extends Cloneable {
	void draw();
	Shape clone();
}
