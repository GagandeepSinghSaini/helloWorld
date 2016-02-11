/**
 * 
 */
package com.rajni.thinkinginjava.holdobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author rajni.ubhi
 *
 */
public class AddingGroups {
	public static void main(String[] args) {
		Collection<Integer> c = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		Integer[] moreInts = {6,7,8,9};
		
		c.addAll(Arrays.asList(moreInts));
		System.out.println(c);
		Collections.addAll(c, 10,11,12,13,14,15);
		System.out.println(c);
		List<Integer> list = Arrays.asList(16,17,18,19);
		list.add(20); // u can not add any element throws UnsupportedOperationException
		System.out.println(list);
		
	}
}
