/**
 * 
 */
package com.rajni.ch1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rajni.ubhi
 *
 */
public class Lists {
	
	public static <T> List<T> toList(T... arr) {
		List<T> list = new ArrayList<>();
		for(T elt : arr) {
			list.add(elt);
		}
		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> ints = toList(1,2);
		System.out.println(ints);
		List<Object> objs = Lists.<Object>toList(1,"one");
		System.out.println(objs);
		
		List<String> obj = toList("1","one");
		System.out.println(obj);
	}

}
