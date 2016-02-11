/**
 * 
 */
package com.rajni.thinkinginjava.holdobjects;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author rajni.ubhi
 *
 */
public class SimpleCollection {
	public static void main(String[] args) {
		Collection<Integer> c = new ArrayList<>();
		for(int i =0 ; i < 10 ; i++) {
			c.add(i);
		}
		for(int n : c) {
			System.out.println(n);
		}
	}
	
}
