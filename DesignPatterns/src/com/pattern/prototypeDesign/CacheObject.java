package com.pattern.prototypeDesign;

import java.util.Hashtable;

public class CacheObject {
private static Hashtable<String, Shape> objectTable = new Hashtable<String, Shape>();
	public static Shape loadCache(String objType) {
		System.out.println("*********New Object id being made***********");
		Circle circle = new Circle(10,"gagan");
		objectTable.put(objType, circle);
		return circle;
	}
	public static Shape getCacheObject(String objType) {
		Shape shp = objectTable.get(objType);
		if(shp != null) {
			System.out.println("<<<<<<<<<<<Getting cache object>>>>>>>>>");
			return (Circle)shp.clone();
		} 
		
		return loadCache(objType);
	}
}
