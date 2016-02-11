/**
 * 
 */
package com.rajni.ch1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author rajni.ubhi
 *
 */
public class AsList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> bigs = Arrays.asList(100,200,300);
		
		System.out.println(sumInteger(bigs)==sum(bigs));
		System.out.println(sumInteger(bigs)==sumInteger(bigs)); // not recommended
		
		List<Integer> smalls = Arrays.asList(1,2,3);
		
		System.out.println(sumInteger(smalls)==sum(smalls));
		System.out.println(sumInteger(smalls)==sumInteger(smalls)); // not recommended
	}
	
	public static int sum(List<Integer> list) {
		int s = 0;
		for(int n : list) {
			s+=n;
		}
		return s;
	}
	// this will be slower than the method above
	public static Integer sumInteger(List<Integer> list) {
		Integer s = 0;
		for(Integer n : list) {
			s+=n;
		}
		return s;
	}
	
	public static double dot(List<Double> u , List<Double> v) {
		double d = 0;
		if(u.size() != v.size()) {
			throw new IllegalArgumentException("Length of both the lists should be equal");
		}
		Iterator<Double> uit = u.iterator();
		Iterator<Double> vit = v.iterator();
		while(uit.hasNext()) {
			assert uit.hasNext() && vit.hasNext();
			d += uit.next() * vit.next();
		}
		assert !uit.hasNext() && !vit.hasNext();
		return d;
	}
	
	static void method(Object... a) {
		System.out.println("Inside the method");
	}
	/**
	 * Object...a is different from Object[] a when used during call
	 * but when compiling will say Duplicate method
	 */
	/*static void method(Object[] a) {
		System.out.println("Inside the method");
	}*/

}
